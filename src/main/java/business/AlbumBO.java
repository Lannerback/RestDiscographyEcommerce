package business;

import java.util.List;
import domain.Album;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luigi Brandolini
 */
@Service
public interface AlbumBO {
 
  @PreAuthorize ("hasRole('ROLE_ADMIN')") //se attivato solo chi è admin lo vedra e funge davvero
  public void delete(Integer uid);
   
  public List<Album> findAllAlbums();
   
  public void save(Album album);
 
  public void update(Album album); 
  
  public boolean existAlbum(Album album);
  
  //public boolean removeAlbumById(Integer id);
  
  public Album findByUid(Integer uid);
}
