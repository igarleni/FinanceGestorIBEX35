/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author Italo
 */
public class Eventos {
    
    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////EVENTOS EN MAIN//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    CarterasGestor carterasGestor = new CarterasGestor();
    private List<CarteraFrame> frameList=new ArrayList<>();
    
    //Boton añadir opcion a cartera
    //tiene que actualizar los datos variables de la cartera
    JTable listaOpcionesPUT; 
    JTextField VolumenCompra;
    JComboBox vencimientoBox;
    JComboBox opcionesBoxPUT;
    ////////////////////PARA CALL Y PUT, CAMBIAR VARIABLE TIPO
    public void botonAddOpcionPUT(){
        if (Tools.esInteger(VolumenCompra.getText())){
            int seleccionado = listaOpcionesPUT.getSelectedRow();
            String nombreCartera = (String) opcionesBoxPUT.getSelectedItem();
            
            //Añadir al arraylist
            Opcion opcion = new Opcion();
            opcion.Tipo = "PUT"; ///////////////////////"CALL" SI ES EL CASO DE OPCIONES CALL
            opcion.Ejercicio = (String)listaOpcionesPUT.getValueAt(0, seleccionado);
            opcion.Compra_Vol = (String)listaOpcionesPUT.getValueAt(1, seleccionado);
            opcion.Compra_Precio = (String)listaOpcionesPUT.getValueAt(2, seleccionado);
            opcion.Venta_Precio = (String)listaOpcionesPUT.getValueAt(3, seleccionado);
            opcion.Venta_Vol = (String)listaOpcionesPUT.getValueAt(4, seleccionado);
            opcion.Ultimo = (String)listaOpcionesPUT.getValueAt(5, seleccionado);
            opcion.Volumen = (String)listaOpcionesPUT.getValueAt(6, seleccionado);
            opcion.Hora = (String)listaOpcionesPUT.getValueAt(7, seleccionado);
            opcion.Vencimiento = (String)vencimientoBox.getSelectedItem();
            
            CarteraFrame carteraFrame = buscarCarteraFrame(nombreCartera);
            carteraFrame.addOpcion(opcion, VolumenCompra.getText());
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////HILO Y MÉTODOS DEL MAIN//////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    MEFF_Opciones meff_opciones = new MEFF_Opciones();

    private CarteraFrame buscarCarteraFrame(String nombreCartera) {
        for (CarteraFrame carteraFrame : frameList) {
            if (carteraFrame.cartera.nombre.equals(nombreCartera))
                return carteraFrame;
        }
        return null;
    }
    
    public class Tarea extends SwingWorker<Void,Integer>{

        @Override
        protected Void doInBackground() throws Exception {
            boolean looping = true;
            while(looping){
                try{
                    Thread.sleep(10000);
                }
                catch(InterruptedException e){}
                if(meff_opciones.getOptions()){
                    for (CarteraFrame carteraFrame : frameList) {
                        carteraFrame.actualizarTabla(meff_opciones.Opciones);
                    }
                }
            }
            return null;
        }

    }
}
