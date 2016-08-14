package com.model;

import java.io.*;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 * Created by Junsuk on 2016-08-14.
 */
public class UserAWSUtil {

    private static final int AMAZON_SERVICE_EXCEPTION = 2;
    private static final int AMAZON_CLIENT_EXCEPTION = 2;
    private static final int COMMON_ERROR = 2;
    private static String bucketName = "lecture-system";
    private static String keyName = "*** Provide key ***";
    private static String uploadFileName = "*** Provide file name ***";
    private AmazonS3 s3client;
    final private static int RESULT_OK = 1;
    public UserAWSUtil() {
        s3client = new AmazonS3Client(new ProfileCredentialsProvider());
    }


    public void fileUpload(String userId, String absolutePath) {
        keyName = userId;
        uploadFileName = absolutePath;
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            File file = new File(uploadFileName);
            s3client.putObject(new PutObjectRequest(
                    bucketName, keyName, file));

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    public int fileDownload(String studentId) {
        int result = -1;
        try {
            System.out.println("Downloading an object");

            keyName = studentId;
            S3Object s3object = s3client.getObject(new GetObjectRequest(
                    bucketName, keyName));
            InputStream inputStream = s3object.getObjectContent();
            String tmp = System.getProperty("user.home") + "/" + keyName;
            OutputStream outputStream = new FileOutputStream(new File(tmp));

            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = inputStream.read(bytes)) != -1){
                outputStream.write(bytes);
            }

            result = RESULT_OK;

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which" +
                    " means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            result = AMAZON_SERVICE_EXCEPTION;
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means" +
                    " the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            result = AMAZON_CLIENT_EXCEPTION;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result = COMMON_ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            result = COMMON_ERROR;
        }
        return result;

    }
}
