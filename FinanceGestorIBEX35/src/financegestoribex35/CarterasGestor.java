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
    private ArrayList<Cartera> carteras; //mesa de trabajo
    
    public CarterasGestor(){
        carteras = new ArrayList<>();
    }

    //COMPROBAR SI OPCION FUERA DE FECHA
    public String cargarCartera(File filePath) {
        FileReader f;
        try {
            f = new FileReader(filePath.getAbsolutePath());
            BufferedReader br = new BufferedReader(f);
            String linea;
            Cartera cartera = new Cartera(br.readLine(), filePath.getAbsolutePath());
            while((linea = br.readLine()) != null){
                char c = linea.charAt(0);
                OpcionCartera opcionCartera = new OpcionCartera();
                opcionCartera.Volumen = linea;
                opcionCartera.Tipo = linea;
                opcionCartera.Vencimiento = linea;
                opcionCartera.Ejercicio = linea;
                opcionCartera.FechaIncorporacionCartera = linea;
                opcionCartera.PrecioDeCompra = linea;
                cartera.addOpcion(opcionCartera);
            }
            f.close();
            
            carteras.add(cartera);
            return cartera.getNombre();
        } catch (IOException ex) {
            return null;
        }
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
                br.write(opcionCartera.Volumen + " " +
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
    
    public boolean guardarComoCartera(String nombre, String filePath){
        Cartera cartera;
        Integer i;
        if ((i = buscarCartera(nombre)) == null)
            return false;
        cartera = carteras.get(i.intValue());
        
        cartera.setCarteraPath(filePath);
        FileWriter f;
        try {
            f = new FileWriter(new File(cartera.getCarteraPath()));
            BufferedWriter br = new BufferedWriter(f);
            ArrayList<OpcionCartera> opciones = cartera.getOpciones();

            br.write(cartera.getNombre());
            for (OpcionCartera opcionCartera : opciones) {
                br.write(opcionCartera.Volumen + " " +
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
    
    public void crearCartera(String nombre, String filePath){
        Cartera cartera = new Cartera(nombre, filePath);
        carteras.add(cartera);
    }
    
    public Cartera getCartera(String nombre){
        Integer i;
        if ((i = buscarCartera(nombre)) == null)
            return null;
        return carteras.get(i.intValue());
    }
    
    private Integer buscarCartera(String nombre){
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
