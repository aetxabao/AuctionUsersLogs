package edu.masanz.da.au.dto;

public class AuditRecord{

    private String username;
    private String rol;
    private boolean legal;
    private String timestamp;
    private String ip;
    private String method;
    private String path;

    public AuditRecord(String username, String rol, boolean legal, String timestamp, String ip, String method, String path) {
        this.username = username;
        this.rol = rol;
        this.legal = legal;
        this.timestamp = timestamp;
        this.ip = ip;
        this.method = method;
        this.path = path;
    }

    // region Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return timestamp.substring(0, 10);
    }

    public String getTime() {
        return timestamp.substring(11, 19);
    }

    public String getMilis() {
        return timestamp.substring(20);
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // endregion

    @Override
    public String toString() {
        return String.format("%-12s %-5s %-5b %-20s %-15s %-4s %-40s",
                username, rol, legal, timestamp, ip, method, path);
    }

}
