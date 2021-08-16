/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ahorcado;

import General.Fondo;
import General.Resize;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author Cris
 */
public class AGame extends javax.swing.JFrame {
    private String nJ;
    private Resize Ajustar = new Resize();
    private Fondo nFondo;
    private String secretWord;
    boolean gameEnded = false;
    private JLabel[] LblSecretWord;
    private ArrayList<String> words;
    private ArrayList<String[]> pistas;
    private int indP = 0;
    private ArrayList<Integer> cantidad = new ArrayList<>();
    private ArrayList<Integer> cantidadW = new ArrayList<>();
    private String imagenes[] = {
        ".\\src\\main\\java\\Recursos/na.png",
        ".\\src\\main\\java\\Recursos/head.png",
        ".\\src\\main\\java\\Recursos/headandbody.png",
        ".\\src\\main\\java\\Recursos/noarms.png",
        ".\\src\\main\\java\\Recursos/ded.png"
    
    
    };
    private int indice = 0;
    int trysP = 0;
    int trysN = 0;
    int Points = 0;
    int nPoints = 0;
    private Date fecha = new Date();
    int cantl = 0;
    /**
     * Creates new form AJuego
     */
    public AGame() {
        setSize(750,600);
        nFondo = new Fondo("PizzarraBG.jpg", this);
        initComponents();
        
        PanelTeclado.setOpaque(false);
        getData();
        ComGame();   
        Load();
    }
    
    
    public AGame(String name) {
        setSize(750,600);
        nFondo = new Fondo("PizzarraBG.jpg", this);
        initComponents();
        
        PanelTeclado.setOpaque(false);
        getData();
        ComGame();       
    }
    
    private void Load(){
        nJ = JOptionPane.showInputDialog("Nombre del Jugador: ");
        while (nJ.equals("")){
            JOptionPane.showMessageDialog(null, "Debes ingresar un nombre!");
            nJ = JOptionPane.showInputDialog("Nombre del Jugador: ");
        }

        LblP.setText(nJ);
        
        //Lblf.setText(fecha.toLocaleString());
    }
    
    /*Inizialice the game setting the variables in their beginning state*/
    public void ComGame(){
        cantidad = new ArrayList<>();
        LabelVisor2.setVisible(false);
        BtnNewGame.setVisible(false);
        LblAlert.setVisible(false);       
        LblHints.setVisible(true);
        PanelTeclado.setVisible(true);
        try {
          for(int n = 0; n < LblSecretWord.length; n++) {
            remove(LblSecretWord[n]);
            }  
          LblSecretWord = null;
        } catch (Exception e) {
        }
        secretWord = getSecretWord();
        setPista();
        ImageIcon Imagen = new ImageIcon(imagenes[0]);
        LabelVisor.setIcon(Imagen);
        indice = 0;
        trysP = 0;
        trysN = 0;
        Points = 0;
        nPoints = 0;
        
    }
    
    /*Disables and Enables the butons*/
    private void Cbotones(boolean condicion) {
        BtnA.setEnabled(condicion);
        BtnB.setEnabled(condicion);
        BtnC.setEnabled(condicion);
        BtnD.setEnabled(condicion);
        BtnE.setEnabled(condicion);
        BtnF.setEnabled(condicion);
        BtnG.setEnabled(condicion);
        BtnH.setEnabled(condicion);
        BtnI.setEnabled(condicion);
        BtnJ.setEnabled(condicion);
        BtnK.setEnabled(condicion);
        BtnL.setEnabled(condicion);
        BtnM.setEnabled(condicion);
        BtnN.setEnabled(condicion);
        BtnNn.setEnabled(condicion);
        BtnO.setEnabled(condicion);
        BtnP.setEnabled(condicion);
        BtnQ.setEnabled(condicion);
        BtnR.setEnabled(condicion);
        BtnS.setEnabled(condicion);
        BtnT.setEnabled(condicion);
        BtnU.setEnabled(condicion);
        BtnV.setEnabled(condicion);
        BtnW.setEnabled(condicion);
        BtnX.setEnabled(condicion);
        BtnY.setEnabled(condicion);
        BtnZ.setEnabled(condicion);
    }
    
