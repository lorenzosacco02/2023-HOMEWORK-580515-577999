package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	ComandoPosa Posa;
	private Partita partita;
	private Stanza StanzaConNome;
	private Stanza StanzaPiena;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("Attrezzo",5),new Attrezzo("Banana",200)};
	
	@BeforeEach
	void setUp(){
		Posa = new ComandoPosa();
		this.partita =new Partita();
		this.StanzaPiena=new Stanza("");
		for(int i=0;i<10;i++){
			if(i<5){
				StanzaPiena.addAttrezzo(attrezzi[i]);
			}else{
				StanzaPiena.addAttrezzo(attrezzi[i-5]);
			}
		}
		this.StanzaConNome = new Stanza("Bagno");
		this.StanzaConNome.addAttrezzo(attrezzi[3]);
		this.StanzaConNome.addAttrezzo(attrezzi[4]);
		this.StanzaConNome.impostaStanzaAdiacente("sud",StanzaPiena);
		this.partita.setStanzaCorrente(StanzaConNome);
		this.partita.getPlayer().getBorsa().addAttrezzo(attrezzi[3]);
	}

	@Test
	void testRimuoviStanzaInesistente() {
		this.partita.setStanzaCorrente(null);
		Posa.setParametro("Attrezzo");
		assertEquals("Stanza innesistente",Posa.esegui(partita).toString());
	}
	@Test
	void testRimuoviAttrezzoInesistente() {
		Posa.setParametro(null);
		assertEquals("Che attrezzo vuoi posare?",Posa.esegui(partita).toString());
	}
	@Test
	void testRimuoviAttrezzoNonTrovato() {
		Posa.setParametro("Banana");
		assertEquals("Ho toccato il fondo, ma Banana non l'ho trovato",Posa.esegui(partita).toString());
	}
	@Test
	void testRimuoviStanzaPiena() {
		this.partita.setStanzaCorrente(StanzaPiena);
		Posa.setParametro("Attrezzo");
		assertEquals("C'è già troppo disordine in questa stanza!\nNon vorrei crearne altro",Posa.esegui(partita).toString());
	}
	@Test
	void testRimuoviSuccesso() {
		Posa.setParametro("Attrezzo");
		assertEquals(Posa.esegui(partita).toString(),"Non mi è mai servito a nulla Attrezzo quindi me ne sono sbarazzato, ora la mia borsa è più leggera! "+partita.getPlayer().getBorsa().getPeso()+"kg/"+partita.getPlayer().getBorsa().getPesoMax()+"kg\n\nCFU rimasti: "+partita.getPlayer().getCfu());
	}

}
