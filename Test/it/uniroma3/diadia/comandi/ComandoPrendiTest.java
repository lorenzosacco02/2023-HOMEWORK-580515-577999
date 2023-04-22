package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {

	private ComandoPrendi Prendi;
	private Partita partita;
	private Stanza StanzaConNome;
	private Stanza StanzaPiena;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("Attrezzo",5),new Attrezzo("Banana",200)};

	@BeforeEach
	void setUp(){
		Prendi = new ComandoPrendi();
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
	void testPrendiNull() {
		Prendi.setParametro(null);
		assertEquals(Prendi.esegui(partita).toString(),"Che attrezzo vuoi prendere?");
	}
	
	@Test
	void testPrendiAttrezzoInesistente() {
		Prendi.setParametro("nonEsisto");
		assertEquals(Prendi.esegui(partita).toString(),"Il tuo attrezzo non esiste");
	}

	@Test
	void testPrendi() {
		Prendi.setParametro("Attrezzo");
		assertEquals(Prendi.esegui(partita).toString(),"Ho preso Attrezzo (5kg)! Lo troverai nella tua Borsa: 10kg/10kg\n"+"\nCFU rimasti: "+ partita.getPlayer().getCfu());
	}

	@Test
	void testPrendiAttrezzoTroppoPesante() {
		Prendi.setParametro("Banana");
		assertEquals(Prendi.esegui(partita).toString(),"La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra");
	}

}
