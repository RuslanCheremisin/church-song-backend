package rus.cheremisin.churchsong.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VimeoUploadResponse extends UploadResponse{
    private String url;
    private Upload upload;

    @Data
    public static class Upload {
        private String upload_link;
    }
}

