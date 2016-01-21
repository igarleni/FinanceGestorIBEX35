/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.awt.BorderLayout;
import java.util.ArrayList;
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

public String nombre;
public final Cartera cartera;
public float precioActualCartera;
public float gananciaCartera;
//precio actual y ganancia
    
public CarteraFrame(Cartera cartera, ArrayList<Opcion> opciones) {
        
        super(cartera.nombre,
          true, //resizable
          true, //closable
          true, //maximizable
          true);//iconifiable
        this.cartera = cartera;
    //...Create the GUI and put it in the window...
    //...Then set the window size or call pack...
        
        
        jTable10 = new javax.swing.JTable();
        internalPane = new javax.swing.JPanel();
        panelScroll = new JScrollPane(jTable10);
        impInvertido = new JLabel();
        impInvertido.setText("Importe invertido = "+ Tools.floatToString(cartera.importeInvertido)+ "€                        Precio actual " + "                        Ganancia: ");
        JButton boton = new JButton("Eliminar opciones");
        boton.setSize(100,45);
    //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        
        
        internalPane.add(boton, BorderLayout.LINE_END);
        internalPane.add( panelScroll, BorderLayout.CENTER );
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
        
        
        OpcionCartera f;
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
        actualizarTabla(opciones);
        //Escritorio.add(new Cartera(), JLayeredPane.DEFAULT_LAYER);*/
    
    }
    /**
     * Añade una opcionCartera a la cartera a partir de una opcion y actualiza variables de la carteraFrame
     * @param opcion opcion a añadir
     * @param cantidad cantidad de opciones de este tipo compradas
     */
    public void addOpcion(Opcion opcion, String cantidad){
        OpcionCartera opcionCartera = new OpcionCartera();
        opcionCartera.Tipo = opcion.Tipo;
        opcionCartera.Cantidad = cantidad;
        opcionCartera.Vencimiento = opcion.Vencimiento;
        opcionCartera.Ejercicio = opcion.Ejercicio;
        opcionCartera.FechaIncorporacionCartera = Tools.getFechaActual();
        opcionCartera.PrecioDeCompra = opcion.Venta_Precio;
        cartera.addOpcion(opcionCartera);
        precioActualCartera += (Tools.StringToFloat(opcion.Venta_Precio)*Tools.StringToInteger(cantidad));
        gananciaCartera = cartera.importeInvertido-precioActualCartera;
        //AÑADIR OPCION EN LA TABLA (TENEMOS LOS DATOS VARIABLES EN OPCION -> ENTRADA)
        //"Nº de opciones", "Tipo", "Fecha de vencimiento",
        //"Precio de ejercicio", "Fecha de incorporación", "Imp. de compra en el mercado",
        //"Precio actual", "Ganancia"
    }
    
    
       /**
     * Actualiza las variables PrecioActual(=opcionMEFF.Venta_Precio) y ganancia (=PrecioDeCompra - PrecioActual)
     * de las opciones que se muestra en la tabla
     * También actualiza las variables de carteraFrame ganancia y precioActual
     * @param opciones opciones de MEFF nuevas
     */
    public void actualizarTabla(ArrayList<Opcion> opciones){
        //Buscar las opciones que tengo en el arraylist y luego compararlas
        precioActualCartera = 0;
        int numFilas = jTable10.getRowCount();
        for (int i = 0; i < numFilas; i++) {
            for (Opcion opcion : opciones) {
                    if(opcion.Tipo.equals((String)jTable10.getValueAt(i, 1))
                            && opcion.Ejercicio.equals((String)jTable10.getValueAt(i, 3))
                            && opcion.Vencimiento.equals((String)jTable10.getValueAt(i, 2))
                            ){
                        float opcionPrecioActual = Tools.StringToFloat(opcion.Venta_Precio);
                        precioActualCartera += opcionPrecioActual * Tools.StringToInteger((String)jTable10.getValueAt(i, 0)); //x Cantidad
                        jTable10.setValueAt(opcion.Venta_Precio,i, 6);
                        
                        float opcionGanancia = Tools.StringToFloat((String)jTable10.getValueAt(i, 5)) //precio de compra
                                - Tools.StringToFloat(opcion.Venta_Precio);
                        jTable10.setValueAt(Tools.floatToString(opcionGanancia), i, 7);
                    }
                    
                }
            }
        gananciaCartera = cartera.importeInvertido - precioActualCartera;
    }
    
    
}
