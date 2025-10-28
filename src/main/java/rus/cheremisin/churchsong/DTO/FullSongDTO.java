package rus.cheremisin.churchsong.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.entity.OriginalRecording;
import rus.cheremisin.churchsong.entity.songparts.SongPart;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FullSongDTO {
    Long id;
    String name;
    Integer bpm;
    String songKey;
    OriginalRecording origRec;
    List<SongPart> songParts;
}
