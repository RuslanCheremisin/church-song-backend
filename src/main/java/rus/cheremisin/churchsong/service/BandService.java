package rus.cheremisin.churchsong.service;

import rus.cheremisin.churchsong.DTO.*;

import java.util.List;

public interface BandService {
    List<BandDTO> getAllBands();

    BandDTO getBandById(Long bandId);

    BandDTO createBand(BandDTO dto);

    BandDTO patchBand(Long bandId, PatchBandDTO dto);

    BandDTO changeBandLeader(Long bandId, LeaderChangeRequest request);

    BandDTO changeBandAvatar(Long bandId, AvatarImageDTO dto);

    BandDTO grantMembershipRequest(Long bandId, GrantMembershipRequest request);

    BandDTO cancelMembershipRequest(Long bandId, CancelMembershipRequest request);

    void deleteBand(Long bandId);
}
