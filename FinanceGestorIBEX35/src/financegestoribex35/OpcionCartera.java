/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.util.GregorianCalendar;

/**
 *
 * @author Italo
 */
public class OpcionCartera {
    
    //Persistente en archivo
    public int Volumen;
    public String Tipo;
    public GregorianCalendar Vencimiento;
    public float Ejercicio; //precio asociado a la fecha de vencimiento (strike)
    public GregorianCalendar FechaIncorporacionCartera;
    public float PrecioDeCompra; //precio al que se compro cada opcion(precio de venta del mercado) (1xVolumen)
    
    //Actualizable
    public float PrecioActual; //Precio de compra actual de la opcion
    public float ganancia; //precio de compra mercado - precio comprado
    
}
