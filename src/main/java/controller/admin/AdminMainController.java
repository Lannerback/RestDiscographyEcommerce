
package controller.admin;

import business.AlbumBO;
import business.ArtistBO;
import business.UserBO;
import domain.Role;
import domain.User;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author adriano
 */
@RestController
@RequestMapping("/admin")
public class AdminMainController {
private final static Logger logger = Logger.getLogger(AdminMainController.class);   
    
      
    @Autowired
    @Qualifier("albumBO")
    private AlbumBO albumBO;

    @Autowired
    @Qualifier("artistBO")
    private ArtistBO artistBO;
    
    @Autowired
    @Qualifier("userBO")
    private UserBO userBO;
    
   
    
    @RequestMapping(value = "user/{id}")
    public User getUser(@PathVariable Integer id){
        try{
            User user = userBO.findUserById(id);
            javax.swing.JOptionPane.showMessageDialog(null, user);
            return user;
        }catch(Exception e){
            logger.error(e.getStackTrace());
        }
        
        return null;
        
                
    }
        
    
    
    
    
   
    
}


