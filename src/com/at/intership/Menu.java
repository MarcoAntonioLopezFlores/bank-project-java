package com.at.intership;

public class Menu {
    private static Configuracion conf = new Configuracion(20000);
    private static Administrador admin = new Administrador(conf);
    private static  LecturaDatos lectura = new LecturaDatos();
    public static void runCommandListener() {
        String command;
        String numCliente;
        Cliente cliente = null;
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
                    numCliente = lectura.readString("Ingresa el número de cliente a buscar: ");
                    cliente = admin.consultarCuentaHabiente(numCliente);
                    if(cliente!=null) System.out.println(cliente);
                    break;
                case "agregar-producto-cuenta-habiente":
                    numCliente = lectura.readString("Ingresa el número de cliente al cual se le asignará el producto: ");
                    cliente= admin.consultarCuentaHabiente(numCliente);
                    if(cliente!=null) menuProducts(cliente);
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
                "- agregar-producto-cuenta-habiente\n"+
                "- salir");
    }


    private static void menuProducts(Cliente cliente){
        String command;
        do {
            printOptionsProducts();
            System.out.print(">_ ");
            command = System.console().readLine();
            switch (command) {
                case "cuenta-cheques":
                    addCuentaCheque(cliente);
                    break;
                case "cuenta-inversion":
                    addCuentaInversion(cliente);
                    break;
                case "tarjeta-credito":
                    addTarjetaCredito(cliente);
                    break;
                case "regresar":
                    break;
                default:
                    System.err.printf("\"%s\" no es un comando reconocido%n", command);
            }
        } while(!"regresar".equalsIgnoreCase(command));
    }

    private static void printOptionsProducts(){
        System.out.println("- cuenta-cheques\n" +
                "- cuenta-inversion\n"+
                "- tarjeta-credito\n"+
                "- regresar (Regresar al menu principal)");
    }

    private static void addCuentaCheque(Cliente cliente){
        double balanceInicial,comisionRetiro;
        balanceInicial = lectura.readDouble("Ingresa balance inicial de la cuenta: ");
        comisionRetiro = lectura.readDouble("Ingresa la comisión de retiro (Ejemplo: 0.05): ");

        CuentaCheques cuentaCheques = new CuentaCheques(balanceInicial, comisionRetiro);
        admin.agregarProducto(cliente, cuentaCheques);
    }

    private static void addCuentaInversion(Cliente cliente){
        double balanceInicial,interesCorte;
        balanceInicial = lectura.readDouble("Ingresa balance inicial de la cuenta: ");
        interesCorte = lectura.readDouble("Ingresa el interes al corte (Ejemplo: 0.05): ");

        CuentaInversion cuentaInversion = new CuentaInversion(balanceInicial, interesCorte);
        admin.agregarProducto(cliente, cuentaInversion);
    }

    private static void addTarjetaCredito(Cliente cliente){
        double lineaCredito;
        lineaCredito = lectura.readDouble("Ingresa la linea de credito: ");

        TarjetaCredito tarjetaCredito = new TarjetaCredito(lineaCredito);
        admin.agregarProducto(cliente, tarjetaCredito);
    }

}
