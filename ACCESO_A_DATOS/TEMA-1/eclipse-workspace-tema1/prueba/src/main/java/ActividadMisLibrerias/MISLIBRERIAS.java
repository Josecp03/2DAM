package ActividadMisLibrerias;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement()
public class MISLIBRERIAS {
    private ArrayList<Libreria> listaLibrerias;

   
    public MISLIBRERIAS(ArrayList<Libreria> listaLibrerias) {
		super();
		this.listaLibrerias = listaLibrerias;
		 }
    public MISLIBRERIAS(){}
    
    //Wrapper, envoltura alrededor la representación XML
    @XmlElementWrapper(name = "MISLIBRERIAS")  
    @XmlElement(name = "Libreria")
    public ArrayList<Libreria> getListaLibrerias() {
        return listaLibrerias;    }
 
    public void setListaLibrerias(ArrayList<Libreria> listaLibrerias) {
        this.listaLibrerias = listaLibrerias;    }
 }