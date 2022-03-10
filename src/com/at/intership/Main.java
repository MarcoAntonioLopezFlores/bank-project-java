package com.at.intership;

public class Main {

    public static void main(String[] args) {
        // Cuenta genérica
//        CuentaBancaria cuentaBancaria = new CuentaBancaria(1000.0);
//        System.out.println("Balance inicial: " + cuentaBancaria.getBalance());
//        System.out.println("Retiro: 600");
//        cuentaBancaria.reducirFondos(600.0);
//        System.out.println("Nuevo balance: " + cuentaBancaria.getBalance());
//        System.out.println("Retiro: 600");
//        cuentaBancaria.reducirFondos(600.0);
//        System.out.println("Nuevo balance:" + cuentaBancaria.getBalance());

        // Cuenta cheques
//        CuentaCheques cuentaCheques = new CuentaCheques(1000, 5.0);
//        System.out.println("Balance inicial: " + cuentaCheques.getBalance());
//        System.out.println("Retiro: 600");
//        cuentaCheques.reducirFondos(600.0);
//        System.out.println("Nuevo balance: " + cuentaCheques.getBalance());
//        System.out.println("Retiro: 600");
//        cuentaCheques.reducirFondos(600.0);
//        System.out.println("Nuevo balance:" + cuentaCheques.getBalance());
//        cuentaCheques.imprimirEstadoCuenta();

        // Cuenta inversion
        // Definir impuesto %
        /*CuentaInversion.setImpuesto(0.15); //15/100
        System.out.println("IMPUESTO APLICADO A TU CUENTA DE INVERSIÓN: "+CuentaInversion.getImpuesto() * 100+"%");
        CuentaInversion cuentaInversion = new CuentaInversion(1000.0, 0.05);
        System.out.println("Balance inicial: " + cuentaInversion.getBalance());
        System.out.println("Retiro: 600");
        cuentaInversion.reducirFondos(600.0);
        System.out.println("------------------------");
        System.out.println("Nuevo balance: " + cuentaInversion.getBalance());
        System.out.println("Retiro: 600");
        cuentaInversion.reducirFondos(600.0);
        System.out.println("------------------------");
        System.out.println("Nuevo balance:" + cuentaInversion.getBalance());
        System.out.println("Aplicando corte...");
        cuentaInversion.aplicarCorte();
        System.out.println("------------------------");
        System.out.println("Nuevo balance:" + cuentaInversion.getBalance());
        cuentaInversion.imprimirEstadoCuenta();
        // modificar impuesto
        System.out.println("------------------------");
        System.out.println("Subir el impuesto a 20%");
        cuentaInversion.setImpuesto(0.20); // 20/100
        System.out.println("IMPUESTO APLICADO A TU CUENTA DE INVERSIÓN: "+CuentaInversion.getImpuesto() * 100+"%");

        System.out.println("------------------------");
        System.out.println("Aplicando corte...");
        cuentaInversion.aplicarCorte();
        System.out.println("Nuevo balance:" + cuentaInversion.getBalance());
        System.out.println("------------------------");
        cuentaInversion.imprimirEstadoCuenta();*/
        // Cuenta de cheques: Comisión por retiro
        // Cuenta de inversion: + intereses

        // Tarjeta de Credito
//        TarjetaCredito tarjetaCredito = new TarjetaCredito(100000);
//        System.out.println("Saldo inicial: " + tarjetaCredito.getSaldo());
//        System.out.println("Linea de credito: " + tarjetaCredito.getLineaCredito());
//        System.out.println("Cargar 600...");
//        tarjetaCredito.cargarTarjeta(600);
//        System.out.println("Nuevo saldo: " + tarjetaCredito.getSaldo());
//        System.out.println("Cargar 600...");
//        tarjetaCredito.cargarTarjeta(600);
//        System.out.println("Nuevo saldo: " + tarjetaCredito.getSaldo());
//        System.out.println("Pagar 600...");
//        tarjetaCredito.pagarTarjeta(600);
//        System.out.println("Nuevo saldo: " + tarjetaCredito.getSaldo());
//        System.out.println("Pagar 600...");
//        tarjetaCredito.pagarTarjeta(600);
//        System.out.println("Nuevo saldo: " + tarjetaCredito.getSaldo());

        // Ejercicio: Cancelar cliente
        // 1. Si todos los productios financieros tienen saldo en 0, imprimir "Cancelación autorizada"
        // 2. Por cada producto financiero que no tenga saldo en 0 imprimir su estado de cuenta
        // 2.1. Al final de todos los estados de cuenta imprimir "Cancelación denegada"

//        Cliente cliente = new Cliente("David", "12345", 20000);
//        Configuracion conf = new Configuracion();
//        conf.setMaxLineaCreditoPorIngresoMensual(4.0);
//        AdministradorProducto adm = new AdministradorProducto(conf);
//        adm.agregarProducto(cliente, tarjetaCredito);
//        System.out.println(String.format("El cliente %s ha solicitado cancelar sus cuentas", cliente.getNumCliente()));
//        if(adm.puedeCancelar(cliente)) {
//            System.out.println("Cancelación autorizada");
//        } else {
//            System.out.println("Cancelación denegada");
//        }

        Cliente cliente = new Cliente("Marco Antonio", "62354", 20000);
        Configuracion conf = new Configuracion();
        conf.setMaxLineaCreditoPorIngresoMensual(4.0);
        AdministradorProducto adm = new AdministradorProducto(conf);

        CuentaInversion cuentaInversion = new CuentaInversion(1000.0, 0.05);
        adm.agregarProducto(cliente, cuentaInversion);


        CuentaCheques cuentaCheques = new CuentaCheques(1000, 5.0);
        adm.agregarProducto(cliente, cuentaCheques);
        adm.agregarProducto(cliente, cuentaInversion);
    }

}
