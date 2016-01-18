/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import javax.swing.JInternalFrame;

/**
 *
 * @author Rapsodia
 */
public class CarteraFrame extends JInternalFrame{
static int openFrameCount = 0;
static final int xOffset = 30, yOffset = 30;
private javax.swing.JTabbedPane jTabbedPane1;
private javax.swing.JScrollPane jScrollPane17;
private javax.swing.JScrollPane jScrollPane18;
private javax.swing.JScrollPane jScrollPane19;
private javax.swing.JScrollPane jScrollPane20;
private javax.swing.JTable jTable10;
private javax.swing.JTable jTable11;
private javax.swing.JTable jTable12;
private javax.swing.JTable jTable13;
private javax.swing.JPanel internalPane;
    
public CarteraFrame() {
        super("" + ("NOMBRECARTERA"),
          true, //resizable
          true, //closable
          true, //maximizable
          true);//iconifiable
    //...Create the GUI and put it in the window...
    //...Then set the window size or call pack...
        
        
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        internalPane = new javax.swing.JPanel();
        
        
    //Set the window's location.
    //setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
}
    
    
        
        
    
    
       /*           
        JButton boton = new JButton("Soy un boton");
        boton.setVisible(true);
        boton.setSize(100,45);
        Escritorio.add(internalFrame);
        internalFrame.add(boton);

        
        
        internalFrame.setVisible(true);
        
        
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
        jScrollPane17.setViewportView(jTable10);
        jTable10.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable10.getColumnModel().getColumnCount() > 0) {
            jTable10.getColumnModel().getColumn(0).setResizable(false);
            jTable10.getColumnModel().getColumn(1).setResizable(false);
            jTable10.getColumnModel().getColumn(2).setResizable(false);
            jTable10.getColumnModel().getColumn(3).setResizable(false);
            jTable10.getColumnModel().getColumn(4).setResizable(false);
            jTable10.getColumnModel().getColumn(5).setResizable(false);
        }

        jTabbedPane1.addTab("Cartera1", jScrollPane17);

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane18.setViewportView(jTable11);
        jTable11.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable11.getColumnModel().getColumnCount() > 0) {
            jTable11.getColumnModel().getColumn(0).setResizable(false);
            jTable11.getColumnModel().getColumn(1).setResizable(false);
            jTable11.getColumnModel().getColumn(2).setResizable(false);
            jTable11.getColumnModel().getColumn(3).setResizable(false);
            jTable11.getColumnModel().getColumn(4).setResizable(false);
            jTable11.getColumnModel().getColumn(5).setResizable(false);
        }

        jTabbedPane1.addTab("Cartera 2", jScrollPane18);

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane19.setViewportView(jTable12);
        jTable12.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable12.getColumnModel().getColumnCount() > 0) {
            jTable12.getColumnModel().getColumn(0).setResizable(false);
            jTable12.getColumnModel().getColumn(1).setResizable(false);
            jTable12.getColumnModel().getColumn(2).setResizable(false);
            jTable12.getColumnModel().getColumn(3).setResizable(false);
            jTable12.getColumnModel().getColumn(4).setResizable(false);
            jTable12.getColumnModel().getColumn(5).setResizable(false);
        }

        jTabbedPane1.addTab("Cartera3", jScrollPane19);

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane20.setViewportView(jTable13);
        jTable13.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable13.getColumnModel().getColumnCount() > 0) {
            jTable13.getColumnModel().getColumn(0).setResizable(false);
            jTable13.getColumnModel().getColumn(1).setResizable(false);
            jTable13.getColumnModel().getColumn(2).setResizable(false);
            jTable13.getColumnModel().getColumn(3).setResizable(false);
            jTable13.getColumnModel().getColumn(4).setResizable(false);
            jTable13.getColumnModel().getColumn(5).setResizable(false);
        }
        
        //internalPane.setResizable(false);
        jTabbedPane1.addTab("Cartera 4", jScrollPane20);
        internalPane.add(jTabbedPane1);
        internalPane.setLayout(new BoxLayout(internalPane, WIDTH));
        internalFrame.add(jTabbedPane1);
        //Escritorio.add(new Cartera(), JLayeredPane.DEFAULT_LAYER);
    
    }*/
}
