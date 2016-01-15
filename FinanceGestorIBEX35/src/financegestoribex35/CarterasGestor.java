/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Italo
 */
public class CarterasGestor {
    
    /**
     * Mesa de trabajo donde se guardan las Carteras que está usando el jFrame
     * 
     * 1.-Se editan las carteras accediendo directamente al ArrayList de la clase
     * 
     * 2.-CargarCartera(filePath): carga la cartera del archivo ubicado en filePath y lo mete en el ArrayList
     * 
     * 3.-BuscarCartera(nombreCartera): esta funcion te devuelve la posicion de la cartera en el ArrayList
     * 
     * 4.-GuardarCartera(nombreCartera): guarda lo que hay en la cartera en su filePath correspondiente
     * 
     * 5.-CrearCartera(nombreCartera, filePath): hay que pasarle un filePath de dónde se va a crear la cartera
     * 
     * 6.-EliminarCartera(nombreCartera): elimina la cartera y su archivo ubicado en filePath
     * 
     * 7.-CerrarCartera(nombreCartera): quita la cartera del ArrayList pero borrar el archivo
     */
    
    public ArrayList<Cartera> carteras; //mesa de trabajo
    
    public CarterasGestor(){
        carteras = new ArrayList<>();
    }
    
    //EJEMPLO: 3 PUT 20161231 3000.4 20160131 3000.5
    public String cargarCartera(File filePath) {
        FileReader f;
        try {
            f = new FileReader(filePath.getAbsolutePath());
            BufferedReader br = new BufferedReader(f);
            String linea;
            Cartera cartera = new Cartera(br.readLine(), filePath.getAbsolutePath());
            boolean carteraEditada = false;
            while((linea = br.readLine()) != null){
                OpcionCartera opcionCartera = extraerDatosOpcion(linea);
                if(opcionCartera.Vencimiento.after(new GregorianCalendar())){
                    carteraEditada = true;
                }else
                    cartera.addOpcion(opcionCartera);
            }
            f.close();
            
            carteras.add(cartera);
            if(carteraEditada) guardarCartera(cartera.getNombre());
            return cartera.getNombre();
            
        } catch (IOException ex) {
            return null;
        }
    }
    
    private OpcionCartera extraerDatosOpcion(String linea) {
                OpcionCartera opcionCartera = new OpcionCartera();
                
                int index = 0;
                while(linea.charAt(index) != ' '){
                    index++;
                }
                opcionCartera.Volumen = Integer.valueOf(linea.substring(0, index));
                
                index++;
                opcionCartera.Tipo = "";
                while(linea.charAt(index) != ' '){
                    opcionCartera.Tipo += linea.charAt(index);
                    index++;
                }
                
                index++;
                opcionCartera.Vencimiento.set(Integer.valueOf(linea.substring(index, index+4)),
                    Integer.valueOf(linea.substring(index+4, index+6))-1,
                    Integer.valueOf(linea.substring(index+6, index+8)));
                
                index += 9;
                int indexEnd = index;
                while(linea.charAt(indexEnd) != ' '){
                    indexEnd++;
                }
                opcionCartera.Ejercicio = Float.valueOf(linea.substring(index, indexEnd));
                index = indexEnd+1;
                opcionCartera.FechaIncorporacionCartera.set(Integer.valueOf(linea.substring(index, index+4)),
                    Integer.valueOf(linea.substring(index+4, index+6))-1,
                    Integer.valueOf(linea.substring(index+6, index+8)));

                index += 9;
                opcionCartera.PrecioDeCompra = Float.valueOf(linea.substring(index));
                return opcionCartera;
    }

    public boolean guardarCartera(String nombre){
        Cartera cartera;
        Integer i;
        if ((i = buscarCartera(nombre)) == null)
            return false;
        cartera = carteras.get(i.intValue());
        
        FileWriter f;
        try {
            f = new FileWriter(new File(cartera.getCarteraPath()));
            BufferedWriter br = new BufferedWriter(f);
            ArrayList<OpcionCartera> opciones = cartera.getOpciones();

            br.write(cartera.getNombre());
            for (OpcionCartera opcionCartera : opciones) {
                br.write(String.valueOf(opcionCartera.Volumen) + " " +
                    opcionCartera.Tipo + " " +
                    fechaToString(opcionCartera.Vencimiento) + " " +
                    String.valueOf(opcionCartera.Ejercicio) + " " +
                    fechaToString(opcionCartera.FechaIncorporacionCartera) + " " +
                    String.valueOf(opcionCartera.PrecioDeCompra)+ "\n");
            }
            f.close();
            br.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean guardarComoCartera(String nombre, String filePath){
        Cartera cartera;
        Integer i;
        if ((i = buscarCartera(nombre)) == null)
            return false;
        String oldCarteraPath = carteras.get(i.intValue()).getCarteraPath();
        carteras.get(i.intValue()).setCarteraPath(filePath);
        if (guardarCartera(nombre))
            return true;
        else{
            carteras.get(i.intValue()).setCarteraPath(oldCarteraPath);
            return false;
        }
    }
    
    private String fechaToString(GregorianCalendar fecha) {
        String resultado = "";
        resultado += String.valueOf(fecha.get(Calendar.YEAR));
        resultado += String.valueOf(fecha.get(Calendar.MONTH)+1);
        resultado += String.valueOf(fecha.get(Calendar.DAY_OF_MONTH));
        return resultado;
    }
    
    public void crearCartera(String nombre, String filePath){
        Cartera cartera = new Cartera(nombre, filePath);
        carteras.add(cartera);
    }
    
    public Integer buscarCartera(String nombre){
        for (Integer i = 0; i < carteras.size(); i++) {
            if (carteras.get(i).getNombre().equals(nombre))
                return i;
        }
        return null;
    }
    
    public boolean eliminarCartera(String nombre){
        Cartera cartera;
        Integer i;
        if ((i = buscarCartera(nombre)) == null)
            return false;
        cartera = carteras.get(i);
        new File(cartera.getCarteraPath()).delete();
        carteras.remove(i.intValue());
        return true;
    }
    
    public boolean cerrarCartera(String nombre){
        Integer i;
        if ((i = buscarCartera(nombre)) == null)
            return false;
        carteras.remove(i.intValue());
        return true;
    }

}
