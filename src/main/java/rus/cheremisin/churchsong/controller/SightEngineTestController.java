package rus.cheremisin.churchsong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.service.MediaUploader;

@RestController("/content-filter")
public class SightEngineTestController {
    private MediaUploader uploader;

    @Autowired
    public SightEngineTestController(@Qualifier("sightengine") MediaUploader uploader) {
        this.uploader = uploader;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadVideo(@RequestParam(value = "videoFile") MultipartFile file) {
        String response = uploader.uploadMedia(file);
        return ResponseEntity.ok(response);
    }
}