    /*Read the Files to get the words and clues or hints*/
    public void getData() {
        words = new ArrayList<String>(); //ArrayList that contains the words
        pistas = new ArrayList<String[]>(); //ArrayList that contains the hints
        try {
            File archivo = new File(".\\src\\main\\java\\Recursos/Palabras.txt");
            String linea;
            Scanner lector = new Scanner(archivo);
            while ((linea = lector.nextLine())!= null) {
                String[] word = linea.split(",");
                for (int w = 0; w < word.length;w++){
                    words.add(word[w]);                              
                }
            
            }
        } catch (Exception e) {
        }
        try {
            File archivo = new File(".\\src\\main\\java\\Recursos/Pistas.txt");
            String linea2;
            Scanner lector2 = new Scanner(archivo);
            while ((linea2 = lector2.nextLine())!= null) {
                String[] word2 = linea2.replace("/"," ").split(",");
                pistas.add(word2);
            
            }
        } catch (Exception e) {
        }
        
    }
    
    /* Selects a random word making */
    private String getSecretWord(){  
        Random r = new Random();
        int n = r.nextInt(words.size());
        if(cantidadW.size() != words.size() && cantidadW.size() != 0) {
           while (cantidadW.contains(n)) {
            n = r.nextInt(words.size());
        }
        }else if (cantidadW.size() == 0) {
            cantidadW.add(n);
        } else if (cantidadW.size() == words.size()) {
            cantidadW = new ArrayList<>();
        }
        cantidadW.add(n);
        indP = n;      
        LblSecretWord = new JLabel[words.get(n).length()];
        cantl = LblSecretWord.length;
        for (int i = 0; i < LblSecretWord.length; i++) {
            LblSecretWord[i] = new JLabel();
            LblSecretWord[i].setBounds(new Rectangle(15, (i + 1)* 40, 60, 25)); 
            if (i == 0) {
                LblSecretWord[i].setLocation(130,380);
            } else {
                LblSecretWord[i].setLocation((LblSecretWord[i-1].getX()+30), 380);
            }
            Font style = new Font("Segoe UI", Font.PLAIN, 24);
            LblSecretWord[i].setText("__");
            LblSecretWord[i].setForeground(Color.white);
            LblSecretWord[i].setFont(style);
            add(LblSecretWord[i],null);
        }
        return words.get(n);       
    }
    
    /*Get the hints of the random word and select one randomly*/
    private void setPista() {
        Random rand = new Random();
        int PR = rand.nextInt(pistas.get(indP).length);
        if(cantidad.size() != pistas.get(indP).length && cantidad.size() != 0) {
           while (cantidad.contains(PR)) {
            PR = rand.nextInt(pistas.get(indP).length);
        }
           cantidad.add(PR);
        } else if (cantidad.size() == 0) {
            cantidad.add(PR);
        }else if (cantidad.size() == pistas.get(indP).length) {
            cantidad = new ArrayList<>();
        }
        LblHints.setText(pistas.get(indP)[PR]);
    }
    
