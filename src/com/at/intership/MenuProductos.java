package com.at.intership;

public class MenuProductos {

    public static void runCommandProducts(Cliente cliente){
        String command;
        do {
            printOptionsProducts();
            System.out.print(">_ ");
            command = System.console().readLine();
            switch (command) {
                case "cuenta-cheques":
                    Menu.admin.registrarCuentaCheque(cliente);
                    break;
                case "cuenta-inversion":
                    Menu.admin.registrarCuentaInversion(cliente);
                    break;
                case "tarjeta-credito":
                    Menu.admin.registrarTarjetaCredito(cliente);
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

    public static void printOptionsProducts(){
        System.out.println("- cuenta-cheques\n" +
                "- cuenta-inversion\n"+
                "- tarjeta-credito\n"+
                "- regresar (Regresar al menu principal)");
    }


}
