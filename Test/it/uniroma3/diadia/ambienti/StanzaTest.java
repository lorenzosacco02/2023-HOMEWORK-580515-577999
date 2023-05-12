package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	private Stanza StanzaSenzaNomeVuota;
	private Stanza StanzaConNome;
	private Stanza StanzaPiena;
	private Attrezzo[] arrayVuoto;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("Attrezzo",5),new Attrezzo("Banana",200)};
	@BeforeEach
	void setup() {
		StanzaSenzaNomeVuota= new Stanza("");
		StanzaPiena=new Stanza("");
		for(int i=0;i<10;i++){
			if(i<5){
				StanzaPiena.addAttrezzo(attrezzi[i]);
			}else{
				StanzaPiena.addAttrezzo(attrezzi[i-5]);
			}
		}
		StanzaConNome = new Stanza("Bagno");
		this.arrayVuoto= new Attrezzo[10];
		for(int i=0;i<10;i++){
			this.arrayVuoto[i]= new Attrezzo();
		}
		StanzaConNome.impostaStanzaAdiacente("sud",StanzaPiena);
	}

	//Test del mettodo Get Nome

	@Test
	void testGetNomeStanzaSenzaNomeVuota() {
		assertEquals("",StanzaSenzaNomeVuota.getNome());
	}
	@Test
	void testGetNomeStanzaConNome(){
		assertEquals("Bagno",StanzaConNome.getNome());
	}

	//Test del mettodo Get Array Di Attrezzi

	@Test
	void testGetArrayDiAttrezziNomeStanzaSenzaNomeVuota() {
		for(int i=0;i<StanzaSenzaNomeVuota.getListaDiAttrezzi().size();i++)
			assertEquals(arrayVuoto[i].getNome(),StanzaSenzaNomeVuota.getListaDiAttrezzi().get(i).getNome());
	}
	@Test
	void testGetArrayDiAttrezziPesoStanzaSenzaNomeVuota() {
		for(int i=0;i<StanzaSenzaNomeVuota.getListaDiAttrezzi().size();i++)
			assertEquals(arrayVuoto[i].getPeso(),StanzaSenzaNomeVuota.getListaDiAttrezzi().get(i).getPeso());
	}

	//Test del mettodo Get Stanza Adiacente

	@Test
	void testGetStanzaNordStanzaSenzaNomeVuota() {
		assertNull(StanzaSenzaNomeVuota.getStanzaAdiacente("nord"));
	}
	@Test
	void testGetStanzaSudStanzaSenzaNomeVuota() {
		assertNull(StanzaSenzaNomeVuota.getStanzaAdiacente("sud"));
	}
	@Test
	void testGetStanzaEstStanzaSenzaNomeVuota() {
		assertNull(StanzaSenzaNomeVuota.getStanzaAdiacente("est"));
	}
	@Test
	void testGetStanzaOvestStanzaSenzaNomeVuota() {
		assertNull(StanzaSenzaNomeVuota.getStanzaAdiacente("ovest"));
	}
	@Test 
	void testImpostaStanzaAdiacente() {
		assertEquals(StanzaPiena,StanzaConNome.getStanzaAdiacente("sud"));
	}

	//Test del mettodo Add Attrezzo

	@Test
	void testAddAttrezzoStanzaVuota(){
		assertTrue(StanzaConNome.addAttrezzo(attrezzi[0]));
	}
	@Test
	void testAddAttrezzoStanzaPienVuotaConAttrezzoNull(){
		assertFalse(StanzaConNome.addAttrezzo(null));
	}
	@Test
	void testAddAttrezzoStanzaPienPienaConAttrezzoNull(){
		assertFalse(StanzaPiena.addAttrezzo(null));
	}

	//Test del mettodo Remove Attrezzo

	@Test
	void testRemoveAttrezzoStanzaPiena(){
		assertEquals(attrezzi[3],StanzaPiena.removeAttrezzo(attrezzi[3]));
	}
	@Test
	void testRemoveAttrezzoStanzaVuota(){
		assertNull(StanzaSenzaNomeVuota.removeAttrezzo(attrezzi[1]));
	}
	@Test
	void testRemoveAttrezzoStanzaPienaConAttrezzoNULL(){
		assertNull(StanzaPiena.removeAttrezzo(null));
	}
	@Test
	void testRemoveAttrezzoStanzaVuotaConAttrezzoNull(){
		assertNull(StanzaSenzaNomeVuota.removeAttrezzo(null));
	}
	@Test
	void testRemoveAttrezzoStanzaPienaConMultiAttrezzo(){
		for(int i=0;i<5;i++)
			assertEquals(attrezzi[i],StanzaPiena.removeAttrezzo(attrezzi[i]));
	}

	//Test del mettodo Get Numero di Attrezzo

	@Test 
	void testGetNumeroAttrezzi() {
		assertEquals(10,StanzaPiena.getNumeroAttrezzi());
	}
	@Test 
	void testGetNumeroAttrezziStanzaVuota() {
		assertEquals(0,StanzaSenzaNomeVuota.getNumeroAttrezzi());
	}
	@Test 
	void testHasAttrezzo() {
		assertTrue(StanzaPiena.hasAttrezzo("Attrezzo"));
	}
}
