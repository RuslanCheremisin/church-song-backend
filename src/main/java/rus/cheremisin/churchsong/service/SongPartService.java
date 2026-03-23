package rus.cheremisin.churchsong.service;

import rus.cheremisin.churchsong.DTO.UploadResponse;
import rus.cheremisin.churchsong.enums.UploadMediaType;

import java.io.File;

public interface SongPartService {
    UploadResponse upload(UploadMediaType type, String uploadLink, File file);
}
