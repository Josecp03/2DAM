/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpanelimagenn;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author josec
 */
public class ImagenFondo implements Serializable {

    private File rutaimagen;
    private Float opacidad;

    public ImagenFondo(File rutaimagen, Float opacidad) {
        this.rutaimagen = rutaimagen;
        this.opacidad = opacidad;
    }

    public File getRutaimagen() {
        return rutaimagen;
    }

    public void setRutaimagen(File rutaimagen) {
        this.rutaimagen = rutaimagen;
    }

    public Float getOpacidad() {
        return opacidad;
    }

    public void setOpacidad(Float opacidad) {
        this.opacidad = opacidad;
    }

}
