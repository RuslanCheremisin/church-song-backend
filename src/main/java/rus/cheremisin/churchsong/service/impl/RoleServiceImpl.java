package rus.cheremisin.churchsong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rus.cheremisin.churchsong.DAO.RoleDAO;
import rus.cheremisin.churchsong.entity.Role;
import rus.cheremisin.churchsong.service.RoleService;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleDAO.findAll());
    }

    @Override
    public Set<Role> getRolesByIds(Set<Long> roleIds) {
        return new HashSet<>(roleDAO.findAllById(roleIds));
    }

    @Override
    public void addRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.findRoleByName(name);
    }

}
