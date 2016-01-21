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
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Italo
 */
public class CarterasGestor {
    
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
            linea = br.readLine();
            while(linea != null){
                System.out.println(linea);
                OpcionCartera opcionCartera = extraerDatosOpcion(linea);
                if(Tools.fechaVencida(opcionCartera.Vencimiento)){
                    carteraEditada = true;
                }else{
                    cartera.importeInvertido += Tools.StringToFloat(opcionCartera.PrecioDeCompra);
                    cartera.addOpcion(opcionCartera);
                }
                linea = br.readLine();
            }
            f.close();
           // if(carteraEditada) guardarCartera(cartera);
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
                opcionCartera.Vencimiento = linea.substring(index,index+12);
                
                index += 13;
                opcionCartera.Ejercicio = "";
                while(linea.charAt(index) != ' '){
                    opcionCartera.Ejercicio += linea.charAt(index);
                    index++;
                }
                
                index++;
                opcionCartera.FechaIncorporacionCartera = linea.substring(index, index+12);

                index += 13;
                opcionCartera.PrecioDeCompra = linea.substring(index);
                return opcionCartera;
    }

    public boolean guardarCartera(Cartera cartera){
        PrintWriter f;
        try {
            f = new PrintWriter(new File(cartera.carteraPath));
            f.println(cartera.nombre);
            for (OpcionCartera opcionCartera : cartera.opciones) {
                f.println(opcionCartera.Cantidad + " " +
                    opcionCartera.Tipo + " " +
                    opcionCartera.Vencimiento + " " +
                    opcionCartera.Ejercicio + " " +
                    opcionCartera.FechaIncorporacionCartera + " " +
                    opcionCartera.PrecioDeCompra+ "\n");
            }
            f.close();
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
