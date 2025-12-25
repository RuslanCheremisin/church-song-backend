package rus.cheremisin.churchsong.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.entity.AvatarImage;
import rus.cheremisin.churchsong.entity.Role;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;
    String firstName;
    String lastName;
    String phone;
    String bio;
    List<String> instruments;
    List<SimpleSongDTO> favoriteSongs;
    List<SimpleBandDTO> bands;
    AvatarImageDTO userAvatar;
    String email;
    String username;
    Set<Role> roles;
}
