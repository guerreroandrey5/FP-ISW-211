package General;


import java.awt.Image;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cris
 */
public class Resize {

    public Resize() {
       
    }
    
    /*Changes the size of the images and return a new one*/
    public ImageIcon Resize(ImageIcon img, JLabel dimensions) {
        
        Image Oimg = img.getImage().getScaledInstance(dimensions.getWidth(), dimensions.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon newIMG = new ImageIcon(Oimg);
        
        return newIMG;
    }
}
