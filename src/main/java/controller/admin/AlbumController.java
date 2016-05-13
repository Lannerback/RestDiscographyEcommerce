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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import util.ClientResponse;
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
