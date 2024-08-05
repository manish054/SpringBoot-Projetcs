package com.scm.serviceimplementation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helper.AppConstants;
import com.scm.service.ImageService;

@Service
public class ImageServiceImplementation implements ImageService{

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile profilePic, String filename) {
       
        // String filename = UUID.randomUUID().toString();
       
        try {
            byte[] data = new byte[profilePic.getInputStream().available()];
            profilePic.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id", filename
            ));
            return this.getUrlFromPublicId(filename);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
                .url()
                .transformation(
                    new Transformation<>()
                         .width(AppConstants.CONTACT_IMAGE_WIDTH)
                          .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                          .crop(AppConstants.CONTACT_IMAGE_CROP))
                .generate(publicId);
    }

}
