package edu.masanz.da.au.dao;

import edu.masanz.da.au.dto.Item;
import edu.masanz.da.au.dto.ItemPujas;
import edu.masanz.da.au.dto.PujaItem;
import edu.masanz.da.au.dto.Usuario;

import java.util.List;

public interface IAuctionDao {
    boolean autenticar(String nombreUsuario, String password);
    boolean esAdmin(String nombreUsuario);
    boolean esUsuario(String nombreUsuario);
    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuario(String usernameToEdit);
    boolean crearUsuario(String nombre, String password, boolean esAdmin);
    boolean modificarPasswordUsuario(String nombre, String password);
    boolean modificarRolUsuario(String nombre, String rol);
    boolean eliminarUsuario(String nombre);
    List<Item> obtenerArticulosPendientes();
    boolean validarArticulo(long id, boolean valido);
    boolean validarTodos();
    List<ItemPujas> obtenerArticulosConPujas();
    boolean resetearSubasta();
    List<PujaItem> obtenerHistoricoGanadores();
    Item obtenerArticuloPujable(long idArt);
    List<Item> obtenerArticulosPujables();
    boolean pujarArticulo(long idArt, String nombre, int precio);
    List<PujaItem> obtenerPujasVigentesUsuario(String nombreUsuario);
    boolean ofrecerArticulo(Item item);

}