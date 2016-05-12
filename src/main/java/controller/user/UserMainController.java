/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import business.AlbumBO;
import business.ArtistBO;
import business.RoleBO;
import business.UserBO;
import domain.User;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import util.ClientResponse;

/**
 *
 * @author adriano
 */
@RestController
@RequestMapping("user")
public class UserMainController {
    @Autowired
    @Qualifier("albumBO")
    private AlbumBO albumBO;

    @Autowired
    @Qualifier("artistBO")
    private ArtistBO artistBO;
    
    @Autowired
    @Qualifier("userBO")
    private UserBO userBO;
    
    @Autowired
    @Qualifier("roleBO")
    private RoleBO roleBO;
    
    static final Logger logger = Logger.getLogger(UserMainController.class);

    
    @RequestMapping({"","home"})
    public String home(){
        return "user/home";
    }
    
    @RequestMapping("userprofile")
    public ModelAndView userprofile(ModelAndView mav,@AuthenticationPrincipal User user){
        mav.setViewName("user/userprofile");        
        mav.addObject("userBean",user);
        return mav;
    }
    
    @RequestMapping(value = "changed", method = RequestMethod.POST)
    public ModelAndView userchanged(@Valid @ModelAttribute("userBean") User user,BindingResult bindingResult,ModelAndView mav
    ,@AuthenticationPrincipal User activeUser){
       
        if(bindingResult.hasErrors()){
            javax.swing.JOptionPane.showMessageDialog(null, bindingResult.getAllErrors());
            mav.setViewName("user/userprofile");
            return mav;
        }
        
        try{    
            user.setOrderedAlbums(activeUser.getOrderedAlbums());
            user.setUserRoles(activeUser.getUserRoles());
            user.setEnabled(activeUser.isEnabled());
            user.setUser_id(activeUser.getUser_id());
            userBO.update(user);
            mav.addObject("response",new ClientResponse(true,"Operation successful"));
            mav.setViewName("user/home");
        }catch(DataIntegrityViolationException e){                                   
            bindingResult.rejectValue("username", "AlreadyExists.user.username");
            mav.setViewName("user/userprofile");     
            mav.addObject("response",new ClientResponse(false,"Operation failed"));
            return mav;
        }
        return mav;
    }
    
}
