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
    
    public CarterasGestor(){
    }
    
    //EJEMPLO: 3 PUT 20161231 3000.4 20160131 3000.5
    public Cartera cargarCartera(File filePath) {
        FileReader f;
        try {
            f = new FileReader(filePath.getAbsolutePath());
            BufferedReader br = new BufferedReader(f);
            String linea;
            Cartera cartera = new Cartera(br.readLine(), filePath.getAbsolutePath());
            boolean carteraEditada = false;
            while((linea = br.readLine()) != null){
                OpcionCartera opcionCartera = extraerDatosOpcion(linea);
                if(Tools.fechaVencida(opcionCartera.Vencimiento)){
                    carteraEditada = true;
                }else
                    cartera.addOpcion(opcionCartera);
            }
            f.close();
            if(carteraEditada) guardarCartera(cartera);
            return cartera;
            
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
                opcionCartera.Cantidad = linea.substring(0, index);
                
                index++;
                opcionCartera.Tipo = "";
                while(linea.charAt(index) != ' '){
                    opcionCartera.Tipo += linea.charAt(index);
                    index++;
                }
                
                index++;
                opcionCartera.Vencimiento = linea.substring(index,index+8);
                
                index += 9;
                opcionCartera.Ejercicio = "";
                while(linea.charAt(index) != ' '){
                    opcionCartera.Ejercicio += linea.charAt(index);
                    index++;
                }
                
                index++;
                opcionCartera.FechaIncorporacionCartera = linea.substring(index, index+8);

                index += 9;
                opcionCartera.PrecioDeCompra = linea.substring(index);
                return opcionCartera;
    }

    public boolean guardarCartera(Cartera cartera){
        FileWriter f;
        try {
            f = new FileWriter(new File(cartera.carteraPath));
            BufferedWriter br = new BufferedWriter(f);
            ArrayList<OpcionCartera> opciones = cartera.opciones;

            br.write(cartera.nombre);
            for (OpcionCartera opcionCartera : opciones) {
                br.write(String.valueOf(opcionCartera.Cantidad) + " " +
                    opcionCartera.Tipo + " " +
                    opcionCartera.Vencimiento + " " +
                    opcionCartera.Ejercicio + " " +
                    opcionCartera.FechaIncorporacionCartera + " " +
                    opcionCartera.PrecioDeCompra+ "\n");
            }
            f.close();
            br.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean guardarComoCartera(Cartera cartera, String filePath){
        Integer i;
        String oldCarteraPath = cartera.carteraPath;
        cartera.carteraPath =filePath ;
        if (guardarCartera(cartera))
            return true;
        else{
            cartera.carteraPath = oldCarteraPath;
            return false;
        }
    }
    
    public Cartera crearCartera(String nombre, String filePath){
        Cartera cartera = new Cartera(nombre, filePath);
        guardarCartera(cartera);
        return cartera;
    }
    
    public boolean eliminarCartera(){
        Cartera cartera = new Cartera("prueba", "prueba"); //Esta variable será la cartera del "carteraFrame"
        new File(cartera.carteraPath).delete();
        return true;
    }
    
}
