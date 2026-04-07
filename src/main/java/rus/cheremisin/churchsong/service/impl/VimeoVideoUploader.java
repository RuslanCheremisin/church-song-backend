package rus.cheremisin.churchsong.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import rus.cheremisin.churchsong.DTO.VimeoUploadResponse;
import rus.cheremisin.churchsong.service.MediaUploader;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
@Qualifier("vimeo")
@RequiredArgsConstructor
public class VimeoVideoUploader implements MediaUploader {

    private final WebClient vimeoWebClient;


    @Override
    public String uploadMedia(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            VimeoUploadResponse response = createUploadTicket(file.getSize());

            WebClient uploadClient = WebClient.builder()
                    .baseUrl(response.getUpload().getUpload_link())
                    .defaultHeader("Tus-Resumable", "1.0.0")
                    .defaultHeader("Upload-Offset", "0")
                    .defaultHeader("Content-Type", "application/offset+octet-stream")
                    .build();

            return Objects.requireNonNull(uploadClient.patch()
                    .bodyValue(bytes)
                    .retrieve()
                    .toBodilessEntity()
                    .block()).toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private VimeoUploadResponse createUploadTicket(long fileSize) {
        return vimeoWebClient.post()
                .uri("me/videos")
                .bodyValue(Map.of("upload", Map.of("approach", "tus", "size", fileSize)))
                .retrieve()
                .bodyToMono(VimeoUploadResponse.class)
                .block();
    }
}
