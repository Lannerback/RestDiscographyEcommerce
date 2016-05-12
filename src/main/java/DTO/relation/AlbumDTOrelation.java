/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.relation;

import DTO.AlbumDTO;
import DTO.ArtistDTO;
import java.util.Date;

/**
 *
 * @author
 */
public class AlbumDTOrelation extends AlbumDTO {
    
    public AlbumDTOrelation(String imagepath, String imagebase64, byte[] imagefile, String title, String length, String label, String producer, Date year, String genre, ArtistDTO artist) {
        super(imagepath, imagebase64, imagefile, title, length, label, producer, year, genre, artist);
    }
    
}
