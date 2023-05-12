package it.uniroma3.diadia.attrezzi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttrezzoTest {
	
	private Attrezzo AttrezzoVuoto;
	private Attrezzo AttrezzoStringaNonVuota;
	private Attrezzo AttrezzoPesoNonNullo;
	private Attrezzo AttrezzoNonNullo;
	@BeforeEach
	void setup() {
		this.AttrezzoVuoto = new Attrezzo();
		this.AttrezzoStringaNonVuota = new Attrezzo("attrezzo",0);
		this.AttrezzoPesoNonNullo = new Attrezzo("",5);
		this.AttrezzoNonNullo = new Attrezzo("attrezzo",5);
	}
	
	@Test
	void testGetNomeAttrezzoVuoto() {
		assertEquals("",AttrezzoVuoto.getNome());
	}
	
	@Test
	void testGetPesoAttrezzoVuoto() {
		assertEquals(0,AttrezzoVuoto.getPeso());
	}
	
	@Test
	void testGetNomeStringaNonVuotaPesoNullo() {
		assertEquals("attrezzo",AttrezzoStringaNonVuota.getNome());
	}
	
	@Test
	void testGetPesoStringaNonVuotaPesoNullo() {
		assertEquals(0,AttrezzoStringaNonVuota.getPeso());
	}
	@Test
	void testGetNomeAttrezzoPesoNonNullo() {
		assertEquals("",AttrezzoPesoNonNullo.getNome());
	}
	@Test
	void testGetPesoAttrezzoPesoNonNullo() {
		assertEquals(5,AttrezzoPesoNonNullo.getPeso());
	}
	@Test
	void testGetNomeAttrezzoNonNullo() {
		assertEquals("attrezzo",AttrezzoNonNullo.getNome());
	}
	
	@Test
	void testGetPesoAttrezzoNonNullo() {
		assertEquals(5,AttrezzoNonNullo.getPeso());
	}
	
	@Test
	void testToStringAttrezzoVuoto() {
		assertEquals(" (0kg)",AttrezzoVuoto.toString());
	}
	
	@Test
	void testToStringAttrezzoStringaNonVuota(){
		assertEquals("attrezzo (0kg)",AttrezzoStringaNonVuota.toString());
	}
	
	@Test
	void testToStringAttrezzoPesoNonNullo() {
		assertEquals(" (5kg)",AttrezzoPesoNonNullo.toString());
	}
	
	@Test
	void testToStringAttrezzoNonNullo() {
		assertEquals("attrezzo (5kg)",AttrezzoNonNullo.toString());
	}
}
