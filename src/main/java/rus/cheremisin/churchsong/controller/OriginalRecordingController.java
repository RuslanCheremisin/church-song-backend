package rus.cheremisin.churchsong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rus.cheremisin.churchsong.DTO.OriginalRecordingDTO;
import rus.cheremisin.churchsong.DTO.songparts.AudioPartDTO;
import rus.cheremisin.churchsong.DTO.songparts.VideoPartDTO;

import java.util.List;

//@RestController
@RequestMapping("/songs/{song-id}/original-recording")
public class OriginalRecordingController {

//    @GetMapping
//    public ResponseEntity<List<OriginalRecordingDTO>> getOriginalRecording(@PathVariable("song-id") Long songId) {
//        return ResponseEntity.ok().build();
//    }


    @PostMapping
    public ResponseEntity<OriginalRecordingDTO> createOriginalRecording(OriginalRecordingDTO dto, @PathVariable("song-id") Long songId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/edit-audio")
    public ResponseEntity<OriginalRecordingDTO> editOriginalRecordingAudio(@PathVariable("song-id") Long songId, AudioPartDTO dto) {
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/edit-video")
    public ResponseEntity<OriginalRecordingDTO> editOriginalRecordingVideo(@PathVariable("song-id") Long songId, VideoPartDTO dto) {
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-audio")
    public ResponseEntity<OriginalRecordingDTO> deleteOriginalRecordingAudio(@PathVariable("song-id") Long songId, AudioPartDTO dto) {
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/delete-video")
    public ResponseEntity<OriginalRecordingDTO> deleteOriginalRecordingVideo(@PathVariable("song-id") Long songId, VideoPartDTO dto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<OriginalRecordingDTO> deleteOriginalRecording(@PathVariable("song-id") Long songId) {
        return ResponseEntity.ok().build();
    }
}
