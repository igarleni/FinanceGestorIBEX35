/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.awt.Component;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Italo
 */
public class Eventos {
    CarterasGestor carterasGestor = new CarterasGestor();
    Cartera cartera = new Cartera("prueba", "prueba");
    
    //Archivo --> Abrir Cartera..
    //Carga la cartera y calcula los datos variables, luego se lo pasa al jFrame "carteraFrame"
    Component Open; //Variable donde esta guardada el boton open
    public void abrirCartera(){
        JFileChooser filechooser = new JFileChooser();
        filechooser.setCurrentDirectory(null);
        filechooser.addChoosableFileFilter(new TxtFilter());
        
        int retorno = filechooser.showOpenDialog(Open);
        
        if (retorno == JFileChooser.APPROVE_OPTION){
            File file = filechooser.getSelectedFile();
            String nombre = carterasGestor.cargarCartera(file);
            Cartera cartera = carterasGestor.carteras.get(carterasGestor.buscarCartera(nombre));
            ///////////////////////AÑADIR AQUI EL CODIGO PARA CREAR LA VENTANA CON CARTERA
            /**
             * añadir nueva ventana "carteraFrame" con esta cartera
             * actualizar el JComboBox de las opciones para añadir la nueva cartera
             */
        }
    }
    
    //Cerrar ventana cartera
    //Lanza una ventanita de si quieres guardar (hay que crearla con sus botones
    String nombreCartera; //Tiene que ser una variable global del jFrame "carteraFrame"
    public void cerrarVentanaCartera(){
        carterasGestor.cerrarCartera(nombreCartera);
        /////////////////////////quitar esta cartera de los JComboBox de las opciones
    }
    
    //Se lanza cuando se actualizan los datos MEFF
    //Se actualizan datos de la cartera y de las opciones
    public void actualizarCarteras(){
        for (Cartera cartera : carterasGestor.carteras) {
            for (OpcionCartera opcion : cartera.opciones) {
                
            }
        }
    }
    
    public void actualizarCartera(String nombre){
        int index = carterasGestor.buscarCartera(nombre);
        //actualizar a raiz del SPOT?
    }
    
    //Boton añadir opcion a cartera
    //tiene que actualizar los datos variables de la cartera
    JTable listaOpciones; //PUT O CALL, depende de donde esté el boton de este event, y asi con todas
    JComboBox vencimientoBox;
    JTextField VolumenCompra;
    JComboBox carteraBox;
    //PARA CALL Y PUT, CAMBIAR VARIABLE TIPO
    public void botonAddOpcion(){
        if (Tools.esFloat(VolumenCompra.getText())){
            int seleccionado = listaOpciones.getSelectedRow();
            String nombreCartera = (String) carteraBox.getSelectedItem();
            
            //Añadir al arraylist
            OpcionCartera opcion = new OpcionCartera();
            opcion.Tipo = "PUT"; //"CALL" SI ES EL CASO DE OPCIONES CALL
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
    
    JTable ListaOpciones; //Tabla de la lista de opciones de la cartera
    public void botonDeleteOpcion(){
        int seleccionado = ListaOpciones.getSelectedRow();
        carterasGestor.carteras.get(carterasGestor.buscarCartera(nombreCartera)).deleteOpcion(nombreCartera, nombreCartera); //nombreCartera es variable globalr del "carteraFrame"
        
    }
    
    
}
