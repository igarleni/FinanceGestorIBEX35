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
    private final String nombre;
    private String carteraPath;
    private ArrayList<OpcionCartera> opciones;

    public String getNombre() {
        return nombre;
    }

    public String getCarteraPath() {
        return carteraPath;
    }

    public ArrayList<OpcionCartera> getOpciones() {
        return opciones;
    }
    
    public Cartera(String nombre, String filePath){
        this.nombre = nombre;
        this.carteraPath = filePath;
        opciones = new ArrayList<>();
    }

    public void setCarteraPath(String carteraPath) {
        this.carteraPath = carteraPath;
    }
    
    public void addOpcion(OpcionCartera opcion){
        opciones.add(opcion);
    }
    
    public void deleteOpcionesFecha(String fecha){
        //eliminar opciones con fecha pasada
    }
}
