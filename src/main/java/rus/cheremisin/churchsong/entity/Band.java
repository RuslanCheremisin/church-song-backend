package rus.cheremisin.churchsong.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import rus.cheremisin.churchsong.exceptions.UserIsAlreadyInTheBandException;
import rus.cheremisin.churchsong.exceptions.UserIsNotInTheBandException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    @ManyToMany
    @JoinTable(
            name = "user_bands",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> members;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id")
    List<Song> songs;

    public void addMember(User newMember) {
        boolean memberOfTheBand = members.stream().anyMatch(u -> u.getId().equals(newMember.getId()));
        if (this.members == null || newMember == null || memberOfTheBand) {
            throw new UserIsAlreadyInTheBandException("user is already in the band");
        }
        members.add(newMember);
    }
    public void removeMember(Long memberId) {
        if (this.members == null || memberId == null) {
            throw new UserIsNotInTheBandException("user is not in the band");
        }
        User memberToRemove = members.stream().filter( u -> u.getId().equals(memberId)).findFirst().orElseThrow(NoSuchElementException::new);
        members.remove(memberToRemove);
    }
    public void addSong(Song newSong) {
        if (this.songs == null || newSong == null || songs.contains(newSong)) {
            return;
        }
        songs.add(newSong);
    }
    public void removeSong(Song songToRemove) {
        if (this.songs == null || songToRemove == null || !songs.contains(songToRemove)) {
            return;
        }
        songs.remove(songToRemove);
    }
}
