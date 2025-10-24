package rus.cheremisin.churchsong.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "avatar_images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AvatarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String link;
}
