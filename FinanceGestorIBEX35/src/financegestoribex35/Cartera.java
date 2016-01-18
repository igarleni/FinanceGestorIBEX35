/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.util.ArrayList;

/**
 *
 * @author Italo
 */
public class Cartera {
    public final String nombre;
    public String carteraPath;
    
    public float importeInvertido; //suma de los (precioCompra x volumen) de sus opciones
    public float precioActual; //Suma de los precios actuales de sus opciones
    public float ganancia; //importeInvertido - precioActual
    public final ArrayList<OpcionCartera> opciones;

    public Cartera(String nombre, String filePath){
        this.nombre = nombre;
        this.carteraPath = filePath;
        opciones = new ArrayList<>();
    }
    
    public void addOpcion(OpcionCartera opcion){
        opciones.add(opcion);
    }
    
    public void deleteOpcion(String Vencimiento, String Ejercicio){
        
    }
}
