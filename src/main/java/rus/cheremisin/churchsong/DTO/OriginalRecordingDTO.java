package rus.cheremisin.churchsong.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.model.songparts.AudioPart;
import rus.cheremisin.churchsong.model.songparts.VideoPart;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OriginalRecordingDTO {
    Long id;
    VideoPart videoPart;
    AudioPart audioPart;
}
