package rus.cheremisin.churchsong.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rus.cheremisin.churchsong.model.church.ChurchLocation;

@Repository
public interface ChurchLocationDAO extends JpaRepository<ChurchLocation, Long> {

}
