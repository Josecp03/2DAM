/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author josec
 */
public class conexion {

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CineDB?useSSL=false&serverTimezone=UTC",
                    "root",
                    ""
            );
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: No se encontró el driver de MySQL.");
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    
    
    

}
