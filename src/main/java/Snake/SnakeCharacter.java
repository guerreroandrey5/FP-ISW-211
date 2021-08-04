/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Cris
 */
public class SnakeCharacter {
    
    JPanel panel;
    private Point[] cuerpo;
    public static final int Limite = 20;
    private int cabeza;
    private int cola;
    private double velocidad;
    private int diametro;
    private int X, Y, largo;
    
    public SnakeCharacter(JPanel board) {
        panel = board;
        cuerpo = new Point[Limite];
        cabeza = -1;
        cola = -1;
        largo = 1;
        velocidad = 4;
        X = (panel.getWidth()/2);
        Y = (panel.getHeight()/2);
        diametro = 13;
    }
    
    public void AumentarVelocidad() {
        this.velocidad = (velocidad + 0.2);
    }
    
    public boolean actualizar(int direction) {
 
            
        switch(direction) {
            case 0:
                Y += velocidad;
                break;
            case 1:
                Y -= velocidad;
                break;
            case 2:
                X += velocidad;
                break;
            case 3:
                X -= velocidad;
                break;
        }
        
        if (((X >= (panel.getWidth())-10) || (Y >= (panel.getHeight()-10)) || (X < -7) || (Y < -7)) || (largo >= 10 && cuerpo[cabeza].distance(cuerpo[cola]) < 10)) {
            return false;
        }

        cabeza = (cabeza + 1) % cuerpo.length;

        cola = (cabeza + cuerpo.length - largo + 1) % cuerpo.length;
        
        cuerpo[cabeza] = new Point(X, Y);
   
       
        
        return true;
        
    }
    
    
    public void draw(Graphics g) {
        g.setColor(Color.green);
        if(largo > 1) {
            int i = cola;
            while (i != cabeza) {
                g.fillOval(cuerpo[i].x, cuerpo[i].y, diametro, diametro);
                i = (i + 1) % cuerpo.length;
            }
        }
        
        g.setColor(Color.red);
        g.fillOval(cuerpo[cabeza].x, cuerpo[cabeza].y, diametro, diametro);
    }
    
    public int getDiametro() {
        return diametro;
    }
    
    public int getPosX() {
        return X;
    }
    public int getPosY() {
        return Y;
    }
    
    public int getLargo(){
        return largo;
    }
    
    public void setLargo(){
        this.largo += 1;
    }
}
