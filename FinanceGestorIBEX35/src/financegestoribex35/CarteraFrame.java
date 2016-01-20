/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Rapsodia
 */
public class CarteraFrame extends JInternalFrame{
static int openFrameCount = 0;
static final int xOffset = 30, yOffset = 30;
private javax.swing.JTable jTable10;
private javax.swing.JPanel internalPane;
    
public CarteraFrame() {
    
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

        
        ///////TABLA
        
               int fila = 0;
        DefaultTableModel tablemodel = (DefaultTableModel)jTable10.getModel();
        }
        tablemodel.setRowCount(6);
        for(int i=0;i<nOptions;i++){
        
        Opcion opcion = opciones.Opciones.get(i);
        // actualiza la tabla de PUT
        if(opcion.Tipo.equals("PUT") && opcion.Vencimiento.equals(selectedItem)){
        TablaOpcionesPUT.setValueAt(opcion.Ejercicio, fila, 0);
        TablaOpcionesPUT.setValueAt(opcion.Compra_Vol, fila, 1);
        TablaOpcionesPUT.setValueAt(opcion.Compra_Precio, fila, 2);
        TablaOpcionesPUT.setValueAt(opcion.Venta_Precio, fila, 3);
        TablaOpcionesPUT.setValueAt(opcion.Venta_Vol, fila, 4);
        TablaOpcionesPUT.setValueAt(opcion.Ultimo, fila, 5);
        TablaOpcionesPUT.setValueAt(opcion.Volumen, fila, 6);
        TablaOpcionesPUT.setValueAt(opcion.Hora, fila, 7);
        fila++;
        }
    }
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
        //Escritorio.add(new Cartera(), JLayeredPane.DEFAULT_LAYER);*/
    
    }

    public void actualizarCartera(){
    
    
    }
}
