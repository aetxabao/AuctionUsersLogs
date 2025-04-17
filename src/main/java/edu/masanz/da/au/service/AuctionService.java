package edu.masanz.da.au.service;

import edu.masanz.da.au.dao.*;
import edu.masanz.da.au.dto.*;

import java.time.LocalDateTime;
import java.util.*;

public class AuctionService {

    private static IAuctionDao auctionDao = new AuctionDao();


    //region Usuarios
    public static boolean autenticar(String nombreUsuario, String password) { return auctionDao.autenticar(nombreUsuario, password); }

    public static boolean esAdmin(String nombreUsuario) { return auctionDao.esAdmin(nombreUsuario); }
    public static boolean esUsuario(String nombreUsuario) { return auctionDao.esUsuario(nombreUsuario); }

    public static List<Usuario> obtenerUsuarios() { return auctionDao.obtenerUsuarios(); }

    public static boolean crearUsuario(String nombre, String password, boolean esAdmin) { return auctionDao.crearUsuario(nombre, password, esAdmin); }

    public static boolean modificarPasswordUsuario(String nombre, String password) { return auctionDao.modificarPasswordUsuario(nombre, password); }

    public static boolean modificarRolUsuario(String nombre, String rol) { return auctionDao.modificarRolUsuario(nombre, rol); }

    public static boolean eliminarUsuario(String nombre) { return auctionDao.eliminarUsuario(nombre); }

    public static List<Item> obtenerArticulosPendientes() { return auctionDao.obtenerArticulosPendientes(); }

    public static boolean validarArticulo(long id, boolean valido) { return auctionDao.validarArticulo(id, valido); }

    public static boolean validarTodos() { return auctionDao.validarTodos(); }
    public static List<ItemPujas> obtenerArticulosConPujas() { return auctionDao.obtenerArticulosConPujas(); }

    public static boolean resetearSubasta() { return auctionDao.resetearSubasta(); }

    public static List<PujaItem> obtenerHistoricoGanadores() { return auctionDao.obtenerHistoricoGanadores(); }

    public static Item obtenerArticuloPujable(long idArt) {  return auctionDao.obtenerArticuloPujable(idArt); }

    public static List<Item> obtenerArticulosPujables() { return auctionDao.obtenerArticulosPujables(); }

    public static boolean pujarArticulo(long idArt, String nombre, int precio) {  return auctionDao.pujarArticulo(idArt, nombre, precio); }

    public static List<PujaItem> obtenerPujasVigentesUsuario(String nombreUsuario) { return auctionDao.obtenerPujasVigentesUsuario(nombreUsuario); }

    public static boolean ofrecerArticulo(Item item) { return auctionDao.ofrecerArticulo(item); }

    public static Usuario obtenerUsuario(String nombreUsuario) {
        return auctionDao.obtenerUsuario(nombreUsuario);
    }

}
