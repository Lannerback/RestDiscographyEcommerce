package business.impl;

import java.util.List;
import business.AlbumBO;
import dao.AlbumDAO;
import domain.Album;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Luigi Brandolini
 */
@Service
@Transactional
public class AlbumBOImpl implements AlbumBO {
    
    @Autowired
    private AlbumDAO albumDao;
    
    static final Logger logger = Logger.getLogger(AlbumBOImpl.class);
    
    @Override
    public void save(Album user){
        try {
         albumDao.save(user);
        }catch(DataAccessException e) {           
            logger.error(e);
            throw e;
        }
    }

    @Override
    public void update(Album user) {
        try {
         albumDao.update(user);
        }catch(DataAccessException e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public void delete(Integer uid) {
       try {
         albumDao.delete(uid);
        }catch(DataAccessException e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public List<Album> findAllAlbums() {
        try {
         return albumDao.findAllAlbums();
        }catch(DataAccessException e) {
            logger.error(e);
            throw e;
        }
    } 
    
    @Override
    public boolean existAlbum(Album album){
        try{
            return albumDao.existAlbum(album);
        }catch(Exception e){
            logger.error(e);
            throw e;
        }
    }
    
    /*@Override
    public boolean removeAlbumById(Integer id){
        try{
            return albumDao.removeAlbumById(id);
        }catch(Exception e){
            logger.error(e);
        }
        return false;
    }*/
    
    @Override
    public Album findByUid(Integer uid){
        try{
            return albumDao.findByUid(uid);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }    
       
}
