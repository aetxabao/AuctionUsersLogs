package edu.masanz.da.au.menus;

import edu.masanz.da.au.dto.Usuario;
import edu.masanz.da.au.service.AuctionService;
import edu.masanz.da.au.terminal.ui.Gui;

import java.util.*;

import static edu.masanz.da.au.conf.Ctes.*;

public class MenuAdmUsuarios implements Menu {

    public void run() {
        Gui.mostrarTituloMenuAdmUsuarios();
        Gui.mostrarMenuUsuarios();
        int opc = Gui.leerOpcion("Opción", OPC_ADM_US_EXIT, OPC_ADM_US_ELIMINAR_USUARIO);
        while (opc != OPC_ADM_US_EXIT) {
            switch (opc) {
                case OPC_ADM_US_MOSTRAR_USUARIOS:
                    mostrarUsuarios();
                    break;
                case OPC_ADM_US_CREAR_USUARIO:
                    crearUsuario();
                    break;
                case OPC_ADM_US_MODIFICAR_PASSWORD:
                    modificarPasswordUsuario();
                    break;
                case OPC_ADM_US_MODIFICAR_ROL:
                    modificarRolUsuario();
                    break;
                case OPC_ADM_US_ELIMINAR_USUARIO:
                    eliminarUsuario();
                    break;
                default:
            }
            Gui.mostrarMenuUsuarios();
            opc = Gui.leerOpcion("Opción", OPC_ADM_US_EXIT, OPC_ADM_US_ELIMINAR_USUARIO);
        }
    }

    private void mostrarUsuarios() {
        List<Usuario> usuarios = AuctionService.obtenerUsuarios();
        Map<String, String> mapaNombresRoles = new TreeMap<>();
        for (Usuario usuario : usuarios) {
           mapaNombresRoles.put(usuario.getNombre(), usuario.getRol());
        }
        Gui.mostrarMapa(mapaNombresRoles, "Usuario", "Rol", 8, 6);
    }

    private void crearUsuario() {
        String nombre = Gui.leerTexto("Nombre: ", 24);
        String password = Gui.leerTexto("Contraseña: ", 24);
        boolean esAdmin = Gui.leerConfirmacion("Es administrador [s/N]: ", 24, "s");
        boolean b = AuctionService.crearUsuario(nombre, password, esAdmin);
        Gui.mostrarUsuarioCreado(b);
    }

    private void modificarPasswordUsuario() {
        String nombre = Gui.leerTexto("Nombre usuario: ", 24);
        String password = Gui.leerTexto("Nueva contraseña: ", 24);
        boolean b = AuctionService.modificarPasswordUsuario(nombre, password);
        Gui.mostrarPasswordCambiada(b);
    }

    private void modificarRolUsuario() {
        String nombre = Gui.leerTexto("Nombre usuario: ", 24);
        boolean esAdmin = Gui.leerConfirmacion("Es administrador [s/N]: ", 24, "s");
        String rol = esAdmin ? ROL_ADMIN : ROL_USER;
        boolean b = AuctionService.modificarRolUsuario(nombre, rol);
        Gui.mostrarRolUsuarioCambiado(b);
    }

    private void eliminarUsuario() {
        String nombre = Gui.leerTexto("Nombre: ", 24);
        boolean b = Gui.leerConfirmacion("Eliminar [s/N]: ", 24, "s");
        if (b) {
            b = AuctionService.eliminarUsuario(nombre);
        }
        Gui.mostrarUsuarioEliminado(b);
    }

}
