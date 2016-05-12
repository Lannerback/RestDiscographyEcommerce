/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import domain.Role;
import domain.User;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author adriano
 */
@Service
public interface UserBO {
    
    public User findUserById(Integer id);
    
    public void save(User user);
        
    public void update(User user);
    
    public List<User> findAllUsers();
    
    public User getUserByUserName(String UserName);
    
    public List<Role> getRolesByUserName(String UserName);
    
    public void delete(User user);
}
