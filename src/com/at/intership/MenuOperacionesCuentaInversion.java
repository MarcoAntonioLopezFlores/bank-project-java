package com.at.intership;

public class MenuOperacionesCuentaInversion {
    public static void runCommandOperations(CuentaInversion cuentaInversion, Cliente cliente){
        String command;
        do {
            printOptionsOperations();
            System.out.print(">_ ");
            command = System.console().readLine();
            switch (command) {
                case "corte":
                    cuentaInversion.aplicarCorte();
                    break;
                case "estado":
                    System.out.println("NOMBRE DEL CUENTA-HABIENTE: "+cliente.getNombre());
                    cuentaInversion.imprimirEstadoCuenta();
                    break;
                case "regresar":
                    System.out.println("HAS REGRESADO AL MENÚ DE COMANDOS INICIALES");
                    Menu.printHelp();
                    break;
                default:
                    System.err.printf("\"%s\" no es un comando reconocido%n", command);
            }
        } while(!"regresar".equalsIgnoreCase(command));
    }

    public static void printOptionsOperations(){
        System.out.println("- corte\n" +
                "- estado\n"+
                "- regresar (Regresar al menu principal)");
    }
}
