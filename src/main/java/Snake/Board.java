/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import General.Fondo;
import General.Resize;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import General.Resize;
import java.time.Instant;
/**
 *
 * @author Cris
 */
public class Board extends javax.swing.JFrame implements Runnable, KeyListener {
    private int direccion = -1; //Defines Snake's direction, '-1' the snake doesn't move 
    private static final int FPS = 30;
    private boolean enJuego = true; // Defines the state of the game
    private Date fecha = new Date();
    private Thread threadJuego; // Defines a Thread to follow the activity in the code
    private Graphics graficcos; //Creates graphics to show the snake and its movement
    private Image imagen; 
    private Comida bolita; 
    private SnakeCharacter Charki; 
    private String Jugador;
    private Resize Ajustar = new Resize();
    private long tiempoIncio, tiempoFinal, tiempo;
    private Fondo nBG;

 

    /**
     * Creates new form Board
     */
    public Board() {
      
    }
    
    public Board(String nombre) {
        setSize(700, 500);
        nBG = new Fondo("cespedBG.jpg", this);
        initComponents();
        PlnGame.setOpaque(false);
        TxtBoxDate.setText(fecha.toLocaleString());
        this.Jugador = nombre;
        TxtBoxPoints.setText(Jugador);
        
        setLocationRelativeTo(null);
        Incializar();
        PlnInts.setVisible(true);
                
    }
    
    public void Incializar() {
        direccion = -1;
        imagen = null;
        Charki = new SnakeCharacter(PlnGame);
        bolita = new Comida(PlnGame, Charki);
        Lbl1.setVisible(false);
        Lbl2.setVisible(false);
        BtnPlayA.setVisible(false);
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
    }
    
    
    /*Inicialize a thread to draw the game*/
    public void EmpezarJuego() {
        if(enJuego) {
            threadJuego = new Thread(this);
            threadJuego.start();
        }
    }
    
    
    /*Stops the Snake and the game*/
    public void DetenerJuego() {
        enJuego = false;
    }
    
    
    /*Creates an iamgen to help the drawing of the game*/
    public void Renderizar() {
        if (imagen == null) {
            imagen = createImage((PlnGame.getWidth()), PlnGame.getHeight());
            if (imagen != null) {
                graficcos = imagen.getGraphics();
            }
        }
        
        graficcos.setColor(Color.white);
        graficcos.fillRect(0, 0, PlnGame.getWidth(), PlnGame.getHeight());
        Charki.draw(graficcos);
        bolita.draw(graficcos);
    }

    
    
    /*Draw the gameboard, the snake and the food by using Graphics library*/
    public void DibujarJuego() {
        Graphics g;
        try {
            g = PlnGame.getGraphics();
            if (g != null && imagen != null) {
                g.drawImage(imagen, 0,0, (PlnGame.getWidth()), (PlnGame.getHeight()), PlnGame);
            }
            g.dispose();
        } catch (Exception e) {
        }
    }    
    
    
    /*Updates the game, snake's movement, food's position and if the player wins or looses*/
    public void Actualizar() {
        if(enJuego) {
            boolean Movimiento = Charki.actualizar(direccion);
            boolean Comer = bolita.Cambio();
            TxtBoxName.setText(String.valueOf(bolita.getPuntos()));
            if(!Comer) {
                FinalizarJuego(true);
            } else if (!Movimiento) {
                FinalizarJuego(false);
            }
        }
    }
    
    /*Ends the game when the player reach the limit or when the player loose*/
    public void FinalizarJuego(boolean finalizar) {
        String img1 = "", img2 = "";
        this.tiempoFinal = System.currentTimeMillis();
        this.tiempo = tiempoFinal - tiempoIncio;
        if(Charki.getLargo() == SnakeCharacter.Limite && finalizar) {
            img1 = "wp.png";
            img2 = "gg.gif";
        } else {
            img1 = "f.png";
            img2 = "ez.gif";
        }
        removeKeyListener(this);
        
        Lbl1.setIcon(Ajustar.Resize(new ImageIcon(".\\src\\main\\java\\Recursos/"+img1),Lbl1));
        ImageIcon gif = Ajustar.Resize(new ImageIcon(".\\src\\main\\java\\Recursos/"+img2), Lbl2);
        Lbl2.setIcon(gif);
        gif.setImageObserver(Lbl2);
        BtnPlayA.setVisible(true);
        Lbl1.setVisible(true);
        Lbl2.setVisible(true);
        guardarDatos();
        DetenerJuego();
        PlnGame.repaint();
    }
    
    
    
    
    
    
    /*Saves the player score*/
    public void guardarDatos() {
        try {
            File archivo = new File("Puntajes.txt");
            FileWriter escribir = new FileWriter(archivo, true);
            
            escribir.write(Jugador+ "," + bolita.getPuntos() +","+ Long.toString(tiempo) +","+ Date.from(Instant.now()).toString() + "\n");
            
            escribir.close();
        } catch (Exception e) {
        }
    }
    
    
    public void Volver() {
        dispose();
        Menu volver = new Menu();
        volver.setVisible(true);
    }
    
