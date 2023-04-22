package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

class ComandoBorsaTest {
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("Attrezzo",5),new Attrezzo("Banana",2),new Attrezzo("osso",6),new Attrezzo("spada",1),new Attrezzo("scudo",1),new Attrezzo("cassa",2),new Attrezzo("sasso",1)};
	private Borsa borsaNonVuota;
	private Borsa borsaVuota;
	private Giocatore GiocatoreConBorsaNonVuota;
	private Giocatore GiocatoreConBorsaVuota;
	private Giocatore GiocatoreSenzaBorsa;
	private Attrezzo attrezzoDiProva;
	private Partita partita;
	private ComandoBorsa Borsa;
	@BeforeEach
	void setUp() {
		borsaNonVuota= new Borsa(10);
		borsaVuota = new Borsa();
		attrezzoDiProva=attrezzi[3];
		borsaNonVuota.addAttrezzo(attrezzoDiProva);
		GiocatoreConBorsaNonVuota = new Giocatore(borsaNonVuota);
		GiocatoreConBorsaVuota = new Giocatore(borsaVuota);
		GiocatoreSenzaBorsa= new Giocatore();
		partita = new Partita();
		Borsa =new ComandoBorsa();
	}

	@Test
	void BorsaVuotaTest() {
		partita.setPlayer(GiocatoreConBorsaVuota);
		assertEquals("Borsa vuota",Borsa.esegui(partita).toString());
	}
	@Test
	void BorsaNonVuotaTest() {
		partita.setPlayer(GiocatoreConBorsaNonVuota);
		assertEquals("Contenuto borsa (5kg/10kg): Attrezzo (5kg) ",Borsa.esegui(partita).toString());
	}
	void BorsaNullTest() {
		partita.setPlayer(GiocatoreSenzaBorsa);
		assertEquals("Borsa vuota",Borsa.esegui(partita).toString());
	}

}
