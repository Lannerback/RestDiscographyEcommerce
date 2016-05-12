package business;

import java.util.List;
import domain.Artist;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luigi Brandolini
 */
@Service
public interface ArtistBO {
  public void delete(Long uid);
 
  public List<Artist> findAllArtists();
 
  public Artist findByUid(Long uid);
   
  public void save(Artist artist);
 
  public void update(Artist artist);  
  
  public boolean existArtist(Artist artist);
}
