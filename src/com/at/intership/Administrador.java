package com.at.intership;

import java.util.*;

public class Administrador {
    private final Configuracion conf;
    private final Map<String, Map<String,ProductoFinanciero>> mapaProductos = new HashMap<>();
    private final Map<String, Cliente> mapaClientes = new HashMap<>();

    public Administrador(Configuracion conf) {
        this.conf = conf;
    }

    public void agregarProducto(Cliente cliente, ProductoFinanciero producto) {
        Map<String, ProductoFinanciero> productos = mapaProductos.computeIfAbsent(cliente.getNumCliente(), k -> new HashMap<>());

        if(producto instanceof TarjetaCredito) {
            double ingresoMensual = cliente.getIngresoMensual();
            double lineaCredito = ((TarjetaCredito) producto).getLineaCredito();
            if(lineaCredito > ingresoMensual * conf.getMaxLineaCreditoPorIngresoMensual()) {
                System.out.println("Linea de credito excesiva para este cliente");
                return;
            }
        }
        if(producto instanceof CuentaInversion){
            boolean hasCuentaCheques=false;
            for(ProductoFinanciero pf : productos.values()) {
                if (pf instanceof CuentaCheques) {
                    hasCuentaCheques = true;
                    break;
                }
            }
            if(!hasCuentaCheques){
                System.out.println("Para abrir una cuenta de inversión es necesario primero tener una cuenta de cheques");
                return;
            }
        }

        productos.put(producto.getId(),producto);
        System.out.println("El producto se añadio con éxito");
    }

    public Map<String,ProductoFinanciero> getProductos(String numCliente) {
        Map<String,ProductoFinanciero> productos = mapaProductos.get(numCliente);

        if(productos == null) System.out.println("El cliente no tiene productos asignados");
        return productos;
    }

    public boolean puedeCancelar(Cliente cliente) {
        Map<String,ProductoFinanciero> productos = getProductos(cliente.getNumCliente());
        boolean resultado = true;

        for(ProductoFinanciero pf : productos.values()) {
            if(pf.getSaldo() != 0.0) {
                resultado = false;
                pf.imprimirEstadoCuenta();
            }
        }
        return resultado;
    }

    public void registrarCuentaHabiente(){
        LecturaDatos lecturaDatos = new LecturaDatos();
        String nombre, numCliente;
        double ingresoMensual;

        nombre = lecturaDatos.readStringWithoutNumbers("Ingresa el nombre del cliente: ");
        numCliente = lecturaDatos.readString("Ingresa el num de cliente: ");
        ingresoMensual = lecturaDatos.readDouble("Ingresa el ingreso mensual del cliente: ");

        Cliente cliente = new Cliente(nombre, numCliente, ingresoMensual);
        mapaClientes.put(numCliente, cliente);
        System.out.println("Registro éxitoso de cuenta-habiente");
    }

    public Cliente consultarCuentaHabiente(String numCliente){
        Cliente cliente = mapaClientes.get(numCliente);
        if(cliente==null)
            System.out.println("No existe la cuenta");


        return cliente;
    }

    public void registrarCuentaCheque(Cliente cliente){
        LecturaDatos lecturaDatos = new LecturaDatos();
        String id;
        double balanceInicial,comisionRetiro;
        id = lecturaDatos.readString("Ingresa el id del producto: ");
        balanceInicial = lecturaDatos.readDouble("Ingresa balance inicial de la cuenta: ");
        comisionRetiro = lecturaDatos.readDouble("Ingresa la comisión de retiro (Ejemplo: 0.05): ");

        CuentaCheques cuentaCheques = new CuentaCheques(id,balanceInicial, comisionRetiro);
        agregarProducto(cliente, cuentaCheques);
    }

    public void registrarCuentaInversion(Cliente cliente){
        LecturaDatos lecturaDatos = new LecturaDatos();
        String id;
        double balanceInicial,interesCorte;
        id = lecturaDatos.readString("Ingresa el id del producto: ");
        balanceInicial = lecturaDatos.readDouble("Ingresa balance inicial de la cuenta: ");
        interesCorte = lecturaDatos.readDouble("Ingresa el interes al corte (Ejemplo: 0.05): ");

        CuentaInversion cuentaInversion = new CuentaInversion(id,balanceInicial, interesCorte);
        agregarProducto(cliente, cuentaInversion);
    }

    public void registrarTarjetaCredito(Cliente cliente){
        LecturaDatos lecturaDatos = new LecturaDatos();
        String id;
        double lineaCredito;
        id = lecturaDatos.readString("Ingresa el id del producto: ");
        lineaCredito = lecturaDatos.readDouble("Ingresa la linea de credito: ");

        TarjetaCredito tarjetaCredito = new TarjetaCredito(id,lineaCredito);
        agregarProducto(cliente, tarjetaCredito);
    }


}
