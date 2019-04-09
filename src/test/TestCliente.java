package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clientes.Cliente;

class TestCliente {
	private Cliente c;

	@BeforeEach
	void setUp() throws Exception {
		
		c = new Cliente("Arthur", "11133322255", 18, "masculino");
	}

	@Test
	void testName() {
		assertEquals("Arthur", c.getName());
	}
	
	@Test
	void testCpf() {
		assertEquals("11133322255", c.getCpf());
	}
	
	@Test
	void testAge() {
		assertEquals(18, c.getAge());
	}
	
	@Test 
	void testGenero() {
		assertEquals("masculino", c.getGender());
	}

}
