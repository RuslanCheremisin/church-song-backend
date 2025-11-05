package rus.cheremisin.churchsong.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import rus.cheremisin.churchsong.DAO.SongDAO;
import rus.cheremisin.churchsong.DTO.FullSongDTO;
import rus.cheremisin.churchsong.DTO.PatchSongDTO;
import rus.cheremisin.churchsong.mapper.SongMapper;
import rus.cheremisin.churchsong.entity.Song;
import rus.cheremisin.churchsong.service.BandService;
import rus.cheremisin.churchsong.service.church.SongService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SongServiceImpl implements SongService {

    SongDAO dao;
    SongMapper mapper;
    BandService bandService;
    @Override
    public FullSongDTO getSongById(Long id) {
        final Song song = dao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("song с данным id не найден!")
        );
        return mapper.toFullSongDto(song);
    }

    @Override
    public List<FullSongDTO> getAllSongs() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    public FullSongDTO createSong(FullSongDTO dto, Long bandId) {
        Song song = mapper.toEntity(dto);
        bandService.addSongToBand(bandId, song);
        return mapper.toFullSongDto(song);
    }

    @Override
    public FullSongDTO editSong(Long id, PatchSongDTO dto) {
        final Song song = dao.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("song с данным id не найден!")
                );
        final Song updatedSong = mapper.mergeToEntity(dto, song);
        return mapper.toFullSongDto(updatedSong);
    }

    @Override
    public void deleteSong(Long songId, Long bandId) {
        Song song = dao.findById(songId)
                .orElseThrow(
                        () -> new EntityNotFoundException("song с данным id не найден!")
                );
        bandService.removeSongFromBand(bandId, song);
        dao.delete(song);
    }
}
