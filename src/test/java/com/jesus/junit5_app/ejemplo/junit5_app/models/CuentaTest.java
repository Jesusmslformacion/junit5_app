package com.jesus.junit5_app.ejemplo.junit5_app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CuentaTest {

    // Test para el nombre de la cuenta
    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Jesus", new BigDecimal("1000.12345"));
        //cuenta.setPersona("Jesus");

        String esperado = "Jesus";
        String real = cuenta.getPersona();
         assertEquals(esperado, real);
         assertTrue(real.equals("Jesus"));
}

    // Test para el saldo de la cuenta
    @Test
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Jesus", new BigDecimal("1000.12345"));
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    // Test para la referencia de la cuenta
    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta =new Cuenta("John Doe", new BigDecimal(8900.9997));
        Cuenta cuenta2 =new Cuenta("John Doe", new BigDecimal(8900.9997));

        //assertNotEquals(cuenta2, cuenta);
        assertEquals(cuenta2, cuenta);
        
        
    }

    

    
}
