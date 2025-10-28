package rus.cheremisin.churchsong.DTO.songparts;

import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.entity.songparts.AudioPart;
import rus.cheremisin.churchsong.entity.songparts.DocPart;
import rus.cheremisin.churchsong.entity.songparts.ImagePart;
import rus.cheremisin.churchsong.entity.songparts.VideoPart;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FullSongPartDTO {
    Long id;
    AudioPart audioPart;
    VideoPart videoPart;
    String text;
    ImagePart imagePart;
    DocPart docPart;
}
