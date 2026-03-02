package com.jesus.junit5_app.ejemplo.junit5_app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Junit5AppApplicationTests {

	@Test
	void contextLoads() {
	}

}

class MiTest {

	@Test
	void pruebaSuma() {
		assertEquals(4, 2 + 2);
	}
}
