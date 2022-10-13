package Test;

import Datos.UsuarioDAO;
import Model.Usuario;

import java.util.List;

public class Test {

    public static void main(String[] args) {


        UsuarioDAO usuarioDAO = new UsuarioDAO();

        List<Usuario> usuarios = usuarioDAO.GET();

        for (Usuario usuario:usuarios) {

            System.out.println("usuario = " + usuario);

        }

        Usuario usuarioPOST = new Usuario("si", "si");
        //usuarioDAO.POST(usuarioPOST);

        Usuario usuarioPUT = new Usuario(3, "no", "no");
        //usuarioDAO.PUT(usuarioPUT);

        Usuario usuarioDELETE = new Usuario(3);
        //usuarioDAO.DELETE(usuarioDELETE);



    }

}
