package rus.cheremisin.churchsong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    String phone;
    String bio;
    List<String> instruments;
    @ManyToMany
    @JoinTable(
            name = "user_favorite_songs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    List<Song> favoriteSongs;
    @ManyToMany()
    @JoinTable(
            name = "user_bands",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id"))
    @JsonIgnore
    List<Band> bands;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_image_id")
    AvatarImage userAvatar;

    String email;

    String username;
    String password;
    Set<Role> roles;

    Timestamp createdAt;
    Timestamp updatedAt;

    public User(Long id,
                String firstName,
                String lastName,
                String phone,
                String bio,
                List<String> instruments,
                List<Song> favoriteSongs,
                List<Band> bands,
                AvatarImage userAvatar,
                String email,
                String username,
                String password,
                Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.bio = bio;
        this.instruments = instruments;
        this.favoriteSongs = favoriteSongs;
        this.bands = bands;
        this.userAvatar = userAvatar;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void addBand(Band newBand) {
        if (bands == null) {
            bands = new ArrayList<>();
        }
        if (newBand != null && !bands.contains(newBand)) {
            this.bands.add(newBand);
        }
    }
    public void removeBand(Band bandToRemove) {
        if (bands == null || bandToRemove == null) {
            return;
        }
        if (bands.contains(bandToRemove)) {
            this.bands.remove(bandToRemove);
        }
    }
    public void addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        if (role != null && !roles.contains(role)) {
            this.roles.add(role);
        }
    }
}
