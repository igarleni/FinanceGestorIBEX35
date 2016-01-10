package financegestoribex35;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juan
 */
public class Opcion {
    public String Tipo;
    public String Hora;
    public String Volumen;
    public String Ultimo;
    public String Compra_Vol;
    public String Compra_Precio; //precio al que se puede vender de esta opcion si se posee
    public String Venta_Vol;
    public String Venta_Precio; //precio para comprar esta opcion si se desea una (si, los conceptos son contrarios)
    public String Vencimiento;
    public String Ejercicio; //precio asociado a la fecha de vencimiento (strike)
}



