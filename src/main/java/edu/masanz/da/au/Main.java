package edu.masanz.da.au;

import edu.masanz.da.au.controller.AuditController;
import edu.masanz.da.au.controller.MainController;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinFreemarker;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        Javalin app1 = Javalin.create(config -> {
            config.jetty.modifyServletContextHandler(handler -> handler.setSessionHandler(fileSessionHandler("session1")));
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinFreemarker());
        }).start(4444);

        Javalin app2 = Javalin.create(config -> {
            config.jetty.modifyServletContextHandler(handler -> handler.setSessionHandler(fileSessionHandler("session2")));
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinFreemarker());
        }).start(5555);

        setJavalinAppRoutes(app1);
        setJavalinAppRoutes(app2);

    }

    private static void setJavalinAppRoutes(Javalin app) {

        app.before("*", AuditController::grabarSolicitud);
        app.after("/autenticar", AuditController::grabarLogin);
        app.before("/admin/*", AuditController::comprobarAdmin);
        app.before("/user/*", AuditController::comprobarUser);

        app.get("/", MainController::iniciar);
        app.get("/login", MainController::iniciar);

        app.get("/error", MainController::iniciar);
        app.get("/exit", MainController::iniciar);

        app.post("/autenticar", MainController::autenticar);
        app.get("/menu", MainController::mostrarMenu);

        app.post("/admin/user-management", MainController::gestionarUsuarios);
        app.get("/admin/user-management", MainController::gestionarUsuarios);
        app.get("/admin/user/del/{username}", MainController::eliminarUsuario);
        app.get("/admin/user/edit/{username}", MainController::mostrarEditarUsuario);
        app.post("/admin/user/change/rol/{username}", MainController::cambiarRolUsuario);
        app.post("/admin/user/change/password/{username}", MainController::cambiarPasswordUsuario);
        app.get("/admin/user/create", MainController::mostrarCrearUsuario);
        app.post("/admin/user/create", MainController::crearUsuario);

        app.post("/admin/logs", AuditController::mostrarMenuLogs);
        app.get("/admin/logs", AuditController::mostrarMenuLogs);
        app.get("/admin/logs/logins", AuditController::mostrarLogins);
        app.get("/admin/logs/alerts", AuditController::mostrarAlertas);
        app.get("/admin/logs/user-logs", AuditController::mostrarLogsUsuario);
        app.post("/admin/logs/user-logs", AuditController::mostrarLogsUsuario);

    }

    public static SessionHandler fileSessionHandler(String sessionName) {
        SessionHandler sessionHandler = new SessionHandler();
        SessionCache sessionCache = new DefaultSessionCache(sessionHandler);
        sessionCache.setSessionDataStore(fileSessionDataStore());
        sessionHandler.setSessionCache(sessionCache);
        sessionHandler.setHttpOnly(true);
        // make additional changes to your SessionHandler here
        sessionHandler.setSessionCookie(sessionName);// YO
        return sessionHandler;
    }

    private static FileSessionDataStore fileSessionDataStore() {
        FileSessionDataStore fileSessionDataStore = new FileSessionDataStore();
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        File storeDir = new File(baseDir, "javalin-session-store");
        //C:\Users\USUARIO\AppData\Local\Temp\javalin-session-store
        storeDir.mkdir();
        fileSessionDataStore.setStoreDir(storeDir);
        return fileSessionDataStore;
    }

}