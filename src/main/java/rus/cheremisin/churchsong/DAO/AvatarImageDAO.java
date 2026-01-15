package rus.cheremisin.churchsong.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import rus.cheremisin.churchsong.entity.AvatarImage;

public interface AvatarImageDAO extends JpaRepository <AvatarImage, Long> {
}
