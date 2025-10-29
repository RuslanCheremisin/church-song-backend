package rus.cheremisin.churchsong.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rus.cheremisin.churchsong.entity.Band;

@Repository
public interface BandDAO extends JpaRepository<Band, Long> {

}
