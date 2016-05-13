/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import business.*;

import dao.impl.DaoBase;
import domain.User;
import DTO.UserDTO;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @Autowired
    @Qualifier("daoBase")
    private DaoBase toDto;
    
    static final Logger logger = Logger.getLogger(UserMainController.class);

    
    @RequestMapping({"","home"})
    public String home(){
        return "user/home";
    }
    
    @RequestMapping("getUser")
    public UserDTO getUser(User user){
        return toDto.getDTO(user);
    }
    
    @RequestMapping(value = "changed", method = RequestMethod.POST)
    public HttpStatus userchanged(@Valid @RequestBody User user,User activeUser){       
        
        try{    
            user.setOrderedAlbums(activeUser.getOrderedAlbums());
            user.setUserRoles(activeUser.getUserRoles());
            user.setEnabled(activeUser.isEnabled());
            user.setUser_id(activeUser.getUser_id());
            userBO.update(user);
            return HttpStatus.OK;
        }catch(DataIntegrityViolationException e){                                   
            logger.error("username already exist: " + e);
            return HttpStatus.CONFLICT;
        }        
    }
    
}
