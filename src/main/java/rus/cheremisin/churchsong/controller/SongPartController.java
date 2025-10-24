package rus.cheremisin.churchsong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rus.cheremisin.churchsong.DTO.songparts.SongPartDTO;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class SongPartController {
    
    @GetMapping
    public ResponseEntity<List<SongPartDTO>> getSongParts() {
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SongPartDTO> getSongPartById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
    
    @PostMapping
    public ResponseEntity<SongPartDTO> createSongPart(SongPartDTO song) {
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SongPartDTO> editSongPart(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<SongPartDTO> deleteSongPart(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
