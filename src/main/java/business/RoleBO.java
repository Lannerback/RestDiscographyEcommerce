/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Role;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author adriano
 */
 @Service
public interface RoleBO {
    public void delete(Integer uid);

    public List<Role> findAllRoles();

    public Role findByUid(Integer uid);

    public void save(Role role);

    public void update(Role role); 

    public boolean existRole(Role role);
}
