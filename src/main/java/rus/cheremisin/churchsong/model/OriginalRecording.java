package rus.cheremisin.churchsong.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.model.songparts.AudioPart;
import rus.cheremisin.churchsong.model.songparts.VideoPart;

@Entity
@Table(name = "orig_recordings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OriginalRecording {
    @Id
    Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "video_part_id")
    VideoPart videoPart;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "audio_part_id")
    AudioPart audioPart;
}
