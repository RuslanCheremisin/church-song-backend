package rus.cheremisin.churchsong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rus.cheremisin.churchsong.DTO.OriginalRecordingDTO;

import java.util.List;

//@RestController
//@RequestMapping(" /{song-id}/original-recording")
public class OriginalRecordingController {

    @GetMapping
    public ResponseEntity<List<OriginalRecordingDTO>> getOriginalRecording(@PathVariable("song-id") Long songId) {
        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<OriginalRecordingDTO> createOriginalRecording(OriginalRecordingDTO dto, @PathVariable("song-id") Long songId) {
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<OriginalRecordingDTO> editOriginalRecording(@PathVariable("song-id") Long songId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<OriginalRecordingDTO> deleteOriginalRecording(@PathVariable("song-id") Long songId) {
        return ResponseEntity.ok().build();
    }
}
