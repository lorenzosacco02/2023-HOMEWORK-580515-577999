package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class ComandoFineTest {
	private Partita partita;
	private ComandoFine Fine;
	
	@BeforeEach
	void setUp(){
		partita = new Partita();
		Fine = new ComandoFine();
	}

	@Test
	void FineTest() {
		assertEquals("Grazie di aver giocato!",Fine.esegui(partita).toString());
	}

}
