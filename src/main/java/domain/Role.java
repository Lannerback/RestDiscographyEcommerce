/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "role",
        uniqueConstraints = {
    @UniqueConstraint(columnNames = {"role"})
})
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;
    
    @ManyToMany(mappedBy = "userRoles") 
    @Cascade({CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,
        CascadeType.SAVE_UPDATE})
    private List<User> users=new ArrayList<User>();
    
    @Column(name="role",nullable = false)    
    private String role;

    public Role() {
    }

    public Role(List<User> users, String role) {
        this.users = users;
        this.role = role;
    }
    
    public Role(String role){
        this.role=role;
    }
 
    
    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (role_id != null) ? role_id.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.role_id == null && other.role_id != null) || 
                (this.role_id != null && !this.role_id.equals(other.role_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User[ id=" + role_id + " ]";
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}