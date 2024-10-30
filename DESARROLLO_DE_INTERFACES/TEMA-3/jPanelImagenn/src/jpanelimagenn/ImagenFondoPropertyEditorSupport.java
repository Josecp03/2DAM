/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanelimagenn;

import java.awt.Component;
import java.beans.PropertyEditorSupport;
import java.io.File;

/**
 *
 * @author josec
 */
public class ImagenFondoPropertyEditorSupport extends PropertyEditorSupport{

    private ImagenFondoPanel imagenFondoPanel = new ImagenFondoPanel();
    
    @Override
    public boolean supportsCustomEditor() {
        return true;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component getCustomEditor() {
        return imagenFondoPanel; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJavaInitializationString() {
        ImagenFondo imagenFondo = imagenFondoPanel.getSelectedValue();
        File rutaImagen = (File) imagenFondo.getRutaimagen();
        String ruta = rutaImagen.getAbsolutePath();
        ruta = ruta.replace("\\", "/");
        return "new jpanelimagenn.ImagenFondo(new java.io.File(\""+ruta+"\"), "+imagenFondo.getOpacidad()+"f)";

    }

    @Override
    public Object getValue() {
        
        return imagenFondoPanel.getSelectedValue(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
