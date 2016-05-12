/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import business.AlbumBO;
import business.ArtistBO;
import static controller.admin.AlbumController.logger;
import domain.Album;
import domain.Artist;
import java.io.FileNotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.ClientResponse;
import util.FileManager;

/**
 *
 * @author adriano
 */
@Controller
@RequestMapping(value = "admin/artist")
public class ArtistController {

    @Autowired
    @Qualifier("albumBO")
    private AlbumBO albumBO;

    @Autowired
    @Qualifier("artistBO")
    private ArtistBO artistBO;

    @Autowired
    @Qualifier("fileManager")
    private FileManager fileManager;

    static final Logger logger = Logger.getLogger(AlbumController.class);

    @RequestMapping(value = {"list", ""})
    public List<Artist> ArtistList() {        
        return artistBO.findAllArtists();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public HttpStatus save(@Valid Artist artist) {

        try {
            if (artistBO.existArtist(artist)) {
                return HttpStatus.CONFLICT;
            }
            artistBO.save(artist);
        } catch (Exception e) {
            logger.error(e);
            return HttpStatus.BAD_GATEWAY;
        }
        return HttpStatus.OK;

    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public HttpStatus remove(
            @PathVariable(value = "id") Long removeartist) {

        try {
            Artist artist = artistBO.findByUid(removeartist);
            artistBO.delete(removeartist);
            String filePath = "/cover/" + artist.getName() + artist.getSurname();
            fileManager.deleteDirOrFile(filePath);            

        } catch (FileNotFoundException e) {
            logger.error(e);
            return HttpStatus.NOT_FOUND;
        } catch (Exception e) {
            logger.error(e);
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public HttpStatus update(Artist artist) {
        
        try {
            artistBO.update(artist);
        } catch (Exception e) {
            logger.error(e);
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
    }    

}
