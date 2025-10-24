package rus.cheremisin.churchsong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rus.cheremisin.churchsong.DTO.songparts.SongPartDTO;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartController {
    
    @GetMapping
    public ResponseEntity<List<SongPartDTO>> getParts() {
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SongPartDTO> getPartById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
    
    @PostMapping
    public ResponseEntity<SongPartDTO> createPart(SongPartDTO song) {
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SongPartDTO> editPart(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<SongPartDTO> deletePart(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
