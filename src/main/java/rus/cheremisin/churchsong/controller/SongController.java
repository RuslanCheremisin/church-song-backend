package rus.cheremisin.churchsong.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rus.cheremisin.churchsong.DTO.FullSongDTO;
import rus.cheremisin.churchsong.DTO.PatchSongDTO;
import rus.cheremisin.churchsong.service.SongService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/songs")
public class SongController {

    SongService service;

    @GetMapping
    public ResponseEntity<List<FullSongDTO>> getSongs() {
        return ResponseEntity.ok(service.getAllSongs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullSongDTO> getSongById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getSongById(id));
    }

    @PostMapping
    public ResponseEntity<FullSongDTO> createSong(@RequestBody FullSongDTO dto, @RequestParam Long bandId) {
        return ResponseEntity.ok(service.createSong(dto, bandId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FullSongDTO> editSong(@PathVariable("id") Long id, @RequestBody PatchSongDTO dto) {
        return ResponseEntity.ok(service.editSong(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FullSongDTO> deleteSong(@PathVariable("id") Long songId, @RequestParam Long bandId) {
        service.deleteSong(songId, bandId);
        return ResponseEntity.noContent().build();
    }
}
