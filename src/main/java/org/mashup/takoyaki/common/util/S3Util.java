package org.mashup.takoyaki.common.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.mashup.takoyaki.common.exception.ServerErrorException;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Properties;


@Slf4j
public class S3Util {

    private static Properties s3Props;
    private static AmazonS3 amazonS3;

    static  {
        try {
            s3Props = PropertiesLoaderUtils.loadProperties(
                    new PathMatchingResourcePatternResolver().getResource("classpath:s3.properties"));
        } catch (IOException ioe) {
            log.info("IOE Error Message: " + ioe.getMessage());
            throw new ServerErrorException();
        }

        AWSCredentials credentials = new BasicAWSCredentials(s3Props.getProperty("s3.access-key"), s3Props.getProperty("s3.secret-key"));

        amazonS3 =
                AmazonS3ClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(credentials))
                        .withRegion(Regions.AP_NORTHEAST_2)       // region
                        .withForceGlobalBucketAccessEnabled(true) // access
                        .build();
    }

    public static String uploadFile(String fileName, MultipartFile file) {

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(s3Props.getProperty("s3.bucket-name"), fileName, file.getInputStream(), metadata);


            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);
            log.info("===================== Upload File - Done! =====================");

        } catch (AmazonServiceException ase) {
            log.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
            log.info("Error Message:    " + ase.getMessage());
            log.info("HTTP Status Code: " + ase.getStatusCode());
            log.info("AWS Error Code:   " + ase.getErrorCode());
            log.info("Error Type:       " + ase.getErrorType());
            log.info("Request ID:       " + ase.getRequestId());
            throw new ServerErrorException();
        } catch (AmazonClientException ace) {
            log.info("Caught an AmazonClientException: ");
            log.info("Error Message: " + ace.getMessage());
            throw new ServerErrorException();
        } catch (IOException ioe) {
            log.info("IOE Error Message: " + ioe.getMessage());
            throw new ServerErrorException();
        }
        return String.format("%s/%s/%s", s3Props.getProperty("s3.hostname"), s3Props.getProperty("s3.bucket-name"), fileName);
    }
}
