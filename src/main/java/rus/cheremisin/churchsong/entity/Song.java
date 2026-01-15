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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Integer bpm;
    String songKey;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orig_recording_id")
    OriginalRecording origRec;
    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SongPart> songParts;
    @ManyToOne
    Band band;

}
