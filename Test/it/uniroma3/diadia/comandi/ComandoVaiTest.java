package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	private ComandoVai Vai;
	private Partita partita;
	private Stanza StanzaConNome;
	private Stanza StanzaPiena;
	
	@BeforeEach
	void setUp(){
		this.partita= new Partita();
		this.Vai = new ComandoVai();
		this.StanzaPiena=new Stanza("");
		this.StanzaConNome = new Stanza("Bagno");
		this.StanzaConNome.impostaStanzaAdiacente("sud",StanzaPiena);
		this.partita.setStanzaCorrente(StanzaConNome);
	}

	@Test
	void testVaiNull() {
		Vai.setParametro(null);
		assertEquals("Dove vuoi andare?\n",Vai.esegui(partita).toString());
	}
	@Test
	void testVaiDirezioneInesistente() {
		Vai.setParametro("nord");
		assertEquals("Direzione inesistente\n"+partita.getStanzaCorrente().getDescrizione(),Vai.esegui(partita).toString());
	}
	
	@Test
	void testVaiDirezione() {
		Vai.setParametro("sud");
		assertEquals(Vai.esegui(partita).toString(),"CFU rimasti: "+partita.getPlayer().getCfu()+"\n"+partita.getStanzaCorrente().getDescrizione());
	}

}