    /*End the game and set the playscreen on Win on Loose depending on the player actions*/
    private void Findeljuego(boolean condicion) {
        Cbotones(false);
        LabelVisor2.setVisible(true);
        BtnNewGame.setVisible(true);
        if (condicion) {
            LblAlert.setText("Has Salvado a Kermit, hurra!!!");
            LblAlert.setVisible(true);
            LblHints.setVisible(false);
            PanelTeclado.setVisible(false);
            ImageIcon gif = Ajustar.Resize(new ImageIcon(".\\src\\main\\java\\Recursos/fynK.gif"), LabelVisor);
            LabelVisor.setIcon(gif);
            gif.setImageObserver(LabelVisor);
            LabelVisor2.setIcon(Ajustar.Resize(new ImageIcon(".\\src\\main\\java\\Recursos/Victory.png"), LabelVisor2));
            guardarDatos();
            
        } else {
            PanelTeclado.setVisible(false);
            LabelVisor2.setIcon(Ajustar.Resize(new ImageIcon(".\\src\\main\\java\\Recursos/Fallaste.jpg"), LabelVisor2));
            LblHints.setText("Ay no, pero que has hecho?");
        }
    }
    
    
    /*Check if the letter selected by the player is in the secretword*/
    public void Comprobar(char Letra, JButton btn) {
        int correct = 0;        
        for(int l = 0; l < secretWord.length(); l++) {
            if(Letra == secretWord.charAt(l)) {
                LblSecretWord[l].setText(String.valueOf(secretWord.charAt(l)));
                trysP += 1;
                correct +=1;              
                Points +=1;               
                if (trysP == secretWord.length()){
                    Findeljuego(true);
                }
            }
            }
        btn.setEnabled(false);
        if (correct == 0) {
              indice++;
              setPista();
              nPoints +=1;
              ImageIcon Imagen = new ImageIcon(imagenes[indice]);
              LabelVisor.setIcon(Imagen);             
              if (indice == (imagenes.length-1))
                  Findeljuego(false);                
        }
        if (nPoints == 0){
            Points = Points * 2;
        }
    }
public void guardarDatos() {//Save the Records History of every Game
        try {
            File archivo = new File("HistorialHanged.txt");
            FileWriter writez = new FileWriter(archivo, true);
            writez.write(nJ + ", logró obtener " + Points + " Puntos, pero perdió " + nPoints + ", " + fecha.toLocaleString() + "\n");           
            writez.close();
        } catch (Exception e) {
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelTeclado = new javax.swing.JPanel();
        BtnA = new javax.swing.JButton();
        BtnB = new javax.swing.JButton();
        BtnD = new javax.swing.JButton();
        BtnC = new javax.swing.JButton();
        BtnE = new javax.swing.JButton();
        BtnF = new javax.swing.JButton();
        BtnG = new javax.swing.JButton();
        BtnH = new javax.swing.JButton();
        BtnI = new javax.swing.JButton();
        BtnJ = new javax.swing.JButton();
        BtnK = new javax.swing.JButton();
        BtnL = new javax.swing.JButton();
        BtnM = new javax.swing.JButton();
        BtnN = new javax.swing.JButton();
        BtnO = new javax.swing.JButton();
        BtnP = new javax.swing.JButton();
        BtnQ = new javax.swing.JButton();
        BtnR = new javax.swing.JButton();
        BtnS = new javax.swing.JButton();
        BtnT = new javax.swing.JButton();
        BtnU = new javax.swing.JButton();
        BtnV = new javax.swing.JButton();
        BtnW = new javax.swing.JButton();
        BtnX = new javax.swing.JButton();
        BtnY = new javax.swing.JButton();
        BtnZ = new javax.swing.JButton();
        BtnNn = new javax.swing.JButton();
        LabelVisor2 = new javax.swing.JLabel();
        BtnExit = new javax.swing.JButton();
        BtnNewGame = new javax.swing.JButton();
        LblHints = new javax.swing.JLabel();
        LabelVisor = new javax.swing.JLabel();
        LblAlert = new javax.swing.JLabel();
        LblPH = new javax.swing.JLabel();
        LblP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ahorcado");
        setName("AGam"); // NOI18N
        setPreferredSize(new java.awt.Dimension(730, 510));
        setResizable(false);
        getContentPane().setLayout(null);

        PanelTeclado.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        PanelTeclado.setLayout(null);

        BtnA.setText("A");
        BtnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnA);
        BtnA.setBounds(6, 5, 50, 40);

        BtnB.setText("B");
        BtnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnB);
        BtnB.setBounds(73, 5, 50, 40);

