/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameEvent;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rapsodia
 */
public class CarteraFrame extends JInternalFrame{
static int openFrameCount = 0;
private javax.swing.JTable jTable10;
private javax.swing.JPanel internalPane;
private JScrollPane panelScroll;
private JLabel impInvertido;

public String nombre;
public final Cartera cartera;
public float precioActualCartera;
public float gananciaCartera;
private DefaultTableModel model;
//precio actual y ganancia
    
public CarteraFrame(Cartera cartera, ArrayList<Opcion> opciones) {
        super(cartera.nombre,
          false, //resizable
          true, //closable
          false, //maximizable
          true);//iconifiable
        this.cartera = cartera;
        model = new DefaultTableModel();
        openFrameCount++;

    //...Create the GUI and put it in the window...
    //...Then set the window size or call pack...

        
        
        jTable10 = new javax.swing.JTable();
        internalPane = new javax.swing.JPanel();
        panelScroll = new JScrollPane(jTable10);
        impInvertido = new JLabel();
        impInvertido.setText("Importe invertido = "+ Tools.floatToString(cartera.importeInvertido)+ 
                "€                        Precio actual " + precioActualCartera 
                + "€                        Ganancia: " + gananciaCartera + "€" );
        
        //////BOTON ELIMINAR
        JButton botonEliminarOp = new JButton("Eliminar opciones");
        JButton botonGuardar = new JButton("Guardar");
        JButton botonGuardarComo = new JButton("Guardar copia");
        JButton botonEliminarCar = new JButton("Eliminar cartera");
        
        
        
        botonEliminarOp.setBounds (10,10,100,45);
        botonEliminarCar.setBounds (111,10,100,45);
        botonGuardar.setBounds (212,10,100,45);
        botonGuardarComo.setBounds (213,10,100,45);
        
 /////Action listeners botones       
        
        botonEliminarOp.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            int seleccionado = jTable10.getSelectedRow();
            String tipo = (String)jTable10.getValueAt(seleccionado, 1);
            String vencimiento = (String)jTable10.getValueAt(seleccionado, 2);
            String ejercicio = (String)jTable10.getValueAt(seleccionado, 3);
            String precioDeCompra = (String)jTable10.getValueAt(seleccionado, 5);
            OpcionCartera opcion = cartera.deleteOpcion(tipo, vencimiento, ejercicio, precioDeCompra);
            if (opcion == null){
                return;
            }
            model.removeRow(seleccionado); 
            precioActualCartera -= (Tools.StringToFloat(opcion.PrecioDeCompra)
                    * Tools.StringToInteger(opcion.Cantidad)); // opcion.precioActual * cantidad
            gananciaCartera = cartera.importeInvertido-precioActualCartera;
            //ELIMINAR OPCION EN LA TABLA
            }

        });      
        
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                CarterasGestor.guardarCartera(cartera);
        }
            }); 
        
        botonGuardarComo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            JFileChooser filechooser = new JFileChooser();
            filechooser.setCurrentDirectory(null);
            filechooser.addChoosableFileFilter(new CarFilter());
            filechooser.setFileFilter(new CarFilter());

            int retorno = filechooser.showOpenDialog(botonGuardarComo);

            if (retorno == JFileChooser.APPROVE_OPTION){
                File file = filechooser.getSelectedFile();
                CarterasGestor.guardarComoCartera(cartera, file.getAbsolutePath());
        }
            }
        }); 
        
        botonEliminarCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                CarterasGestor.eliminarCartera(cartera);
                openFrameCount--;
                fireInternalFrameEvent(InternalFrameEvent .INTERNAL_FRAME_CLOSING);
                dispose();
        }
            }); 
        
        
 ///////Insertar elementos en la ventana      
        
        internalPane.add(botonEliminarOp, new FlowLayout());
        internalPane.add(botonGuardar, new FlowLayout());
        internalPane.add(botonGuardarComo, new FlowLayout());
        internalPane.add(botonEliminarCar, new FlowLayout());
        internalPane.add( panelScroll, new FlowLayout());
        internalPane.add(impInvertido);
        
///////TABLA
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
        jTable10.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
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
        panelScroll.setPreferredSize(new Dimension(600, 150));
        


        internalPane.setLayout(new FlowLayout());
        this.add(internalPane);
        actualizarTabla(opciones);
        //Escritorio.add(new Cartera(), JLayeredPane.DEFAULT_LAYER);*/
    
    }



