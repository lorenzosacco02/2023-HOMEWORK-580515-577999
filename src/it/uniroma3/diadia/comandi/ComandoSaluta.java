package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{

	@Override
	public String esegui(Partita partita) {
		return partita.getStanzaCorrente().getPersonaggio().saluta();
	}

	@Override
	public String getNome() {
		return "ComandoSaluta";
	}

}
