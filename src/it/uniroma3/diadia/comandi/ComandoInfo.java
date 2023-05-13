package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoInfo implements Comando {
	String Parametro;
	public ComandoInfo() {
		this.Parametro=null;
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
			string.append(partita.getStanzaCorrente().toString());
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
		this.Parametro=parametro;

	}

	@Override
	public String getNome() {
		return "ComandoInfo";
	}

	@Override
	public String getParametro() {
		return this.Parametro;
	}

}
