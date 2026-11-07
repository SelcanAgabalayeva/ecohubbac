package com.ecohub.ecohubbac.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class FileService {

    private final Cloudinary cloudinary;

    // application.properties-dəki məlumatları avtomatik oxuyur
    public FileService(
            @org.springframework.beans.factory.annotation.Value("${cloudinary.cloud-name}") String cloudName,
            @org.springframework.beans.factory.annotation.Value("${cloudinary.api-key}") String apiKey,
            @org.springframework.beans.factory.annotation.Value("${cloudinary.api-secret}") String apiSecret) {

        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true));
    }

    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            // Şəkli Cloudinary-ə yükləyirik
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            // Yüklənən şəklin təhlükəsiz (HTTPS) URL ünvanını qaytarırıq
            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
            throw new RuntimeException("Cloudinary-ə şəkil yüklənərkən xəta baş verdi: " + e.getMessage());
        }
    }
}
