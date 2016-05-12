/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityService;

import business.UserBO;
import domain.Role;
import domain.User;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserBO userBO;

    public CustomerUserDetailsService() {

    }

    public CustomerUserDetailsService(UserBO userBO) {
        this.userBO = userBO;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        UserDetails user;       
        user = userBO.getUserByUserName(userName);                        
        return user;
    }

}
