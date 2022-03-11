package com.at.intership;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private final String nombre;
    private final String numCliente;
    private final double ingresoMensual;

    public Cliente(String nombre, String numCliente, double ingresoMensual) {
        this.nombre = nombre;
        this.numCliente = numCliente;
        this.ingresoMensual = ingresoMensual;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumCliente() {
        return numCliente;
    }

    public double getIngresoMensual() {
        return ingresoMensual;
    }

}
