package rus.cheremisin.churchsong.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.entity.songparts.SongPart;

import java.util.List;

@Entity
@Table(name = "songs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Song {
    @Id
    Long id;
    String name;
    Integer bpm;
    String songKey;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orig_record_id")
    OriginalRecording origRec;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    List<SongPart> songParts;

}
