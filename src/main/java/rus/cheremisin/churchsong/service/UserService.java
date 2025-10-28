package rus.cheremisin.churchsong.service;

import rus.cheremisin.churchsong.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long id);

    UserDTO addUser(UserDTO user);

    UserDTO updateUser(Long id, UserDTO user);

    List<UserDTO> getAllUsers();

    void deleteUser(Long id);

    UserDTO getUserByUsername(String username);
}
