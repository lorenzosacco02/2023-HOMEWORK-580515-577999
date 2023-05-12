package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorsaTest {
	private SortedSet<Attrezzo> SetOrdinatoPerNome;
	private Set<Attrezzo> SetDaOrdinare;
	private Borsa BorsaNonVuota;
	private Borsa BorsaPiena;
	private Borsa BorsaVuota;
	private Attrezzo AttrezzoTest;
	private Attrezzo AttrezzoPesante;
	private Attrezzo[] attrezzi={new Attrezzo(),new Attrezzo("Cassa",5),new Attrezzo("Attrezzo",0),new Attrezzo("Attrezzo",5),new Attrezzo("Banana",200)};

	@BeforeEach
	void setup() {
		SetOrdinatoPerNome = new TreeSet<Attrezzo>();
		SetOrdinatoPerNome.add(attrezzi[0]);
		SetOrdinatoPerNome.add(attrezzi[1]);
		SetOrdinatoPerNome.add(attrezzi[2]);
		SetDaOrdinare = new HashSet<Attrezzo>();
		for(int i=0;i<10;i++) {
			if(i<5) {
				SetDaOrdinare.add(attrezzi[i]);
			}
			else {
				SetDaOrdinare.add(attrezzi[i-5]);
			}
		}
		BorsaNonVuota = new Borsa();
		BorsaVuota = new Borsa();
		AttrezzoTest= new Attrezzo("Attrezzone",4);
		BorsaNonVuota.addAttrezzo(AttrezzoTest);
		AttrezzoPesante = new Attrezzo("",11);
		BorsaPiena=new Borsa();
		for(int i=0;i<10;i++) {
			if(i<5){
				BorsaPiena.addAttrezzo(attrezzi[i]);
			}else{
				BorsaPiena.addAttrezzo(attrezzi[i-5]);
			}
		}
	}

	// Test del metodo AddAttrezzo
	@Test
	void testAddAttrezzo() {
		assertTrue(BorsaNonVuota.addAttrezzo(AttrezzoTest));
	}
	@Test
	void testAddAttrezzoVerifica() {
		assertEquals(AttrezzoTest,BorsaNonVuota.getAttrezzo("Attrezzone"));
	}
	@Test
	void testAddAttrezzoNull() {
		assertFalse(BorsaNonVuota.addAttrezzo(null));
	}
	@Test
	void testAddAttrezzoTroppoPesante() {
		assertFalse(BorsaNonVuota.addAttrezzo(AttrezzoPesante));
	}
	void testAddAttrezzoBorsaPiena() {
		assertFalse(BorsaPiena.addAttrezzo(AttrezzoTest));
	}

	//Test del metodo GetAttrezzo
	@Test
	void testGetAttrezzo() {
		assertEquals(AttrezzoTest,BorsaNonVuota.getAttrezzo("Attrezzone"));
	}
	@Test
	void testGetAttrezzoSenzaNome() {
		assertEquals(attrezzi[0],BorsaPiena.getAttrezzo(""));
	}
	@Test
	void testGetAttrezzoNonPresente() {
		assertNull(BorsaPiena.getAttrezzo("NonEsisto"));
	}

	//Test del metodo GetPeso
	@Test
	void testGetPesoBorsaNonVuota() {
		assertEquals(AttrezzoTest.getPeso(),BorsaNonVuota.getPeso());
	}

	@Test
	void testGetPesoBorsaPiena() {
		assertEquals(10,BorsaPiena.getPeso());
	}
	@Test
	void testGetPesoAttrezzoNonPresente() {
		assertEquals(0,BorsaVuota.getPeso());
	}

	//Test del metodo isEmpty
	@Test
	void testIsEmptyBorsaVuota() {
		assertTrue(BorsaVuota.isEmpty());
	}
	@Test
	void testIsEmptyBorsaNonVuota() {
		assertFalse(BorsaNonVuota.isEmpty());
	}
	@Test
	void testIsEmptyBorsaPiena() {
		assertFalse(BorsaPiena.isEmpty());
	}
	//Test del metodo hasAttrezzo
	@Test
	void testIshasAttrezzoAttrezzoNonTrovato() {
		assertFalse(BorsaNonVuota.hasAttrezzo("Banana"));
	}
	@Test
	void testIshasAttrezzoBorsaVuota() {
		assertFalse(BorsaVuota.hasAttrezzo("Banana"));
	}
	@Test
	void testIshasAttrezzoAttrezzoInesistente() {
		assertFalse(BorsaNonVuota.hasAttrezzo(null));
	}
	@Test
	void testIshasAttrezzoAttrezzoTrovato() {
		assertTrue(BorsaNonVuota.hasAttrezzo("Attrezzone"));
	}
	@Test
	void testIshasAttrezzoAttrezzoTrovatoConBorsaPiena() {
		assertTrue(BorsaPiena.hasAttrezzo("Attrezzo"));
	}
	@Test
	void testIshasAttrezzoAttrezzoTrovatoConNomeStingaVuota() {
		assertTrue(BorsaPiena.hasAttrezzo(""));
	}
	//Test del metodo RemoveAttrezzo
	@Test
	void testRemoveAttrezzo() {
		assertEquals(AttrezzoTest,BorsaNonVuota.removeAttrezzo("Attrezzone"));
	}
	@Test
	void testRemoveAttrezzoSenzaNome() {
		assertEquals(attrezzi[0],BorsaPiena.removeAttrezzo(""));
	}
	@Test
	void testRemoveAttrezzoNonPresente() {
		assertNull(BorsaPiena.removeAttrezzo("NonEsisto"));
	}
	@Test
	void testRemoveAttrezzoBorsaVuota() {
		assertNull(BorsaVuota.removeAttrezzo("Attrezzo"));
	}

	//Test del metodo toString
	@Test
	void testToStringBorsaNonVuota() {
		assertEquals("Contenuto borsa (4kg/10kg): Attrezzone (4kg) ",BorsaNonVuota.toString());
	}
	@Test
	void testToStringBorsaVuota() {
		assertEquals("Borsa vuota",BorsaVuota.toString());
	}
	@Test
	void testToStringBorsaPiena() {
		assertEquals("Contenuto borsa (10kg/10kg):  (0kg) Cassa (5kg) Attrezzo (0kg) Attrezzo (5kg)  (0kg) Attrezzo (0kg) ",BorsaPiena.toString());
	}

	//Test dei metodi di ordinamento
	@Test
	void testGetContenutoOrdinatoPerNome() {
		assertEquals(SetOrdinatoPerNome,BorsaPiena.getContenutoOrdinatoPerNome());
	}

	@Test
	void testgetSortedSetOrdinatoPerPeso() {
		assertEquals("[ (0kg), Attrezzo (0kg), Attrezzo (5kg), Cassa (5kg)]",BorsaPiena.getSortedSetOrdinatoPerPeso().toString());
	}

	@Test
	void testgetContenutoOrdinatoPerPeso() {
		assertEquals("[ (0kg),  (0kg), Attrezzo (0kg), Attrezzo (0kg), Attrezzo (5kg), Cassa (5kg)]",BorsaPiena.getContenutoOrdinatoPerPeso().toString());
	}

	@Test
	void testgetContenutoRaggruppatoPerPeso() {
		assertEquals("{0=[ (0kg), Attrezzo (0kg)], 5=[Attrezzo (5kg), Cassa (5kg)]}",BorsaPiena.getContenutoRaggruppatoPerPeso().toString());
	}
}
