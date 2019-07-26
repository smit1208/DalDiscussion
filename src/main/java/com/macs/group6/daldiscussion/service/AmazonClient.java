package com.macs.group6.daldiscussion.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.macs.group6.daldiscussion.AppConfig;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonClient {

    private static AmazonClient amazonClientInstance;

    private AmazonS3 s3client;

    private String endpointUrl;

    private String bucketName;

    private String accessKey;

    private String secretKey;

    private String DESTINATION_FOLDER ;
    public AmazonS3 getS3client() {
        return s3client;
    }

    public void setS3client(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    private void initializeAmazon() {

        BasicAWSCredentials creds = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).withRegion(Regions.CA_CENTRAL_1).build();
    }

    private AmazonClient(){
       endpointUrl = AppConfig.getInstance().getEndpointUrl();

       bucketName = AppConfig.getInstance().get_BucketName();

       accessKey = AppConfig.getInstance().get_AccessKey();

       secretKey = AppConfig.getInstance().get_SecretKey();
    }

    public static AmazonClient getInstance(){
        if(amazonClientInstance == null){
            amazonClientInstance = new AmazonClient();
            amazonClientInstance.initializeAmazon();
        }
        return amazonClientInstance;
    }

    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                multipartFile.getOriginalFilename());
        multipartFile.transferTo(convFile);
        return convFile;
    }

  public List<String> uploadImage(List<MultipartFile> files, int postId)throws AmazonServiceException, SdkClientException, IOException{

      List<String> imageUrls = new ArrayList<String>();
      for (MultipartFile file: files
           ) {
          ObjectMetadata data = new ObjectMetadata();
          data.setContentType(file.getContentType());
          data.setContentLength(file.getSize());
          DESTINATION_FOLDER = "dev/"+postId+"/";
          String destinationPath = DESTINATION_FOLDER + getFileNameWithoutExtension(file);

          try {

              PutObjectResult putObjectResult =  this.getS3client().putObject(bucketName,destinationPath,file.getInputStream(),data);
              this.getS3client().setObjectAcl(bucketName, destinationPath, CannedAccessControlList.PublicRead);
              imageUrls.add(destinationPath);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      return imageUrls;
  }

    private static String getFileNameWithoutExtension(MultipartFile file) {
        String fileName = "";

        try {
            if (file != null && file.getSize()>0) {
                String name = file.getOriginalFilename();
                fileName = name.replaceFirst("[.][^.]+$", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileName = "";
        }

        return fileName;

    }
}