package rus.cheremisin.churchsong.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.DTO.VimeoUploadResponse;
import rus.cheremisin.churchsong.service.impl.VimeoVideoUploadService;
import rus.cheremisin.churchsong.util.TestFileWriter;

import java.io.File;
import java.io.IOException;

@RestController("/vimeo")
@RequiredArgsConstructor
public class VimeoTestController {
    VimeoVideoUploadService service;

    @Autowired
    public VimeoTestController(VimeoVideoUploadService service) {
        this.service = service;
    }

    @PatchMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam(value = "videoFile") MultipartFile videoFile) {
        try {
            long fileSize = videoFile.getSize();
            VimeoUploadResponse response = service.createUploadTicket(fileSize);

            TestFileWriter.writeResponse("upload link: " + response.getUpload().getUpload_link() + "\n"
                    + "link to video: " + response.getUrl());

            File tempFile = File.createTempFile("upload-", videoFile.getOriginalFilename());

            service.uploadVideo(response.getUpload().getUpload_link(), videoFile);

            return ResponseEntity.ok("Successfully uploaded! Video URI: " + response.getUrl());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failure: " + e.getMessage());
        }

    }
}
