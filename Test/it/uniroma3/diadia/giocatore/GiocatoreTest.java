package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	private Stanza stanzaNonVuota;
	private Stanza stanzaVuota;
	private Stanza StanzaPiena;
	private Attrezzo attrezzoDiProva;
	private Stanza StanzaPienaConRipetizione;
	private Stanza StanzaConRipetizione;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",5),new Attrezzo("Attrezzo",5),new Attrezzo("Banana",2),new Attrezzo("osso",6),new Attrezzo("spada",1),new Attrezzo("scudo",1),new Attrezzo("cassa",2),new Attrezzo("sasso",1)};
	private Borsa borsaNonVuota;
	private Borsa borsaVuota;
	private Giocatore GiocatoreConBorsaNonVuota;
	private Giocatore GiocatoreConBorsaVuota;
	@BeforeEach
	void setup() {
		stanzaNonVuota = new Stanza("Bagno");
		for(int i=0;i<5;i++){
			stanzaNonVuota.addAttrezzo(attrezzi[i]);
		}
		stanzaVuota=new Stanza("Docccia");
		StanzaPiena=new Stanza("Mensa");
		for(int i=0;i<10;i++){
			StanzaPiena.addAttrezzo(attrezzi[i]);
		}
		StanzaPienaConRipetizione=new Stanza("N10");
		for(int i=0;i<10;i++){
			StanzaPienaConRipetizione.addAttrezzo(attrezzi[8]);
		}
		StanzaConRipetizione=new Stanza("N11");
		for(int i=0;i<1;i++){
			StanzaConRipetizione.addAttrezzo(attrezzi[8]);
		}
		borsaNonVuota= new Borsa(10);
		borsaVuota = new Borsa();
		attrezzoDiProva=attrezzi[3];
		borsaNonVuota.addAttrezzo(attrezzoDiProva);
		GiocatoreConBorsaNonVuota = new Giocatore(borsaNonVuota);
		GiocatoreConBorsaVuota = new Giocatore(borsaVuota);
	}

	//Test del metodo prendiAttrezzo
	@Test
	void testPrendiAttrezzoInesistente() {
		assertEquals("Il tuo attrezzo non esiste", GiocatoreConBorsaNonVuota.PrendiAttrezzo("NonEsisto", StanzaPiena));
	}
	void testPrendiAttrezzoNull() {
		assertEquals("Il tuo attrezzo non esiste", GiocatoreConBorsaNonVuota.PrendiAttrezzo(null, StanzaPiena));
	}
	@Test
	void testPrendiAttrezzo() {
		assertEquals(GiocatoreConBorsaNonVuota.PrendiAttrezzo(attrezzi[3].getNome(), StanzaPiena),"Ho preso "+attrezzi[3].toString()+"! Lo troverai nella tua Borsa: "+GiocatoreConBorsaNonVuota.getBorsa().getPeso()+"kg/"+GiocatoreConBorsaNonVuota.getBorsa().getPesoMax()+"kg\n");
	}
	@Test
	void testPrendiAttrezzoBorsaTroppoPesante() {
		assertEquals(GiocatoreConBorsaNonVuota.PrendiAttrezzo(attrezzi[5].getNome(), StanzaPiena),"La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra");
	}
	@Test
	void testPrendiAttrezzoStanzaVuota() {
		assertEquals("Il tuo attrezzo non esiste", GiocatoreConBorsaNonVuota.PrendiAttrezzo("", stanzaVuota));
	}
	@Test
	void testPrendiAttrezzoStanzaNull() {
		assertEquals("La stanza non esiste", GiocatoreConBorsaNonVuota.PrendiAttrezzo("", null));
	}

	//Test Metodi Con CFU
	@Test
	void testGetCFU(){
		assertEquals(20,this.GiocatoreConBorsaNonVuota.getCfu());
	}
	@Test
	void testGetCFUDopoAverSettatoiCFUconUnNumeroPositivo(){
		this.GiocatoreConBorsaNonVuota.setCfu(5);
		assertEquals(5,this.GiocatoreConBorsaNonVuota.getCfu());
	}
	@Test
	void testGetCFUDopoAverSettatoiCFUconUnNumeroNegativo(){
		this.GiocatoreConBorsaNonVuota.setCfu(-5);
		assertEquals(-5,this.GiocatoreConBorsaNonVuota.getCfu());
	}
	@Test
	void testGetCFUDopoAverSettatoCFUconZero(){
		this.GiocatoreConBorsaNonVuota.setCfu(0);
		assertEquals(0,this.GiocatoreConBorsaNonVuota.getCfu());
	}
	//Test del metodo removeAttrezzo
	@Test
	void testRemoveAttrezzoInesistente(){
		assertEquals("Che attrezzo vuoi posare?",this.GiocatoreConBorsaNonVuota.RemoveAttrezzo(null, StanzaConRipetizione));
	}
	@Test
	void testRemoveAttrezzoConStanzaInesistente() {
		assertEquals("Stanza innesistente",this.GiocatoreConBorsaNonVuota.RemoveAttrezzo(attrezzi[3].getNome(), null));
	}
	@Test
	void testRemoveAttrezzoConStanzaPiena(){
		assertEquals("C'è già troppo disordine in questa stanza!\nNon vorrei crearne altro",this.GiocatoreConBorsaNonVuota.RemoveAttrezzo(attrezzoDiProva.getNome(), StanzaPiena));
	}
	@Test
	void testRemoveAttrezzoConAttrezzoNonTrovato(){
		assertEquals("Ho toccato il fondo, ma "+attrezzi[4].getNome()+" non l'ho trovato",this.GiocatoreConBorsaNonVuota.RemoveAttrezzo(attrezzi[4].getNome(), stanzaNonVuota));
	}
	@Test
	void testRemoveAttrezzoConBorsaVuota(){
		assertEquals("Ho toccato il fondo, ma "+attrezzi[4].getNome()+" non l'ho trovato",this.GiocatoreConBorsaVuota.RemoveAttrezzo(attrezzi[4].getNome(), stanzaNonVuota));
	}
	@Test
	void testRemoveAttrezzoTrovato(){
		assertEquals(this.GiocatoreConBorsaNonVuota.RemoveAttrezzo(attrezzoDiProva.getNome(),stanzaNonVuota),"Non mi è mai servito a nulla "+attrezzoDiProva.getNome()+" quindi me ne sono sbarazzato, ora la mia borsa è più leggera! "+GiocatoreConBorsaNonVuota.getBorsa().getPeso()+"kg/"+GiocatoreConBorsaNonVuota.getBorsa().getPesoMax()+"kg\n");
	}
}
