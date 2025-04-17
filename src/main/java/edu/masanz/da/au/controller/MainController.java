package edu.masanz.da.au.controller;

import edu.masanz.da.au.service.AuctionService;
import edu.masanz.da.au.dto.*;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {

    // app.get("/", MainController::iniciar);
    // app.get("/login", MainController::iniciar);
    // app.get("/error", MainController::iniciar);
    // app.get("/exit", MainController::iniciar);
    public static void iniciar(Context context) {
        context.req().getSession().invalidate();
        Map<String, Object> model = new HashMap<>();
        model.put("username", "");
        model.put("error", false);
        context.render("/templates/login.ftl", model);
    }

    // app.post("/autenticar", MainController::autenticar);
    public static void autenticar(Context context) {
        String username = null;
        String password = null;
        boolean authenticated = false;
        boolean isAdministrator = false;
        try {
            username = context.formParam("username").trim();
            password = context.formParam("password");
            authenticated = AuctionService.autenticar(username, password);
            if (authenticated) {
                isAdministrator = AuctionService.esAdmin(username);
            }
        }catch (Exception e) {
        }
        Map<String, Object> model = new HashMap<>();
        if (!authenticated) {
            model.put("username", username);
            model.put("error", true);
            context.render("/templates/login.ftl", model);
        }else {
            context.sessionAttribute("username", username);
            context.sessionAttribute("isAdministrator", isAdministrator);
            context.req().changeSessionId();
            context.redirect("/menu");
        }
    }

    //app.get("/menu", MainController::mostrarMenu);
    public static void mostrarMenu(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        boolean isAdministrator = context.sessionAttribute("isAdministrator");
        Map<String, Object> model = new HashMap<>();
        model.put("isAdministrator", isAdministrator);
        context.render("/templates/menu.ftl", model);
    }

    //app.post("/admin/user-management", MainController::gestionarUsuarios);
    public static void gestionarUsuarios(Context context) {
        List<Usuario> users = AuctionService.obtenerUsuarios();
        Map<String, Object> model = new HashMap<>();
        model.put("users", users);
        context.render("/templates/user-management.ftl", model);
    }

    //app.get("/admin/user/del/{username}", MainController::eliminarUsuario);
    public static void eliminarUsuario(Context context) {
        String usernameToDelete = context.pathParam("username");
        boolean b = AuctionService.eliminarUsuario(usernameToDelete);
        if (!b) {
            context.redirect("/error");
        }else {
            context.redirect("/admin/user-management");
        }
    }

    //app.get("/admin/user/edit/{username}", MainController::mostrarEditarUsuario);
    public static void mostrarEditarUsuario(Context context) {
        String usernameToEdit = context.pathParam("username");
        Usuario user = AuctionService.obtenerUsuario(usernameToEdit);
        if (user == null) {
            context.redirect("/error");
            return;
        }
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        context.render("/templates/user-edit.ftl", model);
    }

    //app.post("/admin/user/change/rol/{username}", MainController::cambiarRolUsuario);
    public static void cambiarRolUsuario(Context context) {
        String usernameToEdit = context.pathParam("username");
        String rol = context.formParam("rol");
        boolean b = AuctionService.modificarRolUsuario(usernameToEdit, rol);
        if (!b) {
            context.redirect("/error");
        }else {
            context.redirect("/admin/user-management");
        }
    }

    //app.post("/admin/user/change/password/{username}", MainController::cambiarPasswordUsuario);
    public static void cambiarPasswordUsuario(Context context) {
        String password = context.formParam("password");
        String usernameToEdit = context.pathParam("username");
        boolean b = AuctionService.modificarPasswordUsuario(usernameToEdit, password);
        if (!b) {
            context.redirect("/error");
        }else {
            context.redirect("/admin/user-management");
        }
    }

    //app.get("/admin/user/create", MainController::mostrarCrearUsuario);
    public static void mostrarCrearUsuario(Context context) {
        Map<String, Object> model = new HashMap<>();
        context.render("/templates/user-create.ftl", model);
    }

    //app.post("/admin/user/create", MainController::crearUsuario);
    public static void crearUsuario(Context context) {
        String usernameToCreate = context.formParam("username");
        String passwordToCreate = context.formParam("password");
        String rolToCreate = context.formParam("rol");
        boolean b = AuctionService.crearUsuario(usernameToCreate, passwordToCreate, rolToCreate.equals("ADMIN"));
        if (!b) {
            context.redirect("/error");
        }else {
            context.redirect("/admin/user-management");
        }
    }

}
