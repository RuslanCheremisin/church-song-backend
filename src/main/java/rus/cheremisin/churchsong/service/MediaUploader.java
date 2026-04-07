package rus.cheremisin.churchsong.service;

import org.springframework.web.multipart.MultipartFile;


public interface MediaUploader {
    String uploadMedia(MultipartFile file);
}
