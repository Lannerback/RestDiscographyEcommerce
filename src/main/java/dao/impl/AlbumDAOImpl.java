package dao.impl;

import java.util.List;
import domain.Album;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import dao.AlbumDAO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

/**
 *
 * @author Luigi Brandolini
 */
@Repository
public class AlbumDAOImpl implements AlbumDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void delete(Integer uid) {        
        Album album = (Album) sessionFactory.getCurrentSession()
                .get(Album.class, uid);
        sessionFactory.getCurrentSession().delete(album);       
        
    }

    public List<Album> findAllAlbums()  {
        List<Album> list = (List<Album>) sessionFactory
                .getCurrentSession()
                .createQuery("FROM Album").list();

        return list;
    }

    @Override
    public void save(Album album) {
        sessionFactory.getCurrentSession().save(album);
    }

    @Override
    public void update(Album album) {
        sessionFactory.getCurrentSession().saveOrUpdate(album);

    }
   
    @Override
    public boolean existAlbum(Album album)  {
        for(Album a : findAllAlbums()){
            if(a.equals(album))
                return true;
        }            
        return false;    
    }
    
    @Override
    public Album findByUid(Integer uid) {
        return (Album) sessionFactory.getCurrentSession()
                .get(Album.class, uid);
    }
                    
    
    // POTREBBE ESSERE UTILE PER IL PERSISTENCEINTSANCE, PROVARE A ELIMINARE FIRSTCD SENZA DOVERLO PRIMA
    //RIPRENDERE DAL DB, IN MODO CHE SIA SENZA ID    
    /*@Override
    public boolean removeAlbumById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Object persistentInstance = session.load(Album.class, id);//CREA UNA PERSISTENCEINSTANCE COSI ELIMINA
            // ANCHE GLI ASSOCIATI (IN QUESTO CASO INUTILE PERO)
            if (persistentInstance != null) {
                session.delete(persistentInstance);
                return true;
            }
            return false;
        }catch (HibernateException e) {            
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    */
    
    
   
    
            

    
}
