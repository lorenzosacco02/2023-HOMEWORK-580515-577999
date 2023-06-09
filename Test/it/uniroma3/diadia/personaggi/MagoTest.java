package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class MagoTest {
	private Mago m;
	private Partita p;
	@BeforeEach
	void setUp() throws Exception {
		this.p = new Partita();
		this.m = new Mago();
	}

	@Test
	void costruttoreMagoTest() {
		assertEquals("Merlino",m.getNome());
		assertEquals("Mi piace fare magie gioca con me e lo scoprirai",m.getPresentazione());
	}
	
	@Test
	void agisciBorsaVuotaTest() {
		assertEquals("Da un'occhiata alla tua borsa giocatore ho fatto una magia!",m.agisci(p));
		assertTrue(p.getPlayer().getBorsa().hasAttrezzo("Bacchetta"));
	}
	
	@Test
	void agisciBorsaPienaTest() {
		p.getPlayer().getBorsa().addAttrezzo(new Attrezzo("",10));
		assertEquals("Torna da me quando sarai più leggero e verrai ricompensato",m.agisci(p));
		assertFalse(p.getPlayer().getBorsa().hasAttrezzo("Bacchetta"));
	}
	
	@Test
	void riceviRegaloAttrezzoNonTrovatoTest() {
		assertEquals("Purtroppo non posso aiutarti non ho trovato il tuo attrezzo neanche con l'aiuto della mia magia",m.riceviRegalo(new Attrezzo(),p));
	}
	
	@Test
	void riceviRegaloAttrezzoTrovatoTest() {
		p.getPlayer().getBorsa().addAttrezzo(new Attrezzo("cassa",2));
		assertEquals("Cerca nella stanza, fidati ;)",m.riceviRegalo(new Attrezzo("cassa",2),p));
		assertTrue(p.getPlayer().getBorsa().isEmpty());
		assertEquals("cassa (1kg)",p.getStanzaCorrente().getAttrezzo("cassa").toString());
	}
	
	@Test
	void riceviRegaloAttrezzoNullTest() {
		assertEquals("Mi stai prendendo in giro, non è divertente",m.riceviRegalo(null,p));
	}
	
}
