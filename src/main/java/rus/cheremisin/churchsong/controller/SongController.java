package rus.cheremisin.churchsong.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rus.cheremisin.churchsong.DTO.SongDTO;
import rus.cheremisin.churchsong.service.SongService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/songs")
public class SongController {

    SongService service;

    @GetMapping
    public ResponseEntity<List<SongDTO>> getSongs() {
        return ResponseEntity.ok(service.getAllSongs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSongById(id));
    }

    @PostMapping
    public ResponseEntity<SongDTO> createSong(SongDTO dto) {
        return ResponseEntity.ok(service.createSong(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongDTO> editSong(@PathVariable Long id, @RequestBody SongDTO dto) {
        return ResponseEntity.ok(service.editSong(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SongDTO> deleteSong(@PathVariable Long id) {
        service.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
