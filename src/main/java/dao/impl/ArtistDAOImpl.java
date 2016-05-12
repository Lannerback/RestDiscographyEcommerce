package dao.impl;

import java.util.List;
import domain.Artist;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import dao.ArtistDAO;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Luigi Brandolini
 */
@Repository
public class ArtistDAOImpl implements ArtistDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void delete(Long uid) {
        Artist artist = (Artist) sessionFactory.getCurrentSession()
                .get(Artist.class, uid);
        sessionFactory.getCurrentSession().delete(artist);
    }

    @Override
    public void save(Artist artist) {
        sessionFactory.getCurrentSession().save(artist);

    }

    @Override
    public void update(Artist artist) {
        sessionFactory.getCurrentSession().saveOrUpdate(artist);
    }

    @Override
    public List<Artist> findAllArtists() {
        List<Artist> list = (List<Artist>) sessionFactory
                .getCurrentSession()
                .createQuery("FROM Artist").list();

        return list;
    }

    @Override
    public Artist findByUid(Long uid) {
        return (Artist) sessionFactory.getCurrentSession()
                .get(Artist.class, uid);
    }

    @Override
    public boolean existArtist(Artist artist)  {
        for(Artist a : findAllArtists()){
            if(a.equals(artist))
                return true;
        }            
        return false; 
    }

   /* public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
   */
    
}
