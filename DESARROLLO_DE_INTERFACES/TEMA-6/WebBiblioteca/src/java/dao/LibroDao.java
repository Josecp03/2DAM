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
import model.Libro;

/**
 *
 * @author josec
 */
public class LibroDao {

    public static boolean registrar(Libro l) throws SQLException {

        Connection con = null;
        PreparedStatement st = null;

        try {

            String SQL = "INSERT INTO libros (isbn, titulo, descripcion, nombre_autor, publicacion, fecha_registro, codigo_categoria, nit_editorial) VALUES (?, ?, ?, ?, ?, (select now()), ?, ?)";

            con = conexion.conectar();

            if (con == null) {
                return false;
            }

            st = con.prepareStatement(SQL);
            st.setString(1, l.getIsbn());
            st.setString(2, l.getTitulo());
            st.setString(3, l.getDescripcion());
            st.setString(4, l.getNombre_autor());
            st.setString(5, l.getPublicacion());
            st.setInt(6, l.getCodigo_categoria());
            st.setString(7, l.getNit_editorial());

            if (st.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {

            Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);
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

                Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    public static ArrayList<Libro> listar() {

        ArrayList<Libro> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet resultado = null;

        try {

            String SQL = "select * from libros";
            con = conexion.conectar();

            if (con == null) {
                return null;
            }

            st = con.prepareStatement(SQL);
            resultado = st.executeQuery();

            Libro l;
            while (resultado.next()) {
                l = new Libro();
                l.setIsbn(resultado.getString("isbn"));
                l.setTitulo(resultado.getString("titulo"));
                l.setNombre_autor(resultado.getString("nombre_autor"));
                l.setDescripcion(resultado.getString("descripcion"));
                l.setFecha_registro(resultado.getString("fecha_registro"));
                l.setPublicacion(resultado.getString("publicacion"));
                l.setNit_editorial(resultado.getString("nit_editorial"));
                l.setCodigo_categoria(resultado.getInt("codigo_categoria"));
                lista.add(l);
            }

            return lista;

        } catch (SQLException ex) {

            Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);
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

                Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    public static boolean actualizar(Libro l) {

        Connection con = null;
        PreparedStatement st = null;

        try {

            String SQL = "UPDATE libros SET "
                    + "titulo = ?, "
                    + "descripcion = ?, "
                    + "nombre_autor = ?, "
                    + "publicacion = ?, "
                    + "codigo_categoria = ?, "
                    + "nit_editorial = ? "
                    + "WHERE isbn = ?";
            con = conexion.conectar();

            if (con == null) {
                return false;
            }

            st = con.prepareStatement(SQL);
            st.setString(1, l.getTitulo());
            st.setString(2, l.getDescripcion());
            st.setString(3, l.getNombre_autor());
            st.setString(4, l.getPublicacion());
            st.setInt(5, l.getCodigo_categoria());
            st.setString(6, l.getNit_editorial());
            st.setString(7, l.getIsbn()); 

            if (st.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {

            Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);
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

                Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    public static boolean eliminar(Libro l) {

        Connection con = null;
        PreparedStatement st = null;

        try {

            String SQL = "DELETE FROM libros WHERE isbn=?";
            con = conexion.conectar();

            if (con == null) {
                return false;
            }

            st = con.prepareStatement(SQL);
            st.setString(1, l.getIsbn());

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

                Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

}
