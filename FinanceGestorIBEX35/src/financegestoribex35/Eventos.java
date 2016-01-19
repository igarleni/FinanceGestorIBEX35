/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

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
    
    //Archivo --> Abrir Cartera..
    //Carga la cartera, calcula datos variables y añade la ventana al escritorio
    Component Abrir; ////////////Variable donde esta guardada el boton abrir
    JComboBox opcionesBoxPUT; //CALL Y PUT
    JComboBox opcionesBoxCALL; //CALL Y PUT
    public void abrirCartera(){
        JFileChooser filechooser = new JFileChooser();
        filechooser.setCurrentDirectory(null);
        filechooser.addChoosableFileFilter(new TxtFilter());
        
        int retorno = filechooser.showOpenDialog(Abrir);
        
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
            frameList.add(carteraFrame);
            Escritorio.add(carteraFrame);
            try {
                carteraFrame.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {}
            //añadirlo al JComboBox
            opcionesBoxPUT.addItem(cartera.nombre);
            opcionesBoxCALL.addItem(cartera.nombre);
        }
    }
    
    //Boton añadir opcion a cartera
    //tiene que actualizar los datos variables de la cartera
    JTable listaOpcionesPUT; 
    JTextField VolumenCompra;
    JComboBox vencimientoBox;
    ////////////////////PARA CALL Y PUT, CAMBIAR VARIABLE TIPO
    public void botonAddOpcionPUT(){
        if (Tools.esFloat(VolumenCompra.getText())){
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
            carteraFrame.addOpcion(opcion);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////CARTERA FARME///////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    Cartera cartera = new Cartera("prueba","prueba");
    //Datos ACTUALIZABLES
    public float precioActual; //Suma de los precios actuales de sus opciones
    public float ganancia; //cartera.importeInvertido - precioActual
    
    JTable listaOpcionesCartera; //Tabla de la lista de opciones de la cartera
    public void botonDeleteOpcion(){
        int seleccionado = listaOpcionesCartera.getSelectedRow();
        ////////////////////////////PONER LA POSICION DE VENCIMIENTO, EJERCICIO, PRECIOACTUAL Y GANANCIA BIEN
        String vencimiento = (String)listaOpcionesCartera.getValueAt(0, seleccionado);
        String ejercicio = (String)listaOpcionesCartera.getValueAt(0, seleccionado);
        OpcionCartera opcion = cartera.deleteOpcion(vencimiento, ejercicio);
        if (opcion == null){
            System.out.println("Fallo al borrar opcion!");
            return;
        }
        precioActual -= Tools.StringToFloat((String)listaOpcionesCartera.getValueAt(0, seleccionado));
        ganancia -= Tools.StringToFloat((String)listaOpcionesCartera.getValueAt(0, seleccionado));
        /////////////ACTUALIZAR TABLA
        
    }
    
    //Añade una opcionCartera a la cartera a partir de una opcion
    public void addOpcion(Opcion opcion){
        //////////////////////////////////crear una OpcionCartera a  partir de una Opcion
        OpcionCartera opcionCartera = new OpcionCartera();
        opcionCartera.FechaIncorporacionCartera = Tools.getFechaActual();
        cartera.addOpcion(opcionCartera);
        precioActual += Tools.StringToFloat(opcion.Venta_Precio);
        ganancia += Tools.StringToFloat(opcion.Compra_Precio) - Tools.StringToFloat(opcion.Venta_Precio);
        ///////////ACTUALIZAR TABLA
    }
    
    ///////////////////funcion para actualizar DATOS VARIABLES según SPOT y opciones nuevas
    public void actualizarCartera(ArrayList<Opcion> opciones){
        //Buscar las opciones que tengo en el arraylist y luego compararlas
    }
    
    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////HILO Y MÉTODOS DEL MAIN//////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    private CarteraFrame buscarCarteraFrame(String nombre){
        for (CarteraFrame carteraFrame : frameList) {
            if (carteraFrame.cartera.nombre.equals(nombre))
                return carteraFrame;
        }
        return null;
    }
    
    public class Tarea extends SwingWorker<Void,Integer>{

        @Override
        protected Void doInBackground() throws Exception {
            boolean looping = true;
            Integer cuenta = 0;
            while(true){
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){}
                for (CarteraFrame carteraFrame : frameList) {
                    carteraFrame.actualizarCartera();
                }
            }
            return null;
        }

        @Override
        protected void process(List<Integer> lista){
            int last = lista.size();
            BarraProgreso.setValue(lista.get(last-1).intValue());
        }
        
        @Override
        protected void done(){
            BotonCancelar.setEnabled(false);
            BotonAceptar.setEnabled(true);
            Informacion.setText("Tarea Finalizada!");
            Dialogo.setCursor(null);
        }
    }
    //Se lanza cuando se actualizan los datos MEFF
    //Se actualizan datos de la cartera y de las opciones
    public void actualizarCarteras(){
        
    }
}
