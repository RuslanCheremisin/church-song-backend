package rus.cheremisin.churchsong.service;

import rus.cheremisin.churchsong.entity.Role;

import java.util.Set;

public interface RoleService {
    void addRole(Role role);

    Role getRoleByName(String name);

    Set<Role> getAllRoles();

    Set<Role> getRolesByIds(Set<Long> roleIds);
}
