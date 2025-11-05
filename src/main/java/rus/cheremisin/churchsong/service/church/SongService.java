package rus.cheremisin.churchsong.service.church;

import rus.cheremisin.churchsong.DTO.FullSongDTO;
import rus.cheremisin.churchsong.DTO.PatchSongDTO;
import rus.cheremisin.churchsong.DTO.SimpleSongDTO;

import java.util.List;

public interface SongService {
    FullSongDTO getSongById(Long id);

    List<SimpleSongDTO> getAllSongs();

    FullSongDTO createSong(FullSongDTO dto, Long bandId);

    FullSongDTO editSong(Long id, PatchSongDTO dto);

    void deleteSong(Long songId, Long bandId);
}
