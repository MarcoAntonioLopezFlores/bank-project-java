package com.at.intership;

import static com.at.intership.Menu.runCommandListener;

public class Main {

    private static final String PROP_USERNAME = "com.at.intership.username";
    private static final String PROP_PASSWORD = "com.at.intership.password";
    private static final byte TRY_LIMIT = 3;

    public static void main(String[] args) {
        try {
            PropertyHandler.load("/application-default.properties", "application.properties");
            System.out.println("Bienvenido...");
            System.out.println("Ingresa tus credenciales...");

            String username, password;

            boolean isLoggedIn = false;
            byte tryCount = 0;
            do {
                System.out.print("usuario: ");
                username = System.console().readLine();
                System.out.print("contraseña: ");
                password = new String(System.console().readPassword());

                if(username.equals(PropertyHandler.getStringProperty(PROP_USERNAME)) && password.equals(PropertyHandler.getStringProperty(PROP_PASSWORD))){
                    isLoggedIn = true;
                }else{
                    System.err.println("Usuario y/o contraseña incorrecta\n\n");
                    tryCount++;
                }
            } while (!isLoggedIn && tryCount < TRY_LIMIT);

            if(isLoggedIn) {
                System.out.printf("Inicio de sesión de manera exitosa como  %s%n", username);
                runCommandListener();
            } else{
                System.err.println("Has alcanzado el limite de intentos");
            }

            PropertyHandler.persist();
            System.out.println("Programa finalizado");
        } catch (Exception e) {
            System.err.printf("%s: %s%n", e.getClass().getName(), e.getMessage());
        }
    }

}
