package com.at.intership;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Menu {
    public static Configuracion conf = new Configuracion(20000);
    public static Administrador admin = new Administrador(conf);
    public static  LecturaDatos lectura = new LecturaDatos();
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
                    if(cliente!=null) MenuProductos.runCommandProducts(cliente);
                    break;
                case "operaciones-productos":
                    int optionChoosen;
                    numCliente = lectura.readString("Ingresa el número de cliente para buscar sus productos: ");
                    Map<String,ProductoFinanciero> productos = admin.getProductos(numCliente);
                    imprimirProductos(productos.values());
                    optionChoosen = lectura.readInteger("¿Desea realizar alguna transacción?\n 1.-Si \n 2.-No");
                    if(optionChoosen==1){
                        realizarOperacionProducto(productos);
                    }
                    break;
                case "salir":
                    break;
                default:
                    System.err.printf("\"%s\" no es un comando reconocido%n", command);
            }
        } while(!"exit".equalsIgnoreCase(command));
    }

    public static void printHelp() {
        System.out.println("- ayuda\n" +
                "- registrar-cuenta-habiente\n"+
                "- consultar-cuenta-habiente\n"+
                "- agregar-producto-cuenta-habiente\n"+
                "- salir");
    }

    public static void imprimirProductos(Collection<ProductoFinanciero> productos){
        if(productos!=null){
            for(ProductoFinanciero producto:productos){
                producto.imprimirEstadoCuenta();
            }
        }
    }

    public static void realizarOperacionProducto(Map<String, ProductoFinanciero> productos){
        String idProduct;
        idProduct = lectura.readString("Ingresa el id del producto para realizar alguna operación: ");
        ProductoFinanciero producto = productos.get(idProduct);
        if(producto!=null){
            if(producto instanceof TarjetaCredito){
                MenuOperacionesTarjeta.runCommandOperations((TarjetaCredito) producto);
            }
            if(producto instanceof  CuentaCheques){
                MenuOperacionesCuentaCheques.runCommandOperations((CuentaCheques) producto);
            }
            if(producto instanceof  CuentaInversion){
                MenuOperacionesCuentaInversion.runCommandOperations((CuentaInversion) producto);
            }
        }else{
            System.out.println("No existe el ID del producto");
        }
    }

}
