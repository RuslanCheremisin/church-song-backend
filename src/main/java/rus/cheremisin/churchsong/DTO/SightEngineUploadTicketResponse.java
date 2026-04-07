package rus.cheremisin.churchsong.DTO;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SightEngineUploadTicketResponse {

    private String status;

    private RequestInfo request;

    private UploadInfo upload;

    private MediaInfo media;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestInfo {
        private String id;
        private Double timestamp;
        private Integer operations;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UploadInfo {
        private String url;
        private Long expires;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MediaInfo {
        private String id;
    }
}
