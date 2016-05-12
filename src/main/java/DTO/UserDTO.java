/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;



public class UserDTO implements Serializable {    
    
    
    private String email;
    
    private Date registration_date;  
    
    private String username;   
    
    private boolean enabled;
    
    private boolean accountNonExpired;
    
    private boolean credentialsNonExpired;
    
    private boolean accountNonLocked;
        
    private List<RoleDTO> userRoles = new ArrayList<RoleDTO>();
    
    private List<AlbumDTO> orderedAlbums = new ArrayList<AlbumDTO>();

    public UserDTO(){}
    
    public UserDTO(String email, Date registration_date, String username, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked) {
        this.email = email;
        this.registration_date = registration_date;
        this.username = username;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
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

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public List<RoleDTO> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleDTO> userRoles) {
        this.userRoles = userRoles;
    }

    public List<AlbumDTO> getOrderedAlbums() {
        return orderedAlbums;
    }

    public void setOrderedAlbums(List<AlbumDTO> orderedAlbums) {
        this.orderedAlbums = orderedAlbums;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.registration_date);
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + (this.enabled ? 1 : 0);
        hash = 59 * hash + (this.accountNonExpired ? 1 : 0);
        hash = 59 * hash + (this.credentialsNonExpired ? 1 : 0);
        hash = 59 * hash + (this.accountNonLocked ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.userRoles);
        hash = 59 * hash + Objects.hashCode(this.orderedAlbums);
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
        final UserDTO other = (UserDTO) obj;
        if (this.enabled != other.enabled) {
            return false;
        }
        if (this.accountNonExpired != other.accountNonExpired) {
            return false;
        }
        if (this.credentialsNonExpired != other.credentialsNonExpired) {
            return false;
        }
        if (this.accountNonLocked != other.accountNonLocked) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.registration_date, other.registration_date)) {
            return false;
        }
        if (!Objects.equals(this.userRoles, other.userRoles)) {
            return false;
        }
        if (!Objects.equals(this.orderedAlbums, other.orderedAlbums)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "email=" + email + ", registration_date=" + registration_date + ", username=" + username + ", enabled=" + enabled + ", accountNonExpired=" + accountNonExpired + ", credentialsNonExpired=" + credentialsNonExpired + ", accountNonLocked=" + accountNonLocked + ", userRoles=" + userRoles + ", orderedAlbums=" + orderedAlbums + '}';
    }

            
    
    

}