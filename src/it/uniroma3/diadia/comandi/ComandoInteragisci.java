package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoInteragisci extends AbstractComando{

	@Override
	public String esegui(Partita partita) {
		return partita.getStanzaCorrente().getPersonaggio().agisci(partita);
	}

	@Override
	public String getNome() {
		return "ComandoInteragisci";
	}
}
