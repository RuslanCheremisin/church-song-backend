package rus.cheremisin.churchsong.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.entity.AvatarImage;
import rus.cheremisin.churchsong.entity.Band;
import rus.cheremisin.churchsong.entity.Song;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String bio;
    List<String> instruments;
    boolean isLeader;
    List<Song> favoriteSongs;
    List<Band> bands;
    AvatarImage userAvatar;
}
