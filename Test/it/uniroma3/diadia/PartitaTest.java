package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	private Partita partita;
	private Stanza stanzaConNome;
	private Stanza stanzaSenzaNome;
	private Labirinto Lab;
@BeforeEach 
void Setup(){
	this.partita=new Partita();
	this.stanzaConNome=new Stanza("Bagno");
	this.stanzaSenzaNome=new Stanza("");
	this.Lab=this.partita.getLabirinto();
	this.partita.setStanzaCorrente(stanzaConNome);
}
	//Test del metodo getStanzaVincente
	@Test
	void testGetStanzaVincente() {
		assertEquals(this.Lab.getUscita(),this.partita.getStanzaVincente());
	}
	
	//Test  del metodo SetStanzaCorrente 
	@Test
	void test_SetStanzaCorrenteConNome(){
		assertEquals(stanzaConNome,this.partita.getStanzaCorrente());
	}
	@Test
	void testSetStanzaCorrenteSenzaNome() {
		this.partita.setStanzaCorrente(stanzaSenzaNome);
		assertEquals(stanzaSenzaNome,this.partita.getStanzaCorrente());
	}
	@Test
	void testSetStanzaCorrenteSenzaNomePerNome() {
		this.partita.setStanzaCorrente(stanzaSenzaNome);
		assertEquals("",this.partita.getStanzaCorrente().getNome());
	}
	
	//Test del metodo Vinta
	@Test
	void testVintaHaiVinto(){
		this.partita.setStanzaCorrente(this.Lab.getUscita());
		assertTrue(this.partita.vinta());
	}
	@Test
	void testVintaNonHaiVinto(){
		assertFalse(this.partita.vinta());
	}
	
	//Test del metodo isFinita
	@Test
	void testIsFinitaPartitaNonFinita(){
		assertFalse(this.partita.isFinita());
	}
	@Test
	void testIsFinitaNoCFU(){
		this.partita.getPlayer().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	@Test
	void testIsFinitaVittoria(){
		this.partita.setStanzaCorrente(this.Lab.getUscita());
		assertTrue(this.partita.isFinita());
	}
	@Test
	void testIsFinitaChiamoFine(){
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	@Test
	void testIsFinitaNoCFUVittoria(){
		this.partita.getPlayer().setCfu(0);
		this.partita.setStanzaCorrente(this.Lab.getUscita());
		assertTrue(this.partita.isFinita());
	}
}
