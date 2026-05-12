package rus.cheremisin.churchsong.service;

import org.springframework.data.domain.Pageable;
import rus.cheremisin.churchsong.DTO.*;
import rus.cheremisin.churchsong.entity.Song;

import java.util.List;

public interface BandService {
    List<SimpleBandDTO> getAllBands(Pageable pageable);

    BandDTO getBandById(Long bandId);

    BandDTO createBand(CreateBandRequest request);

    BandDTO patchBand(Long bandId, PatchBandInfoDTO dto);

    BandDTO changeBandLeader(Long bandId, LeaderChangeRequest request);

    BandDTO changeBandAvatar(Long bandId, AvatarImageDTO dto);

    BandDTO grantBandMembership(Long bandId, GrantMembershipRequest request);

    BandDTO cancelBandMembership(Long bandId, CancelMembershipRequest request);

    void deleteBand(Long bandId);

    void addSongToBand(Long id, Song song);

    void removeSongFromBand(Long bandId, Song song);

    List<SimpleBandDTO> getBandsByUserId(Long userId);

    List<UserDTO> getBandMembers(Long bandId);
}
