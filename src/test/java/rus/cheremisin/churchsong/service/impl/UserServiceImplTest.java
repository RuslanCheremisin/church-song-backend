package rus.cheremisin.churchsong.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rus.cheremisin.churchsong.DAO.UserDAO;
import rus.cheremisin.churchsong.DTO.AvatarImageDTO;
import rus.cheremisin.churchsong.DTO.GrantMembershipRequest;
import rus.cheremisin.churchsong.DTO.UserCreateRequest;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.entity.AvatarImage;
import rus.cheremisin.churchsong.entity.Band;
import rus.cheremisin.churchsong.entity.User;
import rus.cheremisin.churchsong.service.BandService;
import rus.cheremisin.churchsong.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    Long validId;
    Long invalidId;
    User validUser;
    User newUser;
    UserDTO validUserDTO;
    UserCreateRequest userCreateRequest;
    Band band;

    @Mock
    UserService userService;
    @Mock
    BandService bandService;
    @Mock
    UserDAO dao;

    @BeforeEach
    void setUp() {
        validId = 1L;
        invalidId = 999L;
        validUser = new User(
                1L,
                "Ivan",
                "Grozny",
                "+71234567890",
                "Biography article",
                List.of("balalayka", "flute"),
                new ArrayList<>(),
                new ArrayList<>(),
                new AvatarImage(1L, "http link"),
                "test@email.test",
                "username",
                "password",
                new HashSet<>());
        newUser = new User(
                1L,
                "Ivan",
                "Grozny",
                "+71234567890",
                "Biography article",
                List.of("balalayka", "flute"),
                new ArrayList<>(),
                new ArrayList<>(),
                new AvatarImage(1L, "http link"),
                "test@email.test",
                "username",
                "password",
                new HashSet<>());

        validUserDTO = new UserDTO(
                1L,
                "Ivan",
                "Grozny",
                "+71234567890",
                "Biography article",
                List.of("balalayka", "flute"),
                new ArrayList<>(),
                new ArrayList<>(),
                new AvatarImageDTO(1L, "http link"),
                "username",
                "test@email.test",
                new HashSet<>());

        userCreateRequest = new UserCreateRequest(
                "Ivan",
                "Grozny",
                new AvatarImageDTO(),
                "Biography article",
                "username",
                "password");
        band = new Band();
        band.setId(1L);
        band.setMembers(new ArrayList<>());
    }

    @Test
    @DisplayName("Должен вернуть UserDto, когда ID действителен")
    void findById_shouldReturnUserDto_whenIdIsValid() {
        when(dao.findById(validId)).thenReturn(Optional.of(validUser));
        when(userService.findById(validId)).thenReturn(validUserDTO);
        Optional<User> testedUser = dao.findById(validId);
        UserDTO testedDto = userService.findById(validId);
        assertThat(testedUser).isNotNull();
        assertThat(testedUser).isPresent();
        assertThat(testedUser.get()).isEqualTo(validUser);
        assertNotNull(testedDto);
        assertThat(testedDto).isEqualTo(validUserDTO);
    }

    @Test
    @DisplayName("Должен выбросить EntityNotFoundException, когда ID недействителен")
    void findById_shouldThrowEntityNotFoundException_whenIdIsInvalid() {

        when(dao.findById(invalidId)).thenThrow(EntityNotFoundException.class);
        when(userService.findById(invalidId)).thenThrow(EntityNotFoundException.class);

        assertThatThrownBy(() -> dao.findById(invalidId)).isInstanceOf(EntityNotFoundException.class);
        assertThatThrownBy(() -> userService.findById(invalidId)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("Должен вернуть сущность User со сгенерированным Id при сохранении")
    void save_shouldReturnUserWithGeneratedId() {
        when(dao.save(newUser)).thenReturn(validUser);
        when(userService.addUser(userCreateRequest)).thenReturn(validUserDTO);

        User testedUser = dao.save(newUser);
        UserDTO testedUserDto = userService.addUser(userCreateRequest);

        assertThat(testedUser).isNotNull();
        assertThat(testedUser).isEqualTo(validUser);
        assertThat(testedUserDto).isNotNull();
        assertThat(testedUserDto).isEqualTo(validUserDTO);
        assertThat(testedUser.getId()).isEqualTo(validId);
        assertThat(testedUserDto.getId()).isEqualTo(validId);

    }

    @Test
    @DisplayName("Должен вернуть обновлённую сущность User при сохранении")
    void update_shouldReturnUpdatedUserEntityWithGeneratedId() {
        User updatedUser = validUser;
        updatedUser.setBio("Updated bio");
        UserDTO updatedUserDto = validUserDTO;
        updatedUserDto.setBio("Updated bio");

        when(dao.save(updatedUser)).thenReturn(updatedUser);
        when(userService.updateUser(validId, updatedUserDto)).thenReturn(updatedUserDto);

        User testedUser = dao.save(updatedUser);
        UserDTO testedUserDto = userService.updateUser(validId, updatedUserDto);

        assertThat(testedUser).isNotNull();
        assertThat(testedUser).isEqualTo(updatedUser);
        assertThat(testedUserDto).isNotNull();
        assertThat(testedUserDto).isEqualTo(updatedUserDto);
    }

    @Test
    @DisplayName("При обновлении должен выбросить EntityNotFoundException, когда ID недействителен")
    void update_shouldThrowEntityNotFoundException_whenIdIsInvalid() {

        User updatedUser = validUser;
        updatedUser.setBio("Updated bio");
        UserDTO updatedUserDto = validUserDTO;
        updatedUserDto.setBio("Updated bio");

        when(dao.save(updatedUser)).thenThrow(EntityNotFoundException.class);
        when(userService.updateUser(invalidId, updatedUserDto)).thenThrow(EntityNotFoundException.class);

        assertThatThrownBy(() -> dao.save(updatedUser)).isInstanceOf(EntityNotFoundException.class);
        assertThatThrownBy(() -> userService.updateUser(invalidId, updatedUserDto)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("Должен удалить группу из списка групп пользователя")
    void removeBandFromUser_shouldRemoveBandFromListOfUsersBands() {

        GrantMembershipRequest request = new GrantMembershipRequest();
        request.setNewMemberId(validId);
        bandService.grantMembershipRequest(band.getId(), request);

        userService.removeBandFromUser(validId, band.getId());
        assertFalse(validUser.getBands().contains(band));
    }

//    @Test
//    void getAllUsers() {
//        userCreateRequest.setId(2L);
//        when(userService.getAllUsers()).thenReturn(List.of(validUserDTO, userCreateRequest));
//        assertEquals(userService.getAllUsers(), List.of(validUserDTO, userCreateRequest));
//    }
//
//    @Test
//    void deleteUser() {
//        when(userService.getAllUsers()).thenReturn(List.of(userCreateRequest));
//        userService.addUser(validUserDTO);
//        userService.addUser(userCreateRequest);
//        userService.deleteUser(validId);
//        assertEquals(userService.getAllUsers(), List.of(userCreateRequest));
//
//    }

}