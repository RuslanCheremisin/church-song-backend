package rus.cheremisin.churchsong.service.impl;

import org.springframework.stereotype.Service;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.service.UserService;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDTO getUserById(Long id) {
        return null;
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
