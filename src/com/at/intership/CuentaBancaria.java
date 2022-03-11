package com.at.intership;

public abstract class CuentaBancaria implements ProductoFinanciero {
    private String id;
    private double balance;

    public CuentaBancaria(String id,double balanceInicial) {
        this.id =id;
        this.balance = balanceInicial;
    }

    public double getBalance() {
        return balance;
    }
    @Override
    public String getId() {
        return id;
    }
    public void agregarFondos(double importe) {
        this.balance += importe;
    }

    public void reducirFondos(double importe) {
        System.out.println("Reduciendo fondos");
        if(importe > balance)
            System.out.println("Fondos insuficientes");
        else
            this.balance -= importe;
    }

    @Override
    public double getSaldo() {
        return balance;
    }

    public abstract void imprimirEstadoCuenta();
}
