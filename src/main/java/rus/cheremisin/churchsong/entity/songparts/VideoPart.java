package rus.cheremisin.churchsong.entity.songparts;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "video_parts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
//    Byte[] videoData;
    String link;

}
