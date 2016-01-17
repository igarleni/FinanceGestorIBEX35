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
        }
    }
    
    //Cerrar ventana cartera
    //Lanza una ventanita de si quieres guardar (hay que crearla con sus botones
    Component ventanaGuardar;
    public void cerrarVentanaCartera(){
        carterasGestor.cerrarCartera(cartera.nombre);
    }
    
    //Se lanza cuando se actualizan los datos MEFF
    //Se actualizan datos de la cartera y de las opciones
    public void actualizarCarteras(){
        
    }
    
    //Boton añadir opcion a cartera
    //tiene que actualizar los datos variables de la cartera
    JTable listaOpcionesPUT;
    JComboBox Vencimiento;
    JTextField VolumenCompra;
    public void botonAddOpcionPUT(){
        if (Tools.esFloat(VolumenCompra.getText())){
            int seleccionado = listaOpcionesPUT.getSelectedRow();
            //Añadir al arraylist
            OpcionCartera opcion = new OpcionCartera();
            opcion.Tipo = "PUT";
            opcion.Hora;
            opcion.Volumen = VolumenCompra;
            opcion.Ultimo;
            opcion.Compra_Vol;
            opcion.Compra_Precio; //precio al que se puede vender de esta opcion si se posee
            opcion.Venta_Vol;
            opcion.Venta_Precio; //precio para comprar esta opcion si se desea una (si, los conceptos son contrarios)
            opcion.Vencimiento;
            opcion.Ejercicio;
            /////////////////////////////Actualizar la ventana cartera??
        }
    }
    
    public void botonDeleteOpcion(){
        
    }
    
    
}
