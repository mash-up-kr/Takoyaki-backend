package org.mashup.takoyaki.common.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

public class S3Util {
    //bucketName
    private String bucketName = "mashup-takoyaki";  //생성한 버킷 명
    private String accessKey = "";  //
    private String secretKey = "";  //

    private AmazonS3 amazonS3;
    Logger logger = LoggerFactory.getLogger(S3Util.class);

    public S3Util() {
        AWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
        amazonS3 =
                AmazonS3ClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(creds))
                        .withRegion(Regions.AP_NORTHEAST_2)       // region
                        .withForceGlobalBucketAccessEnabled(true) // access
                        .build();
    }

    // 업로드하고 파일 URL 반환
    public String uploadFile(String bucketName, String fileName, InputStream inputStream, ObjectMetadata objectMetadata) {
        if ( amazonS3 != null ) {
            try {
                PutObjectRequest putObjectRequest =
                        new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata);

                // file permission
                putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

                // upload file
                amazonS3.putObject(putObjectRequest);

            } catch ( AmazonServiceException ase) {
                ase.printStackTrace();
            }


        }
        String imgName = (fileName).replace(File.separatorChar, '/');
        return amazonS3.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, imgName)).toString();

    }

    public String getFileURL(String bucketName, String fileName) {
        String imgName = (fileName).replace(File.separatorChar, '/');
        return amazonS3.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, imgName)).toString();
    }
}
