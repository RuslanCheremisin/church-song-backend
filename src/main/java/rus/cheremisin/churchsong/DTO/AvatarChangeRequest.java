package rus.cheremisin.churchsong.DTO;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class AvatarChangeRequest {
    private MultipartFile photoFile;
}
