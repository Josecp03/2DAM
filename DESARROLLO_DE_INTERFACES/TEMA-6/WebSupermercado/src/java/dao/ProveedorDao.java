/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Proveedor;

/**
 *
 * @author josec
 */
public class ProveedorDao {

    public static boolean registrar(Proveedor p) {

        Connection con = null;
        PreparedStatement st = null;

        try {

            String SQL = "INSERT INTO proveedores (nit_proveedor, nombre_proveedor, telefono_proveedor, direccion_proveedor, correo_electronico_proveedor)"
                    + " VALUES (?, ?, ?, ?, ?);";

            con = conexion.conectar();

            if (con == null) {
                return false;
            }

            st = con.prepareStatement(SQL);
            st.setString(1, p.getNit());
            st.setString(2, p.getNombre());
            st.setString(3, p.getTelefono());
            st.setString(4, p.getDireccion());
            st.setString(5, p.getCorreo_electronico());

            if (st.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {

            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {

                if (st != null) {
                    st.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    public static ArrayList<Proveedor> listar() {

        ArrayList<Proveedor> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultado = null;

        try {

            String SQL = "select * from proveedores";
            con = conexion.conectar();

            if (con == null) {
                return null;
            }

            st = con.prepareStatement(SQL);
            resultado = st.executeQuery();

            Proveedor prov;

            while (resultado.next()) {
                prov = new Proveedor();
                prov.setNit(resultado.getString("nit_proveedor"));
                prov.setNombre(resultado.getString("nombre_proveedor"));
                prov.setTelefono(resultado.getString("telefono_proveedor"));
                prov.setDireccion(resultado.getString("direccion_proveedor"));
                prov.setCorreo_electronico(resultado.getString("correo_electronico_proveedor"));
                lista.add(prov);
            }

            return lista;

        } catch (SQLException ex) {

            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        } finally {

            try {

                if (resultado != null) {
                    resultado.close();
                }

                if (st != null) {
                    st.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    public static String getProveedor(String nit) {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultado = null;

        try {

            String SQL = "select nombre_proveedor from proveedores where nit_proveedor=?";
            con = conexion.conectar();

            if (con == null) {
                return null;
            }

            st = con.prepareStatement(SQL);
            st.setString(1, nit);
            resultado = st.executeQuery();

            if (resultado.next()) {
                return (resultado.getString("nombre_proveedor"));
            }

            return "--";

        } catch (SQLException ex) {

            Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return "--";

        } finally {

            try {

                if (resultado != null) {
                    resultado.close();
                }

                if (st != null) {
                    st.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger.getLogger(ProveedorDao.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

}
