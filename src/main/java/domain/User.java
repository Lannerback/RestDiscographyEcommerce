/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity
@Table(name = "user")
public class User implements UserDetails {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
     
    
    @Pattern(regexp="\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name="email", unique=true,nullable=true)
    private String email;
    
    @NotNull
    @Column(name="registration_date",nullable=false)
    private Date registration_date= Calendar.getInstance().getTime();  
    
    /*
    altrimenti facendola generare (forse) da sql, non so se portabile per ogni database
    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();*/
    
    @NotBlank    
    @Column(name="username",nullable = false,unique=true)    
    private String username;
    
    @NotBlank
    @Column(name="password",nullable = false)
    private String password;
        
    
    @Column(name="enabled", nullable = false,columnDefinition = "BIT")
    private boolean enabled =  true;
    
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = { 
			@JoinColumn(name = "user_id", nullable = true, updatable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id", 
					nullable = true, updatable = true)},
                        uniqueConstraints={@UniqueConstraint(columnNames = {"user_id","role_id"})}
                    )
    @Cascade({CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST,
        CascadeType.SAVE_UPDATE})
    private List<Role> userRoles = new ArrayList<Role>();

    @ManyToMany
    @JoinTable(name = "orders", joinColumns = { 
			@JoinColumn(name = "user_id", nullable = true, updatable = true) }, 
			inverseJoinColumns = { @JoinColumn(name = "album_id", 
					nullable = true, updatable = true)}                                             
                        )	    
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<Album> orderedAlbums = new ArrayList<Album>();
    
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, List<Role> userRoles) {
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
    }

    public User(String username, String password, boolean enabled){
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
    public User(Integer user_id, String username, String password, List<Role> userRoles, 
            List<Album> orderedAlbums) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
        this.orderedAlbums = orderedAlbums;
    }  
    
    public User(Integer user_id, String username, String password, List<Role> userRoles, 
            List<Album> orderedAlbums,String email) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
        this.orderedAlbums = orderedAlbums;
        this.email = email;
    } 

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
    
    
    public Integer getUser_id() {
        return user_id;
    }

    public List<Album> getOrderedAlbums() {
        return orderedAlbums;
    }

    public void setOrderedAlbums(List<Album> orderedAlbums) {
        this.orderedAlbums = orderedAlbums;
    }
    
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    } 

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

  
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getUserRoles() {
        return (List<Role>)userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.user_id);
        hash = 97 * hash + Objects.hashCode(this.registration_date);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + (this.enabled ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.userRoles);
        hash = 97 * hash + Objects.hashCode(this.orderedAlbums);
        return hash;
    }       

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() {
        return "User[ id=" + user_id + " ]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection) userRoles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    
    }

}