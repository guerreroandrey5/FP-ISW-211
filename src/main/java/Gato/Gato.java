/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gato;

//import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.time.Instant;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author guerreroandrey5
 */
public class Gato extends javax.swing.JFrame {

    String equis = "X";
    private int Contador;
    private int ContadorB;
    private Date fecha = new Date();
    private long tiempoIncio, tiempoFinal, tiempo;
    String nJ1 = "";
    String nJ2 = "";
    
    //private Date fecha = new Date();
    /**
     * Creates new form Gato
     */
    public Gato() {
        initComponents();
        
        nJ1 = JOptionPane.showInputDialog("Nombre del Jugador 1: ");
        LblP1.setText(nJ1 + ":");
        
        nJ2 = JOptionPane.showInputDialog("Nombre del Jugador 2: ");
        LblP2.setText(nJ2 + ":");
        //Lblf.setText(fecha.toLocaleString()); 
    }
    //<editor-fold defaultstate="collapsed" desc="Condiciones para ganar">
    public void ganar() {
        String B1 = Btn1.getText();
        String B2 = Btn2.getText();
        String B3 = Btn3.getText();
        String B4 = Btn4.getText();
        String B5 = Btn5.getText();
        String B6 = Btn6.getText();
        String B7 = Btn7.getText();
        String B8 = Btn8.getText();
        String B9 = Btn9.getText();
        
        //Condiciones para el primer jugador
        
        if (B1.equals("X") && B2.equals("X") && B3.equals("X")){
            ganaJ1();
        }
        if (B4.equals("X") && B5.equals("X") && B6.equals("X")){
            ganaJ1();
        }
        if (B7.equals("X") && B8.equals("X") && B9.equals("X")){
            ganaJ1();
        }
        if (B1.equals("X") && B4.equals("X") && B7.equals("X")){
            ganaJ1();
        }
        if (B2.equals("X") && B5.equals("X") && B8.equals("X")){
            ganaJ1();
        }
        if (B3.equals("X") && B6.equals("X") && B9.equals("X")){
            ganaJ1();
        }
        if (B3.equals("X") && B5.equals("X") && B7.equals("X")){
            ganaJ1();
        }
        if (B1.equals("X") && B5.equals("X") && B9.equals("X")){
            ganaJ1();
        }
        
        //Condicioneas para el segundo jugador
        
        if (B1.equals("O") && B2.equals("O") && B3.equals("O")){
            ganaJ2();
        }
        if (B4.equals("O") && B5.equals("O") && B6.equals("O")){
            ganaJ2();
        }
        if (B7.equals("O") && B8.equals("O") && B9.equals("O")){
            ganaJ2();
        }
        if (B1.equals("O") && B4.equals("O") && B7.equals("O")){
            ganaJ2();
        }
        if (B2.equals("O") && B5.equals("O") && B8.equals("O")){
            ganaJ2();
        }
        if (B3.equals("O") && B6.equals("O") && B9.equals("O")){
            ganaJ2();
        }
        if (B3.equals("O") && B5.equals("O") && B7.equals("O")){
            ganaJ2();
        }
        if (B1.equals("O") && B5.equals("O") && B9.equals("O")){
            ganaJ2();
        }
    }
    //</editor-fold>
    public void guardarDatos() {
        try {
            File archivo = new File("PuntajesGato.txt");
            FileWriter writez = new FileWriter(archivo, true);
            writez.write(nJ1+ ", tiene " + LblPts1.getText() + " Puntos " + nJ2 + ", tiene " + LblPts2.getText() + " Puntos" +", "+ Date.from(Instant.now()).toString() + "\n");           
            writez.close();
        } catch (Exception e) {
        }
    }
    
