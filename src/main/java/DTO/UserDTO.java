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



public class UserDTO implements Serializable {    
    
    
    private String email;
    
    private Date registration_date;  
    
    private String username;   
    
    private boolean enabled;
    
    private boolean accountExpired;
    
    private boolean credentialsExpired;
    
    private boolean accountLocked;
        
    private List<RoleDTO> userRoles = new ArrayList<RoleDTO>();
    
    private List<AlbumDTO> orderedAlbums = new ArrayList<AlbumDTO>();
    
    

}