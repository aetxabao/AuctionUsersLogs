package edu.masanz.da.au.dto;

public class Usuario implements Comparable<Usuario> {

    private String nombre;
    private String sal;
    private String hashPwSal;
    private String rol;

    // region Constructors
    public Usuario(String nombre, String sal, String hashPwSal, String rol) {
        this.nombre = nombre;
        this.sal = sal;
        this.hashPwSal = hashPwSal;
        this.rol = rol;
    }

    public Usuario() {
        this("", "", "", "");
    }
    // endregion

    // region Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

    public String getHashPwSal() {
        return hashPwSal;
    }

    public void setHashPwSal(String hashPwSal) {
        this.hashPwSal = hashPwSal;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // endregion

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int compareTo(Usuario o) {
        // Ordenar por rol y luego por nombre alfabéticamente
        if (o == null) {
            return 1;
        }else if (this.rol.equals(o.rol) && this.nombre.equals(o.nombre)) {
            return 0;
        } else if (this.rol.equals(o.rol)) {
            return this.nombre.compareTo(o.nombre);
        } else {
            return this.rol.compareTo(o.rol);
        }
    }
}
