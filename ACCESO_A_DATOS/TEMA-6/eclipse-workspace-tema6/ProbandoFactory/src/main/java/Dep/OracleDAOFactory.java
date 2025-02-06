package Dep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleDAOFactory extends DAOFactory {
    static Connection conexion = null;
    // Driver de Oracle
    static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    // URL de conexión (por ejemplo, para Oracle XE)
    static String URLDB = "jdbc:oracle:thin:@localhost:1521:xe";
    static String USUARIO = "UNIDAD6";
    static String CLAVE = "UNIDAD6";

    public OracleDAOFactory() { }

    /**
     * Crea y retorna la conexión a Oracle.
     * Si la conexión ya existe, se retorna la misma.
     */
    public static Connection crearConexion() {
        if (conexion == null) {
            try {
                Class.forName(DRIVER); // Cargar el driver de Oracle
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OracleDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                conexion = DriverManager.getConnection(URLDB, USUARIO, CLAVE);
            } catch (SQLException ex) {
                System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n");
                System.out.printf("Mensaje   : %s %n", ex.getMessage());
                System.out.printf("SQL estado: %s %n", ex.getSQLState());
                System.out.printf("Cód error : %s %n", ex.getErrorCode());
            }
        }
        return conexion;
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new OracleDepartamentoImpl();
    }
    
    /**
     * Método para visualizar el contenido de la tabla departamentos.
     */
    public void visualizarDepartamentos() {
        Connection conn = crearConexion();
        String sql = "SELECT dept_no, dnombre, loc FROM departamentos";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("Contenido de la tabla DEPARTAMENTOS:");
            while (rs.next()) {
                int dept_no = rs.getInt("dept_no");
                String dnombre = rs.getString("dnombre");
                String loc = rs.getString("loc");
                System.out.printf("Departamento: %d, Nombre: %s, Localidad: %s%n", dept_no, dnombre, loc);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al visualizar departamentos:");
            System.out.printf("Mensaje   : %s %n", e.getMessage());
            System.out.printf("SQL estado: %s %n", e.getSQLState());
            System.out.printf("Cód error : %s %n", e.getErrorCode());
        }
    }
}
