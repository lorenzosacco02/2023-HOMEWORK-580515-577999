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
		assertEquals("Una comunissima Stanza\n"+ "Uscite: \n"+ "Attrezzi nella stanza: \n"+"In questa Stanza sucedono cose strane quasi magiche",this.comandoStanza.esegui(P));
	}
	@Test
	void testComandoInfoConParametroStanzaMagicaConOggetti() {
		this.StanzaMagica.addAttrezzo(new Attrezzo("libro",5));
		this.StanzaMagica.addAttrezzo(new Attrezzo("libro",5));
		this.P.setStanzaCorrente(StanzaMagica);
		assertEquals("Una comunissima Stanza\n"+ "Uscite: \n"+ "Attrezzi nella stanza: libro (5kg) orbil (10kg)\n"+"In questa Stanza sucedono cose strane quasi magiche",this.comandoStanza.esegui(P));
	}
	//Test ComandoInfo con Parametro Stanza Bloccata
	@Test
	void testComandoInfoConParametroStanzaBloccataSenzaOggetti() {
		this.P.setStanzaCorrente(StanzaBloccata);
		assertEquals("cucina\n"+ "Uscite: sud\n"+ "Attrezzi nella stanza: \n"+"Dai segni sul pavimento deduco che ci sono altri passaggi oltre a questi portei usare\n[chiave]\nper cercare di riaprire tutti i passaggi",this.comandoStanza.esegui(P));
	}
	@Test
	void testComandoInfoConParametroStanzaBloccataConOggetti() {
		this.P.setStanzaCorrente(StanzaBloccata);
		this.StanzaBloccata.addAttrezzo(new Attrezzo("libro",5));
		assertEquals("cucina\n"+ "Uscite: sud\n"+ "Attrezzi nella stanza: libro (5kg)\n"+"Dai segni sul pavimento deduco che ci sono altri passaggi oltre a questi portei usare\n[chiave]\nper cercare di riaprire tutti i passaggi",this.comandoStanza.esegui(P));
	}
	@Test
	void testComandoInfoConParametroStanzaBloccataConOggettiSbloccante() {
		this.P.setStanzaCorrente(StanzaBloccata);
		this.StanzaBloccata.addAttrezzo(new Attrezzo("chiave",5));
		assertEquals("cucina\n"+ "Uscite: nord sud\n"+ "Attrezzi nella stanza: chiave (5kg)",this.comandoStanza.esegui(P));
	}
	//Test ComandoInfo con Parametro Stanza Buia
	@Test
	void testComandoInfoConParametroStanzaBuiaSenzaOggetti() {
		this.P.setStanzaCorrente(StanzaBuia);
		assertEquals("qui c'è un buio pesto",this.comandoStanza.esegui(P));
	}
	@Test
	void testComandoInfoConParametroStanzaBuiaConOggetti() {
		this.P.setStanzaCorrente(StanzaBuia);
		this.StanzaBuia.addAttrezzo(new Attrezzo("libro",5));
		assertEquals("qui c'è un buio pesto",this.comandoStanza.esegui(P));
	}
	@Test
	void testComandoInfoConParametroStanzaBuiaConOggettiLuminoso() {
		this.P.setStanzaCorrente(StanzaBuia);
		this.StanzaBuia.addAttrezzo(new Attrezzo("torcia",5));
		assertEquals("cantina\n"+ "Uscite: \n"+ "Attrezzi nella stanza: torcia (5kg)",this.comandoStanza.esegui(P));
	}
	//Test ComandoInfo con Parametro fuori dal ordinario
	@Test
	void testComandoInfoConParametroNull() {
		this.comando.setParametro(null);
		assertEquals("Su cosa vuoi avere le informazioni?",this.comando.esegui(P));
	}
	@Test
	void testComandoInfoConParametroStringaVuota() {
		this.comando.setParametro("");
		assertEquals("Su cosa vuoi avere le informazioni?",this.comando.esegui(P));
	}
	@Test
	void testComandoInfoConParametroRandom() {
		this.comando.setParametro("sasso");
		assertEquals("Non esiste alcuna info su: sasso",this.comando.esegui(P));
	}
}