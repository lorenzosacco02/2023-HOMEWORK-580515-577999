package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoBuilderTest {
	Labirinto monolocale;
	Labirinto bilocale;
	Labirinto trilocale;
	@BeforeEach
	void setUp() {
		monolocale= new LabirintoBuilder().addStanzaIniziale("salotto").addAttrezzo("Banana",50).addStanzaVincente("salotto").getLabirinto();
		bilocale= new LabirintoBuilder().addStanzaIniziale("salotto").addStanzaVincente("camera").addAttrezzo("letto",10).addAdiacenza("salotto", "camera", "nord").getLabirinto();
		trilocale= new LabirintoBuilder().addStanza("salotto").addStanza("cucina").addAttrezzo("pentola",1).addStanzaVincente("camera").addAdiacenza("salotto", "cucina", "nord").addAdiacenza("cucina", "camera", "est").getLabirinto();
	}

	@Test
	void testMonolocale() {
		assertEquals(monolocale.getEntrata().getNome(),"salotto");
		assertEquals(monolocale.getUscita().getNome(),"salotto");
		assertEquals(monolocale.getUscita().getListaDiAttrezzi().get(0).toString(),"Banana (50kg)");
	}
	
	@Test
	void testBilocale() {
		assertEquals(bilocale.getEntrata().getNome(),"salotto");
		assertEquals(bilocale.getUscita().getNome(),"camera");
		assertEquals(bilocale.getEntrata().getStanzaAdiacente("nord").getNome(),"camera");
		assertEquals(bilocale.getUscita().getListaDiAttrezzi().get(0).toString(),"letto (10kg)");
	}
	@Test
	void testTrilocale() {
		assertEquals(trilocale.getEntrata().getNome(),"salotto");
		assertEquals(trilocale.getUscita().getNome(),"camera");
		assertEquals(trilocale.getEntrata().getStanzaAdiacente("nord").getListaDiAttrezzi().get(0).toString(),"pentola (1kg)");
		assertEquals(trilocale.getEntrata().getStanzaAdiacente("nord").getNome(),"cucina");
		assertEquals(trilocale.getUscita().getStanzaAdiacente("ovest").getNome(),"cucina");
	}
}