package com.jesus.junit5_app.ejemplo.junit5_app.models;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.Assumptions;

import com.jesus.junit5_app.ejemplo.junit5_app.exception.DineroInsuficienteException;
// Esta anotación se utiliza para indicar que los métodos anotados con @BeforeAll y @AfterAll no necesitan ser estáticos, ya que se ejecutarán en una instancia de prueba por clase en lugar de una instancia de prueba por método.
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
public class CuentaTest {
    Cuenta cuenta;

    // Método que se ejecuta antes de cada test
    @BeforeEach
    void initMetodoTest() {
        this.cuenta = new Cuenta("Jesus", new BigDecimal("1000.12345"));
        System.out.println("Iniciando el metodo de prueba");
    }

    // Método que se ejecuta después de cada test
    @AfterEach
    void tearDown() {
        System.out.println("Finalizando el metodo de prueba");
    }

    // Método que se ejecuta antes de todos los test
    @BeforeAll
    static void beforeAll() {
        System.out.println("Iniciando el test");
    }

    // Método que se ejecuta después de todos los test
    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando el test");
        
    }

    // Test para el nombre de la cuenta
    @Test
    @DisplayName("Probando el nombre de la cuenta!")
    void testNombreCuenta() {
       
        //cuenta.setPersona("Jesus");

        String esperado = "Jesus";
        String real = cuenta.getPersona();
        assertNotNull(real, () -> "El nombre de la cuenta no puede ser nulo");
         assertEquals(esperado, real,() -> "El nombre de la cuenta no es el esperado" + esperado
            + " per fue " + real);
         assertTrue(real.equals("Jesus"), () -> "El nombre de la cuenta debe ser igual a Jesus");
}

    // Test para el saldo de la cuenta
    @Test
    @DisplayName("Probando el saldo de la cuenta!")
    void testSaldoCuenta() {
        assertNotNull(cuenta.getSaldo());
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    // Test para la referencia de la cuenta
    @Test
    @DisplayName("Probando la referencia de la cuenta!")
    void testReferenciaCuenta() {
        cuenta = new Cuenta("John Doe", new BigDecimal(8900.9997));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal(8900.9997));

        //assertNotEquals(cuenta2, cuenta);
        assertEquals(cuenta2, cuenta);
        
        
    }

    // Test para el método debito de la cuenta
    @Test
    @DisplayName("Probando el debito de la cuenta!")
    void testDebitoCuenta() {
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
        
    }

    @Test
    @DisplayName("Probando el credito de la cuenta!")
    void testCreditoCuenta() {
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
        
    }

    // Test para el método debito de la cuenta con excepción
    @Test
    @DisplayName("Probando el debito de la cuenta con excepcion!")
    void testDineroInsuficienteException() {
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero insuficiente";

        assertEquals(esperado, actual);

    }

    // Test para el método transferir de la cuenta
    @Test
    @DisplayName("Probando el transferir de la cuenta!")
    void testTransferirDineroCuentas() {
        Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Jesus", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());
        
    }

    // Test para la relación entre banco y cuentas
    @Test
   // @Disabled
    @DisplayName("Probando la relacion entre banco y cuentas!")
    void testRelacionBancoCuentas() {
        //fail(); // Este test se ha dejado pendiente para mostrar el uso del fail() y no se ejecute el resto del código
        Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Jesus", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);

        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
        assertAll(()-> assertEquals("1000.8989", cuenta2.getSaldo().toPlainString(), 
                                    () -> "El saldo de la cuenta 2 no es el esperado"), 
                ()-> assertEquals("3000", cuenta1.getSaldo().toPlainString(), 
                                    () -> "El saldo de la cuenta 1 no es el esperado"), 
                ()-> assertEquals(2, banco.getCuentas().size(), 
                                    () -> "El numero de las cuentas en el banco no es el esperado"), 
                ()-> assertEquals("Banco del Estado", cuenta1.getBanco().getNombre()),
                ()-> assertEquals("Jesus", banco.getCuentas().stream()
                        .filter(c -> c.getPersona().equals("Jesus"))
                        .findFirst()
                        .get().getPersona()),
                ()-> assertTrue(banco.getCuentas().stream()
                        .anyMatch(c -> c.getPersona().equals("Jesus"))));
        
        
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testSoloWindows() {

    }

     @Test
    @EnabledOnOs({OS.LINUX,OS.MAC})
    void testSoloLinuxMac() {

    }

     @Test
    @DisabledOnOs(OS.WINDOWS)
    void testNoWindows() {

    }


     @Test
     @EnabledOnJre(JRE.JAVA_21)
    void soloJdk21() {

    }

    @Test
     @DisabledOnJre(JRE.JAVA_21)
    void testNoJdk21() {

    }

    @Test
    void imprimirSystemProperties() {
        Properties properties = System.getProperties();
        properties.forEach((k,v)-> System.out.println(k + " :" + v));

    }

    @Test
    @EnabledIfSystemProperty(named = "java.version", matches = ".*21.*")
    void testJavaVersion() {
        
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void testSolo64() {
        
    }

    @Test
    void imprimirVariablesAmbiente() {
        Map<String, String> getenv =System.getenv();
        getenv.forEach((k, v) -> System.out.println(k + " = " + v));
        
    }

    @Test
    @EnabledIfEnvironmentVariable(named =  "JAVA_HOME", matches = ".*jdk-21.*")
    void testJavaHome() {
        
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = ".*4.*")
    void testProcesadores() {
        
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENVIRONMENT" , matches = "dev")
    void testEnv() {
        
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "ENVIRONMENT" , matches = "dev")
    void testEnvProdDisabled() {
        
    }

    @Test
    @DisplayName("test Saldo Cuenta Dev")
    void testSaldoCuentaDev() {
        boolean esDev = "dev".equals(System.getProperty("ENV"));
        assumeTrue(esDev);
        assertNotNull(cuenta.getSaldo());
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }


    
}
