package rus.cheremisin.churchsong.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.service.MediaUploader;
import rus.cheremisin.churchsong.service.impl.VimeoVideoUploader;

import java.io.IOException;

@RestController("/vimeo")
@RequiredArgsConstructor
public class VimeoTestController {

    private MediaUploader service;

    @Autowired
    public VimeoTestController(@Qualifier("vimeo") VimeoVideoUploader service) {
        this.service = service;
    }

    @PatchMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam(value = "videoFile") MultipartFile videoFile) {
            String response = service.uploadMedia(videoFile);

            return ResponseEntity.ok(response);
    }
}
