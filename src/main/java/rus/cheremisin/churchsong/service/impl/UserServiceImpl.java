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
    public UserDTO addBandToUser(Long newMemberId, Band band) {
        User user = dao.findById(newMemberId).orElseThrow(() -> new EntityNotFoundException("no user with such id"));
        user.addBand(band);
        return mapper.toDto(dao.save(user));
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
        dao.save(member);
    }

    @Override
    public void addUserToBandAsLeader(Band band, Long leaderId) {
        User leader = dao.findById(leaderId).orElseThrow(() -> new EntityNotFoundException("no user with such id"));
        if (band != null) {
            band.setLeader(leader);
        }
    }

    @Override
    public UserDTO addUser(UserDTO user) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }
}
