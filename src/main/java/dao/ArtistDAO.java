package dao;

import java.util.List;
import domain.Artist;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Luigi Brandolini
 */
@Repository
public interface ArtistDAO {
    
  public void delete(Long uid);
 
  public List<Artist> findAllArtists();
 
  public Artist findByUid(Long uid);
   
  public void save(Artist artst);
 
  public void update(Artist artist); 
  
  public boolean existArtist(Artist artist);
}
