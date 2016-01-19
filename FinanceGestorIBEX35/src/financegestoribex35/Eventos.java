/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Italo
 */
public class Eventos {
    CarterasGestor carterasGestor = new CarterasGestor();
    private List<JInternalFrame> frameList=new ArrayList<>();
    Cartera cartera = new Cartera("prueba", "prueba");
    
    //Archivo --> Abrir Cartera..
    //Carga la cartera, calcula datos variables y añade la ventana al escritorio
    Component Open; ////////////Variable donde esta guardada el boton open
    JComboBox opcionesBoxPUT; //CALL Y PUT
    JComboBox opcionesBoxCALL; //CALL Y PUT
    public void abrirCartera(){
        JFileChooser filechooser = new JFileChooser();
        filechooser.setCurrentDirectory(null);
        filechooser.addChoosableFileFilter(new TxtFilter());
        
        int retorno = filechooser.showOpenDialog(Open);
        
        if (retorno == JFileChooser.APPROVE_OPTION){
            File file = filechooser.getSelectedFile();
            Cartera cartera = carterasGestor.cargarCartera(file);
            //añadir nueva ventana "carteraFrame" con esta cartera
            CarteraFrame carteraFrame = new CarteraFrame(cartera);
            carteraFrame.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosing(InternalFrameEvent e){
                    frameList.remove(e.getInternalFrame());
                    int nElem = opcionesBoxCALL.getItemCount();
                    for (int i = 0; i < nElem; i++) {
                        if (opcionesBoxPUT.getItemAt(i).equals(cartera.nombre))
                            opcionesBoxPUT.remove(i);
                        if (opcionesBoxCALL.getItemAt(i).equals(cartera.nombre))
                            opcionesBoxCALL.remove(i);
                    }
                }
            });
            carteraFrame.actualizarCartera();
            carteraFrame.setBounds(22, 140, 650, 300); 
            carteraFrame.setVisible(true);
            Escritorio.add(carteraFrame);
            try {
                carteraFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {}
            //añadirlo al JComboBox
            opcionesBoxPUT.addItem(cartera.nombre);
            opcionesBoxCALL.addItem(cartera.nombre);
        }
    }
    
    //Se lanza cuando se actualizan los datos MEFF
    //Se actualizan datos de la cartera y de las opciones
    public void actualizarCarteras(){
        for (Cartera cartera : carterasGestor.carteras) {
            for (OpcionCartera opcion : cartera.opciones) {
                
            }
        }
    }
    
    ///////////////////funcion propia de CarteraFrame
    public void actualizarCartera(String nombre){
        ///////////////////////////actualizar a raiz del SPOT?
    }
    
    //Boton añadir opcion a cartera
    //tiene que actualizar los datos variables de la cartera
    JTable listaOpciones; //PUT O CALL, depende de donde esté el boton de este event, y asi con todas
    JTextField VolumenCompra;
    JComboBox vencimientoBox;
    ////////////////////PARA CALL Y PUT, CAMBIAR VARIABLE TIPO
    public void botonAddOpcion(){
        if (Tools.esFloat(VolumenCompra.getText())){
            int seleccionado = listaOpciones.getSelectedRow();
            String nombreCartera = (String) carteraBox.getSelectedItem();
            
            //Añadir al arraylist
            OpcionCartera opcion = new OpcionCartera();
            opcion.Tipo = "PUT"; ///////////////////////"CALL" SI ES EL CASO DE OPCIONES CALL
            opcion.Ejercicio = (String)listaOpciones.getValueAt(0, seleccionado);
            opcion.PrecioDeCompra = (String)listaOpciones.getValueAt(3, seleccionado);
            opcion.Vencimiento = (String)vencimientoBox.getSelectedItem();
            opcion.FechaIncorporacionCartera = Tools.getFechaActual(); 

            opcion.PrecioActual = (String)listaOpciones.getValueAt(2, seleccionado);
            opcion.ganancia = Tools.floatToString(Tools.StringToFloat(opcion.PrecioDeCompra)-Tools.StringToFloat(opcion.PrecioActual));
            carterasGestor.carteras.get(carterasGestor.buscarCartera(nombreCartera)).addOpcion(opcion);
            
            //Actualizar los datos generales de la cartera 
            carterasGestor.carteras.get(carterasGestor.buscarCartera(nombreCartera)).ganancia += Tools.StringToFloat(opcion.ganancia);
            carterasGestor.carteras.get(carterasGestor.buscarCartera(nombreCartera)).importeInvertido += Tools.StringToFloat(opcion.PrecioDeCompra);
            carterasGestor.carteras.get(carterasGestor.buscarCartera(nombreCartera)).precioActual += Tools.StringToFloat(opcion.PrecioActual);
            /////////////////////////////Actualizar la ventana cartera??
        }
    }
    
    ////////////////////////////PONER LA POSICION DE VENCIMIENTO Y EJERCICIO BIEN //ACTUALIZAR TABLA
    //JTable listaOpciones; //Tabla de la lista de opciones de la cartera
    ///////////String nombreCartera //variable global donde se guarda el nombre de la cartera usaada por el "carteraFrame"
    public void botonDeleteOpcion(){
        int seleccionado = listaOpciones.getSelectedRow();
        String vencimiento = (String)listaOpciones.getValueAt(0, seleccionado);
        String ejercicio = (String)listaOpciones.getValueAt(0, seleccionado);
        carterasGestor.carteras.get(carterasGestor.buscarCartera(nombreCartera)).deleteOpcion(vencimiento, ejercicio);
        /////////ACTUALIZAR TABLA
    }
    
    
}
