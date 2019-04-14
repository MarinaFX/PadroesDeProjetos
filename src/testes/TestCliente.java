package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clientes.Cliente;
import clientes.Genero;

class TestCliente {
	
	private Cliente c;

	@BeforeEach
	void setUp() throws Exception {
		
		c = new Cliente("Arthur", "00011122233", 17, Genero.MASCULINO);
		
	}

	@Test
	void testName() {
		assertEquals("Arthur", c.getName());
	}

	@Test
	void testCpf() { 
		assertEquals("00011122233", c.getCpf());
	}
	
	@Test 
	void testAge() {
		assertEquals(17, c.getAge());
	}
	
	@Test
	void testGender() {
		assertEquals(Genero.MASCULINO, c.getGender());
	}
}
