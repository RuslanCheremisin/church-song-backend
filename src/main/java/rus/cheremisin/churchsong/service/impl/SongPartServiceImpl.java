package rus.cheremisin.churchsong.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rus.cheremisin.churchsong.DTO.UploadResponse;
import rus.cheremisin.churchsong.DTO.songparts.VideoPartDTO;
import rus.cheremisin.churchsong.enums.UploadMediaType;
import rus.cheremisin.churchsong.service.SongPartService;

import java.io.File;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class SongPartServiceImpl implements SongPartService {


    @Override
    public UploadResponse upload(UploadMediaType type, String uploadLink, File file) {
        return null;
    }
}
