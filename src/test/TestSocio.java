package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clientes.Socio;

class TestSocio {
	
	private Socio soc;

	@BeforeEach
	void setUp() throws Exception {
		soc = new Socio("Arthur", "126354", 20, "Masculino", "2222222");
	}

	@Test
	void testVip() {
		assertEquals("2222222", soc.getVip());
	}

}
