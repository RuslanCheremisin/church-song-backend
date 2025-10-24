package rus.cheremisin.churchsong.service;

import rus.cheremisin.churchsong.DTO.UserDTO;

public interface UserService {
    UserDTO getUserById(Long id);
}
