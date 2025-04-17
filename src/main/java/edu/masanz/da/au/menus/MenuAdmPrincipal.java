package edu.masanz.da.au.menus;

import edu.masanz.da.au.dto.PujaItem;
import edu.masanz.da.au.dto.ItemPujas;
import edu.masanz.da.au.service.AuctionService;
import edu.masanz.da.au.terminal.ui.Gui;

import java.util.List;

import static edu.masanz.da.au.conf.Ctes.*;

public class MenuAdmPrincipal implements Menu {

    public void run() {
        Gui.mostrarTituloMenuAdmin();
        Gui.mostrarMenuPrincipalAdministrador();
        int opc = Gui.leerOpcion("Opción", OPC_ADM_PR_EXIT, OPC_ADM_PR_GESTIONAR_USUARIOS);
        while (opc != OPC_ADM_PR_EXIT) {
            switch (opc) {
                case OPC_ADM_PR_VALIDAR_ITEMS:
                    menuValidarItems();
                    Gui.mostrarTituloMenuAdmin();
                    break;
                case OPC_ADM_PR_MOSTRAR_ITEMS_CON_PUJAS:
                    mostrarItemsConPujas();
                    break;
                case OPC_ADM_PR_RESETEAR_SUBASTA:
                    resetearSubasta();
                    break;
                case OPC_ADM_PR_MOSTRAR_HISTORICO_GANADORES:
                    mostrarHistoricoGanadores();
                    break;
                case OPC_ADM_PR_GESTIONAR_USUARIOS:
                    menuGestionarUsuarios();
                    Gui.mostrarTituloMenuAdmin();
                    break;
                default:
            }
            Gui.mostrarMenuPrincipalAdministrador();
            opc = Gui.leerOpcion("Opción", OPC_ADM_PR_EXIT, OPC_ADM_PR_GESTIONAR_USUARIOS);
        }
    }

    private void menuValidarItems() {
        Gui.mostrarTituloValidacion();
        Menu menu = new MenuAdmValidacion();
        menu.run();
    }

    private void mostrarItemsConPujas() {
        List<ItemPujas> articulosConPujas = AuctionService.obtenerArticulosConPujas();
        List<String> texto = articulosConPujas.stream().map(ItemPujas::getInfo).toList();
        Gui.mostrarListado(texto);
    }

    private void resetearSubasta() {
        boolean b = AuctionService.resetearSubasta();
        Gui.mostrarReseteado(b);
    }

    private void mostrarHistoricoGanadores() {
        List<PujaItem> historico = AuctionService.obtenerHistoricoGanadores();
        List<String> texto = historico.stream().map(PujaItem::getTransferInfo).toList();
        Gui.mostrarHistoricoGanadores(texto);
    }

    private void menuGestionarUsuarios() {
        Menu menu = new MenuAdmUsuarios();
        menu.run();
    }

}
