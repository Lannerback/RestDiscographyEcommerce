package business.impl;

import java.util.List;
import business.ArtistBO;
import dao.ArtistDAO;
import domain.Artist;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import util.FileManager;

/**
 *
 * @author Luigi Brandolini
 */
@Service
@Transactional
public class ArtistBOImpl implements ArtistBO {
    
    @Autowired
    private ArtistDAO artistDao;   
    
    @Autowired
    private FileManager fileManager;
    
    static final Logger logger = Logger.getLogger(ArtistBOImpl.class);

    public void save(Artist artist) {        
        try {            
            artistDao.save(artist);
        } catch (DataAccessException e) {
            logger.error(e);
            throw e;
        }        
        String subPath = "/cover/" + artist.getName() + artist.getSurname();
        try{
            fileManager.makeDir(subPath);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, "cannot create artist dir: " + e.getMessage());
        }
    }

    public void update(Artist artist)   {
        try {
            artistDao.update(artist);
        } catch (DataAccessException e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public void delete(Long uid)   {
        try {
            artistDao.delete(uid);
        } catch (DataAccessException e) {
            logger.error(e);
            throw e;
        }
    }

    public List<Artist> findAllArtists()  {
        try {
            return artistDao.findAllArtists();
        } catch (DataAccessException e) {
            logger.error(e);
            throw e;
        }
    }

    public Artist findByUid(Long uid)  {
        try {
            return artistDao.findByUid(uid);
        } catch (DataAccessException e) {
            logger.error(e);
            throw e;
        }
    }
    
    public boolean existArtist(Artist artist){
        try{
            return artistDao.existArtist(artist);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }
        

 

   
}
