package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoInfo extends AbstractComando {
	
	public ComandoInfo() {
		this.parametro=null;
	}
	
	@Override
	public String esegui(Partita partita) {
		StringBuilder string = new StringBuilder("");
		if(this.getParametro()==null)
			this.setParametro("");
		switch(this.getParametro()) {
		case "":
			string.append("Su cosa vuoi avere le informazioni?");
			break;
		case "borsa":
			string.append(partita.getPlayer().getBorsa().toString());
			break;
		case "stanza":
			string.append(partita.getStanzaCorrente().getDescrizione());
			break;
		default:
			string.append("Non esiste alcuna info su: ");
			string.append(this.getParametro());
			break;
		}
		
		return string.toString();
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;

	}

	@Override
	public String getNome() {
		return "ComandoInfo";
	}
}