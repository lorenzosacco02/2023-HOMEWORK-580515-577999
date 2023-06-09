package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class PersonaggioTest {
	private Personaggio p;
	class diProva extends Personaggio{
		public diProva() {
			super("diProva","ciao");
		}

		@Override
		public String agisci(Partita partita) {
			return "aggito";
		}

		@Override
		public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
			return "ricevuto";
		}

		@Override
		public void setter(String nome, String presentazione, Attrezzo attrezzo, Attrezzo preferito) {
			this.setNome(nome);
			this.setPresentazione(presentazione);
		}
	}
	@BeforeEach
	void setUp() throws Exception {
		this.p = new diProva();
	}

	@Test
	void getNomeTest() {
		assertEquals("diProva", this.p.getNome());
	}
	
	@Test
	void getPresentazioneTest() {
		assertEquals("ciao", this.p.getPresentazione());
	}
	
	@Test
	void salutaMaiSalutatoTest() {
		assertEquals("Ciao, io sono diProva\nciao", this.p.saluta());
	}
	
	@Test
	void salutaGiaSalutatoTest() {
		p.saluta();
		assertEquals("Ciao, io sono diProva\nCi siamo gia' presentati!", this.p.saluta());
	}
	
	@Test
	void RegaloNullTest() {
		assertEquals("ricevuto", this.p.riceviRegalo(null, null));
	}
	
	@Test
	void RegaloTest() {
		assertEquals("ricevuto", this.p.riceviRegalo(new Attrezzo("sasso", 5), new Partita()));
	}
	
	@Test
	void setterTest() {
		p.setter("a", "b", null, null);
		assertEquals("a", this.p.toString());
		assertEquals("b", this.p.getPresentazione());
		}
}
