package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private StanzaBloccata StanzaBloccata;
	private StanzaBloccata StanzaSbloccata;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("ATTREZZO",5),new Attrezzo("piedediporco",200)};
	
	@BeforeEach
	void setUp(){
		StanzaSbloccata=new StanzaBloccata("Cucina");
		StanzaSbloccata.addAttrezzo(attrezzi[4]);
		
		StanzaBloccata = new StanzaBloccata("Bagno","ATTREZZO","pugnale","caciavite");
		StanzaBloccata.impostaStanzaAdiacente("nord",StanzaSbloccata);
		StanzaBloccata.impostaStanzaAdiacente("sud",StanzaBloccata);
		StanzaBloccata.impostaStanzaAdiacente("est",StanzaSbloccata);
		StanzaBloccata.impostaStanzaAdiacente("ovest",StanzaBloccata);
		StanzaSbloccata.impostaStanzaAdiacente("nord",StanzaBloccata);
		StanzaSbloccata.impostaStanzaAdiacente("sud",StanzaSbloccata);
	}
	
	@Test
	void testImpostaDirezioniBloccate() {
		StanzaBloccata.impostaDirezioneBloccata("nord");
		StanzaBloccata.impostaDirezioneBloccata("sud");
		assertEquals(StanzaBloccata.getDirezioniBloccate().get(0),"nord");
		assertEquals(StanzaBloccata.getDirezioniBloccate().get(1),"sud");
	}
	
	@Test
	void testGetDirezioniStanzabloccataConDueDirezioneBloccate() {
		StanzaBloccata.impostaDirezioneBloccata("nord");
		StanzaBloccata.impostaDirezioneBloccata("sud");
		assertEquals(StanzaBloccata.getDirezioni().get(0),"est");
		assertEquals(StanzaBloccata.getDirezioni().get(1),"ovest");
	}
	
	@Test
	void testGetDirezioniStanzabloccataConAlmenoUnaDirezioneSbloccata() {
		StanzaBloccata.impostaDirezioneBloccata("nord");
		assertEquals(StanzaBloccata.getDirezioni().get(0),"sud");
	}
	
	@Test
	void testGetDirezioniStanzabloccataConZeroDirezioniBloccate() {
		assertEquals(StanzaBloccata.getDirezioni().get(0),"nord");
		assertEquals(StanzaBloccata.getDirezioni().get(1),"sud");
	}
	
	@Test
	void testBloccaQuattroDirezioni() {
		StanzaBloccata.impostaDirezioneBloccata("nord");
		StanzaBloccata.impostaDirezioneBloccata("sud");
		StanzaBloccata.impostaDirezioneBloccata("est");
		StanzaBloccata.impostaDirezioneBloccata("ovest");
		assertEquals(StanzaBloccata.getDirezioniBloccate().get(0),"nord");
		assertEquals(StanzaBloccata.getDirezioniBloccate().get(1),"sud");
		assertEquals(StanzaBloccata.getDirezioniBloccate().get(2),"est");
		assertEquals(StanzaBloccata.getDirezioni().get(0),"ovest");
	}
	
	@Test
	void testGetDirezioniStanzaSbloccataConTutteLeDirezioneBloccate() {
		StanzaSbloccata.impostaDirezioneBloccata("nord");
		StanzaSbloccata.impostaDirezioneBloccata("sud");
		assertEquals(StanzaSbloccata.getDirezioniBloccate().get(0),"nord");
		assertEquals(StanzaSbloccata.getDirezioniBloccate().get(1),"sud");
	}
	
	

}
