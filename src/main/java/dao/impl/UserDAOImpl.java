/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.UserDAO;
import domain.Role;
import domain.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adriano
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public User findUserById(Integer id) {        
        return (User) sessionFactory.getCurrentSession()
                .get(User.class, id);
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
   
    @Override
    public void update(User user) {        
        sessionFactory.getCurrentSession().update(user);
    }
    @Override
    public List<User> findAllUsers() {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("FROM user");
        
    }

    @Override
    public User getUserByUserName(String UserName) {
        
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("FROM User c where c.username = :username");
        query.setParameter("username", UserName);
        return (User) query.uniqueResult();
    }

    @Override
    public List<Role> getRolesByUserName(String UserName) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("select r from User u join u.userRoles r where u.username = :username");
        query.setParameter("username", UserName);
        return (List<Role>) query.list();
    }
    
    @Override
    public void delete(User user){
        sessionFactory.getCurrentSession().delete(user);
    }

    
    
    
    
}
