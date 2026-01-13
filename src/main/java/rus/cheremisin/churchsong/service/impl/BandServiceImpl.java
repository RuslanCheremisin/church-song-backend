package rus.cheremisin.churchsong.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rus.cheremisin.churchsong.DAO.BandDAO;
import rus.cheremisin.churchsong.DTO.*;
import rus.cheremisin.churchsong.entity.Band;
import rus.cheremisin.churchsong.entity.Song;
import rus.cheremisin.churchsong.entity.User;
import rus.cheremisin.churchsong.mapper.AvatarImageMapper;
import rus.cheremisin.churchsong.mapper.BandMapper;
import rus.cheremisin.churchsong.mapper.UserMapper;
import rus.cheremisin.churchsong.service.BandService;
import rus.cheremisin.churchsong.service.UserService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BandServiceImpl implements BandService {
    private BandDAO bandsDao;
    private BandMapper bandMapper;
    private UserService userService;
    private UserMapper userMapper;
    private AvatarImageMapper imageMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SimpleBandDTO> getAllBands() {
        return bandMapper.toDtoList(bandsDao.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public BandDTO getBandById(Long bandId) {
        return bandMapper.toDto(bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id")));
    }

    @Override
    public BandDTO createBand(BandDTO dto) {
        return bandMapper.toDto(bandsDao.save(bandMapper.toEntity(dto)));
    }

    @Override
    public BandDTO patchBand(Long bandId, PatchBandDTO dto) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        Band updatedBand = bandMapper.mergeToEntity(dto, band);
        return bandMapper.toDto(bandsDao.save(updatedBand));
    }

    @Override
    public BandDTO changeBandLeader(Long bandId, LeaderChangeRequest request) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        if (request.getLeaderId() == null) {
            band.setLeader(null);
        } else {
            User leader = userMapper.toEntity(userService.findById(request.getLeaderId()));
            band.setLeader(leader);
        }
        return bandMapper.toDto(bandsDao.save(band));
    }

    @Override
    public BandDTO changeBandAvatar(Long bandId, AvatarImageDTO dto) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        band.setBandAvatar(imageMapper.toEntity(dto));
        return bandMapper.toDto(bandsDao.save(band));
    }

    @Override
    public BandDTO grantMembershipRequest(Long bandId, GrantMembershipRequest request) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        User newMember = userMapper.toEntity(userService.findById(request.getNewMemberId()));
        band.addMember(newMember);
        return bandMapper.toDto(userService.addBandToUser(newMember.getId(), band));
    }

    @Override
    public BandDTO cancelMembershipRequest(Long bandId, CancelMembershipRequest request) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        User newMember = userMapper.toEntity(userService.findById(request.getMemberId()));
        band.removeMember(newMember);
        return bandMapper.toDto(bandsDao.save(band));
    }

    @Override
    public void deleteBand(Long bandId) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        bandsDao.delete(band);
    }

    @Override
    public void addSongToBand(Long bandId, Song song) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        band.addSong(song);
        bandsDao.save(band);
    }
    @Override
    public void removeSongFromBand(Long bandId, Song song) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        band.removeSong(song);
        bandsDao.save(band);
    }

    @Override
    public List<SimpleBandDTO> getUserBands(Long userId) {
        UserDTO givenUser = userService.findById(userId);
        List<User> userList = List.of(userMapper.toEntity(givenUser));
        return bandMapper.toDtoList(bandsDao.findAllByMembersContaining(userList));
    }
}
