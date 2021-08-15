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
    private Resize Ajustar = new Resize(); 
    public Fondo(String imagen, JFrame frame) {
        ImageIcon icon = Ajustar.ResizeJF(new ImageIcon(".\\src\\main\\java\\Recursos/" + imagen), frame);
        frame.setLayout(new BorderLayout());
        frame.setContentPane(new JLabel(icon));
        frame.setLayout(new FlowLayout());
    }
}
