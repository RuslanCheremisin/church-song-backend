package rus.cheremisin.churchsong.service;

import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.DTO.AvatarImageDTO;

public interface ImageService {

    AvatarImageDTO uploadAvatarImage(MultipartFile file);

    AvatarImageDTO getAvatarImageById(Long id);
}
