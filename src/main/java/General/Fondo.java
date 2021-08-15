/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Cris
 */
public class Fondo {

    private Resize Ajustar = new Resize(); //Inicialize a Resize class to ajust the image to the JFram
   
    //A Funtion that recive the name of the image, and the designated JFrame to set the background image
    public Fondo(String imagen, JFrame frame) {
        ImageIcon icon = Ajustar.ResizeJF(new ImageIcon(".\\src\\main\\java\\Recursos/" + imagen), frame); //Call the function ResizeJF to ajuste the image width and height
        frame.setLayout(new BorderLayout()); //Set the Frame layaout to border layout
        frame.setContentPane(new JLabel(icon)); //Set creates a content panel with the size of the Frame to asing the background
        frame.setLayout(new FlowLayout()); //Revert the layout to flowLayout

    }
}
