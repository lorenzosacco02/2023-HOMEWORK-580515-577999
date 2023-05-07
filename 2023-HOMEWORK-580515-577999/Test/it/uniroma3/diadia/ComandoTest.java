package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoTest {
	private Comando comando;
	private Partita partita;
	private Stanza StanzaConNome;
	private Stanza StanzaPiena;
	private Attrezzo[] arrayVuoto;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("Attrezzo",5),new Attrezzo("Banana",200)};
	@BeforeEach
	
	void setup(){
		this.partita =new Partita();
		this.comando=new Comando();
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
		assertEquals("Dove vuoi andare ?\n"+partita.getStanzaCorrente().getDescrizione(),comando.vai(null, partita));
	}
	@Test
	void testVaiDirezioneInesistente() {
		assertEquals("Direzione inesistente\n"+partita.getStanzaCorrente().getDescrizione(),comando.vai("nord", partita));
	}
	
	@Test
	void testVaiDirezione() {
		assertEquals(comando.vai("sud", partita),"CFU rimasti: "+partita.getPlayer().getCfu()+"\n"+partita.getStanzaCorrente().getDescrizione());
	}
	
	//Test Aiuto
	
	@Test
	void testAiutoAiuto() {
		assertEquals("Il comando aiuto ti da informazioni su tutti i comandi del gioco\nsi scriva aiuto e una di queste opzioni [vai aiuto fine prendi posa]",comando.aiuto("aiuto"));
	}
	@Test
	void testAiutoVai() {
		assertEquals("Il comando vai ti fa andare nella stanza adiacente a quella in cui ti trovi nella direzione da te indicata\nsi scriva vai e una di queste opzioni [nord sud est ovest]",comando.aiuto("vai"));
	}
	@Test
	void testAiutoFine() {
		assertEquals("Il comando fine ti permette di finire il gioco in qualsiasi momento\nsi scriva fine",comando.aiuto("fine"));
	}
	@Test
	void testAiutoPrendi() {
		assertEquals("Il comando prendi ti fa prendere un attrezzo che si trova nella stanza in cui ti trovi\nsi scriva prendi e il nome del attrezzo che vuoi prendere",comando.aiuto("prendi"));
	}
	@Test
	void testAiutoPosa() {
		assertEquals("Il comando posa ti fa lasciare un attrezzo nella stanza in cui ti trovi\nsi scriva posa e il nome del attrezzo che vuoi prendere",comando.aiuto("posa"));
	}
	@Test
	void testAiutoSenzaArgomento() {
		assertEquals("[vai aiuto fine prendi posa]",comando.aiuto(""));
	}
	@Test
	void testAiutoDefault() {
		assertEquals("non esiste il comando:\nsasso",comando.aiuto("sasso"));
	}
	
	//test Fine
	
	@Test
	void testFine() {
		assertEquals(comando.fine(),"Grazie di aver giocato!");
	}
	
	//Test Prendi
	@Test
	void testPrendiNull() {
		assertEquals(comando.prendi(null, partita),"Il tuo attrezzo non esiste");
	}
	
	@Test
	void testPrendi() {
		assertEquals(comando.prendi("Attrezzo", partita),"Ho preso Attrezzo (5kg)! Lo troverai nella tua Borsa: 10kg/10kg\n"+"\nCFU rimasti: "+ partita.getPlayer().getCfu());
	}
	
	@Test
	void testPrendiAttrezzoNonPresente() {
		assertEquals(comando.prendi("Banana", partita),"La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra");
	}
	
	//test Rimuovi
	@Test
	void testRimuoviStanzaInesistente() {
		this.partita.setStanzaCorrente(null);
		assertEquals("Oggetto o Stanza innesistente",comando.rimuovi("Attrezzo", partita));
	}
	@Test
	void testRimuoviAttrezzoInesistente() {
		assertEquals("Oggetto o Stanza innesistente",comando.rimuovi(null, partita));
	}
	@Test
	void testRimuoviAttrezzoNonTrovato() {
		assertEquals("Ho toccato il fondo, ma Banana non l'ho trovato",comando.rimuovi("Banana", partita));
	}
	@Test
	void testRimuoviStanzaPiena() {
		this.partita.setStanzaCorrente(StanzaPiena);
		assertEquals("C'è già troppo disordine in questa stanza!\nNon vorrei crearne altro",comando.rimuovi("Attrezzo", partita));
	}
	@Test
	void testRimuoviSuccesso() {
		assertEquals(comando.rimuovi("Attrezzo", partita),"Non mi è mai servito a nulla Attrezzo quindi me ne sono sbarazzato, ora la mia borsa è più leggera! "+partita.getPlayer().getBorsa().getPeso()+"kg/"+partita.getPlayer().getBorsa().getPesoMax()+"kg\n\nCFU rimasti: "+partita.getPlayer().getCfu());
	}
}
