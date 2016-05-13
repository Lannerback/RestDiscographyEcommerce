
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @Autowired
    @Qualifier("daoBase")
    private DaoBase toDto;
                                         
}


