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
    public String Volumen;
    public String Tipo;
    public String Vencimiento;
    public String Ejercicio; //precio asociado a la fecha de vencimiento (strike)
    public String FechaIncorporacionCartera;
    public String PrecioDeCompra; //precio al que se compro cada opcion(precio de venta del mercado) (1xVolumen)
    
    //Actualizable
    public String PrecioActual; //Precio de compra actual de la opcion
    public String ganancia; //precio de compra mercado - precio comprado
    
}
