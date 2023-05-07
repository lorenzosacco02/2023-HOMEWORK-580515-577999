package it.uniroma3.diadia.ambienti.protetti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaProtectedTest {

	private StanzaMagicaProtected StanzaSenzaNomeVuota;
	private StanzaMagicaProtected StanzaConNome;
	private StanzaMagicaProtected StanzaPiena;
	private Attrezzo[] arrayVuoto;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("",5),new Attrezzo("Attrezzo",0),new Attrezzo("Attrezzo",5),new Attrezzo("Banana",200)};
	@BeforeEach

	void setup() {
		StanzaSenzaNomeVuota= new StanzaMagicaProtected("");
		StanzaPiena=new StanzaMagicaProtected("");
		for(int i=0;i<10;i++){
			if(i<5){
				StanzaPiena.addAttrezzo(attrezzi[i]);
			}else{
				StanzaPiena.addAttrezzo(attrezzi[i-5]);
			}
		}
		StanzaConNome = new StanzaMagicaProtected("Bagno");
		this.arrayVuoto= new Attrezzo[10];
		for(int i=0;i<10;i++){
			this.arrayVuoto[i]= new Attrezzo();
		}
		StanzaConNome.impostaStanzaAdiacente("sud",StanzaPiena);
	}

	//Test del mettodo Add Attrezzo prima di diventare magica

	@Test
	void testAddAttrezzoStanzaVuota(){
		assertTrue(StanzaConNome.addAttrezzo(attrezzi[0]));
	}

	@Test
	void testAddAttrezzoStanzaPiena(){
		assertFalse(StanzaPiena.addAttrezzo(attrezzi[4]));
	}

	//Test del mettodo GetArrayDiAttrezzi prima di diventare magica

	@Test
	void testGetArrayDiAttrezziNomeStanzaSenzaNomeVuota() {
		for(int i=0;i<10;i++)
			assertEquals(arrayVuoto[i].getNome(),StanzaSenzaNomeVuota.getArrayDiAttrezzi()[i].getNome());
	}

	@Test
	void testGetArrayDiAttrezziPesoStanzaSenzaNomeVuota() {
		for(int i=0;i<10;i++)
			assertEquals(arrayVuoto[i].getPeso(),StanzaSenzaNomeVuota.getArrayDiAttrezzi()[i].getPeso());
	}

	//Test del mettodo Add Attrezzo dopo essere diventata magica

	@Test
	void testAddAttrezzoStanzaVuotaMagica() {
		this.StanzaSenzaNomeVuota.addAttrezzo(attrezzi[1]);
		this.StanzaSenzaNomeVuota.addAttrezzo(attrezzi[2]);
		this.StanzaSenzaNomeVuota.addAttrezzo(attrezzi[3]);
		assertTrue(StanzaConNome.addAttrezzo(attrezzi[0]));
	}

	@Test
	void testAddAttrezzoStanzaPienaMagica(){
		assertFalse(StanzaPiena.addAttrezzo(attrezzi[4]));
	}

	//Test del mettodo GetArrayDiAttrezzi dopo esser diventata magica

	@Test
	void testGetArrayDiAttrezziStanzaMagica() {
		this.StanzaSenzaNomeVuota.addAttrezzo(attrezzi[1]);
		this.StanzaSenzaNomeVuota.addAttrezzo(attrezzi[2]);
		this.StanzaSenzaNomeVuota.addAttrezzo(attrezzi[3]);
		this.StanzaSenzaNomeVuota.addAttrezzo(attrezzi[4]);
		for(int i=1;i<5;i++) {
			assertEquals(attrezzi[i].getNome(),StanzaSenzaNomeVuota.getArrayDiAttrezzi()[i-1].getNome());
		}
		assertEquals("ananaB",StanzaSenzaNomeVuota.getArrayDiAttrezzi()[3].getNome());
	}
}