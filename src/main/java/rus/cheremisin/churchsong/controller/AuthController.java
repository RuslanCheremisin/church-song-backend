package rus.cheremisin.churchsong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rus.cheremisin.churchsong.DTO.UserCreateRequest;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/local/login")
    public ResponseEntity localLogin() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/local/register")
    public ResponseEntity<UserDTO> localRegister(@RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userService.addUser(request));
    }

    @PostMapping("/local/verify-mail")
    public ResponseEntity localVerifyEmail() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/local/reset-password")
    public ResponseEntity localResetPassword() {
        return ResponseEntity.ok().build();
    }
}