        BtnD.setText("D");
        BtnD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnD);
        BtnD.setBounds(207, 5, 50, 40);

        BtnC.setText("C");
        BtnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnC);
        BtnC.setBounds(140, 5, 50, 40);

        BtnE.setText("E");
        BtnE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnE);
        BtnE.setBounds(6, 54, 50, 40);

        BtnF.setText("F");
        BtnF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnF);
        BtnF.setBounds(73, 54, 50, 40);

        BtnG.setText("G");
        BtnG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnG);
        BtnG.setBounds(140, 54, 50, 40);

        BtnH.setText("H");
        BtnH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnH);
        BtnH.setBounds(207, 54, 50, 40);

        BtnI.setText("I");
        BtnI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnI);
        BtnI.setBounds(6, 103, 50, 40);

        BtnJ.setText("J");
        BtnJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnJ);
        BtnJ.setBounds(73, 103, 50, 40);

        BtnK.setText("K");
        BtnK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnK);
        BtnK.setBounds(140, 103, 50, 40);

        BtnL.setText("L");
        BtnL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnL);
        BtnL.setBounds(207, 103, 50, 40);

        BtnM.setText("M");
        BtnM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnM);
        BtnM.setBounds(6, 152, 50, 40);

        BtnN.setText("N");
        BtnN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnN);
        BtnN.setBounds(73, 152, 50, 40);

        BtnO.setText("O");
        BtnO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnO);
        BtnO.setBounds(207, 152, 50, 40);

        BtnP.setText("P");
        BtnP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnP);
        BtnP.setBounds(6, 201, 50, 40);

        BtnQ.setText("Q");
        BtnQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnQActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnQ);
        BtnQ.setBounds(73, 201, 50, 40);

        BtnR.setText("R");
        BtnR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnR);
        BtnR.setBounds(140, 200, 50, 40);

        BtnS.setText("S");
        BtnS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnS);
        BtnS.setBounds(207, 201, 50, 40);

        BtnT.setText("T");
        BtnT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnT);
        BtnT.setBounds(6, 250, 50, 40);

        BtnU.setText("U");
        BtnU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnU);
        BtnU.setBounds(73, 250, 50, 40);

        BtnV.setText("V");
        BtnV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnV);
        BtnV.setBounds(140, 250, 50, 40);

        BtnW.setText("W");
        BtnW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnWActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnW);
        BtnW.setBounds(207, 250, 50, 40);

        BtnX.setText("X");
        BtnX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnXActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnX);
        BtnX.setBounds(40, 300, 50, 40);

        BtnY.setText("Y");
        BtnY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnYActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnY);
        BtnY.setBounds(110, 300, 50, 40);

        BtnZ.setText("Z");
        BtnZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnZActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnZ);
        BtnZ.setBounds(180, 300, 50, 40);

        BtnNn.setText("Ñ");
        BtnNn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNnActionPerformed(evt);
            }
        });
        PanelTeclado.add(BtnNn);
        BtnNn.setBounds(140, 152, 50, 40);

        getContentPane().add(PanelTeclado);
        PanelTeclado.setBounds(410, 50, 270, 350);

        LabelVisor2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        getContentPane().add(LabelVisor2);
        LabelVisor2.setBounds(410, 70, 270, 310);

        BtnExit.setText("Volver");
        BtnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExitActionPerformed(evt);
            }
        });
        getContentPane().add(BtnExit);
        BtnExit.setBounds(580, 420, 90, 40);

        BtnNewGame.setText("Nuevo Juego");
        BtnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNewGameActionPerformed(evt);
            }
        });
        getContentPane().add(BtnNewGame);
        BtnNewGame.setBounds(450, 420, 100, 40);

        LblHints.setBackground(new java.awt.Color(255, 255, 255));
        LblHints.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        LblHints.setForeground(new java.awt.Color(255, 255, 255));
        LblHints.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblHints.setToolTipText("");
        getContentPane().add(LblHints);
        LblHints.setBounds(20, 60, 420, 50);

        LabelVisor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        getContentPane().add(LabelVisor);
        LabelVisor.setBounds(150, 120, 150, 210);

        LblAlert.setFont(new java.awt.Font("Showcard Gothic", 0, 24)); // NOI18N
        LblAlert.setForeground(new java.awt.Color(255, 255, 255));
        LblAlert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblAlert.setText("Has Salvado a Kermit!!!");
        getContentPane().add(LblAlert);
        LblAlert.setBounds(0, 0, 720, 100);

        LblPH.setForeground(new java.awt.Color(204, 204, 204));
        LblPH.setText("Jugador: ");
        getContentPane().add(LblPH);
        LblPH.setBounds(10, 10, 60, 16);

        LblP.setForeground(new java.awt.Color(204, 204, 204));
        LblP.setText("P1");
        getContentPane().add(LblP);
        LblP.setBounds(70, 10, 140, 16);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHActionPerformed
        Comprobar('H',BtnH);
    }//GEN-LAST:event_BtnHActionPerformed

    private void BtnMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMActionPerformed
        Comprobar('M',BtnM);
    }//GEN-LAST:event_BtnMActionPerformed

    private void BtnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAActionPerformed
        Comprobar('A',BtnA);
    }//GEN-LAST:event_BtnAActionPerformed
    
    private void BtnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBActionPerformed
         Comprobar('B',BtnB);       
    }//GEN-LAST:event_BtnBActionPerformed

    private void BtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExitActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea regresar al menú?", "Está seguro?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
       if (respuesta == 0){
           dispose();
           AMenu AhoMenu = new AMenu();
           AhoMenu.setVisible(true);
           }         
    }//GEN-LAST:event_BtnExitActionPerformed

    private void BtnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNewGameActionPerformed
        Cbotones(true);
        ComGame();
    }//GEN-LAST:event_BtnNewGameActionPerformed

    private void BtnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCActionPerformed
        Comprobar('C',BtnC);
    }//GEN-LAST:event_BtnCActionPerformed

    private void BtnDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDActionPerformed
        Comprobar('D',BtnD);
    }//GEN-LAST:event_BtnDActionPerformed

    private void BtnEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEActionPerformed
        Comprobar('E',BtnE);
    }//GEN-LAST:event_BtnEActionPerformed

    private void BtnFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFActionPerformed
        Comprobar('F',BtnF);
    }//GEN-LAST:event_BtnFActionPerformed

    private void BtnGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGActionPerformed
        Comprobar('G',BtnG);
    }//GEN-LAST:event_BtnGActionPerformed

    private void BtnIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIActionPerformed
        Comprobar('I',BtnI);
    }//GEN-LAST:event_BtnIActionPerformed

    private void BtnJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJActionPerformed
        Comprobar('J',BtnJ);
    }//GEN-LAST:event_BtnJActionPerformed

    private void BtnKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKActionPerformed
        Comprobar('K',BtnK);
    }//GEN-LAST:event_BtnKActionPerformed

    private void BtnLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLActionPerformed
        Comprobar('L',BtnL);
    }//GEN-LAST:event_BtnLActionPerformed

    private void BtnNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNActionPerformed
       Comprobar('N',BtnN);
    }//GEN-LAST:event_BtnNActionPerformed

    private void BtnNnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNnActionPerformed
        Comprobar('Ñ',BtnN);
    }//GEN-LAST:event_BtnNnActionPerformed

    private void BtnOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOActionPerformed
        Comprobar('O',BtnO);
    }//GEN-LAST:event_BtnOActionPerformed

    private void BtnPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPActionPerformed
        Comprobar('P',BtnP);
    }//GEN-LAST:event_BtnPActionPerformed

    private void BtnQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnQActionPerformed
        Comprobar('Q',BtnQ);
    }//GEN-LAST:event_BtnQActionPerformed

    private void BtnRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRActionPerformed
        Comprobar('R',BtnR);
    }//GEN-LAST:event_BtnRActionPerformed

    private void BtnSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSActionPerformed
        Comprobar('S',BtnS);
    }//GEN-LAST:event_BtnSActionPerformed

    private void BtnTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTActionPerformed
        Comprobar('T',BtnT);
    }//GEN-LAST:event_BtnTActionPerformed

    private void BtnUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUActionPerformed
        Comprobar('U',BtnU);
    }//GEN-LAST:event_BtnUActionPerformed

    private void BtnVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVActionPerformed
        Comprobar('V',BtnV);
    }//GEN-LAST:event_BtnVActionPerformed

    private void BtnWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnWActionPerformed
        Comprobar('W',BtnW);
    }//GEN-LAST:event_BtnWActionPerformed

    private void BtnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnXActionPerformed
        Comprobar('X',BtnX);
    }//GEN-LAST:event_BtnXActionPerformed

    private void BtnYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnYActionPerformed
        Comprobar('Y',BtnY);
    }//GEN-LAST:event_BtnYActionPerformed

    private void BtnZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnZActionPerformed
        Comprobar('Z',BtnZ);
    }//GEN-LAST:event_BtnZActionPerformed

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
            java.util.logging.Logger.getLogger(AGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AGame().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnA;
    private javax.swing.JButton BtnB;
    private javax.swing.JButton BtnC;
    private javax.swing.JButton BtnD;
    private javax.swing.JButton BtnE;
    private javax.swing.JButton BtnExit;
    private javax.swing.JButton BtnF;
    private javax.swing.JButton BtnG;
    private javax.swing.JButton BtnH;
    private javax.swing.JButton BtnI;
    private javax.swing.JButton BtnJ;
    private javax.swing.JButton BtnK;
    private javax.swing.JButton BtnL;
    private javax.swing.JButton BtnM;
    private javax.swing.JButton BtnN;
    private javax.swing.JButton BtnNewGame;
    private javax.swing.JButton BtnNn;
    private javax.swing.JButton BtnO;
    private javax.swing.JButton BtnP;
    private javax.swing.JButton BtnQ;
    private javax.swing.JButton BtnR;
    private javax.swing.JButton BtnS;
    private javax.swing.JButton BtnT;
    private javax.swing.JButton BtnU;
    private javax.swing.JButton BtnV;
    private javax.swing.JButton BtnW;
    private javax.swing.JButton BtnX;
    private javax.swing.JButton BtnY;
    private javax.swing.JButton BtnZ;
    private javax.swing.JLabel LabelVisor;
    private javax.swing.JLabel LabelVisor2;
    private javax.swing.JLabel LblAlert;
    private javax.swing.JLabel LblHints;
    private javax.swing.JLabel LblP;
    private javax.swing.JLabel LblPH;
    private javax.swing.JPanel PanelTeclado;
    // End of variables declaration//GEN-END:variables

}
