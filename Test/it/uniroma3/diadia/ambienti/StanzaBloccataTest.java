package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private StanzaBloccata StanzaBloccata;
	private StanzaBloccata StanzaSbloccata;
	private StanzaBloccata StanzaSbloccataConNuovoCostruttore;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("ATTREZZO",5),new Attrezzo("piedediporco",200)};
	
	@BeforeEach
	void setUp(){
		StanzaSbloccata=new StanzaBloccata("Cucina");
		StanzaSbloccata.addAttrezzo(attrezzi[4]);
		
		StanzaBloccata = new StanzaBloccata("Bagno","attrezzo pugnale caciavite");
		StanzaSbloccataConNuovoCostruttore= new StanzaBloccata("Bagno","nord sud","attrezzo pugnale caciavite piedediporco");
		StanzaSbloccataConNuovoCostruttore.impostaStanzaAdiacente("nord",StanzaSbloccata);
		StanzaSbloccataConNuovoCostruttore.impostaStanzaAdiacente("sud",StanzaBloccata);
		StanzaSbloccataConNuovoCostruttore.impostaStanzaAdiacente("est",StanzaBloccata);
		StanzaSbloccataConNuovoCostruttore.impostaStanzaAdiacente("ovest",StanzaSbloccataConNuovoCostruttore);
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
	@Test
	void testGetDirezioniBloccateNuovoCostuttoreBloccaDirrezioni() {
		assertEquals(StanzaSbloccataConNuovoCostruttore.getDirezioniBloccate().get(0),"nord");
		assertEquals(StanzaSbloccataConNuovoCostruttore.getDirezioniBloccate().get(1),"sud");
	}
	
	@Test
	void testGetDirezioniStanzaNuovoCostuttoreBloccaDirrezioni() {
		assertEquals(StanzaSbloccataConNuovoCostruttore.getDirezioni().get(0),"est");
		assertEquals(StanzaSbloccataConNuovoCostruttore.getDirezioni().get(1),"ovest");
	}
	@Test
	void testGetDirezioniStanzaToStringNuovoCostuttoreBloccaDirrezioni() {
		assertEquals(StanzaSbloccataConNuovoCostruttore.getDirezioni().toString(),"[est, ovest]");
	}
	@Test
	void testGetDirezioniStanzaToStringNuovoCostuttoreBloccaDirrezioniConLaStanzaSbloccata() {
		assertTrue(StanzaSbloccataConNuovoCostruttore.addAttrezzo(attrezzi[4]));
		assertEquals(StanzaSbloccataConNuovoCostruttore.getDirezioni().toString(),"[nord, sud, est, ovest]");
	}
	
	

}
