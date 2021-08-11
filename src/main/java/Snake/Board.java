/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

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

 

    /**
     * Creates new form Board
     */
    public Board() {
       
    }
    
    public Board(String nombre) {
        initComponents();
        jTextField1.setText(fecha.toLocaleString());
        this.Jugador = nombre;
        jTextField3.setText(Jugador);
        setSize(700, 500);
        setLocationRelativeTo(null);
        Incializar();
        jPanel1.setVisible(true);
                
    }
    
    public void Incializar() {
        direccion = -1;
        imagen = null;
        Charki = new SnakeCharacter(jPanel3);
        bolita = new Comida(jPanel3, Charki);
        jLabel4.setVisible(false);
        jLabel6.setVisible(false);
        jButton5.setVisible(false);
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
            imagen = createImage((jPanel3.getWidth()), jPanel3.getHeight());
            if (imagen != null) {
                graficcos = imagen.getGraphics();
            }
        }
        
        graficcos.setColor(Color.white);
        graficcos.fillRect(0, 0, jPanel3.getWidth(), jPanel3.getHeight());
        Charki.draw(graficcos);
        bolita.draw(graficcos);
    }

    
    
    /*Draw the gameboard, the snake and the food by using Graphics library*/
    public void DibujarJuego() {
        Graphics g;
        try {
            g = jPanel3.getGraphics();
            if (g != null && imagen != null) {
                g.drawImage(imagen, 0,0, (jPanel3.getWidth()), (jPanel3.getHeight()), jPanel3);
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
            jTextField2.setText(String.valueOf(bolita.getPuntos()));
            if(!Comer) {
                FinalizarJuego(true);
            } else if (!Movimiento) {
                FinalizarJuego(false);
            }
        }
    }
    
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
        
        jLabel4.setIcon(Ajustar.Resize(new ImageIcon(".\\src\\main\\java\\Recursos/"+img1),jLabel4));
        ImageIcon gif = Ajustar.Resize(new ImageIcon(".\\src\\main\\java\\Recursos/"+img2), jLabel6);
        jLabel6.setIcon(gif);
        gif.setImageObserver(jLabel6);
        jButton5.setVisible(true);
        jLabel4.setVisible(true);
        jLabel6.setVisible(true);
        guardarDatos();
        DetenerJuego();
        jPanel3.repaint();
    }
    
    
    
    
    
    
    /*Saves the player score*/
    public void guardarDatos() {
        try {
            File archivo = new File("Puntuajes.txt");
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
    
    public void reiniciar() {
        enJuego = true;
        jPanel1.setVisible(true);
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

        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);
        jPanel3.setVisible(true);

        jButton5.setText("Volver a Jugar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton1.setVisible(false);
        jPanel3.add(jButton5);
        jButton5.setBounds(130, 290, 120, 50);
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 40, 160, 180);
        jPanel3.add(jLabel6);
        jLabel6.setBounds(190, 10, 200, 250);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 10, 400, 370);

        jLabel5.setText("<html>\n<body>\nUse la flechas (← ↑ ↓ →) para moverse:  <br>  ← Derecha  <br> ↑ Arriba <br>  ↓ Abajo  <br> → Izquierda <br> Presione una para continuar...\n</body>\n</html>");
        jLabel5.setToolTipText("   ← ↑ ↓ →");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(420, 10, 260, 340);

        jButton1.setText("Volver al Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setVisible(true);
        getContentPane().add(jButton1);
        jButton1.setBounds(510, 360, 120, 40);

        jLabel1.setText("Fecha y Hora");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(450, 20, 150, 16);

        jTextField1.setEditable(false);
        jTextField1.setText("Fehca");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(460, 50, 130, 30);

        jLabel2.setText("Puntuacion");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(450, 240, 170, 16);

        jTextField2.setEditable(false);
        jTextField2.setText("Puntuacion");
        getContentPane().add(jTextField2);
        jTextField2.setBounds(460, 270, 130, 30);

        jLabel3.setText("Jugador");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(450, 130, 170, 16);

        jTextField3.setEditable(false);
        jTextField3.setText("Nombre");
        getContentPane().add(jTextField3);
        jTextField3.setBounds(460, 160, 130, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        reiniciar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Volver();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        long t1,t2,dt,sleepTime;  
        long period=1000/FPS;  // Calculates the time need for the ejecution
        t1=System.nanoTime();  // calculates the time before the ejecution
          
        while(enJuego){
           Actualizar();
           Renderizar();
           DibujarJuego();
           t2= System.nanoTime() ; // Tiempo del sistema después de que se ejecuta el bucle del juego, en nanosegundos
           dt=(t2-t1)/1000000L;  // El tiempo real empleado en este bucle, convertido a milisegundos
           sleepTime = period - dt;// Calcula el tiempo restante de este ciclo, en milisegundos
           if(sleepTime<=0)        // Evita que el valor sleepTime sea negativo
                 sleepTime=2;
           try {     
           Thread.sleep(sleepTime); // Deja que el hilo duerma, determinado por el valor sleepTime
          } catch (InterruptedException ex) { }
             t1 = System.nanoTime();  // Obtener la hora actual del sistema nuevamente
        }
        jButton1.setVisible(true);
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        jPanel1.setVisible(false);
        jButton1.setVisible(false);
        
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
