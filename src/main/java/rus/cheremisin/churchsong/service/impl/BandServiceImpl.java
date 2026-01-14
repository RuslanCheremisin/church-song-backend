package rus.cheremisin.churchsong.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BandServiceImpl implements BandService {
    BandDAO bandsDao;
    BandMapper bandMapper;
    UserService userService;
    UserMapper userMapper;
    AvatarImageMapper imageMapper;
    @PersistenceContext
    EntityManager entityManager;

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
    public BandDTO createBand(BandCreateRequest request) {
        User creatorUser = getCurrentUser();
        User managedUser = entityManager.merge(creatorUser);
        Band newBand = new Band(
                null,
                request.getName(),
                managedUser,
                request.getEmail(),
                request.getContactPhone(),
                null,
                request.getBio(),
                new ArrayList<>(),
                new ArrayList<>());
        Band savedBand = bandsDao.save(newBand);
        userService.addBandToUser(getCurrentUser().getId(), newBand);
        return bandMapper.toDto(savedBand);
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

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails) {
            return (User) auth.getPrincipal();
        } else {
            throw new RuntimeException("there are no authenticated user");
        }
    }
}
