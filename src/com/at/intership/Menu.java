package com.at.intership;

public class Menu {
    public static void runCommandListener() {
        String command;
        Configuracion conf = new Configuracion();
        conf.setMaxLineaCreditoPorIngresoMensual(20000);
        Administrador admin = new Administrador(conf);
        do {
            System.out.print(">_ ");
            command = System.console().readLine();
            switch (command) {
                case "ayuda":
                    printHelp();
                    break;
                case "registrar-cuenta-habiente":
                    admin.registrarCuentaHabiente();
                    break;
                case "consultar-cuenta-habiente":
                    LecturaDatos lectura = new LecturaDatos();
                    String numCliente = lectura.readString("Ingresa el número de cliente a buscar: ");
                    admin.consultarCuentaHabiente(numCliente);
                    break;
                case "salir":
                    break;
                default:
                    System.err.printf("\"%s\" no es un comando reconocido%n", command);
            }
        } while(!"exit".equalsIgnoreCase(command));
    }

    private static void printHelp() {
        System.out.println("- ayuda\n" +
                "- registrar-cuenta-habiente\n"+
                "- consultar-cuenta-habiente\n"+
                "- salir");
    }

}
