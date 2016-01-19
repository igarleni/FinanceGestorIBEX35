/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import javax.swing.BoxLayout;

/**
 *
 * @author Rapsodia
 */
public class CarteraFrame extends JInternalFrame{
static int openFrameCount = 0;
static final int xOffset = 30, yOffset = 30;
private javax.swing.JTable jTable10;
private javax.swing.JPanel internalPane;
    
public CarteraFrame(Cartera cartera) {
        super("" + ("NOMBRECARTERA"),
          true, //resizable
          true, //closable
          true, //maximizable
          true);//iconifiable
    //...Create the GUI and put it in the window...
    //...Then set the window size or call pack...
        
        
        jTable10 = new javax.swing.JTable();
        internalPane = new javax.swing.JPanel();
        
        
    //Set the window's location.
    //setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        JButton boton = new JButton("Soy un boton");
        boton.setVisible(true);
        boton.setSize(100,45);
        internalPane.add(boton);

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nº de opciones", "Tipo", "Fecha de vencimiento", "Precio de ejercicio", "Fecha de incorporación", "Imp. de compra en el mercado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable10.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable10.getColumnModel().getColumnCount() > 0) {
            jTable10.getColumnModel().getColumn(0).setResizable(false);
            jTable10.getColumnModel().getColumn(1).setResizable(false);
            jTable10.getColumnModel().getColumn(2).setResizable(false);
            jTable10.getColumnModel().getColumn(3).setResizable(false);
            jTable10.getColumnModel().getColumn(4).setResizable(false);
            jTable10.getColumnModel().getColumn(5).setResizable(false);
        }

        internalPane.add(jTable10);

        internalPane.setLayout(new BoxLayout(internalPane, WIDTH));
        this.add(internalPane);
        //Escritorio.add(new Cartera(), JLayeredPane.DEFAULT_LAYER);
    
    }

    public void actualizarCartera(){
    
    
    }
}
