package rus.cheremisin.churchsong.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.entity.AvatarImage;

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
    UserMemberDTO leader;
    String email;
    String contactPhone;
    AvatarImage bandAvatar;
    String bio;
    List<UserMemberDTO> members;
    List<SimpleSongDTO> songs;
}
