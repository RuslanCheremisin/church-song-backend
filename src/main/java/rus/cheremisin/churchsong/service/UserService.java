package rus.cheremisin.churchsong.service;

import rus.cheremisin.churchsong.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO user);

    UserDTO updateUser(Long id, UserDTO user);

    List<UserDTO> getAllUsers();

    void deleteUser(Long id);

    UserDTO getUserByUsername(String username);
    UserDTO findById(Long userId);
}
