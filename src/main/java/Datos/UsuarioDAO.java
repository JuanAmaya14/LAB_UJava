package Datos;

import Model.Usuario;

import java.sql.*;
import java.util.*;

import static Datos.Conexion.*;

public class UsuarioDAO {

    private static final String SELECT = "SELECT idUsuario, nombre, password FROM usuario";
    private static final String INSERT = "INSERT INTO usuario(nombre, password) VALUE(?, ?)";
    private static final String UPDATE = "UPDATE usuario SET nombre = ?, password = ? WHERE idUsuario = ?";
    private static final String DELETE = "DELETE FROM usuario WHERE idUsuario = ?";

    public List<Usuario> GET(){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SELECT);
            rs = pstmt.executeQuery();

            while (rs.next()){

                int idUsuario = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");

                usuario = new Usuario(idUsuario, nombre, password);

                usuarios.add(usuario);
            }


        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {

            try {
                close(pstmt);
                close(rs);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }


        }


        return usuarios;
    }

    public int POST(Usuario usuario){

        Connection conn = null;
        PreparedStatement pstmt = null;
        int registro = 0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(INSERT);

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getPassword());

            registro = pstmt.executeUpdate();

            System.out.println("El usuario se ha registrado con exito");


        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {

            try {
                close(pstmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }


        return registro;
    }

    public int PUT(Usuario usuario){

        Connection conn = null;
        PreparedStatement pstmt = null;
        int actualizado = 0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setInt(3, usuario.getIdUsuario());

            actualizado = pstmt.executeUpdate();

            System.out.println("El usuario se ha actualizado con exito");


        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {

            try {
                close(pstmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return actualizado;
    }

    public int DELETE(Usuario usuario){

        Connection conn = null;
        PreparedStatement pstmt = null;
        int borrado = 0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(DELETE);

            pstmt.setInt(1, usuario.getIdUsuario());

            borrado = pstmt.executeUpdate();

            System.out.println("El usuario se ha borrado con exito");


        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {

            try {
                close(pstmt);
                close(conn);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return borrado;

    }



}
