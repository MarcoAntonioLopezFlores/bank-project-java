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

    public boolean cancelarProducto(String numCliente) {
        LecturaDatos lectura = new LecturaDatos();
        int optionChoosen;
        String idProducto;
        boolean fueEliminado = false, puedeEliminar;
        Map<String,ProductoFinanciero> productos;

        productos = getProductos(numCliente);
        if(productos!=null){
            idProducto = lectura.readString("Ingresa el id del producto a eliminar: ");
            ProductoFinanciero producto = productos.get(idProducto);
            puedeEliminar= producto != null && producto.getSaldo() == 0.0;

            if(puedeEliminar){
                optionChoosen = lectura.readInteger("¿Desea continuar, toda la información sera eliminada de forma permanente?\n 1.-Continuar \n Cualquier otra número.-Cancelar");
                if(optionChoosen==1){
                    productos.remove(idProducto);
                    fueEliminado=true;
                }
            }else {
                if(producto!=null) {
                    System.out.println("El producto del cliente "+ numCliente+" cuenta con saldo por lo cual no puede ser eliminado");
                    producto.imprimirEstadoCuenta();
                } else {
                    System.out.println("El ID del producto no existe");
                }
            }
        }
        return fueEliminado;
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

    public void eliminarCuentaHabiente(String numCliente){
        Map<String,ProductoFinanciero> productos;
        Cliente cliente = consultarCuentaHabiente(numCliente);
        boolean puedeEliminar=true;
        if(cliente!=null){
            productos = getProductos(numCliente);
            if(productos!=null){
                for (ProductoFinanciero producto:productos.values()) {
                    if(producto.getSaldo()!=0.0){
                        puedeEliminar=false;
                        break;
                    }
                }
            }

            if(puedeEliminar){
                System.out.println("La cuenta de "+cliente.getNombre()+" con el num. de cliente "+cliente.getNumCliente()+" se ha dado de baja correctamente");
                mapaClientes.remove(numCliente);
            }else{
                System.out.println("La cuenta de "+cliente.getNombre()+" no puede ser dada de baja ya que tiene productos con saldo aún");
                productos.values().forEach(ProductoFinanciero::imprimirEstadoCuenta);
            }
        }

    }

    public void registrarCuentaCheque(Cliente cliente){
        LecturaDatos lecturaDatos = new LecturaDatos();
        String id;
        double balanceInicial,comisionRetiro;
        id = lecturaDatos.readString("Ingresa el id del producto: ");
        balanceInicial = lecturaDatos.readDouble("Ingresa balance inicial de la cuenta: ");
        comisionRetiro = lecturaDatos.readDouble("Ingresa la comisión de retiro (Ejemplo: 18.5): ");

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

    public void configurarImpuesto(){
        LecturaDatos lectura = new LecturaDatos();
        double nuevoImpuesto = lectura.readDouble("Ingresa el nuevo valor para la linea de credito: ");
        CuentaInversion.setImpuesto(nuevoImpuesto);
        System.out.println("Se ha configurado correctamente el nuevo impuesto por interes generados en cuentas de inversión ("+CuentaInversion.getImpuesto()+")");
    }

    public void configurarLineaCredito() {
        LecturaDatos lectura = new LecturaDatos();
        double nuevaLineaCredito = lectura.readDouble("Ingresa el nuevo valor para la linea de credito: ");
        conf.setMaxLineaCreditoPorIngresoMensual(nuevaLineaCredito);
        System.out.println("Se ha configurado correctamente el nuevo máximo de linea de credito por ingreso mensual ("+conf.getMaxLineaCreditoPorIngresoMensual()+")");
    }
}
