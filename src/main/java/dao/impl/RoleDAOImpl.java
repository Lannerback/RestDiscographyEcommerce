/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.RoleDAO;
import domain.Role;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RoleDAOImpl implements RoleDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void delete(Integer uid) {
        Role role = (Role) sessionFactory.getCurrentSession()
                .get(Role.class, uid);
        sessionFactory.getCurrentSession().delete(role);
    }

    @Override
    public void save(Role role) {  
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public void update(Role role) {
        sessionFactory.getCurrentSession().saveOrUpdate(role);
    }

    @Override
    public List<Role> findAllRoles() {
        List<Role> list = (List<Role>) sessionFactory
                .getCurrentSession()
                .createQuery("FROM Role").list();
        return list;
    }

    @Override
    public Role findByUid(Integer uid) {
        return (Role) sessionFactory.getCurrentSession()
                .get(Role.class, uid);
    }

    @Override
    public boolean existRole(Role role){
        for(Role a : findAllRoles()){
            if(a.equals(role))
                return true;
        }            
        return false; 
    }
}
