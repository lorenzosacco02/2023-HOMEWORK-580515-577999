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
		
		StanzaBloccata = new StanzaBloccata("Bagno","ATTREZZO","");
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
		assertEquals(StanzaBloccata.getDirezioniBloccate()[0],"nord");
		assertEquals(StanzaBloccata.getDirezioniBloccate()[1],"sud");
		assertNull(StanzaBloccata.getDirezioniBloccate()[2]);
	}
	
	@Test
	void testGetDirezioniStanzabloccataConTutteLeDirezioneBloccate() {
		StanzaBloccata.impostaDirezioneBloccata("nord");
		StanzaBloccata.impostaDirezioneBloccata("sud");
		assertNull(StanzaBloccata.getDirezioni()[0]);
		assertNull(StanzaBloccata.getDirezioni()[1]);
	}
	
	@Test
	void testGetDirezioniStanzabloccataConAlmenoUnaDirezioneSbloccata() {
		StanzaBloccata.impostaDirezioneBloccata("nord");
		assertNull(StanzaBloccata.getDirezioni()[0]);
		assertEquals(StanzaBloccata.getDirezioni()[1],"sud");
	}
	
	@Test
	void testGetDirezioniStanzabloccataConZeroDirezioniBloccate() {
		assertEquals(StanzaBloccata.getDirezioni()[0],"nord");
		assertEquals(StanzaBloccata.getDirezioni()[1],"sud");
	}
	
	@Test
	void testBloccaQuattroDirezioni() {
		StanzaBloccata.impostaDirezioneBloccata("nord");
		StanzaBloccata.impostaDirezioneBloccata("sud");
		StanzaBloccata.impostaDirezioneBloccata("est");
		StanzaBloccata.impostaDirezioneBloccata("ovest");
		assertEquals(StanzaBloccata.getDirezioniBloccate()[0],"nord");
		assertEquals(StanzaBloccata.getDirezioniBloccate()[1],"sud");
		assertEquals(StanzaBloccata.getDirezioniBloccate()[2],"est");
	}
	
	@Test
	void testGetDirezioniStanzaSbloccataConTutteLeDirezioneBloccate() {
		StanzaSbloccata.impostaDirezioneBloccata("nord");
		StanzaSbloccata.impostaDirezioneBloccata("sud");
		assertEquals(StanzaSbloccata.getDirezioniBloccate()[0],"nord");
		assertEquals(StanzaSbloccata.getDirezioniBloccate()[1],"sud");
	}
	
	

}
