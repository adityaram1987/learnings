package com.learning.aws;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

@SpringBootApplication
public class LearningAwsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(LearningAwsApplication.class);
			
	public static void main(String[] args) {
		SpringApplication.run(LearningAwsApplication.class, args);
		AWSCredentials credentials = new BasicAWSCredentials(
				  "<Access_key>", 
				  "<Secret_key>"
				);
		AmazonS3 amazonS3 = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(Regions.US_EAST_1)
				  .build();
		
		TransferManager tm = TransferManagerBuilder.standard()
				  .withS3Client(amazonS3)
				  //.withMultipartUploadThreshold((long) (5 * 1024 * 1025))
				  .build();
		
		
		System.out.println(Thread.activeCount());
		String bucketName = "demojardeploy";
		String keyName = "1080p (1).mov";
		String keyName1 = "1080p.mov";
		File file = new File("C:\\Users\\rayadity\\personal\\1080p (1).mov");
		File file1 = new File("C:\\Users\\rayadity\\personal\\1080p.mov");
		
		
		LOGGER.info("Upload started");
		
		try {
			byte[] fileContent = Files.readAllBytes(file.toPath());
			
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(fileContent.length);
			
			InputStream in = new ByteArrayInputStream(fileContent);
			Upload upload = tm.upload(bucketName, keyName, in, objectMetadata);
			
			TransferManager tm1 = TransferManagerBuilder.standard()
					  .withS3Client(amazonS3)
					  //.withMultipartUploadThreshold((long) (5 * 1024 * 1025))
					  .build();
			InputStream in1 = new ByteArrayInputStream(fileContent);
			LOGGER.info("Upload1 started");
			Upload upload1 = tm1.upload(bucketName, keyName, in1, objectMetadata);
			
			TransferManager tm2 = TransferManagerBuilder.standard()
					  .withS3Client(amazonS3)
					  //.withMultipartUploadThreshold((long) (5 * 1024 * 1025))
					  .build();
			LOGGER.info("Upload2 started");
			Upload upload2 = tm1.upload(bucketName, keyName1, file1);
			
			LOGGER.info("first file before state" + upload.getState().name());
			upload.waitForCompletion();
			LOGGER.info("first file after state" + upload.getState().name());
			
			upload1.waitForCompletion();
			LOGGER.info("second file after state" + upload.getState().name());
			upload2.waitForCompletion();
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (AmazonClientException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("Upload ended");
	}

}
