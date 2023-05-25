package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoRegala extends AbstractComando{
	@Override
	public String esegui(Partita partita) {
		return partita.getStanzaCorrente().getPersonaggio().riceviRegalo(partita.getPlayer().getBorsa().getAttrezzo(this.getParametro()), partita);
	}

	@Override
	public String getNome() {
		return "ComandoRegala";
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
}
