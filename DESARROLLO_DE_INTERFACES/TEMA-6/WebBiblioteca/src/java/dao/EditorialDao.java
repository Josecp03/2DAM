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
import model.Editorial;

/**
 *
 * @author josec
 */
public class EditorialDao {

    public static boolean registrar(Editorial e) {

        Connection con = null;
        PreparedStatement st = null;

        try {

            String SQL = "INSERT INTO editoriales (nit, nombre, telefono, direccion, email, sitioweb)"
                    + "values (?, ?, ?, ?, ?, ?);";
            con = conexion.conectar();

            if (con == null) {
                return false;
            }

            st = con.prepareStatement(SQL);
            st.setString(1, e.getNit());
            st.setString(2, e.getNombre());
            st.setString(3, e.getTelefono());
            st.setString(4, e.getDireccion());
            st.setString(5, e.getEmail());
            st.setString(6, e.getSitioweb());

            if (st.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {

            Logger.getLogger(Editorial.class.getName()).log(Level.SEVERE, null, ex);
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

    public static ArrayList<Editorial> listar() {

        ArrayList<Editorial> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultado = null;

        try {

            String SQL = "select * from editoriales";
            con = conexion.conectar();

            if (con == null) {
                return null;
            }

            st = con.prepareStatement(SQL);
            resultado = st.executeQuery();

            Editorial edi;

            while (resultado.next()) {

                edi = new Editorial();
                edi.setNit(resultado.getString("nit"));
                edi.setNombre(resultado.getString("nombre"));
                edi.setTelefono(resultado.getString("telefono"));
                edi.setDireccion(resultado.getString("direccion"));
                edi.setEmail(resultado.getString("email"));
                edi.setSitioweb(resultado.getString("sitioweb"));
                lista.add(edi);

            }

            return lista;

        } catch (SQLException ex) {

            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
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

                Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    public static String getEditorial(String nit) {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultado = null;

        try {

            String SQL = "select nombre from editoriales where nit=?";
            con = conexion.conectar();

            if (con == null) {
                return null;
            }

            st = con.prepareStatement(SQL);
            st.setString(1, nit);
            resultado = st.executeQuery();

            if (resultado.next()) {
                return (resultado.getString("nombre"));
            }

            return "--";

        } catch (SQLException ex) {

            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
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

                Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

}
