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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rus.cheremisin.churchsong.DAO.UserDAO;
import rus.cheremisin.churchsong.DTO.AvatarImageDTO;
import rus.cheremisin.churchsong.DTO.UserCreateRequest;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.entity.AvatarImage;
import rus.cheremisin.churchsong.entity.Band;
import rus.cheremisin.churchsong.entity.User;
import rus.cheremisin.churchsong.exceptions.UserIsNotInTheBandException;
import rus.cheremisin.churchsong.mapper.AvatarImageMapper;
import rus.cheremisin.churchsong.mapper.UserMapper;
import rus.cheremisin.churchsong.service.ImageService;
import rus.cheremisin.churchsong.service.RoleService;
import rus.cheremisin.churchsong.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    UserDAO dao;
    UserMapper mapper;
    RoleService roleService;
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager entityManager;
    ImageService imageService;

    @Override
    public UserDTO findById(Long id) {
        return mapper.toDto(dao.findById(id).orElseThrow(() -> new EntityNotFoundException("no user with such id")));
    }

    @Override
    public Band addBandToUser(Long newMemberId, Band band) {
        User user = dao.findById(newMemberId).orElseThrow(() -> new EntityNotFoundException("no user with such id"));
        user.addBand(band);
        band.addMember(user);
        dao.save(user);
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
    public UserDTO addUser(UserCreateRequest request) {
        AvatarImageDTO avatarImageDTO = imageService.uploadAvatarImage(request.getPhotoFile());
        AvatarImage avatarImage = entityManager.getReference(AvatarImage.class, avatarImageDTO.getId());

        User user = new User(
                null,
                request.getFirstName(),
                request.getLastName(),
                null,
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                avatarImage,
                request.getEmail(),
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                null);
        user.addRole(roleService.getRoleByName("USER"));
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
    public UserDTO getUserByUsername(String username) {
        User user = dao.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("no user with such username"));
        return mapper.toDto(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = dao.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("no user with such email"));
        return mapper.toDto(user);
    }

    @Override
    public UserDTO addOAuth2OrTGUser(UserDTO userDTO) {
        User user = dao.save(mapper.toEntity(userDTO));
        return mapper.toDto(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("no user with such username"));
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            if (auth.getPrincipal() instanceof UserDetails) {
                return (User) auth.getPrincipal();
            } else if (auth.getPrincipal() instanceof OAuth2User) {
                String username = "";
                Map<String, Object> attributes = ((OAuth2User) auth.getPrincipal()).getAttributes();
                if (attributes.containsKey("default_email")) {
                    username = (String) attributes.get("default_email");
                } else if (attributes.containsKey("email")) {
                    username = (String) attributes.get("email");
                }
                UserDTO dto = getUserByUsername(username);
                return mapper.toEntity(dto);

            } else {
                throw new NullPointerException("current principal is null");
            }
        } else {
            throw new RuntimeException("there are no authenticated user");
        }
    }
}
