package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class ComandoAiutoTest {
	private Partita partita;
	private ComandoAiuto Aiuto;
	
	@BeforeEach
	void setUp(){
		Aiuto = new ComandoAiuto();
		partita = new Partita();
	}

	@Test
	void testAiutoAiuto() {
		Aiuto.setParametro("aiuto");
		assertEquals("Il comando aiuto ti da informazioni su tutti i comandi del gioco\nsi scriva aiuto e una di queste opzioni [vai aiuto fine prendi posa borsa]",Aiuto.esegui(partita).toString());
	}
	@Test
	void testAiutoVai() {
		Aiuto.setParametro("vai");
		assertEquals("Il comando vai ti fa andare nella stanza adiacente a quella in cui ti trovi nella direzione da te indicata\nsi scriva vai e una di queste opzioni [nord sud est ovest]",Aiuto.esegui(partita).toString());
	}
	@Test
	void testAiutoFine() {
		Aiuto.setParametro("fine");
		assertEquals("Il comando fine ti permette di finire il gioco in qualsiasi momento\nsi scriva fine",Aiuto.esegui(partita).toString());
	}
	@Test
	void testAiutoPrendi() {
		Aiuto.setParametro("prendi");
		assertEquals("Il comando prendi ti fa prendere un attrezzo che si trova nella stanza in cui ti trovi\nsi scriva prendi e il nome del attrezzo che vuoi prendere",Aiuto.esegui(partita).toString());
	}
	@Test
	void testAiutoPosa() {
		Aiuto.setParametro("posa");
		assertEquals("Il comando posa ti fa lasciare un attrezzo nella stanza in cui ti trovi\nsi scriva posa e il nome del attrezzo che vuoi prendere",Aiuto.esegui(partita).toString());
	}
	@Test
	void testAiutoBorsa() {
		Aiuto.setParametro("borsa");
		assertEquals("Il comando borsa ti permette di vedere quali oggetti hai nella tua borsa con i relativi pesi",Aiuto.esegui(partita).toString());
	}
	@Test
	void testAiutoSenzaArgomento() {
		Aiuto.setParametro("");
		assertEquals("[vai aiuto fine prendi posa borsa]",Aiuto.esegui(partita).toString());
	}
	@Test
	void testAiutoDefault() {
		Aiuto.setParametro("sasso");
		assertEquals("non esiste il comando:\nsasso",Aiuto.esegui(partita).toString());
	}

}
