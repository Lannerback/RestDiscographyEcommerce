
package controller.admin;

import DTO.*;
import business.AlbumBO;
import business.ArtistBO;
import business.UserBO;
import dao.impl.DaoBase;
import domain.*;
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
    public UserDTO getUser(@PathVariable Integer id){
        try{
            javax.swing.JOptionPane.showMessageDialog(null, "sfasfsd");
            User user = userBO.findUserById(id);
            DaoBase toDto = new DaoBase();
            javax.swing.JOptionPane.showMessageDialog(null, toDto.getDTO(user));                        
            return toDto.getDTO(user);
        }catch(Exception e){
            logger.error(e.getStackTrace());
        }
        
        return null;
        
                
    }
        
    
    
    
    
   
    
}


