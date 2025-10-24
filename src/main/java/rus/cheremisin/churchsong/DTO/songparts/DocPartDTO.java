package rus.cheremisin.churchsong.DTO.songparts;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocPartDTO {
    Long id;
    String link;
}
