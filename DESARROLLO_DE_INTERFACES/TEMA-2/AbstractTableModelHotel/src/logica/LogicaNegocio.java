/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import beans.Cliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josec
 */
public class LogicaNegocio {

    private List<Cliente> listaClientes = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public LogicaNegocio() throws ParseException {

        listaClientes = new ArrayList<>();

        try {
            listaClientes = new ArrayList<>();
            listaClientes.add(new Cliente("José", "Corrochano Pardo", sdf.parse("08-10-2024"), sdf.parse("12-10-2024"), 1, "Doble", "11111111H", "España", sdf.parse("28-04-2003")));
            listaClientes.add(new Cliente("María", "López García", sdf.parse("15-11-2023"), sdf.parse("18-11-2023"), 2, "Individual", "22222222J", "España", sdf.parse("15-05-1998")));
            listaClientes.add(new Cliente("Carlos", "Fernández Martín", sdf.parse("05-12-2024"), sdf.parse("10-12-2024"), 1, "Doble", "33333333K", "España", sdf.parse("12-08-1995")));
            listaClientes.add(new Cliente("Ana", "Gómez Sánchez", sdf.parse("20-01-2025"), sdf.parse("25-01-2025"), 3, "Triple", "44444444L", "España", sdf.parse("30-01-1990")));
            listaClientes.add(new Cliente("David", "Pérez Rodríguez", sdf.parse("10-02-2024"), sdf.parse("15-02-2024"), 2, "Individual", "55555555M", "España", sdf.parse("14-03-1985")));
            listaClientes.add(new Cliente("Lucía", "Díaz López", sdf.parse("25-03-2024"), sdf.parse("30-03-2024"), 1, "Doble", "66666666N", "España", sdf.parse("22-07-2001")));
            listaClientes.add(new Cliente("Javier", "Martínez Ruiz", sdf.parse("05-04-2024"), sdf.parse("12-04-2024"), 2, "Doble", "77777777P", "España", sdf.parse("05-09-1987")));
            listaClientes.add(new Cliente("Laura", "Hernández Ortiz", sdf.parse("15-05-2024"), sdf.parse("20-05-2024"), 1, "Individual", "88888888Q", "España", sdf.parse("19-11-1993")));
            listaClientes.add(new Cliente("Miguel", "Jiménez Sánchez", sdf.parse("01-06-2024"), sdf.parse("05-06-2024"), 3, "Triple", "99999999R", "España", sdf.parse("10-06-2000")));
            listaClientes.add(new Cliente("Elena", "Moreno Vega", sdf.parse("10-07-2024"), sdf.parse("15-07-2024"), 2, "Doble", "00000000S", "España", sdf.parse("28-12-1980")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

}
