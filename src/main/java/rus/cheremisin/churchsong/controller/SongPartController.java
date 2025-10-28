package rus.cheremisin.churchsong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rus.cheremisin.churchsong.DTO.songparts.*;

import java.util.List;

@RestController
@RequestMapping("/songs/{song-id}/parts")
public class SongPartController {

    @GetMapping
    public ResponseEntity<List<FullSongPartDTO>> getSongParts() {
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{part-id}")
    public ResponseEntity<FullSongPartDTO> getSongPartById(@PathVariable("song-id") Long songId, @PathVariable("part-id") Long partId) {
        return ResponseEntity.ok().build();
    }
    
    @PostMapping
    public ResponseEntity<FullSongPartDTO> createSongPart(FullSongPartDTO song, @PathVariable("song-id") Long songId) {
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/{part-id}/audio")
    public ResponseEntity<FullSongPartDTO> editAudioPart(@PathVariable("song-id") Long songId, @PathVariable("part-id") Long partId, AudioPartDTO dto) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{part-id}/video")
    public ResponseEntity<FullSongPartDTO> editVideoPart(@PathVariable("song-id") Long songId, @PathVariable("part-id") Long partId, VideoPartDTO dto) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{part-id}/text")
    public ResponseEntity<FullSongPartDTO> editText(@PathVariable("song-id") Long songId, @PathVariable("part-id") Long partId, String text) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{part-id}/image")
    public ResponseEntity<FullSongPartDTO> editImagePart(@PathVariable("song-id") Long songId, @PathVariable("part-id") Long partId, ImagePartDTO dto) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{part-id}/doc")
    public ResponseEntity<FullSongPartDTO> editDocPart(@PathVariable("song-id") Long songId, @PathVariable("part-id") Long partId, DocPartDTO dto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{part-id}")
    public ResponseEntity<FullSongPartDTO> deleteSongPart(@PathVariable("song-id") Long songId, @PathVariable("part-id") Long partId) {
        return ResponseEntity.ok().build();
    }
}
