package rus.cheremisin.churchsong.service;

import rus.cheremisin.churchsong.DTO.FullSongDTO;
import rus.cheremisin.churchsong.DTO.PatchSongDTO;

import java.util.List;

public interface SongService {
    FullSongDTO getSongById(Long id);

    List<FullSongDTO> getAllSongs();

    FullSongDTO createSong(FullSongDTO dto);

    FullSongDTO editSong(Long id, PatchSongDTO dto);

    void deleteSong(Long id);
}
