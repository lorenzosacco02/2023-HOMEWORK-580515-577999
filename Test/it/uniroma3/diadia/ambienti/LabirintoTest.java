package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoTest {
	private Labirinto Lab;
	private Stanza StanzaEntrata;
	private Stanza StanzaUscita;
	private Attrezzo Lanterna;
	@BeforeEach

	void setup() {
		StanzaEntrata = new Stanza("Atrio");
		StanzaUscita = new Stanza("Biblioteca");
		Lanterna = new Attrezzo("lanterna",3);
		StanzaEntrata.addAttrezzo(Lanterna);
		StanzaEntrata.impostaStanzaAdiacente("nord", StanzaUscita);
		StanzaEntrata.impostaStanzaAdiacente("est", StanzaUscita);
		StanzaEntrata.impostaStanzaAdiacente("sud", StanzaUscita);
		StanzaEntrata.impostaStanzaAdiacente("ovest", StanzaUscita);
		StanzaUscita.impostaStanzaAdiacente("sud", StanzaEntrata);
		Lab = new Labirinto();
	}

	//	Test del costruttore di Labirinto
	@Test
	void testLabirintoGetEntrata() {
		assertEquals(Lab.getEntrata().toString(),StanzaEntrata.toString());
	}

	@Test
	void testLabirintoGetUscita() {
		assertEquals(Lab.getUscita().toString(),StanzaUscita.toString());
	}
}
