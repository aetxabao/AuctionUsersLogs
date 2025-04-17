package edu.masanz.da.au.terminal;

import edu.masanz.da.au.menus.*;

public class Main {

    public static void main(String[] args) {
//        Menu menu = new MenuUsrPrincipal();
//        Menu menu = new MenuUsrPujarArticulos();
//        Menu menu = new MenuAdmUsuarios();
//        Menu menu = new MenuAdmValidacion();
//        Menu menu = new MenuAdmPrincipal();
        Menu menu = new Login();
        menu.run();
    }

}