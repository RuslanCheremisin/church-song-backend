package rus.cheremisin.churchsong.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rus.cheremisin.churchsong.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
}
