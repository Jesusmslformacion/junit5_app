package com.jesus.junit5_app.ejemplo.junit5_app.models;

import java.math.BigDecimal;

public class Cuenta {

    // Atributos
    private String persona;
    private BigDecimal saldo;

    
    // Constructor
    public String getPersona() {
        return persona;
    }

    // Setters y Getters
    public void setPersona(String persona) {
        this.persona = persona;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    
}
