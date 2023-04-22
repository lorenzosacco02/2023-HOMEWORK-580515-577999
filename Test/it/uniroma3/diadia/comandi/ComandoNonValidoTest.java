package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class ComandoNonValidoTest {

	private Partita partita;
	private ComandoNonValido NonValido;
	
	@BeforeEach
	void setUp(){
		partita = new Partita();
		NonValido = new ComandoNonValido();
	}

	@Test
	void NonValidoTest() {
		assertEquals("Comando sconosciuto",NonValido.esegui(partita).toString());
	}

}
