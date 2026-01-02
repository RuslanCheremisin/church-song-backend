package rus.cheremisin.churchsong.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import rus.cheremisin.churchsong.DTO.AvatarImageDTO;
import rus.cheremisin.churchsong.DTO.UserDTO;
import rus.cheremisin.churchsong.entity.Role;
import rus.cheremisin.churchsong.service.RoleService;
import rus.cheremisin.churchsong.service.UserService;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomOAuth2UserService
        implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public CustomOAuth2UserService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {


        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String firstName = "";
        String lastName = "";
        String email = "";
        String pictureLink = "";

        if (registrationId.equals("yandex")) {
            firstName = (String) attributes.get("first_name");
            lastName = (String) attributes.get("last_name");
            email = (String) attributes.get("default_email");
            pictureLink = (String) attributes.get("default_avatar_id");
        } else if (registrationId.equals("google")) {
            firstName = (String) attributes.get("given_name");
            lastName = (String) attributes.get("family_name");
            email = (String) attributes.get("email");
            pictureLink = (String) attributes.get("picture");
        }

        Set<Role> authorities = oAuth2User.getAuthorities().stream().map(ga -> roleService.getRoleByName(ga.getAuthority())).collect(Collectors.toSet());

        try {
            UserDTO user = userService.getUserByEmail(email);
        } catch (EntityNotFoundException e) {
            UserDTO newUser = new UserDTO(
                    null,
                    firstName,
                    lastName,
                    "please, enter phone",
                    "here can be your bio",
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new AvatarImageDTO(null, pictureLink),
                    email,
                    email,
                    authorities);
            userService.addOAuth2User(newUser);
        }


        return oAuth2User;
    }
}
