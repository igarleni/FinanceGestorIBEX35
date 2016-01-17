/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Italo
 */
public class TxtFilter extends FileFilter {
            
    @Override
    public boolean accept(File f) {         
        if (f.isDirectory()) {             
            return true;
        }         
        String filename = f.getName();
        int dot = filename.lastIndexOf('.');
        String extension = filename.substring(dot + 1);
  
        if (extension != null) {             
            if( extension.equals("txt"))               
                return true;             
            else               
                return false;             
        }           
        return false;     
    }       
    @Override
    public String getDescription() {         
        return "Ficheros de Carteras";     
    } 
}
