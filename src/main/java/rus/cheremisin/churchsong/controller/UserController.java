package rus.cheremisin.churchsong.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.addUser(dto));
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
    
}
