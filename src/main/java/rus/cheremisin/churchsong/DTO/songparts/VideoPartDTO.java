package rus.cheremisin.churchsong.DTO.songparts;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoPartDTO {
    Long id;
    File file;
}
