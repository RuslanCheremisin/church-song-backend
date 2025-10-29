package rus.cheremisin.churchsong.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bands")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    User leader;
    String email;
    String contactPhone;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_image_id")
    AvatarImage bandAvatar;
    String bio;
    @ManyToMany(mappedBy = "bands")
    List<User> members;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id")
    List<Song> songs;

    public void addMember(User newMember) {
        if (this.members == null) {
            this.members = new ArrayList<>();
        }
        if (newMember != null && !members.contains(newMember)) {
            members.add(newMember);
        }
    }
    public void removeMember(User newMember) {
        if (this.members == null || newMember == null || !members.contains(newMember)) {
            return;
        }
        members.remove(newMember);
    }
}
