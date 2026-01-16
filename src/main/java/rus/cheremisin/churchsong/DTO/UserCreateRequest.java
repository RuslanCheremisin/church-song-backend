package rus.cheremisin.churchsong.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;
import rus.cheremisin.churchsong.entity.AvatarImage;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String firstName;
    String lastName;
    String email;
    String username;
    String password;
    MultipartFile photoFile;
}
