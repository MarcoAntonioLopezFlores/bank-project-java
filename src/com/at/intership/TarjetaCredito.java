package com.at.intership;

public class TarjetaCredito implements ProductoFinanciero {
    private final String id;
    private double lineaCredito;
    private double saldo;

    public TarjetaCredito(String id,double lineaCredito) {
        this.id = id;
        this.lineaCredito = lineaCredito;
        this.saldo = 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public void imprimirEstadoCuenta() {
        System.out.println("Estado de Cuenta - Tarjeta de Credito");
        System.out.println("Identificador: "+getId());
        System.out.println("Saldo: " + saldo);
        System.out.println("Línea de crédito: " + lineaCredito);
    }

    public void pagarTarjeta(double importe) {
        System.out.println("Pagando tarjeta");
        saldo += importe;
    }

    public void cargarTarjeta(double importe) {
        System.out.println("Cargar tarjeta");
        if(saldo - importe < lineaCredito * -1)
            System.out.println("Línea de crédito insuficiente");
        else
            saldo -= importe;
    }

    public double getLineaCredito() {
        return lineaCredito;
    }
    public void setLineaCredito(double lineaCredito) {
        this.lineaCredito = lineaCredito;
    }
}
