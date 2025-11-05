package rus.cheremisin.churchsong.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rus.cheremisin.churchsong.DAO.UserDAO;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.entity.Band;
import rus.cheremisin.churchsong.entity.User;
import rus.cheremisin.churchsong.exceptions.UserIsNotInTheBandException;
import rus.cheremisin.churchsong.mapper.UserMapper;
import rus.cheremisin.churchsong.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserDAO dao;
    UserMapper mapper;

    @Override
    public UserDTO findById(Long id) {
        return mapper.toDto(dao.findById(id).orElseThrow(() -> new EntityNotFoundException("no user with such id")));
    }

    @Override
    public Band addBandToUser(User member, Band band) {
        member.addBand(band);
//        band.addMember(member);
        return band;
    }

    @Override
    public void removeBandFromUser(Long memberId, Long bandId) {
        User member = dao.findById(memberId).orElseThrow(() -> new EntityNotFoundException("no user with such id"));
        Band band = member
                .getBands()
                .stream()
                .filter(b -> b.getId().equals(bandId))
                .findAny()
                .orElseThrow(() -> new UserIsNotInTheBandException("user is not in the band"));
        member.removeBand(band);
    }

    @Override
    public void addUserToBandAsLeader(Band band, Long leaderId) {
        User leader = dao.findById(leaderId).orElseThrow(() -> new EntityNotFoundException("no user with such id"));
        if (band != null) {
            band.setLeader(leader);
        }
    }

    @Override
    public UserDTO addUser(UserDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.toDto(dao.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("no user with such id"));
        return mapper.toDto(mapper.mergeToEntity(dto, user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    public void deleteUser(Long id) {
        User userToDelete = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("no user with such id"));
        List<Band> userBands = userToDelete.getBands() != null ? new ArrayList<>(userToDelete.getBands()) : new ArrayList<>();
        List<Band> ledBands = userBands
                .stream()
                .filter(b -> b
                        .getLeader()
                        .equals(userToDelete))
                .toList();
        for (Band band : ledBands) {
            if (band.getLeader() != null) {
                band.setLeader(null);
            }
        }
        for (Band band : userBands) {
            userToDelete.removeBand(band);
            if (band.getMembers() != null) {
                band.getMembers().remove(userToDelete);
            }
        }
        dao.delete(userToDelete);
    }

    @Override
    public UserDTO getUserByUsername(String email) {
        User user = dao.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("no user with such id"));
        return mapper.toDto(user);
    }
}