    /*Restar the game*/
    public void reiniciar() {
        enJuego = true;
        PlnInts.setVisible(true);
        Incializar();
        EmpezarJuego();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PlnInts = new javax.swing.JPanel();
        PlnInt = new javax.swing.JLabel();
        PlnGame = new javax.swing.JPanel();
        BtnPlayA = new javax.swing.JButton();
        Lbl1 = new javax.swing.JLabel();
        Lbl2 = new javax.swing.JLabel();
        BtnExit = new javax.swing.JButton();
        LblDate = new javax.swing.JLabel();
        TxtBoxDate = new javax.swing.JTextField();
        LblName = new javax.swing.JLabel();
        TxtBoxName = new javax.swing.JTextField();
        LblPoints = new javax.swing.JLabel();
        TxtBoxPoints = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        PlnInt.setText("<html>\n<body>\nUse la flechas (← ↑ ↓ →) para moverse:  <br>  ← Derecha  <br> ↑ Arriba <br>  ↓ Abajo  <br> → Izquierda <br> Presione una para continuar...\n</body>\n</html>");
        PlnInt.setToolTipText("   ← ↑ ↓ →");

        javax.swing.GroupLayout PlnIntsLayout = new javax.swing.GroupLayout(PlnInts);
        PlnInts.setLayout(PlnIntsLayout);
        PlnIntsLayout.setHorizontalGroup(
            PlnIntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlnIntsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(PlnInt, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        PlnIntsLayout.setVerticalGroup(
            PlnIntsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlnIntsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PlnInt, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(PlnInts);
        PlnInts.setBounds(420, 10, 260, 340);

        PlnGame.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PlnGame.setForeground(new java.awt.Color(255, 255, 255));
        PlnGame.setLayout(null);
        PlnGame.setVisible(true);

        BtnPlayA.setText("Volver a Jugar");
        BtnPlayA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPlayAActionPerformed(evt);
            }
        });
        BtnExit.setVisible(false);
        PlnGame.add(BtnPlayA);
        BtnPlayA.setBounds(130, 290, 120, 50);
        PlnGame.add(Lbl1);
        Lbl1.setBounds(10, 40, 160, 180);
        PlnGame.add(Lbl2);
        Lbl2.setBounds(190, 10, 200, 250);

        getContentPane().add(PlnGame);
        PlnGame.setBounds(10, 10, 400, 370);

        BtnExit.setText("Volver al Menu");
        BtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExitActionPerformed(evt);
            }
        });
        BtnExit.setVisible(true);
        getContentPane().add(BtnExit);
        BtnExit.setBounds(510, 360, 120, 40);

        LblDate.setText("Fecha y Hora");
        getContentPane().add(LblDate);
        LblDate.setBounds(450, 20, 150, 16);

        TxtBoxDate.setEditable(false);
        TxtBoxDate.setText("Fehca");
        TxtBoxDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtBoxDateActionPerformed(evt);
            }
        });
        getContentPane().add(TxtBoxDate);
        TxtBoxDate.setBounds(460, 50, 130, 30);

        LblName.setText("Puntuacion");
        getContentPane().add(LblName);
        LblName.setBounds(450, 240, 170, 16);

        TxtBoxName.setEditable(false);
        TxtBoxName.setText("Puntuacion");
        getContentPane().add(TxtBoxName);
        TxtBoxName.setBounds(460, 270, 130, 30);

        LblPoints.setText("Jugador");
        getContentPane().add(LblPoints);
        LblPoints.setBounds(450, 130, 170, 16);

        TxtBoxPoints.setEditable(false);
        TxtBoxPoints.setText("Nombre");
        getContentPane().add(TxtBoxPoints);
        TxtBoxPoints.setBounds(460, 160, 130, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPlayAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPlayAActionPerformed
        reiniciar();
    }//GEN-LAST:event_BtnPlayAActionPerformed

    private void BtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExitActionPerformed
        Volver();
    }//GEN-LAST:event_BtnExitActionPerformed

    private void TxtBoxDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtBoxDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtBoxDateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Board().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnExit;
    private javax.swing.JButton BtnPlayA;
    private javax.swing.JLabel Lbl1;
    private javax.swing.JLabel Lbl2;
    private javax.swing.JLabel LblDate;
    private javax.swing.JLabel LblName;
    private javax.swing.JLabel LblPoints;
    private javax.swing.JPanel PlnGame;
    private javax.swing.JLabel PlnInt;
    private javax.swing.JPanel PlnInts;
    private javax.swing.JTextField TxtBoxDate;
    private javax.swing.JTextField TxtBoxName;
    private javax.swing.JTextField TxtBoxPoints;
    // End of variables declaration//GEN-END:variables

    @Override
    /*Ejecutes the code always to keep the snake in movement*/
    public void run() {
        long t1,t2,dt,sleepTime;  
        long period=1000/FPS;  // Calculates the time need for the ejecution
        t1=System.nanoTime();  // calculates the time before the ejecution
          
        while(enJuego){
           Actualizar();
           Renderizar();
           DibujarJuego();
           t2= System.nanoTime() ; // Is the time after that the game start 
           dt=(t2-t1)/1000000L;  // Is the realtime used in the cicle 
           sleepTime = period - dt;// Calculates the left time in the cicle 
           if(sleepTime<=0)        // Avoids the time of sleep of the cicle goes negative
                 sleepTime=2;
           try {     
           Thread.sleep(sleepTime); // Lets the tread rest
          } catch (InterruptedException ex) { }
             t1 = System.nanoTime();  // Get the time again
        }
        BtnExit.setVisible(true);
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        PlnInts.setVisible(false);
        BtnExit.setVisible(false);
        
        if(direccion == -1 ) {
            this.tiempoIncio = System.currentTimeMillis();
        }
        switch (tecla) {
                
                case KeyEvent.VK_DOWN:
                    if (direccion == 1) {
                        break;
                    }
                    direccion = 0;
                    break;
                case KeyEvent.VK_UP:
                    if (direccion == 0) {
                        break;
                    }
                    direccion = 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direccion == 3) {
                        break;
                    }
                    direccion = 2;
                    break;
                case KeyEvent.VK_LEFT:
                    if (direccion == 2) {
                        break;
                    }
                    direccion = 3;
                    break;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
