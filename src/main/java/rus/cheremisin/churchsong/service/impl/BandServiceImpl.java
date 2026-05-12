package rus.cheremisin.churchsong.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import rus.cheremisin.churchsong.DAO.BandDAO;
import rus.cheremisin.churchsong.DTO.*;
import rus.cheremisin.churchsong.entity.AvatarImage;
import rus.cheremisin.churchsong.entity.Band;
import rus.cheremisin.churchsong.entity.Song;
import rus.cheremisin.churchsong.entity.User;
import rus.cheremisin.churchsong.exceptions.CurrentUserIsNotTheLeaderOfTheBandException;
import rus.cheremisin.churchsong.mapper.AvatarImageMapper;
import rus.cheremisin.churchsong.mapper.BandMapper;
import rus.cheremisin.churchsong.mapper.UserMapper;
import rus.cheremisin.churchsong.service.BandService;
import rus.cheremisin.churchsong.service.ImageService;
import rus.cheremisin.churchsong.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BandServiceImpl implements BandService {
    BandDAO bandsDao;
    BandMapper bandMapper;
    UserService userService;
    UserMapper userMapper;
    @PersistenceContext
    EntityManager entityManager;
    AvatarImageMapper imageMapper;

    @Override
    @Transactional
    public List<SimpleBandDTO> getAllBands(Pageable pageable) {
        if (pageable == null) {
            throw new NullPointerException("pageable is null!");
        }

        return bandMapper.toDtoList(bandsDao.findAll(pageable).toList());
    }

    @Override
    @Transactional
    public BandDTO getBandById(Long bandId) {
        return bandMapper.toDto(bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id")));
    }

    @Override
    @Transactional
    public BandDTO createBand(CreateBandRequest request) {
        User creatorUser = userService.getCurrentAuthUser();
        User managedUser = entityManager.merge(creatorUser);

        Band newBand = new Band(
                null,
                request.name(),
                managedUser,
                request.email(),
                request.contactPhone(),
                null,
                request.bio(),
                new ArrayList<>(),
                new ArrayList<>());
        Band savedBand = bandsDao.save(newBand);
        userService.addBandToUser(userService.getCurrentAuthUser().getId(), newBand);
        return bandMapper.toDto(savedBand);
    }

    @Override
    public BandDTO patchBand(Long bandId, PatchBandInfoDTO dto) {
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
        User currentAuthUser = userService.getCurrentAuthUser();
        Long currentUserId = userService.getUserByUsername(currentAuthUser.getUsername()).getId();
        User bandLeader = band.getLeader();
        if (Objects.equals(bandLeader.getId(), currentUserId)) {
            band.setBandAvatar(imageMapper.toEntity(dto));
        } else {
            throw new CurrentUserIsNotTheLeaderOfTheBandException();
        }
        return bandMapper.toDto(bandsDao.save(band));
    }

    @Override
    public BandDTO grantBandMembership(Long bandId, GrantMembershipRequest request) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        User newMember = userMapper.toEntity(userService.findById(request.newMemberId()));
        band.addMember(newMember);
        return bandMapper.toDto(userService.addBandToUser(newMember.getId(), band));
    }

    @Override
    public BandDTO cancelBandMembership(Long bandId, CancelMembershipRequest request) {
        Band band = bandsDao.findById(bandId).orElseThrow(() -> new EntityNotFoundException("no band with such id"));
        User newMember = userMapper.toEntity(userService.findById(request.memberId()));
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
    public List<SimpleBandDTO> getBandsByUserId(Long userId) {
        UserDTO givenUser = userService.findById(userId);
        List<User> userList = List.of(userMapper.toEntity(givenUser));
        return bandMapper.toDtoList(bandsDao.findAllByMembersContaining(userList));
    }

    @Override
    public List<UserDTO> getBandMembers(Long bandId) {
        return userMapper.toDtoList(bandsDao.findById(bandId).orElseThrow().getMembers());
    }


}
