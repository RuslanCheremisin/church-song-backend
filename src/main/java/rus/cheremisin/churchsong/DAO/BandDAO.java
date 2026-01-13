package rus.cheremisin.churchsong.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rus.cheremisin.churchsong.entity.Band;
import rus.cheremisin.churchsong.entity.User;

import java.util.List;

@Repository
public interface BandDAO extends JpaRepository<Band, Long> {

    List<Band> findAllByMembersContaining(List<User> members);

    double members(List<User> members);
}
