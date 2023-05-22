package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiMap;

class ComandoTest {
	private Comando comando;
	private Partita partita;
	private Stanza StanzaConNome;
	private FabbricaDiComandi Fabbrica;
	private Stanza StanzaPiena;
	private Attrezzo[] arrayVuoto;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("Attrezzo",5),new Attrezzo("Banana",200)};
	@BeforeEach
	
	void setup(){
		this.partita =new Partita();
		this.Fabbrica=new FabbricaDiComandiMap();
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
		this.arrayVuoto= new Attrezzo[10];
		for(int i=0;i<10;i++){
			this.arrayVuoto[i]= new Attrezzo();
		}
		this.StanzaConNome.impostaStanzaAdiacente("sud",StanzaPiena);
		this.partita.setStanzaCorrente(StanzaConNome);
		this.partita.getPlayer().getBorsa().addAttrezzo(attrezzi[3]);
	}

	//Test Vai

	@Test
	void testVaiNull() {
		comando=this.Fabbrica.costruisciComando("vai");
		assertEquals("Dove vuoi andare?\n",comando.esegui(partita));
	}
	@Test
	void testVaiDirezioneInesistente() {
		comando=this.Fabbrica.costruisciComando("vai nord");
		assertEquals("Direzione inesistente\n"+partita.getStanzaCorrente().getDescrizione(),comando.esegui(partita));
	}
	
	@Test
	void testVaiDirezione() {
		comando=this.Fabbrica.costruisciComando("vai sud");
		assertEquals(comando.esegui(partita),"CFU rimasti: "+partita.getPlayer().getCfu()+"\n"+partita.getStanzaCorrente().getDescrizione());
	}
	
	//Test Aiuto
	
	@Test
	void testAiutoAiuto() {
		comando=this.Fabbrica.costruisciComando("aiuto aiuto");
		assertEquals("Il comando aiuto ti da informazioni su tutti i comandi del gioco\nsi scriva aiuto e una di queste opzioni [aiuto, prendi, fine, posa, vai, info]",comando.esegui(partita));
	}
	@Test
	void testAiutoVai() {
		comando=this.Fabbrica.costruisciComando("aiuto vai");
		assertEquals("Il comando vai ti fa andare nella stanza adiacente a quella in cui ti trovi nella direzione da te indicata\nsi scriva vai e una di queste opzioni [nord sud est ovest]",comando.esegui(partita));
	}
	@Test
	void testAiutoFine() {
		comando=this.Fabbrica.costruisciComando("aiuto fine");
		assertEquals("Il comando fine ti permette di finire il gioco in qualsiasi momento\nsi scriva fine",comando.esegui(partita));
	}
	@Test
	void testAiutoPrendi() {
		comando=this.Fabbrica.costruisciComando("aiuto prendi");
		assertEquals("Il comando prendi ti fa prendere un attrezzo che si trova nella stanza in cui ti trovi\nsi scriva prendi e il nome del attrezzo che vuoi prendere",comando.esegui(partita));
	}
	@Test
	void testAiutoPosa() {
		comando=this.Fabbrica.costruisciComando("aiuto posa");
		assertEquals("Il comando posa ti fa lasciare un attrezzo nella stanza in cui ti trovi\nsi scriva posa e il nome del attrezzo che vuoi prendere",comando.esegui(partita));
	}
	@Test
	void testAiutoBorsa() {
		comando=this.Fabbrica.costruisciComando("aiuto info");
		assertEquals("Il comando info ti permette di ricevere informazioni sulla stanza in qui ti trovi o sulla tua borsa con tutti gli oggetti con i relativi pesi\nsi scriva info e una di queste opzioni [borsa stanza]",comando.esegui(partita));
	}
	@Test
	void testAiutoSenzaArgomento() {
		comando=this.Fabbrica.costruisciComando("aiuto");
		assertEquals("[aiuto, prendi, fine, posa, vai, info]",comando.esegui(partita));
	}
	@Test
	void testAiutoDefault() {
		comando=this.Fabbrica.costruisciComando("aiuto sasso");
		assertEquals("non esiste il comando:\nsasso",comando.esegui(partita));
	}
	
	//test Fine
	
	@Test
	void testFine() {
		comando=this.Fabbrica.costruisciComando("fine");
		assertEquals("Grazie di aver giocato!",comando.esegui(partita));
	}
	
	//Test Prendi
	@Test
	void testPrendiNull() {
		comando=this.Fabbrica.costruisciComando("prendi");
		assertEquals("Che attrezzo vuoi prendere?",comando.esegui(partita));
	}
	
	@Test
	void testPrendi() {
		comando=this.Fabbrica.costruisciComando("prendi Attrezzo");
		assertEquals(comando.esegui(partita),"Ho preso Attrezzo (5kg)! Lo troverai nella tua Borsa: 10kg/10kg\n"+"\nCFU rimasti: "+ partita.getPlayer().getCfu());
	}
	
	@Test
	void testPrendiAttrezzoNonPresente() {
		comando=this.Fabbrica.costruisciComando("prendi Banana");
		assertEquals(comando.esegui(partita),"La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra");
	}
	
	//test Rimuovi
	@Test
	void testRimuoviStanzaInesistente() {
		this.partita.setStanzaCorrente(null);
		comando=this.Fabbrica.costruisciComando("posa Attrezzo");
		assertEquals("Stanza innesistente",comando.esegui(partita));
	}
	@Test
	void testRimuoviAttrezzoInesistente() {
		comando=this.Fabbrica.costruisciComando("posa");
		assertEquals("Che attrezzo vuoi posare?",comando.esegui(partita));
	}
	@Test
	void testRimuoviAttrezzoNonTrovato() {
		comando=this.Fabbrica.costruisciComando("posa Banana");
		assertEquals("Ho toccato il fondo, ma banana non l'ho trovato",comando.esegui(partita));
	}
	@Test
	void testRimuoviStanzaPiena() {
		this.partita.setStanzaCorrente(StanzaPiena);
		comando=this.Fabbrica.costruisciComando("posa Attrezzo");
		assertEquals("C'è già troppo disordine in questa stanza!\nNon vorrei crearne altro",comando.esegui(partita));
	}
	@Test
	void testRimuoviSuccesso() {
		comando=this.Fabbrica.costruisciComando("posa Attrezzo");
		assertEquals(comando.esegui(partita),"Non mi è mai servito a nulla attrezzo quindi me ne sono sbarazzato, ora la mia borsa è più leggera! "+partita.getPlayer().getBorsa().getPeso()+"kg/"+partita.getPlayer().getBorsa().getPesoMax()+"kg\n\nCFU rimasti: "+partita.getPlayer().getCfu());
	}
	//test Borsa
	@Test
	void testBorsaSuccesso() {
		comando=this.Fabbrica.costruisciComando("info borsa");
		assertEquals("Contenuto borsa (5kg/10kg): Attrezzo (5kg) ",comando.esegui(partita));
	}
	@Test
	void testBorsaVuota() {
		this.Fabbrica.costruisciComando("posa Attrezzo").esegui(partita);
		comando=this.Fabbrica.costruisciComando("info borsa");
		assertEquals("Borsa vuota",comando.esegui(partita));
	}
	
}
