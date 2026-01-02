package rus.cheremisin.churchsong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rus.cheremisin.churchsong.DTO.UserCreateRequest;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/local/register")
    public ResponseEntity<UserDTO> localRegister(
//            @RequestBody
            UserCreateRequest request) {
        return ResponseEntity.ok(userService.addUser(request));
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails, @AuthenticationPrincipal OAuth2User oAuth2User) {
        UserDTO userDTO = new UserDTO(-1L, null, null, null, null, null, null, null, null, null, null, null);;
        if (oAuth2User != null) {
            if (oAuth2User.getAttribute("email") != null) {
                userDTO = userService.getUserByEmail(oAuth2User.getAttribute("email"));
            } else if (oAuth2User.getAttribute("default_email") != null) {
                userDTO = userService.getUserByEmail(oAuth2User.getAttribute("default_email"));
            }
        } else if (userDetails != null) {
            userDTO = userService.getUserByUsername(userDetails.getUsername());
        }

        return ResponseEntity.ok(userDTO);
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
