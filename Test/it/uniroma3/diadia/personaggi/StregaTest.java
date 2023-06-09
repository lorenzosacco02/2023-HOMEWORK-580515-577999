package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StregaTest {
	Strega s;
	Partita p;
	@BeforeEach
	void setUp() throws Exception {
		this.s = new Strega();
		this.p = new Partita();
	}
	
	//Test dei costruttori
	@Test
	void costruttoreTest() {
		assertEquals("Wendy", s.getNome());
		assertEquals("Hai fatto bene a salutarmi non te ne pentirai",s.getPresentazione());
	}
	
	@Test
	void costruttoreNonVuotoTest() {
		Strega c = new Strega("c","sono c");
		assertEquals("c", c.getNome());
		assertEquals("sono c",c.getPresentazione());
	}
	
	//Test del metodo agisci
	@Test
	void agisciNoSalutoTest() {
		assertEquals("Impara l'educazione e saluta la prossima volta! ora ti spedisco in una stanza desolata",s.agisci(p));
		assertEquals("Biblioteca" ,p.getStanzaCorrente().getNome());
		assertEquals(0,p.getStanzaCorrente().getNumeroAttrezzi());
	}
	
	@Test
	void agisciHoSalutoTest() {
		s.saluta();
		assertEquals("Sei stato bravo ora divertiti ti ho trasportato in un posto pieno di attrezzi",s.agisci(p));
		assertEquals("Aula N10" ,p.getStanzaCorrente().getNome());
		assertEquals(3,p.getStanzaCorrente().getNumeroAttrezzi());
	}
	
	//Test del metodo ricevi regalo
	@Test
	void riceviRegaloTest() {
		assertEquals(String.class,s.riceviRegalo(new Attrezzo(),p).getClass());
	}
}
