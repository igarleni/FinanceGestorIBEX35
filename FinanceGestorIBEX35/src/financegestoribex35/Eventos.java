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
        filechooser.addChoosableFileFilter(new CarFilter());
        
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
    ///////////////////////////////CARTERA FARME///////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * VARIABLES GLOBALES
     * HAY QUE INICIALIZARLAS CUANDO SE CREA LA CARTERAFRAME
     */
    Cartera cartera = new Cartera("prueba","prueba");
    //Datos ACTUALIZABLES
    public float precioActual = 0; //Suma de los precios actuales de sus opciones x Cantidades
    public float ganancia = 0; //cartera.importeInvertido - precioActual
    JTable listaOpcionesCartera; //Tabla de la lista de opciones de la cartera
    
    /**
     * Evento: seleccionar una opcion para borrarla de la cartera
     */
    public void botonDeleteOpcion(){
        int seleccionado = listaOpcionesCartera.getSelectedRow();
        String tipo = (String)listaOpcionesCartera.getValueAt(1, seleccionado);
        String vencimiento = (String)listaOpcionesCartera.getValueAt(2, seleccionado);
        String ejercicio = (String)listaOpcionesCartera.getValueAt(3, seleccionado);
        String precioDeCompra = (String)listaOpcionesCartera.getValueAt(5, seleccionado);
        OpcionCartera opcion = cartera.deleteOpcion(tipo, vencimiento, ejercicio, precioDeCompra);
        if (opcion == null){
            System.out.println("Fallo al borrar opcion!");
            return;
        }
        
        precioActual -= (Tools.StringToFloat(opcion.PrecioDeCompra)
                * Tools.StringToInteger(opcion.Cantidad)); // opcion.precioActual * cantidad
        ganancia = cartera.importeInvertido-precioActual;
        //ELIMINAR OPCION EN LA TABLA
        
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
        precioActual += (Tools.StringToFloat(opcion.Venta_Precio)*Tools.StringToInteger(cantidad));
        ganancia = cartera.importeInvertido-precioActual;
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
        precioActual = 0;
        int numFilas = listaOpcionesCartera.getRowCount();
        for (int i = 0; i < numFilas; i++) {
            for (Opcion opcion : opciones) {
                    if(opcion.Tipo.equals((String)listaOpcionesCartera.getValueAt(1, i))
                            && opcion.Ejercicio.equals((String)listaOpcionesCartera.getValueAt(3, i))
                            && opcion.Vencimiento.equals((String)listaOpcionesCartera.getValueAt(2, i))
                            ){
                        float opcionPrecioActual = Tools.StringToFloat(opcion.Venta_Precio);
                        precioActual += opcionPrecioActual * Tools.StringToInteger((String)listaOpcionesCartera.getValueAt(0, i)); //x Cantidad
                        listaOpcionesCartera.setValueAt(opcion.Venta_Precio, 6, i);
                        
                        float opcionGanancia = Tools.StringToFloat((String)listaOpcionesCartera.getValueAt(5, i)) //precio de compra
                                - Tools.StringToFloat(opcion.Venta_Precio);
                        listaOpcionesCartera.setValueAt(Tools.floatToString(opcionGanancia), 7, i);
                    }
                    
                }
            }
        ganancia = cartera.importeInvertido - precioActual;
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
            Integer cuenta = 0;
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
    //Se lanza cuando se actualizan los datos MEFF
    //Se actualizan datos de la cartera y de las opciones
}
