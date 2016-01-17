/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

<<<<<<< HEAD
/**
 *
 * @author Rapsodia
 */
public class Cartera {
    
    
    ...//In the constructor of InternalFrameDemo, a JFrame subclass:
    desktop = new JDesktopPane();
    createFrame(); //Create first window
    setContentPane(desktop);
    ...
    //Make dragging a little faster but perhaps uglier.
    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
...
protected void createFrame() {
    MyInternalFrame frame = new MyInternalFrame();
    frame.setVisible(true);
    desktop.add(frame);
    try {
        frame.setSelected(true);
    } catch (java.beans.PropertyVetoException e) {}
}

...//In the constructor of MyInternalFrame, a JInternalFrame subclass:
static int openFrameCount = 0;
static final int xOffset = 30, yOffset = 30;

public MyInternalFrame() {
    super("Document #" + (++openFrameCount),
          true, //resizable
          true, //closable
          true, //maximizable
          true);//iconifiable
    //...Create the GUI and put it in the window...
    //...Then set the window size or call pack...
    ...
    //Set the window's location.
    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
}
    
    
=======
import java.util.ArrayList;

/**
 *
 * @author Italo
 */
public class Cartera {
    private final String nombre;
    private String carteraPath;
    private final ArrayList<OpcionCartera> opciones;

    public Cartera(String nombre, String filePath){
        this.nombre = nombre;
        this.carteraPath = filePath;
        opciones = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getCarteraPath() {
        return carteraPath;
    }

    public ArrayList<OpcionCartera> getOpciones() {
        return opciones;
    }

    public void setCarteraPath(String carteraPath) {
        this.carteraPath = carteraPath;
    }
    
    public void addOpcion(OpcionCartera opcion){
        opciones.add(opcion);
    }
>>>>>>> origin/master
    
}
