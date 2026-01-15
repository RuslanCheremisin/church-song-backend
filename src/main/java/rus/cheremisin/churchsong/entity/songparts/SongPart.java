package rus.cheremisin.churchsong.entity.songparts;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.entity.Song;
import rus.cheremisin.churchsong.enums.MusicalInstrument;

@Entity
@Table(name = "song_parts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SongPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    MusicalInstrument musicalInstrument;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "audio_part_id")
    AudioPart audioPart;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "video-part_id")
    VideoPart videoPart;
    String text;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_part_id")
    ImagePart imagePart;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_part_id")
    DocPart docPart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    Song song;



}
