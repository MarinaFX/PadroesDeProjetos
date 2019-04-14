package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clientes.Genero;
import clientes.Socio;

class TestSocio {
	
	private Socio s;

	@BeforeEach
	void setUp() throws Exception {
		
		s = new Socio("Thais", "11122233344", 19, Genero.FEMININO, "666");
		
	}

	@Test
	void testVip() {
		assertEquals("666", s.getVip());
	}

}
