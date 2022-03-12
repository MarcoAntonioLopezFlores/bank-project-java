package com.at.intership;

public class CuentaCheques extends CuentaBancaria {

    private double comisionRetiro;

    public CuentaCheques(String id,double balanceInicial, double comisionRetiro) {
        super(id,balanceInicial);
        this.comisionRetiro = comisionRetiro;
    }

    @Override
    public void reducirFondos(double importe) {
        System.out.println("Realizando retiro");
        double importeTotal = importe + comisionRetiro;
        super.reducirFondos(importeTotal);
    }

    @Override
    public void imprimirEstadoCuenta() {
        System.out.println("Estado de Cuenta de Cheques...");
        System.out.println("Balance actual: " + getBalance());
        System.out.println("Comision retiro: " + comisionRetiro);
    }


    public double getComisionRetiro() {
        return comisionRetiro;
    }

    public void setComisionRetiro(double comisionRetiro) {
        this.comisionRetiro = comisionRetiro;
    }
}
