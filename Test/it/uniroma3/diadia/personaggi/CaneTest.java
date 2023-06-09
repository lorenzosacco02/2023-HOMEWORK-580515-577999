package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.Partita;
import static org.junit.jupiter.api.Assertions.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaneTest {
	private Cane doggo;
	private Cane doggoNonIniziallizato;
	private Partita Partita;
	private Attrezzo CiboPreferito;
	private Attrezzo CiboOdiato;
	@BeforeEach
	void setUp() throws Exception {
		this.doggo=new Cane();
		this.Partita=new Partita();
		this.CiboPreferito=new Attrezzo("Osso",1);
		this.CiboOdiato=new Attrezzo("banana",200);
	}
	//test Costruttori
	@Test
	void costruttoreVuoto() {
		this.doggoNonIniziallizato=new Cane();
		assertEquals("Wof Wof Wof Wof...WofWof",this.doggoNonIniziallizato.getNome());
		assertEquals("Wof Wof Wof Wof...WofWof",this.doggoNonIniziallizato.getPresentazione());
		assertEquals(new Attrezzo("Osso",1),this.doggoNonIniziallizato.getCiboPreferito());
		assertEquals(new Attrezzo("passepartout",0),this.doggoNonIniziallizato.getAttrezzoDaDonare());
	}
	@Test
	void costruttoreParametri_Nome() {
		this.doggoNonIniziallizato=new Cane("giaFranco");
		assertEquals("giaFranco",this.doggoNonIniziallizato.getNome());
		assertEquals("Wof Wof Wof Wof...WofWof",this.doggoNonIniziallizato.getPresentazione());
		assertEquals(new Attrezzo("Osso",1),this.doggoNonIniziallizato.getCiboPreferito());
		assertEquals(new Attrezzo("passepartout",0),this.doggoNonIniziallizato.getAttrezzoDaDonare());
	}
	@Test
	void costruttoreParametri_Nome_CiboPreferito() {
		this.doggoNonIniziallizato=new Cane("pierLuigi",new Attrezzo("Croccantini della monge",20));
		assertEquals("pierLuigi",this.doggoNonIniziallizato.getNome());
		assertEquals("Wof Wof Wof Wof...WofWof",this.doggoNonIniziallizato.getPresentazione());
		assertEquals(new Attrezzo("Croccantini della monge",10),this.doggoNonIniziallizato.getCiboPreferito());
		assertEquals(new Attrezzo("passepartout",0),this.doggoNonIniziallizato.getAttrezzoDaDonare());
	}
	@Test
	void costruttoreParametri_Nome_CiboPreferito_presentazione() {
		this.doggoNonIniziallizato=new Cane("PierFrancesco",new Attrezzo("tonno",5),"miao sono un gatto");
		assertEquals("PierFrancesco",this.doggoNonIniziallizato.getNome());
		assertEquals("miao sono un gatto",this.doggoNonIniziallizato.getPresentazione());
		assertEquals(new Attrezzo("tonno",10),this.doggoNonIniziallizato.getCiboPreferito());
		assertEquals(new Attrezzo("passepartout",0),this.doggoNonIniziallizato.getAttrezzoDaDonare());
	}
	@Test
	void costruttoreParametri_Nome_CiboPreferito_presentazione_Regalo() {
		this.doggoNonIniziallizato=new Cane("Carlo Detto il Magno",new Attrezzo("tartufo bianco",30),"",new Attrezzo("piede Di porco a pile",200));
		assertEquals("Carlo Detto il Magno",this.doggoNonIniziallizato.getNome());
		assertEquals("",this.doggoNonIniziallizato.getPresentazione());
		assertEquals(new Attrezzo("tartufo bianco",0),this.doggoNonIniziallizato.getCiboPreferito());
		assertEquals(new Attrezzo("piede Di porco a pile",0),this.doggoNonIniziallizato.getAttrezzoDaDonare());
	}
	//metodo agisci
	@Test
	void agisciNull(){
		assertEquals("Qualcosa è andato storto in cane",this.doggo.agisci(null));
	}
	@Test
	void agisci(){
		assertEquals("Il cane ti ha morso",this.doggo.agisci(this.Partita));
		assertEquals(19,this.Partita.getPlayer().getCfu());
	}
	//metodo RegaloRicevuto
	@Test
	void RegaloRicevutoiNullNull(){
		assertEquals("Qualcosa è andato storto in cane",this.doggo.riceviRegalo(null, null));
	}
	@Test
	void RegaloRicevutoiPreferitoNull(){
		assertEquals("Qualcosa è andato storto in cane",this.doggo.riceviRegalo(this.CiboPreferito, null));
	}
	@Test
	void RegaloRicevutoiOdiatoNull(){
		assertEquals("Qualcosa è andato storto in cane",this.doggo.riceviRegalo(this.CiboOdiato, null));
	}
	@Test
	void RegaloRicevutoiNullPartita(){
		assertEquals("Qualcosa è andato storto in cane",this.doggo.riceviRegalo(null, this.Partita));
	}
	@Test
	void RegaloRicevutoiOdiatoPartita(){
		assertEquals("Il cane ti ha morso",this.doggo.riceviRegalo(this.CiboOdiato, this.Partita));
		assertEquals(19,this.Partita.getPlayer().getCfu());
	}
	@Test
	void RegaloRicevutoiPreferitoPartita(){
		assertEquals("WOOOOOOF",this.doggo.riceviRegalo(this.CiboPreferito, this.Partita));
		assertTrue(this.Partita.getStanzaCorrente().hasAttrezzo("passepartout"));
	}
}
