/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template imagebase64, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class AlbumDTO implements java.io.Serializable {
         
    private String imagepath;
        
    private String imagebase64;
        
    private byte[] imagefile;    
    
    private String title;
    
    private String length,label,producer;
       
    private Date year;
        
    private String genre;
                
    private ArtistDTO artist;     
        
    private List<UserDTO> users = new ArrayList<UserDTO>();

    public AlbumDTO(String imagepath, String imagebase64, byte[] imagefile, String title, String length, String label, String producer, Date year, String genre, ArtistDTO artist) {
        this.imagepath = imagepath;
        this.imagebase64 = imagebase64;
        this.imagefile = imagefile;
        this.title = title;
        this.length = length;
        this.label = label;
        this.producer = producer;
        this.year = year;
        this.genre = genre;
        this.artist = artist;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getImagebase64() {
        return imagebase64;
    }

    public void setImagebase64(String imagebase64) {
        this.imagebase64 = imagebase64;
    }

    public byte[] getImagefile() {
        return imagefile;
    }

    public void setImagefile(byte[] imagefile) {
        this.imagefile = imagefile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.imagepath);
        hash = 97 * hash + Objects.hashCode(this.imagebase64);
        hash = 97 * hash + Arrays.hashCode(this.imagefile);
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + Objects.hashCode(this.length);
        hash = 97 * hash + Objects.hashCode(this.label);
        hash = 97 * hash + Objects.hashCode(this.producer);
        hash = 97 * hash + Objects.hashCode(this.year);
        hash = 97 * hash + Objects.hashCode(this.genre);
        hash = 97 * hash + Objects.hashCode(this.artist);
        hash = 97 * hash + Objects.hashCode(this.users);
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
        final AlbumDTO other = (AlbumDTO) obj;
        if (!Objects.equals(this.imagepath, other.imagepath)) {
            return false;
        }
        if (!Objects.equals(this.imagebase64, other.imagebase64)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.length, other.length)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.producer, other.producer)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Arrays.equals(this.imagefile, other.imagefile)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.artist, other.artist)) {
            return false;
        }
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AlbumDTO{" + "imagepath=" + imagepath + ", imagebase64=" + imagebase64 + ", imagefile=" + imagefile + ", title=" + title + ", length=" + length + ", label=" + label + ", producer=" + producer + ", year=" + year + ", genre=" + genre + ", artist=" + artist + ", users=" + users + '}';
    }

    
   
    
   
     
}
