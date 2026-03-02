package com.jesus.junit5_app.ejemplo.junit5_app.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CuentaTest {

    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Jesus", new BigDecimal("1000.12345"));
        //cuenta.setPersona("Jesus");

        String esperado = "Jesus";
        String real = cuenta.getPersona();
         assertEquals(esperado, real);
         assertTrue(real.equals("Jesus"));
}

    
}
