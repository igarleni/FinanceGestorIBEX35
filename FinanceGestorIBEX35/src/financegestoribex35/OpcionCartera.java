/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.util.Calendar;

/**
 *
 * @author Italo
 */
public class OpcionCartera {
    
    public int Volumen;
    public String Tipo;
    public Calendar Vencimiento;
    public float Ejercicio; //precio asociado a la fecha de vencimiento (strike)
    public Calendar FechaIncorporacionCartera;
    public float PrecioDeCompra; //precio al que se compro esta opcion
    
    /**
     * Al mostrar por pantalla, mostrar tambien
     * --precio de venta actual (precio de compra en el mercado)
     * --ganancia de valor (precio de compra mercado - precio comprado)
     */
}
