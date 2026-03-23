package rus.cheremisin.churchsong.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import rus.cheremisin.churchsong.DTO.VimeoUploadResponse;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VimeoVideoUploadService {
    private final WebClient vimeoWebClient;

    public VimeoUploadResponse createUploadTicket(long fileSize) {
        return vimeoWebClient.post()
                .uri("me/videos")
                .bodyValue(Map.of("upload", Map.of("approach", "tus", "size", fileSize)))
                .retrieve()
                .bodyToMono(VimeoUploadResponse.class)
                .block();
    }

    public long getUploadOffset(String uploadLink) {
        WebClient client = WebClient.builder()
                .baseUrl(uploadLink)
                .defaultHeader("Tus-Resumable", "1.0.0")
                .build();

        return client.head()
                .retrieve()
                .toBodilessEntity()
                .map(response -> {
                    String offset = response.getHeaders().getFirst("Upload-Offset");
                    return offset != null ? Long.parseLong(offset) : 0;
                })
                .block();
    }

    public void uploadVideo(String uploadLink, MultipartFile file) throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try (InputStream is = new FileInputStream(file.getBytes())) {
//            byte[] buffer = new byte[8192];
//            int bytesRead;
//            while ((bytesRead = is.read(buffer)) != -1) {
//                baos.write(buffer, 0, bytesRead);
//            }
//        }
        byte[] bytes = file.getBytes();
        long uploadOffset = getUploadOffset(uploadLink);

        WebClient uploadClient = WebClient.builder()
                .baseUrl(uploadLink)
                .defaultHeader("Tus-Resumable", "1.0.0")
                .defaultHeader("Upload-Offset", String.valueOf(uploadOffset))
                .defaultHeader("Content-Type", "application/offset+octet-stream")
                .build();

        uploadClient.patch()
                .bodyValue(bytes)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
