package edu.masanz.da.au.controller;

import edu.masanz.da.au.dto.AuditRecord;
import edu.masanz.da.au.dto.UserAuditRecords;
import edu.masanz.da.au.dto.Usuario;
import edu.masanz.da.au.service.AuctionService;
import edu.masanz.da.au.service.AuditService;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static edu.masanz.da.au.conf.Ctes.*;


public class AuditController {
    public static void grabarSolicitud(Context context) {
        String method = String.valueOf(context.method());
        String path = context.path();
        // No registrar path de autenticar en POST, se hace en el propio método asociado a autenticar:
        // app.after("/autenticar", AuditController::recordLogin);
        if (method.equals("POST") && path.contains("autenticar")) { return; }
        // Omitir paths que terminen en algunas de las extensiones
        List<String> extensiones = List.of(new String[]{".webp", "svg", ".png", ".jpg", ".ico", ".css", ".js"});
        for (String f : extensiones) { if (context.path().endsWith(f)) { return; } }
        // Obtener atributos a registrar
        String username = context.sessionAttribute("username");
        // Los usuario no autenticados se registran como "ANONYMOUS"
        String rol = ROL_OTHER;
        if (username == null) { username = USER_ANONYMOUS; }
        else {
            if (context.sessionAttribute("isAdministrator")){
                rol = ROL_ADMIN;
            } else {
                rol = ROL_USER;
            }
        }
        // Los accesos a paths que contienen "admin" y no son de Dokumentu horretan ez dut DBHBATX_INF erlaitzan aldaketarik ikusi, agian ez daude. administrador se consideran ilegales
        boolean legal = true;
        if (path.contains("admin") && !rol.equals(ROL_ADMIN)) { legal = false; }
        String timestamp = String.valueOf(LocalDateTime.now());
        timestamp  = timestamp.replaceAll("T", " ");
        String ip = context.ip();
        // Registrar acceso
        AuditRecord record = new AuditRecord(username, rol, legal, timestamp, ip, method, path);
        System.out.println(record);
        AuditService.addAuditRecord(record);
    }

    public static void grabarLogin(Context context) {
        String username = context.formParam("username");
        boolean isAdministrator = AuctionService.esAdmin(username);
        String rol = AuctionService.esUsuario(username)?isAdministrator?ROL_ADMIN:ROL_USER:ROL_OTHER;
        boolean legal = false;
        if (context.sessionAttribute("username") != null){ legal = true;}
        String timestamp = String.valueOf(LocalDateTime.now());
        timestamp  = timestamp.replaceAll("T", " ");
        String path = "/autenticar";
        String method = "POST";
        String ip = context.ip();
        AuditRecord record = new AuditRecord(username, rol, legal, timestamp, ip, method, path);
        System.out.println(record);
        AuditService.addAuditRecord(record);
    }

    public static void comprobarAdmin(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
        boolean isAdministrator = context.sessionAttribute("isAdministrator");
        if (!isAdministrator) {
            context.redirect("/error");
            return;
        }
    }

    public static void comprobarUser(Context context) {
        String username = context.sessionAttribute("username");
        if (username == null) {
            context.redirect("/error");
            return;
        }
    }

    public static void mostrarMenuLogs(Context context) {
        context.render("templates/menu-logs.ftl");
    }

    public static void mostrarLogins(Context context) {
        List<AuditRecord> logins = AuditService.getUserLoginRecords();
        String instanteActual = instanteActual();
        Map<String, Object> model = new HashMap<>();
        model.put("logins", logins);
        model.put("instanteActual", instanteActual);
        context.render("/templates/logs-auth-list.ftl", model);
    }

//    public static void mostrarAlertas(Context context) {
//        List<UserAuditRecords> alerts = AuditService.getIlegalUserAuditRecords();
//        String instanteActual = instanteActual();
//        Map<String, Object> model = new HashMap<>();
//        model.put("alerts", alerts);
//        model.put("instanteActual", instanteActual);
//        context.render("/templates/logs-alerts-list.ftl", model);
//    }

    public static void mostrarAlertas(Context context) {
        Map<String, List<AuditRecord>> mapa = AuditService.getIlegalUserAuditRecordsMap();
        String instanteActual = instanteActual();
        Map<String, Object> model = new HashMap<>();
        model.put("mapa", mapa);
        model.put("instanteActual", instanteActual);
        context.render("/templates/logs-alerts-list.ftl", model);
    }

    public static void mostrarLogsUsuario(Context context) {
        String appUsername = context.sessionAttribute("username");
        List<Usuario> usuarios = AuctionService.obtenerUsuarios();
        List<String> usernames = usuarios.stream()
                .map(Usuario::getNombre)
                .sorted()
                .collect(Collectors.toList());
        String selectedUsername = context.formParam("username");
        if ((selectedUsername==null || selectedUsername.isEmpty())) {
            selectedUsername = appUsername;
        }
        UserAuditRecords uar = AuditService.getUserAuditRecords(selectedUsername);
        String instanteActual = instanteActual();
        Map<String, Object> model = new HashMap<>();
        model.put("usernames", usernames);
        model.put("selectedUsername", selectedUsername);
        model.put("uar", uar);
        model.put("instanteActual", instanteActual);
        context.render("/templates/logs-user-list.ftl", model);
    }

    public static String instanteActual() {
        String timestamp = String.valueOf(LocalDateTime.now());
        timestamp  = timestamp.replaceAll("T", " ");
        return timestamp;
    }

}
