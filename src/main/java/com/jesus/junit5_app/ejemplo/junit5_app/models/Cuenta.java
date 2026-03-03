package com.jesus.junit5_app.ejemplo.junit5_app.models;

import java.math.BigDecimal;

import com.jesus.junit5_app.ejemplo.junit5_app.exception.DineroInsuficienteException;

public class Cuenta {

    // Atributos
    private String persona;
    private BigDecimal saldo;
    private Banco banco;

    
    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

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
    
    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    

    public void debito(BigDecimal monto) {
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new DineroInsuficienteException("Dinero insuficiente");
        }
        this.saldo = nuevoSaldo;
    }

    public void credito(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }

    
    // Sobrescribir el método equals para comparar objetos Cuenta
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cuenta)) {
            return false;
        }
        Cuenta c = (Cuenta)obj;
        if (this.persona == null || this.saldo == null) {
            return false;
            
        }
        return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
    }

    
}
