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
    private BandDAO dao;
    private BandMapper bandMapper;
    private UserService userService;
    private UserMapper userMapper;
    private AvatarImageMapper imageMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BandDTO> getAllBands() {
        return bandMapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public BandDTO getBandById(Long bandId) {
        return bandMapper.toDto(dao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id")));
    }

    @Override
    public BandDTO createBand(BandDTO dto) {
        return bandMapper.toDto(dao.save(bandMapper.toEntity(dto)));
    }

    @Override
    public BandDTO patchBand(Long bandId, PatchBandDTO dto) {
        Band band = dao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        Band updatedBand = bandMapper.mergeToEntity(dto, band);
        return bandMapper.toDto(dao.save(updatedBand));
    }

    @Override
    public BandDTO changeBandLeader(Long bandId, LeaderChangeRequest request) {
        Band band = dao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        if (request.getLeaderId() == null) {
            band.setLeader(null);
        } else {
            User leader = userMapper.toEntity(userService.findById(request.getLeaderId()));
            band.setLeader(leader);
        }
        return bandMapper.toDto(dao.save(band));
    }

    @Override
    public BandDTO changeBandAvatar(Long bandId, AvatarImageDTO dto) {
        Band band = dao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        band.setBandAvatar(imageMapper.toEntity(dto));
        return bandMapper.toDto(dao.save(band));
    }

    @Override
    public BandDTO grantMembershipRequest(Long bandId, GrantMembershipRequest request) {
        Band band = dao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        User newMember = userMapper.toEntity(userService.findById(request.getNewMemberId()));
        band.addMember(newMember);
        return bandMapper.toDto(userService.addBandToUser(newMember.getId(), band));
    }

    @Override
    public BandDTO cancelMembershipRequest(Long bandId, CancelMembershipRequest request) {
        Band band = dao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        User newMember = userMapper.toEntity(userService.findById(request.getMemberId()));
        band.removeMember(newMember);
        return bandMapper.toDto(dao.save(band));
    }

    @Override
    public void deleteBand(Long bandId) {
        Band band = dao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        dao.delete(band);
    }

    @Override
    public void addSongToBand(Long bandId, Song song) {
        Band band = dao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        band.addSong(song);
        dao.save(band);
    }
    @Override
    public void removeSongFromBand(Long bandId, Song song) {
        Band band = dao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        band.removeSong(song);
        dao.save(band);
    }
}
