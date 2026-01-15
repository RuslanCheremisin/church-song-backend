package rus.cheremisin.churchsong.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rus.cheremisin.churchsong.DTO.AvatarImageDTO;
import rus.cheremisin.churchsong.service.ImageService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{image-id}")
    public ResponseEntity<AvatarImageDTO> getBandById(@PathVariable("image-id") Long bandId) {
        return ResponseEntity.ok(imageService.getAvatarImageById(bandId));
    }
}
