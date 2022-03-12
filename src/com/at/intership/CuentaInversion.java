package com.at.intership;

public class CuentaInversion extends CuentaBancaria {

    private double interesAlCorte;
    private static double IMPUESTO = 0.15;
    public CuentaInversion(String id,double balanceInicial, double interesAlCorte) {
        super(id,balanceInicial);
        this.interesAlCorte = interesAlCorte;
    }

    public void aplicarCorte() {
        System.out.println("Aplicando corte....");
        double interesParcial = getBalance() * interesAlCorte;
        double interesFinal = interesParcial - interesParcial * IMPUESTO;
        agregarFondos(interesFinal);
        System.out.println("Ahora el balance es: "+getBalance());
    }

    @Override
    public void imprimirEstadoCuenta() {
        System.out.println("Estado de Cuenta - Cuenta Inversión");
        System.out.println("Identificador: "+getId());
        System.out.println("Balance: " + getBalance());
        System.out.println("Tasa de Interés: " + interesAlCorte);
    }

    public static void setImpuesto(double impuesto){
        IMPUESTO = impuesto;
    }

    public static double getImpuesto(){
        return IMPUESTO;
    }

    public double getInteresAlCorte() {
        return interesAlCorte;
    }

    public void setInteresAlCorte(double interesAlCorte) {
        this.interesAlCorte = interesAlCorte;
    }
}