//////Métodos

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
        
        if(!Tools.esFloat(opcion.Compra_Precio))
            opcion.Compra_Precio = "0";
        if(!Tools.esFloat(opcion.Venta_Precio))
            opcion.Venta_Precio = "0";
        opcionCartera.PrecioDeCompra = opcion.Venta_Precio;
        
        //añadir la opcion a la tabla y a la cartera
        int numFilas = jTable10.getRowCount();
        if (cartera.addOpcion(opcionCartera)){ //si existe ya
            for (int i = 0; i < numFilas; i++) {
                if(opcionCartera.Tipo.equals((String)jTable10.getValueAt(i, 1))
                            && opcionCartera.Ejercicio.equals((String)jTable10.getValueAt(i, 3))
                            && opcionCartera.Vencimiento.equals((String)jTable10.getValueAt(i, 2))
                            && opcionCartera.PrecioDeCompra.equals((String)jTable10.getValueAt(i,5))
                            ){
                    //sumar cantidad
                    jTable10.setValueAt(String.valueOf(Tools.StringToInteger((String) jTable10.getValueAt(i, 0))
                            + Tools.StringToInteger(opcionCartera.Cantidad)), i, 0);
                    //actualizar precio actual
                    jTable10.setValueAt(opcion.Compra_Precio,i, 6);
                    //calcular nueva ganancia
                    float opcionGanancia = Tools.StringToFloat(opcionCartera.PrecioDeCompra)
                            - Tools.StringToFloat(opcion.Compra_Precio);
                    jTable10.setValueAt(Tools.floatToString(opcionGanancia), i, 7);
                    break;
                }
            }
        }
        else{ //si no existia
            model.addRow((Vector)null);
            jTable10.setValueAt(opcionCartera.Cantidad, numFilas, 0);
            jTable10.setValueAt(opcionCartera.Tipo, numFilas, 1);
            jTable10.setValueAt(opcionCartera.Vencimiento, numFilas, 2);
            jTable10.setValueAt(opcionCartera.Ejercicio, numFilas, 3);
            jTable10.setValueAt(opcionCartera.FechaIncorporacionCartera, numFilas, 4);
            jTable10.setValueAt(opcionCartera.PrecioDeCompra, numFilas, 5);
            jTable10.setValueAt(opcion.Venta_Precio, numFilas, 6);
            jTable10.setValueAt(Tools.StringToFloat(opcionCartera.PrecioDeCompra)
                            - Tools.StringToFloat(opcion.Venta_Precio), numFilas, 7);
        }
        precioActualCartera += (Tools.StringToFloat(opcionCartera.PrecioDeCompra)*Tools.StringToInteger(cantidad));
        this.gananciaCartera = cartera.importeInvertido-precioActualCartera;
        
        //Reimprimir datos de la cartera
        impInvertido.setText("Importe invertido = "+ Tools.floatToString(cartera.importeInvertido)+ 
                "€                        Precio actual " + Tools.floatToString(precioActualCartera) 
                + "€                        Ganancia: " + Tools.floatToString(this.gananciaCartera) + "€" );
    
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
                        float opcionPrecioActual;
                        if(Tools.esFloat(opcion.Venta_Precio))
                            opcionPrecioActual = Tools.StringToFloat(opcion.Venta_Precio);
                        else
                            opcionPrecioActual = 0;
                        precioActualCartera += opcionPrecioActual * Tools.StringToInteger((String)jTable10.getValueAt(i, 0)); //x Cantidad
                        jTable10.setValueAt(Tools.floatToString(opcionPrecioActual),i, 6);
                        
                        float opcionGanancia = Tools.StringToFloat((String)jTable10.getValueAt(i, 5)) //precio de compra
                                - opcionPrecioActual;
                        jTable10.setValueAt(Tools.floatToString(opcionGanancia), i, 7);
                    }
                    
                }
            }
        gananciaCartera = cartera.importeInvertido - precioActualCartera;
        impInvertido.setText("Importe invertido = "+ Tools.floatToString(cartera.importeInvertido)+ 
                "€                        Precio actual " + Tools.floatToString(precioActualCartera) 
                + "€                        Ganancia: " + Tools.floatToString(this.gananciaCartera) + "€" );
    }
    
    
}
