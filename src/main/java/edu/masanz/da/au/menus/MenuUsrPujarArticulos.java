package edu.masanz.da.au.menus;

import edu.masanz.da.au.dto.Item;
import edu.masanz.da.au.service.AuctionService;
import edu.masanz.da.au.terminal.ui.Gui;

import java.util.List;

import static edu.masanz.da.au.conf.Ctes.*;

public class MenuUsrPujarArticulos  implements Menu {

    public void run() {
        Gui.mostrarMenuPujarArticulos();
        int opc = Gui.leerOpcion("Opción", 0, 2);
        while (opc != OPC_USR_PU_EXIT) {
            switch (opc) {
                case OPC_USR_PU_MOSTRAR_ITEMS_PUJABLES:
                    mostrarItemsPujables();
                    break;
                case OPC_USR_PU_PUJAR:
                    pujar();
                    break;
                default:
            }
            Gui.mostrarMenuPujarArticulos();
            opc = Gui.leerOpcion("Opción", 0, 2);
        }
    }

    private void mostrarItemsPujables() {
        List<Item> articulos = AuctionService.obtenerArticulosPujables();
        if (articulos.isEmpty()) {
            Gui.mostrarNoHayArticulosPujables();
            return;
        }
        List<String> texto = articulos.stream().map(Item::getInfo).toList();
        Gui.mostrarArticulos(texto);
    }

    private void pujar() {
        String nombre = Gui.leerTexto("Nombre usuario: ", 24);
        String password = Gui.leerTexto("Contraseña: ", 24);
        boolean esUsuario = AuctionService.autenticar(nombre, password) && !AuctionService.esAdmin(nombre);
        if (!esUsuario) {
            Gui.mostrarAutenticacion(false);
            return;
        }
        long idArt = Gui.leerNumero("Número artículo: ", 24);
        Item item = AuctionService.obtenerArticuloPujable(idArt);
        if (item == null) {
            Gui.mostrarArticuloNoDisponible();
            return;
        }
        int precio = (int)Gui.leerNumero("Precio que se puja: ", 24);
        if (precio < item.getPrecioInicio()) {
            Gui.mostrarPrecioInsuficiente();
            return;
        }
        boolean b = Gui.leerConfirmacion("Confirma la puja [s/N]: ", 24, "s");
        if (b) {
            b = AuctionService.pujarArticulo(idArt, nombre, precio);
        }
        Gui.mostrarPujaRealizada(b);
    }

}
