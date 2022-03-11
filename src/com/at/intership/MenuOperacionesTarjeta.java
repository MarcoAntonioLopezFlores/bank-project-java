package com.at.intership;

public class MenuOperacionesTarjeta {
    public static void runCommandOperations(TarjetaCredito tarjeta){
        String command;
        do {
            printOptionsOperations();
            System.out.print(">_ ");
            command = System.console().readLine();
            switch (command) {
                case "pago":
                    double pago = Menu.lectura.readDouble("Ingresa la cantidad a pagar: ");
                    tarjeta.pagarTarjeta(pago);
                    break;
                case "cargo":
                    double cantidadCargo = Menu.lectura.readDouble("Ingresa la cantidad del cargo: ");
                    tarjeta.cargarTarjeta(cantidadCargo);
                    break;
                case "estado":
tarjeta.imprimirEstadoCuenta();
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
        System.out.println("- pago\n" +
                "- cargo\n"+
                "- estado\n"+
                "- regresar (Regresar al menu principal)");
    }
}
