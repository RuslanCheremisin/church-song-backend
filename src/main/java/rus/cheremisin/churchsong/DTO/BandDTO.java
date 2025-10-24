package rus.cheremisin.churchsong.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.entity.AvatarImage;
import rus.cheremisin.churchsong.entity.Song;
import rus.cheremisin.churchsong.entity.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BandDTO {
    Long id;
    String name;
    User leader;
    String email;
    String contactPhone;
    AvatarImage bandAvatar;
    String bio;
    List<User> members;
    List<Song> songs;
}
