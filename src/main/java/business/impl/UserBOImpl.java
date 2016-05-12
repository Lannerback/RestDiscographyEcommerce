/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.impl;

import business.UserBO;
import dao.UserDAO;
import domain.Role;
import domain.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author adriano
 */
@Service
@Transactional
public class UserBOImpl implements UserBO{
    
    @Autowired
    private UserDAO userDao;
    
    static final Logger logger = Logger.getLogger(ArtistBOImpl.class);

    @Override
    public User findUserById(Integer id) {
        try {
            return userDao.findUserById(id);
        } catch (DataAccessException e) {
            logger.error(e);
            throw e;
        }        
    }

    @Override
    public void save(User user) {
        try {
            userDao.save(user);
        } catch (DataAccessException e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public void update(User user) {
        try{
            userDao.update(user);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }

    @Override
    public List<User> findAllUsers() {
        try{
            return userDao.findAllUsers();
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }

    @Override
    public User getUserByUserName(String UserName) {
        User user;
        try{
            user = userDao.getUserByUserName(UserName);
            if(user==null){
                throw new UsernameNotFoundException("Error: User Not Found");
            }
            
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
        return user;        
    }

    @Override
    public List<Role> getRolesByUserName(String UserName) {
        try{
            return userDao.getRolesByUserName(UserName);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }
    
    @Override
    public void delete(User user){
        try{
            userDao.delete(user);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }
    
 

    
    
}
