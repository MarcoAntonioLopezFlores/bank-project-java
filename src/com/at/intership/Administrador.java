package com.at.intership;

import java.util.*;

public class Administrador {
    private final Configuracion conf;
    private final Map<String, List<ProductoFinanciero>> mapaProductos = new HashMap<>();
    private final Map<String, Cliente> mapaClientes = new HashMap<>();

    public Administrador(Configuracion conf) {
        this.conf = conf;
    }

    public void agregarProducto(Cliente cliente, ProductoFinanciero producto) {
        List<ProductoFinanciero> productos = mapaProductos.get(cliente.getNumCliente());

        if(productos == null) {
            productos = new ArrayList<>();
            mapaProductos.put(cliente.getNumCliente(), productos);
        }

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
            for(ProductoFinanciero pf : productos) {
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

        productos.add(producto);
        System.out.println("El producto se añadio con éxito");
    }

    public List<ProductoFinanciero> getProductos(String numCliente) {
        List<ProductoFinanciero> productos = mapaProductos.get(numCliente);

        if(productos == null) System.out.println("El cliente no tiene productos asignados");

        return productos;
    }

    public boolean puedeCancelar(Cliente cliente) {
        List<ProductoFinanciero> productos = getProductos(cliente.getNumCliente());
        boolean resultado = true;
        for(ProductoFinanciero pf : productos) {
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

    public void registrarProductoFinanciero(Cliente cliente){

    }


}
