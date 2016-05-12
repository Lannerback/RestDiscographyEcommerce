/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import DTO.*;
import domain.*;

/**
 *
 * @author
 */
public class DaoBase {
    
    public DaoBase(){
    }
    
    public ArtistDTO getDTO(Artist artist){
        ArtistDTO artistDTO;
        artistDTO = new ArtistDTO(artist.getName(), artist.getSurname());        
        return artistDTO;
    }
    
    public RoleDTO getDTO(Role role){
        RoleDTO roleDTO;
        roleDTO = new RoleDTO(role.getRole());
        return roleDTO;
    }
    
    public UserDTO getDTO(User user){
        UserDTO userDTO;
        userDTO = new UserDTO(user.getEmail(),user.getRegistration_date(),user.getUsername(),user.isEnabled(),
                user.isAccountNonExpired(),user.isCredentialsNonExpired(),user.isAccountNonLocked());
        return userDTO;
    }
    public AlbumDTO getDTO(Album album){
        AlbumDTO albumDTO;        
        ArtistDTO artistDTO = getDTO(album.getArtist());

        albumDTO = new AlbumDTO(album.getImagepath(), album.getImagebase64(),
                album.getImagefile(), album.getTitle(), 
                album.getLength(), album.getLabel(), album.getProducer(),
                album.getYear(), album.getGenre(), artistDTO);

        return albumDTO;
    }
        
	
    
            
}
