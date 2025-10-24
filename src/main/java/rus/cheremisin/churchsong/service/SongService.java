package rus.cheremisin.churchsong.service;

import rus.cheremisin.churchsong.DTO.SongDTO;

import java.util.List;

public interface SongService {
    SongDTO getSongById(Long id);

    List<SongDTO> getAllSongs();

    SongDTO createSong(SongDTO dto);

    SongDTO editSong(Long id, SongDTO dto);

    void deleteSong(Long id);
}
