package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stubsEdummies.DummySocio;
import stubsEdummies.SistemaBarUsandoDummy;
import stubsEdummies.StubCliente;

class TestSistema {
	
	private SistemaBarUsandoDummy s;
	private StubCliente c;
	private DummySocio ds;

	@BeforeEach
	void setUp() throws Exception {
		s = new SistemaBarUsandoDummy();
		c = new StubCliente();
		ds = new DummySocio();
	}

	@Test
	void testCadastra() {
		s.cadastraCliente(c);
		s.cadastraCliente(ds);
		assertEquals(2, s.getNumeroPessoas());
	}
	
	@Test
	void testSaida() {
		s.cadastraCliente(c);
		s.cadastraCliente(ds);
		assertEquals(2, s.getNumeroPessoas());
		s.cadastraSaida(c);
		assertEquals(1, s.getNumeroPessoas());
	}
	
	@Test
	void testProcuraCliente() {
		s.cadastraCliente(c);
		assertEquals(c, s.procuraCliente("00011122233"));
	}
	
	@Test
	void testDistribuicao() {
		s.cadastraCliente(c);
		s.cadastraCliente(ds);
		assertEquals(100.0, s.getDistribuicaoMasculina());
		assertEquals(0.0, s.getDistribuicaoFeminina());
	}
	
	@Test
	void testSocios() {
		s.cadastraCliente(c);
		s.cadastraCliente(ds);
		assertEquals(50.0, s.getDistribuicaoStatusSocio());
		assertEquals(50.0, s.getDistribuicaoNaoSocios());
	}

}
