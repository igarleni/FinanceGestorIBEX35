/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

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
    
    private static Float toFloat(String texto){
        texto = texto.replace(".", "");
        texto = texto.replace(",", ".");
        return Float.valueOf(texto);
    }
    
    private static String floatToString(Float numero){
        String resultado = String.valueOf(numero);
        int posPunto = resultado.indexOf(".");
        int contador = 0;
        for (int i = posPunto; i >= 0; i--) {
            if (contador==3){
                //añadir punto
                contador = 0;
            }
            else
                contador++;
        }
        if(resultado.charAt(0) == '.')
            resultado = resultado.substring(1);
        resultado = resultado.replace(".", ",");
        return resultado;
    }
}
