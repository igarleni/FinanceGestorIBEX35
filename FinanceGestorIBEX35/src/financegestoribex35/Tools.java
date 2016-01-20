/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Italo
 */
public class Tools {
    
    public static boolean esFloat(String texto){
        try{
            Float.parseFloat(texto);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    
    public static Float StringToFloat(String texto){
        texto = texto.replace(".", "");
        texto = texto.replace(",", ".");
        return Float.valueOf(texto);
    }
    
    public static String floatToString(Float numero){
        String resultado = String.valueOf(numero);
        resultado = resultado.replace(".", ",");
        int posPunto = resultado.indexOf(",");
        if (posPunto == -1)
            posPunto = resultado.length();
        int contador = 0;
        for (int i = posPunto-1; i >= 0; i--) {
            if (contador==2){
                resultado = resultado.substring(0, i) + "." + resultado.substring(i, resultado.length());
                contador = 0;
            }
            else
                contador++;
        }
        if(resultado.charAt(0) == '.')
            resultado = resultado.substring(1);
        return resultado;
    }
    
    public static boolean fechaVencida(String fecha){
        GregorianCalendar fechaOpcion = new GregorianCalendar();
        fechaOpcion.set(Integer.valueOf(fecha.substring(4, 8)),
                    Integer.valueOf(fecha.substring(2, 4))-1,
                    Integer.valueOf(fecha.substring(0, 2)));
        return fechaOpcion.after(new GregorianCalendar());
    }
    
    public static String getFechaActual(){
        GregorianCalendar fechaActual = new GregorianCalendar();
        String resultado = String.valueOf(fechaActual.get(Calendar.DAY_OF_MONTH));
        resultado += String.valueOf(fechaActual.get(Calendar.MONTH)+1);
        resultado += String.valueOf(fechaActual.get(Calendar.YEAR));
        return resultado;
    }
    
    public static int StringToInteger(String texto){
        return Integer.parseInt(texto);
    }
    
    public static boolean esInteger(String texto){
        try{
            Integer.parseInt(texto);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    public static String darFormatoFecha(Object fechaPalabras){
        String fecha = fechaPalabras.toString();
        return fecha.substring(8, 11) + "" + getMesNumerico(fecha.substring(4, 6)) 
                + "" + fecha.substring(1, 2) ;
    }
    
    public static String getMesNumerico(String fecha){
        if(fecha.equals("ene")) return "01";
        if(fecha.equals("feb")) return "02";
        if(fecha.equals("mar")) return "03";
        if(fecha.equals("abr")) return "04";
        if(fecha.equals("may")) return "05";
        if(fecha.equals("jun")) return "06";
        if(fecha.equals("jul")) return "07";
        if(fecha.equals("ago")) return "08";
        if(fecha.equals("sep")) return "09";
        if(fecha.equals("oct")) return "10";
        if(fecha.equals("nov")) return "11";
        return "12";
    }
}
