/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;

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
private JScrollPane panelScroll;
private JLabel impInvertido;
private OpcionCartera f;
//precio actual y ganancia
    
public CarteraFrame(Cartera cartera) {
    
        super(cartera.nombre,
          true, //resizable
          true, //closable
          true, //maximizable
          true);//iconifiable
    //...Create the GUI and put it in the window...
    //...Then set the window size or call pack...
        System.out.println(Tools.floatToString(cartera.importeInvertido));

        jTable10 = new javax.swing.JTable();
        internalPane = new javax.swing.JPanel();
        panelScroll = new JScrollPane(jTable10);
        impInvertido = new JLabel();
        impInvertido.setText(Tools.floatToString(cartera.importeInvertido));
        
    //Set the window's location.
    //setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        JButton boton = new JButton("Soy un boton");
        boton.setSize(100,45);
        internalPane.add( panelScroll, BorderLayout.CENTER );
        internalPane.add(boton);
        internalPane.add(impInvertido);
        
        ///////TABLA
        DefaultTableModel model;
        model = new DefaultTableModel();
        jTable10.setModel(model);
        model.addColumn("Nº de opciones");
        model.addColumn("Tipo");
        model.addColumn("Fecha de vencimiento");
        model.addColumn("Precio de ejercicio");
        model.addColumn("Fecha de incorporación");
        model.addColumn("Comprado por");
        model.addColumn("Precio actual");
        model.addColumn("Ganancia");
        jTable10.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable10.getTableHeader().setReorderingAllowed(false);
        
        

        for(int i=0;i<cartera.opciones.size();i++){
            f = cartera.opciones.get(i);
            model.addRow(new Object[]{null,null,null,null,null,null,null,null});
            jTable10.setValueAt(f.Cantidad, i, 0);
            jTable10.setValueAt(f.Tipo, i, 1);
            jTable10.setValueAt(f.Vencimiento, i, 2);
            jTable10.setValueAt(f.Ejercicio, i, 3);
            jTable10.setValueAt(f.FechaIncorporacionCartera, i, 4);
            jTable10.setValueAt(f.PrecioDeCompra, i, 5);
        }

        
        
        
        

        internalPane.setLayout(new BoxLayout(internalPane, WIDTH));
        this.add(internalPane);
        //Escritorio.add(new Cartera(), JLayeredPane.DEFAULT_LAYER);*/
    
    }

    public void actualizarCartera(){
    
    
    }
}
