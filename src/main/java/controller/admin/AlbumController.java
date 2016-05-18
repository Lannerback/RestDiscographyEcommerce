/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import DTO.AlbumDTO;
import business.AlbumBO;
import business.ArtistBO;
import dao.impl.DaoBase;
import domain.Album;
import domain.Artist;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import util.FileManager;

@RestController
@RequestMapping(value = "admin/album")
public class AlbumController {

    @Autowired
    @Qualifier("albumBO")
    private AlbumBO albumBO;

    @Autowired
    @Qualifier("artistBO")
    private ArtistBO artistBO;

    @Autowired
    @Qualifier("fileManager")
    private FileManager fileManager;
    
    @Autowired
    @Qualifier("daoBase")
    private DaoBase toDto;

    static final Logger logger = Logger.getLogger(AlbumController.class);


    @RequestMapping(value = {"", "list"}) 
    //@CrossOrigin(origins = "http://localhost")
    public ResponseEntity<List<AlbumDTO>> AlbumList() {        

        try {
            List<AlbumDTO> albums = new ArrayList<>(); 
            
            for(Album album : albumBO.findAllAlbums()){
                albums.add(toDto.getDTO(album));
            }                     
            return ResponseEntity.ok(albums);
        } catch (Exception e) {            
            logger.error(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);            
        }        
    }    

    @RequestMapping(value = "getAlbum/{id}")
    public ResponseEntity<AlbumDTO> getAlbum(@PathVariable Integer id) {        
        try{
            Album album = albumBO.findByUid(id);          
            
            if(album == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
             
            return ResponseEntity.ok(toDto.getDTO(album));
        }catch(Exception e){            
            logger.error(e.getStackTrace());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public HttpStatus save(@Valid @RequestBody Album album, @RequestParam(value = "file") MultipartFile imagefile) {
        

        if (albumBO.existAlbum(album)) {
            return HttpStatus.CONFLICT;
        }   
       
        if (imagefile != null) {

            String encoded = null;
            try {
                encoded = Base64.getEncoder().encodeToString(imagefile.getBytes());
                album.setImagefile(imagefile.getBytes());
            } catch (IOException io) {
                logger.error(io);
            }
            album.setImagebase64("data:image/jpeg;base64," + encoded);
        }

        if (imagefile != null) {
            Artist artist = artistBO.findByUid(album.getArtist().getUid());
            String subPath = "/cover/" + artist.getName() + artist.getSurname() + "/" + album.getTitle() + "/";
            try {
                fileManager.makeDir(subPath);
                Long imagePath = new Date().getTime();
                fileManager.saveFile(imagefile, subPath + imagePath);
                album.setImagepath(artist.getName() + artist.getSurname() + "/" + album.getTitle() + "/" + imagePath);
                albumBO.save(album); 
            } catch (FileNotFoundException e) {
                logger.error(e);
                return HttpStatus.NOT_FOUND;
            } catch (IOException e) {
                logger.error(e);
                return HttpStatus.BAD_REQUEST;
            }
        }
        
        return HttpStatus.OK;
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public HttpStatus remove(@PathVariable("id") Integer removecd) {

        try {            
            Album album = albumBO.findByUid(removecd);
            albumBO.delete(removecd);
            Artist artist = artistBO.findByUid(album.getArtist().getUid());
            String filePath = "/cover/" + artist.getName() + artist.getSurname() + "/" + album.getTitle();           
            fileManager.deleteDirOrFile(filePath);
            
        }catch(FileNotFoundException e){
            logger.error(e);
            return HttpStatus.NOT_FOUND;
        } 
        catch (Exception e) {
            logger.error(e);  
            return HttpStatus.BAD_REQUEST;
        }
        
        return HttpStatus.OK;
    }

    @RequestMapping(value = "saveupdate", method = RequestMethod.PUT)
    public HttpStatus update(
            @Valid @RequestBody Album cd) {

        try {
            albumBO.update(cd);
        } catch (Exception e) {
            logger.error(e);
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
    
}
