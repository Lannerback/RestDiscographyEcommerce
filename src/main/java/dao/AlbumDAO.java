package dao;

import java.util.List;
import domain.Album;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Luigi Brandolini
 */
@Repository
public interface AlbumDAO {
    
    
    public List<Album> findAllAlbums();

    public void save(Album album);

    public void update(Album album);

    public void delete(Integer uid);

    public boolean existAlbum(Album album) ;

    //public boolean removeAlbumById(Integer id);

    public Album findByUid(Integer uid);
}
