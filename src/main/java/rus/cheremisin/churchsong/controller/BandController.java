package rus.cheremisin.churchsong.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.DTO.*;
import rus.cheremisin.churchsong.service.BandService;
import rus.cheremisin.churchsong.service.ImageService;
import rus.cheremisin.churchsong.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/bands")
public class BandController {
    BandService bandService;
    ImageService imageService;

    @GetMapping
    public ResponseEntity<List<SimpleBandDTO>> getAllBands() {
        return ResponseEntity.ok(bandService.getAllBands());
    }
    @GetMapping("/by-user/{user-id}")
    public ResponseEntity<List<SimpleBandDTO>> getUserBands(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(bandService.getUserBands(userId));
    }

    @GetMapping("/{band-id}")
    public ResponseEntity<BandDTO> getBandById(@PathVariable("band-id") Long bandId) {
        return ResponseEntity.ok(bandService.getBandById(bandId));
    }

    @PostMapping
    public ResponseEntity<BandDTO> createBand(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "contactPhone", required = false) String contactPhone,
            @RequestParam(value = "bio", required = false) String bio,
            @RequestParam(value = "photoFile", required = false) MultipartFile photoFile) {

        BandCreateRequest request = new BandCreateRequest();
        request.setName(name);
        request.setEmail(email);
        request.setContactPhone(contactPhone);
        request.setBio(bio);
        request.setPhotoFile(photoFile);
        return ResponseEntity.ok(bandService.createBand(request));
    }

    @PatchMapping("/{band-id}")
    public ResponseEntity<BandDTO> patchBand(@PathVariable("band-id") Long bandId, @RequestBody PatchBandDTO dto) {
        return ResponseEntity.ok(bandService.patchBand(bandId, dto));
    }

    @PatchMapping("/{band-id}/leader")
    public ResponseEntity<BandDTO> changeBandLeader(@PathVariable("band-id") Long bandId, @RequestBody LeaderChangeRequest request) {
        return ResponseEntity.ok(bandService.changeBandLeader(bandId, request));
    }

    @PostMapping("/{band-id}/avatar")
    public ResponseEntity<BandDTO> changeBandAvatar(@PathVariable("band-id") Long bandId,
                                                    @RequestParam(value = "photoFile") MultipartFile photoFile) {
        AvatarImageDTO dto = imageService.uploadAvatarImage(photoFile);
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
