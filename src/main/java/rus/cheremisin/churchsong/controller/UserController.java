package rus.cheremisin.churchsong.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.DTO.AvatarImageDTO;
import rus.cheremisin.churchsong.DTO.BandDTO;
import rus.cheremisin.churchsong.DTO.UserCreateRequest;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.service.ImageService;
import rus.cheremisin.churchsong.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {
    UserService userService;
    ImageService imageService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestParam(value = "firstName") String firstName,
                                              @RequestParam(value = "lastName") String lastName,
                                              @RequestParam(value = "email") String email,
                                              @RequestParam(value = "username") String username,
                                              @RequestParam(value = "password") String password,
                                              @RequestParam(value = "photoFile", required = false) MultipartFile photoFile) {

        UserCreateRequest request = new UserCreateRequest(
                firstName,
                lastName,
                email,
                username,
                password,
                photoFile);

        return ResponseEntity.ok(userService.addUser(request));
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity<UserDTO> patchUser(@PathVariable("user-id") Long userId, @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(userId, dto));
    }

    @PatchMapping("/{user-id}/leave")
    public ResponseEntity<UserDTO> leaveBand(@PathVariable("user-id") Long userId, @RequestParam Long bandId) {
        userService.removeBandFromUser(userId, bandId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteBand(@PathVariable("user-id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping(value = "/{user-id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDTO> changeUserAvatar(@PathVariable("user-id") Long userId,
                                                    @RequestParam(value = "photoFile") MultipartFile photoFile) {
        AvatarImageDTO dto = imageService.uploadAvatarImage(photoFile);
        return ResponseEntity.ok(userService.changeUserAvatar(userId, dto));
    }
}


