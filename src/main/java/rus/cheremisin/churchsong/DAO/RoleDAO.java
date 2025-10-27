package rus.cheremisin.churchsong.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rus.cheremisin.churchsong.entity.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);
}
