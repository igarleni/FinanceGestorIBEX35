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
    
    public float importeInvertido; //suma de los (precioDeCompra x volumen) de sus opciones
    public final ArrayList<OpcionCartera> opciones;

    public Cartera(String nombre, String filePath){
        this.nombre = nombre;
        this.carteraPath = filePath;
        opciones = new ArrayList<>();
    }
    
    public void addOpcion(OpcionCartera opcion){ 
        for (OpcionCartera opcione : opciones) {
            if (opcione.Ejercicio.equals(opcion.Ejercicio) && opcione.Vencimiento.equals(opcion.Vencimiento) && opcione.PrecioDeCompra.equals(opcion.PrecioDeCompra)) {
                opcione.Cantidad += opcion.Cantidad;
                break;
            }
        }
        importeInvertido += (Tools.StringToFloat(opcion.PrecioDeCompra)*Tools.StringToInteger(opcion.Cantidad));
        opciones.add(opcion);
    }
    
    public OpcionCartera deleteOpcion(String vencimiento, String ejercicio, String precioDeCompra){
        for (int i = 0; i < opciones.size(); i++) {
            if (opciones.get(i).Ejercicio.equals(ejercicio) 
                    && opciones.get(i).Vencimiento.equals(vencimiento)
                    && opciones.get(i).PrecioDeCompra.equals(precioDeCompra)
                    ){
                OpcionCartera opcion = opciones.get(i);
                importeInvertido -= (Tools.StringToFloat(opcion.PrecioDeCompra)*Tools.StringToInteger(opcion.Cantidad));
                return opciones.remove(i);
            }
        }
        return null;
    }
}
