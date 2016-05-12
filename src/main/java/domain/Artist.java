/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.annotations.CascadeType;


/**
 *
 * @author adriano
 */
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name","surname"})
})
@Entity
public class Artist{
    
    @Id
    @GeneratedValue
    private Long uid;

    @NotBlank
    private String name;
    
    @NotBlank
    private String surname;
    
    @OneToMany(mappedBy="artist")
    @Cascade(CascadeType.ALL)
    private List<Album> albums = new ArrayList<Album>();
     
    public Artist() {
    }

    public Artist(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    
    
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.surname);
        hash = 89 * hash + Objects.hashCode(this.albums);
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
        final Artist other = (Artist) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }     
        if (!Objects.equals(this.albums, other.albums)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Artist{" + "uid=" + uid + ", name=" + name + ", surname=" + surname + '}';
    }          
}
