package rus.cheremisin.churchsong.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.DTO.AvatarImageDTO;
import rus.cheremisin.churchsong.DTO.CreateBandRequest;
import rus.cheremisin.churchsong.DTO.BandDTO;
import rus.cheremisin.churchsong.DTO.CancelMembershipRequest;
import rus.cheremisin.churchsong.DTO.GrantMembershipRequest;
import rus.cheremisin.churchsong.DTO.LeaderChangeRequest;
import rus.cheremisin.churchsong.DTO.PatchBandInfoDTO;
import rus.cheremisin.churchsong.DTO.SimpleBandDTO;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.service.BandService;
import rus.cheremisin.churchsong.service.ImageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/bands")
public class BandController {
    BandService bandService;
    ImageService imageService;

    @GetMapping
    public ResponseEntity<List<SimpleBandDTO>> getAllBands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(bandService.getAllBands(pageable));
    }

    @GetMapping("/by-user/{user-id}")
    public ResponseEntity<List<SimpleBandDTO>> getBandsByUserId(@PathVariable("user-id") @Positive Long userId) {
        return ResponseEntity.ok(bandService.getBandsByUserId(userId));
    }

    @GetMapping("/{band-id}")
    public ResponseEntity<BandDTO> getBandById(@PathVariable("band-id") @Positive Long bandId) {
        return ResponseEntity.ok(bandService.getBandById(bandId));
    }

    @PostMapping
    public ResponseEntity<BandDTO> createBand(@Valid @RequestBody CreateBandRequest request) {
        return ResponseEntity.ok(bandService.createBand(request));
    }

    @PutMapping("/{band-id}")
    public ResponseEntity<BandDTO> patchBand(@PathVariable("band-id") @Positive Long bandId, @Valid @RequestBody PatchBandInfoDTO dto) {
        return ResponseEntity.ok(bandService.patchBand(bandId, dto));
    }

    @PatchMapping("/{band-id}/leader")
    public ResponseEntity<BandDTO> changeBandLeader(@PathVariable("band-id") @Positive Long bandId, @Valid @RequestBody LeaderChangeRequest request) {
        return ResponseEntity.ok(bandService.changeBandLeader(bandId, request));
    }

    @PostMapping(value = "/{band-id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BandDTO> changeBandAvatar(@PathVariable("band-id") @Positive Long bandId,
                                                    @RequestParam(value = "photoFile") MultipartFile photoFile) {
        AvatarImageDTO dto = imageService.uploadAvatarImage(photoFile);
        return ResponseEntity.ok(bandService.changeBandAvatar(bandId, dto));
    }

    @GetMapping("/{band-id}/members")
    public ResponseEntity<List<UserDTO>> getBandMembers(@PathVariable("band-id") @Positive Long bandId) {
        return ResponseEntity.ok(bandService.getBandMembers(bandId));
    }

    @PatchMapping("/{band-id}/members")
    public ResponseEntity<BandDTO> grantBandMembership(@PathVariable("band-id") Long bandId, @Valid @RequestBody GrantMembershipRequest request) {
        return ResponseEntity.ok(bandService.grantBandMembership(bandId, request));
    }

    @DeleteMapping("/{band-id}/members")
    public ResponseEntity<BandDTO> cancelBandMembership(@PathVariable("band-id") Long bandId, @Valid @RequestBody CancelMembershipRequest request) {
        return ResponseEntity.ok(bandService.cancelBandMembership(bandId, request));
    }

    @DeleteMapping("/{band-id}")
    public ResponseEntity<?> deleteBand(@PathVariable("band-id") @Positive Long bandId) {
        bandService.deleteBand(bandId);
        return ResponseEntity.noContent().build();
    }


}
