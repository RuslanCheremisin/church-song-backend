package rus.cheremisin.churchsong.DTO.songparts;

import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.model.songparts.AudioPart;
import rus.cheremisin.churchsong.model.songparts.DocPart;
import rus.cheremisin.churchsong.model.songparts.ImagePart;
import rus.cheremisin.churchsong.model.songparts.VideoPart;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SongPartDTO {
    Long id;
    VideoPart videoPart;
    AudioPart audioPart;
    String text;
    ImagePart imagePart;
    DocPart docPart;
}
