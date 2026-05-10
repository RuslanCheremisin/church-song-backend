package rus.cheremisin.churchsong.DTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
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
    OriginalRecordingDTO origRec;
    List<SongPart> songParts;
    BandDTO band;
}
