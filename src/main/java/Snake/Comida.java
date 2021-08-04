/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class Comida {
    public Point location;
    public Point size;
    private JPanel panel;
    private SnakeCharacter charki;
    private Random RandPos;
    private int puntos;
    
    public Comida (JPanel jp, SnakeCharacter chark) {
       panel = jp;
       charki = chark;
       RandPos = new Random();
       location = setPosition();
       size = new Point((charki.getDiametro()-2), (charki.getDiametro()-2));
    }
    
    
    private Point setPosition() {
        Point pointd = new Point();
        while(true) {
            pointd  = new Point(Math.abs(RandPos.nextInt() % panel.getWidth()), Math.abs(RandPos.nextInt()% panel.getHeight()));
            if ((pointd.x < (panel.getWidth())-20) && (pointd.y < (panel.getHeight()-20)) && (pointd.x > -7) && (pointd.y > -7)){
                break;
            }
        }   
         return pointd;
    }
    
    public boolean Cambio() {
        if (Math.abs((charki.getPosX() + charki.getDiametro() / 2) - (location.x + size.x / 2)) < charki.getDiametro() &&
           (Math.abs((charki.getPosY() + charki.getDiametro() / 2) - (location.y + size.y / 2)) < charki.getDiametro())) {   
            location = setPosition();
            charki.AumentarVelocidad();
            puntos += 1; 
                if (charki.getLargo() == SnakeCharacter.Limite) {
                    return false;
                } else if (charki.getLargo() < SnakeCharacter.Limite) {
                    charki.setLargo();
                    
                }           
        }
        return true;
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(location.x, location.y, size.x, size.y);
    }
}
