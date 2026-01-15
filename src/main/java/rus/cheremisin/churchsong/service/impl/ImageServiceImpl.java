package rus.cheremisin.churchsong.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.DAO.AvatarImageDAO;
import rus.cheremisin.churchsong.DTO.AvatarImageDTO;
import rus.cheremisin.churchsong.entity.AvatarImage;
import rus.cheremisin.churchsong.exceptions.ImageFileIsEmptyException;
import rus.cheremisin.churchsong.mapper.AvatarImageMapper;
import rus.cheremisin.churchsong.service.ImageService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageServiceImpl implements ImageService {

    static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    AvatarImageDAO dao;
    AvatarImageMapper mapper;

    @Override
    public AvatarImageDTO uploadAvatarImage(MultipartFile file) {
        String safeFilename = "";
        try {
            if (file.isEmpty()) {
                throw new ImageFileIsEmptyException();
            }
            String origFileName = file.getOriginalFilename();
            safeFilename = Paths.get(origFileName).getFileName().toString();
            Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "images", "avatar_images", safeFilename);
            file.transferTo(destination.toFile());

        } catch (ImageFileIsEmptyException e) {
            logger.error("Image file is empty");
        } catch (IOException e) {
            logger.error("Error transferring image to destination directory.\n{}", e.getMessage());
        }
        AvatarImage image = new AvatarImage();
        image.setLink("/images/avatar_images/" + safeFilename);
        return mapper.toDto(dao.save(image));
    }

    @Override
    public AvatarImageDTO getAvatarImageById(Long id) {
        return mapper.toDto(dao.findById(id).orElseThrow(EntityNotFoundException::new));
    }

}
