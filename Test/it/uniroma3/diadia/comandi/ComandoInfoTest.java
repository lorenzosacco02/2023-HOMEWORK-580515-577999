package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

class ComandoInfoTest {
private Comando comando;
private Comando comandoStanza;
private Stanza StanzaNormale;
private StanzaBloccata StanzaBloccata;
private StanzaBuia StanzaBuia;
private StanzaMagica StanzaMagica;
private Partita P;
	@BeforeEach
	void setUp(){
		this.comando=new ComandoInfo();
		this.comandoStanza=new ComandoInfo();
		this.StanzaBloccata=new StanzaBloccata("cucina","nord","chiave");
		this.StanzaBuia=new StanzaBuia("cantina","torcia");
		this.StanzaMagica=new StanzaMagica("Una comunissima Stanza",1);
		this.P=new Partita();
		this.StanzaNormale=new Stanza("bibliotteca");
		this.StanzaBloccata.impostaStanzaAdiacente("sud", this.StanzaBuia);
		this.StanzaBloccata.impostaStanzaAdiacente("nord", this.StanzaMagica);
		this.comandoStanza.setParametro("stanza");
	}
	//Test ComandoInfo con Parametro Borsa
	@Test
	void testComandoInfoConParametroBorsaVuota() {
		this.comando.setParametro("borsa");
		assertEquals("Borsa vuota",this.comando.esegui(P));
	}
	@Test
	void testComandoInfoConParametroBorsaPiena() {
		this.P.getPlayer().getBorsa().addAttrezzo(new Attrezzo("libro",10));
		this.comando.setParametro("borsa");
		assertEquals("Contenuto borsa (10kg/10kg): libro (10kg) ",this.comando.esegui(P));
		
	}
	@Test
	void testComandoInfoConParametroBorsaConPiùOggetti() {
		this.P.getPlayer().getBorsa().addAttrezzo(new Attrezzo("libro",2));
		this.P.getPlayer().getBorsa().addAttrezzo(new Attrezzo("manganello",5));
		this.P.getPlayer().getBorsa().addAttrezzo(new Attrezzo("banana",1));
		this.comando.setParametro("borsa");
		assertEquals("Contenuto borsa (8kg/10kg): libro (2kg) manganello (5kg) banana (1kg) ",this.comando.esegui(P));
	}
	//Test ComandoInfo con Parametro Stanza normale
	@Test
	void testComandoInfoConParametroStanzaNormaleSenzaOggetti() {
		this.P.setStanzaCorrente(StanzaNormale);
		assertEquals("bibliotteca\n"+ "Uscite: \n"+ "Attrezzi nella stanza: ",this.comandoStanza.esegui(P));
	}
	@Test
	void testComandoInfoConParametroStanzaNormaleConOggetti() {
		this.StanzaNormale.addAttrezzo(new Attrezzo("libro",5));
		this.P.setStanzaCorrente(StanzaNormale);
		assertEquals("bibliotteca\n"+ "Uscite: \n"+ "Attrezzi nella stanza: libro (5kg)",this.comandoStanza.esegui(P));
	}
	//Test ComandoInfo con Parametro Stanza Magica
	@Test
	void testComandoInfoConParametroStanzaMagicaSenzaOggetti() {
		this.P.setStanzaCorrente(StanzaMagica);
		assertEquals("Una comunissima Stanza\n"+ "Uscite: \n"+ "Attrezzi nella stanza: ",this.comandoStanza.esegui(P));
	}
	@Test
	void testComandoInfoConParametroStanzaMagicaConOggetti() {
		this.StanzaMagica.addAttrezzo(new Attrezzo("libro",5));
		this.StanzaMagica.addAttrezzo(new Attrezzo("libro",5));
		this.P.setStanzaCorrente(StanzaMagica);
		assertEquals("Una comunissima Stanza\n"+ "Uscite: \n"+ "Attrezzi nella stanza: libro (5kg) orbil (10kg)",this.comandoStanza.esegui(P));
	}
	@Test
	void testComandoInfoConParametroStanzaBloccataSenzaOggetti() {
		
	}
	@Test
	void testComandoInfoConParametroStanzaBloccataConOggetti() {
		
	}
	@Test
	void testComandoInfoConParametroStanzaBuiaSenzaOggetti() {
		
	}
	@Test
	void testComandoInfoConParametroStanzaBuiaConOggetti() {
		
	}
	//
	@Test
	void testComandoInfoConParametroNull() {
		
	}
	@Test
	void testComandoInfoConParametroRandom() {
		
	}
}
