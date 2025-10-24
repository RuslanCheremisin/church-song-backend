package rus.cheremisin.churchsong.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.model.OriginalRecording;
import rus.cheremisin.churchsong.model.songparts.SongPart;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SongDTO {
    Long id;
    String name;
    Integer bpm;
    String songKey;
    OriginalRecording origRec;
    List<SongPart> songParts;
}
