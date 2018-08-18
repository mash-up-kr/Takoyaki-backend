package org.mashup.takoyaki.common.util;

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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class S3Util {

    private String bucketName = "";
    private String accessKey = "";
    private String secretKey = "";
    private String hostName = "";

    private AmazonS3 amazonS3;

    public S3Util() {
        AWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
        amazonS3 =
                AmazonS3ClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(creds))
                        .withRegion(Regions.AP_NORTHEAST_2)       // region
                        .withForceGlobalBucketAccessEnabled(true) // access
                        .build();
    }

    public String uploadFileToS3(String fileName, MultipartFile file) {

        if (amazonS3 != null) {
            InputStream inputStream = null;
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(file.getContentType());
                metadata.setContentLength(file.getSize());

                PutObjectRequest putObjectRequest =
                        new PutObjectRequest(bucketName, fileName, inputStream, metadata);

                putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
                amazonS3.putObject(putObjectRequest);

            } catch (AmazonServiceException ase) {
                ase.printStackTrace();
            }
        }
        return "https://" + hostName +  "/" + bucketName + "/" + fileName;
    }
}
