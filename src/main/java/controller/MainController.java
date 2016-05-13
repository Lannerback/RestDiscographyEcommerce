/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DTO.*;
import Exception.NotFoundException;
import business.AlbumBO;
import business.ArtistBO;
import business.RoleBO;
import business.UserBO;
import dao.impl.DaoBase;
import domain.Album;
import domain.Artist;
import domain.User;
import domain.Role;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import util.FileManager;

@RestController
public class MainController {

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
    @Qualifier(value = "FirstCD")
    Album firstcd;

    @Autowired
    @Qualifier(value = "FirstArtist")
    Artist artist;
    
    @Autowired
    @Qualifier("fileManager")
    private FileManager fileManager;
    
    
    @Autowired
    @Qualifier("daoBase")
    private DaoBase toDto;
    
    
    static final Logger logger = Logger.getLogger(MainController.class);

  

    /* PER CRIPTAZIONE
    byte[] bytesOfMessage = yourString.getBytes("UTF-8");

    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] thedigest = md.digest(bytesOfMessage);
    */
      
    
    @RequestMapping(value = "tomcatdir")
    public String getTomcatDir(HttpServletRequest request){          
          System.out.println("----------");
          System.out.println("---------- CrunchifyExample Servlet Initialized successfully ----------");
          System.out.println("----------");
 
          System.out.println("\nApp Deployed Directory path: " + request.getServletContext().getRealPath("/"));
          System.out.println("getContextPath(): " + request.getServletContext().getContextPath());
          System.out.println("Apache Tomcat Server: " + request.getServletContext().getServerInfo());
          System.out.println("Servlet API version: " + request.getServletContext().getMajorVersion() + "." +request.getServletContext().getMinorVersion());
          System.out.println("Tomcat Project Name: " + request.getServletContext().getServletContextName());
          return "index";
    
    }
    
    
    @RequestMapping(value = "initall")
    public String initall() {
        createimagedir();
        init();
        init2();
        init3();
        init4(1);
        init5(1);
        init6();        

        return "index";
    }

    @RequestMapping("createdir")
    public String createimagedir(){
        fileManager.makeDir("/cover");
        return "index";
    }       

    @RequestMapping(value = {"initusererole"})
    public String init6() {
        logger.debug("iniusererole");
        User user = new User("user", "password", true);
        try {
            Role role = new Role("ROLE_USER");
            roleBO.save(role);

            userBO.save(user);

            user = userBO.getUserByUserName(user.getUsername());
            List<Role> roles = user.getUserRoles();
            roles.add(roleBO.findByUid(2));
            user.setUserRoles(roles);

            userBO.update(user);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "errore save role: "
                    + e.getMessage());
            logger.error(e);
        }

        return "index";
    }      

    @RequestMapping(value = "initalbumartist")
    public String init() {
        logger.debug("init() invoked");
        List<Album> albums = new ArrayList<Album>();
        albums.add(firstcd);
        try {
            artist.setAlbums(albums);
            artistBO.save(artist);
        } catch (Exception e) {
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "errore inserimento: " + e.getMessage());
        }

        return "index";
    }

    @RequestMapping(value = "initrole")
    public String init2() {
        try {
            Role role = new Role("ROLE_ADMIN");
            roleBO.save(role);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "errore save role: "
                    + e.getMessage());
            logger.error(e);
        }

        return "index";
    }

    @RequestMapping(value = "initadmin")
    public String init3() {
        logger.debug("init2() invoked");
        try {
            User user = new User("admin", "password", true);
            userBO.save(user);
        } catch (Exception e) {
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "errore save user: "
                    + e.getMessage());
        }
        return "index";
    }

    @RequestMapping(value = "addorder/{userid}")
    public String init4(@PathVariable Integer userid) {
        try {
            User user = userBO.findUserById(userid);
            List<Album> albums = user.getOrderedAlbums();
            albums.add(albumBO.findByUid(Integer.parseInt("1")));
            user.setOrderedAlbums(albums);
            userBO.update(user);
        } catch (Exception e) {
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "errore save order: " + e.getMessage());
        }
        return "index";
    }

    @RequestMapping(value = "addrole/{userid}")
    public String init5(@PathVariable Integer userid) {
        try {
            User user = userBO.findUserById(userid);
            List<Role> roles = user.getUserRoles();
            roles.add(roleBO.findByUid(1));
            user.setUserRoles(roles);

            userBO.update(user);

        } catch (Exception e) {
            logger.error(e);
            javax.swing.JOptionPane.showMessageDialog(null, "errore save order: " + e.getMessage());
        }
        return "index";
    }
    
/*________________________________________________________________________________________________________________*/
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "showalbum/{id}")
    public ResponseEntity<AlbumDTO> show_album_detail(@PathVariable Integer id) {        
        try{
            Album album = albumBO.findByUid(id);                       
            return ResponseEntity.ok(toDto.getDTO(album));
        }catch(Exception e){            
            logger.error(e.getStackTrace());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "showartist/{id}")
    public ResponseEntity<ArtistDTO> show_artist_detail(@PathVariable Long id) {
        try{
            Artist artist;
            artist = artistBO.findByUid(id);
            return ResponseEntity.ok(toDto.getDTO(artist));
        }catch(Exception e){
            logger.error(e.getStackTrace());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }

}
