/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.impl;

import business.RoleBO;
import dao.RoleDAO;
import domain.Role;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author adriano
 */
@Service
@Transactional
public class RoleBOImpl implements RoleBO {

    @Autowired
    private RoleDAO roleDao;
    
    static final Logger logger = Logger.getLogger(RoleBOImpl.class);

    
    @Override
    public void delete(Integer uid) {
        try{
            roleDao.delete(uid);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }

    @Override
    public List<Role> findAllRoles() {
        try{
            return roleDao.findAllRoles();
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }        
    }

    @Override
    public Role findByUid(Integer uid) {
        try{
            return roleDao.findByUid(uid);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }

    @Override
    public void save(Role role) {
        try{
            roleDao.save(role);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }

    @Override
    public void update(Role role) {
        try{
            roleDao.update(role);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }

    @Override
    public boolean existRole(Role role) {
        try{
            return roleDao.existRole(role);
        }catch(DataAccessException e){
            logger.error(e);
            throw e;
        }
    }
    
}
