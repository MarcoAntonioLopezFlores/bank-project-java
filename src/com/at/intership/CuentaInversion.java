package com.at.intership;

public class CuentaInversion extends CuentaBancaria {

    private double interesAlCorte;
    private static double IMPUESTO = 0.15;
    public CuentaInversion(double balanceInicial, double interesAlCorte) {
        super(balanceInicial);
        this.interesAlCorte = interesAlCorte;
    }

    public void aplicarCorte() {
        double interesParcial = getBalance() * interesAlCorte;
        double interesFinal = interesParcial - interesParcial * IMPUESTO;
        agregarFondos(interesFinal);
    }

    @Override
    public void imprimirEstadoCuenta() {
        System.out.println("Estado de Cuenta de Inversión ...");
        System.out.println("Balance: " + getBalance());
        System.out.println("Tasa de Interés: " + interesAlCorte);
    }

    public static void setImpuesto(double impuesto){
        IMPUESTO = impuesto;
    }

    public static double getImpuesto(){
        return IMPUESTO;
    }


}
