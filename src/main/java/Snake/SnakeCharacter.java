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
    private Point[] cuerpo; // A point value to define the snake size
    public static final int Limite = 20; // The limit of the snake size
    private int cabeza; //A variable to set the head value
    private int cola; //A variable used to identify the tail
    private double velocidad; //The speed of the snake
    private int diametro; //The size of the head and body
    private int X, Y, largo; // Variables to set the positiona and large of the snake
    
    /*Inicialize the snake setting its position to the middle of the panel and the size of the head*/
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
    
    /*Enhance the speed of the sneak*/
    public void AumentarVelocidad() {
        this.velocidad = (velocidad + 0.2);
    }
    /*Updates the snake movement and does the comparation to check if the snake has died*/
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
        
        if (((X >= (panel.getWidth())-10) || (Y >= (panel.getHeight()-10)) || (X < -7) || (Y < -7)) || (largo >= 5 && cuerpo[cabeza].distance(cuerpo[cola]) < 5)) {
            return false;
        }

        cabeza = (cabeza + 1) % cuerpo.length;

        cola = (cabeza + cuerpo.length - largo + 1) % cuerpo.length;
        
        cuerpo[cabeza] = new Point(X, Y);
   
       
        
        return true;
        
    }
    
    /*Draws the snake figure with the size parametres (head and tail) and set their colors*/
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
