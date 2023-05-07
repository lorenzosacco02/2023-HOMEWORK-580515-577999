package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiMapTest {
	private FabbricaDiComandiMap Factory;
	private Comando command;
	@BeforeEach
	void setup() {
		Factory= new FabbricaDiComandiMap();
	}

	//Test del Comando vai

	@Test
	void testFisarmonicaVaiNord() {
		command = Factory.costruisciComando("vai nord");
		assertEquals(command.getNome(),"ComandoVai");
		assertEquals(command.getParametro(),"nord");
	}

	@Test
	void testFisarmonicaVaiSudScrittoMale() {
		command = Factory.costruisciComando("Vai?sUd");
		assertEquals(command.getNome(),"ComandoVai");
		assertEquals(command.getParametro(),"sud");
	}

	@Test
	void testFisarmonicaVaiNull() {
		command = Factory.costruisciComando("vai");
		assertEquals(command.getNome(),"ComandoVai");
		assertNull(command.getParametro());
	}

	//Test del Comando Prendi

	@Test
	void testFisarmonicaPrendiCassa() {
		command = Factory.costruisciComando("prendi cassa");
		assertEquals(command.getNome(),"ComandoPrendi");
		assertEquals(command.getParametro(),"cassa");
	}

	@Test
	void testFisarmonicaPrendiCassaScrittoMale() {
		command = Factory.costruisciComando("PRENdi%/()CasSA");
		assertEquals(command.getNome(),"ComandoPrendi");
		assertEquals(command.getParametro(),"cassa");
	}

	@Test
	void testFisarmonicaPrendiNull() {
		command = Factory.costruisciComando("prendi");
		assertEquals(command.getNome(),"ComandoPrendi");
		assertNull(command.getParametro());
	}

	//Test del Comando Posa

	@Test
	void testFisarmonicaPosaCassa() {
		command = Factory.costruisciComando("posa cassa");
		assertEquals(command.getNome(),"ComandoPosa");
		assertEquals(command.getParametro(),"cassa");
	}

	@Test
	void testFisarmonicaPosaCassaScrittoMale() {
		command = Factory.costruisciComando("PoSa%/()\nCasSA");
		assertEquals(command.getNome(),"ComandoPosa");
		assertEquals(command.getParametro(),"cassa");
	}

	@Test
	void testFisarmonicaPosaNull() {
		command = Factory.costruisciComando("posa\"");
		assertEquals(command.getNome(),"ComandoPosa");
		assertNull(command.getParametro());
	}

	//Test del Comando Borsa

	@Test
	void testFisarmonicaBorsa() {
		command = Factory.costruisciComando("borsa");
		assertEquals(command.getNome(),"ComandoBorsa");
		assertNull(command.getParametro());
	}
	@Test
	void testFisarmonicaBorsaScrittoMale() {
		command = Factory.costruisciComando("BoRSa?");
		assertEquals(command.getNome(),"ComandoBorsa");
		assertNull(command.getParametro());
	}

	//Test del Comando Fine

	@Test
	void testFisarmonicaFine() {
		command = Factory.costruisciComando("fine");
		assertEquals(command.getNome(),"ComandoFine");
		assertNull(command.getParametro());
	}
	@Test
	void testFisarmonicaFineScrittoMale() {
		command = Factory.costruisciComando("FINE^");
		assertEquals(command.getNome(),"ComandoFine");
		assertNull(command.getParametro());
	}

	//Test del Comando NonValido

	@Test
	void testFisarmonicaNonValido() {
		command = Factory.costruisciComando("spara");
		assertEquals(command.getNome(),"ComandoNonValido");
		assertNull(command.getParametro());
	}
	@Test
	void testFisarmonicaNonValidoScrittoMale() {
		command = Factory.costruisciComando("awppòp iwado epokqppsM9028136");
		assertEquals(command.getNome(),"ComandoNonValido");
		assertNull(command.getParametro());
	}


	//Test del Comando Aiuto

	@Test
	void testFisarmonicaAiutoconPiù() {
		command = Factory.costruisciComando("aiuto+ciao");
		assertEquals(command.getNome(),"ComandoAiuto");
		assertEquals(command.getParametro(),"ciao");
	}

	@Test
	void testFisarmonicaAiutoTuttoMaiuscolo() {
		command = Factory.costruisciComando("aIUTO%?&\0sUd");
		assertEquals(command.getNome(),"ComandoAiuto");
		assertEquals(command.getParametro(),"sud");
	}

	@Test
	void testFisarmonicaAiutoNull() {
		command = Factory.costruisciComando("AiUTO%?&?^=?=&$£%");
		assertEquals(command.getNome(),"ComandoAiuto");
		assertNull(command.getParametro());
	}
	@Test
	void testFisarmonicaAiutoconSpazio() {
		command = Factory.costruisciComando("aiuto aiuto");
		assertEquals(command.getNome(),"ComandoAiuto");
		assertEquals(command.getParametro(),"aiuto");
	}
}
