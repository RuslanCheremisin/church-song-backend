package rus.cheremisin.churchsong.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rus.cheremisin.churchsong.DTO.*;
import rus.cheremisin.churchsong.service.BandService;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/bands")
public class BandController {
    private BandService bandService;

    @GetMapping
    public ResponseEntity<List<BandDTO>> getAllBands() {
        return ResponseEntity.ok(bandService.getAllBands());
    }

    @GetMapping("/{band-id}")
    public ResponseEntity<BandDTO> getBandById(@PathVariable("band-id") Long bandId) {
        return ResponseEntity.ok(bandService.getBandById(bandId));
    }

    @PostMapping
    public ResponseEntity<BandDTO> createBand(@RequestBody BandDTO dto) {
        return ResponseEntity.ok(bandService.createBand(dto));
    }

    @PatchMapping("/{band-id}")
    public ResponseEntity<BandDTO> patchBand(@PathVariable("band-id") Long bandId, @RequestBody PatchBandDTO dto) {
        return ResponseEntity.ok(bandService.patchBand(bandId, dto));
    }

    @PatchMapping("/{band-id}/leader")
    public ResponseEntity<BandDTO> changeBandLeader(@PathVariable("band-id") Long bandId, @RequestBody LeaderChangeRequest request) {
        return ResponseEntity.ok(bandService.changeBandLeader(bandId, request));
    }

    @PatchMapping("/{band-id}/avatar")
    public ResponseEntity<BandDTO> changeBandAvatar(@PathVariable("band-id") Long bandId, @RequestBody AvatarImageDTO dto) {
        return ResponseEntity.ok(bandService.changeBandAvatar(bandId, dto));
    }

    @PostMapping("/{band-id}/members")
    public ResponseEntity<BandDTO> grantMembership(@PathVariable("band-id") Long bandId, @RequestBody GrantMembershipRequest request) {
        return ResponseEntity.ok(bandService.grantMembershipRequest(bandId, request));
    }

    @DeleteMapping("/{band-id}/members")
    public ResponseEntity<BandDTO> cancelMembership(@PathVariable("band-id") Long bandId, @RequestBody CancelMembershipRequest request) {
        return ResponseEntity.ok(bandService.cancelMembershipRequest(bandId, request));
    }

    @DeleteMapping("/{band-id}")
    public ResponseEntity<?> deleteBand(@PathVariable("band-id") Long bandId) {
        bandService.deleteBand(bandId);
        return ResponseEntity.noContent().build();
    }


}
