package com.macs.group6.daldiscussion;
/*
@author Sharon Alva
*/
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

public class AmazonClientMock {
    private String DESTINATION_FOLDER;

    public List<String> uploadImage(List<MultipartFile> files, int postId){

        List<String> imageUrls = new ArrayList<String>();
        for(MultipartFile file: files){
            DESTINATION_FOLDER = "prod/"+postId+"/";
            String destinationPath = DESTINATION_FOLDER + getFileNameWithoutExtension(file);
            imageUrls.add(destinationPath);

        }
        return imageUrls;
    }

    private static String getFileNameWithoutExtension(MultipartFile file) {
        String fileName = "";

        if (file != null && file.getSize()>0) {
            String name = file.getOriginalFilename();
            fileName = name.replaceFirst("[.][^.]+$", "");
        }

        return fileName;
    }
}
