package rus.cheremisin.churchsong.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.DTO.UserCreateRequest;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/local/register")
    public ResponseEntity<UserDTO> registerUser(@RequestParam(value = "firstName") String firstName,
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

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails, @AuthenticationPrincipal OAuth2User oAuth2User) {
        UserDTO userDTO = new UserDTO(-1L, null, null, null, null, null, null, null, null, null, null, null);;
        if (oAuth2User != null) {
            if (oAuth2User.getAttribute("email") != null) { //google
                userDTO = userService.getUserByEmail(oAuth2User.getAttribute("email"));
            } else if (oAuth2User.getAttribute("default_email") != null) { //yandex
                userDTO = userService.getUserByEmail(oAuth2User.getAttribute("default_email"));
            }
        } else if (userDetails != null) {
            userDTO = userService.getUserByUsername(userDetails.getUsername());
        }

        return ResponseEntity.ok(userDTO);
    }

//    @PostMapping("/telegram")
//    public ResponseEntity<Void> telegramLogin(
////            @RequestBody
//            TelegramAuthRequest request, HttpServletRequest httpServletRequest) {
//        telegramAuthService.authenticate(request);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/local/verify-mail")
    public ResponseEntity localVerifyEmail() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/local/reset-password")
    public ResponseEntity localResetPassword() {
        return ResponseEntity.ok().build();
    }


}