    public void empatar() {
        String B1 = Btn1.getText();
        String B2 = Btn2.getText();
        String B3 = Btn3.getText();
        String B4 = Btn4.getText();
        String B5 = Btn5.getText();
        String B6 = Btn6.getText();
        String B7 = Btn7.getText();
        String B8 = Btn8.getText();
        String B9 = Btn9.getText();
            
        if(B1 != "" && B2 != "" && B3 != "" &&
           B4 != "" && B5 != "" && B6 != "" && 
           B7 != "" && B8 != "" && B9 != ""){
            JOptionPane.showMessageDialog(null, "El juego está empatado");
            nGame();
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Funciones de ganar y reiniciar el juego">
    public void ganaJ1() {
        JOptionPane.showMessageDialog(null, "Ha ganado " + nJ1);
        nGame();
        Contador +=1;
        String ContStr = String.valueOf(Contador);
        LblPts1.setText(ContStr);
        this.tiempoFinal = System.currentTimeMillis();
        this.tiempo = tiempoFinal - tiempoIncio;
        guardarDatos();
    }

    public void ganaJ2() {
        JOptionPane.showMessageDialog(null, "Ha ganado " + nJ2);
        nGame();
        ContadorB +=1;
        String ContStr = String.valueOf(ContadorB);
        LblPts2.setText(ContStr);
        this.tiempoFinal = System.currentTimeMillis();
        this.tiempo = tiempoFinal - tiempoIncio;
        guardarDatos();
    }

    public void nGame() {       
        //Resetea todos los botones y borra su texto
        Btn1.setEnabled(true);
        Btn1.setText("");
        Btn2.setEnabled(true);
        Btn2.setText("");
        Btn3.setEnabled(true);
        Btn3.setText("");
        Btn4.setEnabled(true);
        Btn4.setText("");
        Btn5.setEnabled(true);
        Btn5.setText("");
        Btn6.setEnabled(true);
        Btn6.setText("");
        Btn7.setEnabled(true);
        Btn7.setText("");
        Btn8.setEnabled(true);
        Btn8.setText("");
        Btn9.setEnabled(true);
        Btn9.setText("");
        equis = "X";
    }
//</editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelGato = new javax.swing.JPanel();
        Btn1 = new javax.swing.JButton();
        Btn2 = new javax.swing.JButton();
        Btn3 = new javax.swing.JButton();
        Btn4 = new javax.swing.JButton();
        Btn5 = new javax.swing.JButton();
        Btn6 = new javax.swing.JButton();
        Btn7 = new javax.swing.JButton();
        Btn8 = new javax.swing.JButton();
        Btn9 = new javax.swing.JButton();
        BtnNew = new javax.swing.JButton();
        LblHora = new javax.swing.JLabel();
        Lblh = new javax.swing.JLabel();
        LblFHoy = new javax.swing.JLabel();
        Lblf = new javax.swing.JLabel();
        LblP1 = new javax.swing.JLabel();
        LblP2 = new javax.swing.JLabel();
        LblPts1 = new javax.swing.JLabel();
        LblPts2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        PanelGato.setName("PanelGato"); // NOI18N
        PanelGato.setLayout(new java.awt.GridLayout(3, 3));

        Btn1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn1ActionPerformed(evt);
            }
        });
        PanelGato.add(Btn1);

        Btn2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn2ActionPerformed(evt);
            }
        });
        PanelGato.add(Btn2);

        Btn3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn3ActionPerformed(evt);
            }
        });
        PanelGato.add(Btn3);

        Btn4.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn4ActionPerformed(evt);
            }
        });
        PanelGato.add(Btn4);

        Btn5.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn5ActionPerformed(evt);
            }
        });
        PanelGato.add(Btn5);

        Btn6.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn6ActionPerformed(evt);
            }
        });
        PanelGato.add(Btn6);

        Btn7.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn7ActionPerformed(evt);
            }
        });
        PanelGato.add(Btn7);

        Btn8.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn8ActionPerformed(evt);
            }
        });
        PanelGato.add(Btn8);

        Btn9.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        Btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn9ActionPerformed(evt);
            }
        });
        PanelGato.add(Btn9);

        BtnNew.setText("Nuevo Juego");
        BtnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNewActionPerformed(evt);
            }
        });

        LblHora.setText("Hora:");

        Lblh.setText("h");

        LblFHoy.setText("Fecha de Juego:");

        Lblf.setText("fhoy");

        LblP1.setText("Jugador 1:");

        LblP2.setText("Jugador 2:");

        LblPts1.setText("0");

        LblPts2.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelGato, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LblFHoy)
                                .addGap(18, 18, 18)
                                .addComponent(Lblf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LblHora)
                                .addGap(18, 18, 18)
                                .addComponent(Lblh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnNew)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LblP2)
                            .addComponent(LblP1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblPts1)
                            .addComponent(LblPts2))
                        .addGap(53, 53, 53))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblP1)
                            .addComponent(LblPts1))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblP2)
                            .addComponent(LblPts2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblFHoy)
                            .addComponent(Lblf))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(LblHora)
                                    .addComponent(Lblh)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnNew)
                                .addGap(26, 26, 26)))))
                .addComponent(PanelGato, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    //<editor-fold defaultstate="collapsed" desc="Botones">
    private void Btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn2ActionPerformed
        Btn2.setText(equis);
        if (equis.equals("X")) {
            Btn2.setEnabled(false);
            equis = "O";
        } else {
            equis = "X";
            Btn2.setEnabled(false);
        }         
            ganar();
            empatar();
    }//GEN-LAST:event_Btn2ActionPerformed

    private void Btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn5ActionPerformed
        Btn5.setText(equis);
        if (equis.equals("X")) {
            Btn5.setEnabled(false);
            equis = "O";
        } else {
            equis = "X";
            Btn5.setEnabled(false);
        }
            ganar();
            empatar();
    }//GEN-LAST:event_Btn5ActionPerformed

    private void Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn1ActionPerformed
        Btn1.setText(equis);
        if (equis.equals("X")) {
            Btn1.setEnabled(false);
            equis = "O";
        } else {
            equis = "X";
            Btn1.setEnabled(false);
        }
            ganar();
            empatar();
    }//GEN-LAST:event_Btn1ActionPerformed

    private void Btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn3ActionPerformed
        Btn3.setText(equis);
        if (equis.equals("X")) {
            Btn3.setEnabled(false);
            equis = "O";
        } else {
            equis = "X";
            Btn3.setEnabled(false);
        }
            ganar();
            empatar();
    }//GEN-LAST:event_Btn3ActionPerformed

    private void Btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn4ActionPerformed
        Btn4.setText(equis);
        if (equis.equals("X")) {
            Btn4.setEnabled(false);
            equis = "O";
        } else {
            equis = "X";
            Btn4.setEnabled(false);
        }
            ganar();
            empatar();
    }//GEN-LAST:event_Btn4ActionPerformed

    private void Btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn6ActionPerformed
        Btn6.setText(equis);
        if (equis.equals("X")) {
            Btn6.setEnabled(false);
            equis = "O";
        } else {
            equis = "X";
            Btn6.setEnabled(false);
        }
            ganar();
            empatar();
    }//GEN-LAST:event_Btn6ActionPerformed

    private void Btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn7ActionPerformed
        Btn7.setText(equis);
        if (equis.equals("X")) {
            Btn7.setEnabled(false);
            equis = "O";
        } else {
            equis = "X";
            Btn7.setEnabled(false);
        }
            ganar();
            empatar();
    }//GEN-LAST:event_Btn7ActionPerformed

    private void Btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn8ActionPerformed
        Btn8.setText(equis);
        if (equis.equals("X")) {
            Btn8.setEnabled(false);
            equis = "O";
        } else {
            equis = "X";
            Btn8.setEnabled(false);
        }
            ganar();
            empatar();
    }//GEN-LAST:event_Btn8ActionPerformed

    private void Btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn9ActionPerformed
        Btn9.setText(equis);
        if (equis.equals("X")) {
            Btn9.setEnabled(false);
            equis = "O";
        } else {
            equis = "X";
            Btn9.setEnabled(false);
        }
            ganar();
            empatar();
    }//GEN-LAST:event_Btn9ActionPerformed

    private void BtnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNewActionPerformed
        LblPts1.setText("0");
        LblPts2.setText("0");
        Contador = 0;
        ContadorB = 0;
    }//GEN-LAST:event_BtnNewActionPerformed
    //</editor-fold>
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
            java.util.logging.Logger.getLogger(Gato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gato().setVisible(true);
            }
        });
    }
    //<editor-fold defaultstate="collapsed" desc="Variables">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn1;
    private javax.swing.JButton Btn2;
    private javax.swing.JButton Btn3;
    private javax.swing.JButton Btn4;
    private javax.swing.JButton Btn5;
    private javax.swing.JButton Btn6;
    private javax.swing.JButton Btn7;
    private javax.swing.JButton Btn8;
    private javax.swing.JButton Btn9;
    private javax.swing.JButton BtnNew;
    private javax.swing.JLabel LblFHoy;
    private javax.swing.JLabel LblHora;
    private javax.swing.JLabel LblP1;
    private javax.swing.JLabel LblP2;
    private javax.swing.JLabel LblPts1;
    private javax.swing.JLabel LblPts2;
    private javax.swing.JLabel Lblf;
    private javax.swing.JLabel Lblh;
    private javax.swing.JPanel PanelGato;
    // End of variables declaration//GEN-END:variables
//</editor-fold>
}
