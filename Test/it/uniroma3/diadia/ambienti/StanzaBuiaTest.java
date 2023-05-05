package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private StanzaBuia StanzaSenzaLuce;
	private StanzaBuia StanzaPienaConLuce;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("ATTREZZO",5),new Attrezzo("lanterna",200)};
	
	@BeforeEach
	void setUp(){
		StanzaPienaConLuce=new StanzaBuia("Cucina");
		StanzaPienaConLuce.addAttrezzo(attrezzi[4]);
		StanzaSenzaLuce = new StanzaBuia("Bagno","ATTREZZO");
	}

	@Test
	void testGetDescrizioneStanzaBuia() {
		assertEquals(StanzaSenzaLuce.getDescrizione(),"qui c'è un buio pesto");
	}
	
	@Test
	void testGetDescrizioneStanzaConAttrezzoLuminoso() {
		assertEquals(StanzaPienaConLuce.getDescrizione(),StanzaPienaConLuce.getNome()+"\nUscite: \nAttrezzi nella stanza: "+attrezzi[4].toString()+" ");
	}
	
	@Test
	void testGetDescrizioneStanzaBuiaInseriscoAttrezzoLuminoso() {
		assertEquals(StanzaSenzaLuce.getDescrizione(),"qui c'è un buio pesto");
		StanzaSenzaLuce.addAttrezzo(attrezzi[3]);
		assertEquals(StanzaSenzaLuce.getDescrizione(),StanzaSenzaLuce.getNome()+"\nUscite: \nAttrezzi nella stanza: "+attrezzi[3].toString()+" ");
	}

}
