package com.at.intership;

public class MenuOperacionesCuentaCheques {
    public static void runCommandOperations(CuentaCheques cuentaCheques){
        String command;
        do {
            printOptionsOperations();
            System.out.print(">_ ");
            command = System.console().readLine();
            switch (command) {
                case "retiro":
                    double retiro = Menu.lectura.readDouble("Ingresa la cantidad a retirar: ");
                    cuentaCheques.reducirFondos(retiro);
                    break;
                case "agregar":
                    double agregado = Menu.lectura.readDouble("Ingresa la cantidad a agregar: ");
                    cuentaCheques.agregarFondos(agregado);
                    break;

                case "estado":
cuentaCheques.imprimirEstadoCuenta();
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
        System.out.println("- retiro\n" +
                "- agregar\n"+
                "- estado\n"+
                "- regresar (Regresar al menu principal)");
    }
}
