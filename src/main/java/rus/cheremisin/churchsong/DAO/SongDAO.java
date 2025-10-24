package rus.cheremisin.churchsong.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rus.cheremisin.churchsong.DTO.SongDTO;
import rus.cheremisin.churchsong.entity.Song;

@Repository
public interface SongDAO extends JpaRepository<Song, Long> {
    SongDTO removeById(Long id);
}
