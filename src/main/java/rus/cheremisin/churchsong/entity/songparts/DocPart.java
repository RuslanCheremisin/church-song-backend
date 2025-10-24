package rus.cheremisin.churchsong.entity.songparts;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "doc_parts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String link;

}
