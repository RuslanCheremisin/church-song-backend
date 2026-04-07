package rus.cheremisin.churchsong.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import rus.cheremisin.churchsong.DTO.SightEngineUploadTicketResponse;
import rus.cheremisin.churchsong.service.MediaUploader;
import rus.cheremisin.churchsong.util.TestFileWriter;

import java.util.Objects;

@Service
@Qualifier("sightengine")
public class SightEngineService implements MediaUploader {
    @Value("${sightengine.api_user}")
    private String apiUser;
    @Value("${sightengine.api_secret}")
    private String apiSecret;

    @Override
    public String uploadMedia(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            SightEngineUploadTicketResponse response = createUploadTicket(file.getOriginalFilename());
            WebClient uploadClient = WebClient.builder()
                    .baseUrl(response.getUpload().getUrl())
                    .defaultHeader("Content-Type", "application/offset+octet-stream")
                    .build();
            return Objects.requireNonNull(uploadClient.put()
                    .bodyValue(bytes)
                    .retrieve()
                    .toBodilessEntity()
                    .block()).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private SightEngineUploadTicketResponse createUploadTicket(String fileName) {
        WebClient client = WebClient.builder()
                .baseUrl("https://api.sightengine.com")
                .build();

        SightEngineUploadTicketResponse response = client.get()
                .uri(uriBuilder -> uriBuilder.path("/1.0/upload/create-video.json")
                        .queryParam("api_user", apiUser)
                        .queryParam("api_secret", apiSecret)
//                        .queryParam("filename", fileName)
                        .build())
                .retrieve()
                .onStatus(status -> true, clientResponse -> clientResponse.bodyToMono(String.class).map(body -> new RuntimeException(body)))
                .bodyToMono(SightEngineUploadTicketResponse.class)
                .block();

        TestFileWriter.writeResponse(response.toString());

        return response;

    }
}
