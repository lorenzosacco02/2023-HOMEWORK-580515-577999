package it.uniroma3.diadia.comandi;
import java.util.HashSet;
import java.util.Set;

import it.uniroma3.diadia.*;

public class ComandoAiuto extends AbstractComando{

	private Set<String> elencoComandi;


	public ComandoAiuto(){
		this.elencoComandi=new HashSet<String>();
		this.parametro = null;
	}
	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}

	@Override
	public String esegui(Partita partita){
		StringBuilder string = new StringBuilder("");
		if(this.parametro==null)
			this.setParametro("");
		switch (this.getParametro()) {
		case "aiuto":
			string.append("Il comando aiuto ti da informazioni su tutti i comandi del gioco\nsi scriva aiuto e una di queste opzioni ");
		case "":
			string.append(this.getElencoComandi().toString());
			break;
		case "vai":
			string.append("Il comando vai ti fa andare nella stanza adiacente a quella in cui ti trovi nella direzione da te indicata\nsi scriva vai e una di queste opzioni [nord sud est ovest]");
			break;
		case "fine":
			string.append("Il comando fine ti permette di finire il gioco in qualsiasi momento\nsi scriva fine");
			break;
		case "prendi":
			string.append("Il comando prendi ti fa prendere un attrezzo che si trova nella stanza in cui ti trovi\nsi scriva prendi e il nome del attrezzo che vuoi prendere");
			break;
		case "posa":
			string.append("Il comando posa ti fa lasciare un attrezzo nella stanza in cui ti trovi\nsi scriva posa e il nome del attrezzo che vuoi prendere");
			break;
		case "info":
			string.append("Il comando info ti permette di ricevere informazioni sulla stanza in qui ti trovi o sulla tua borsa con tutti gli oggetti con i relativi pesi\nsi scriva info e una di queste opzioni [borsa stanza]");
			break;
		default:
			string.append("non esiste il comando:\n");
			string.append(parametro);
			break;
		}
		return string.toString();
	}

	@Override
	public String getNome() {
		return "ComandoAiuto";
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	public void setElencoComandi(Set<String> elencoComandi) {
		Set<String> Temp=new HashSet<String>(elencoComandi);
		Temp.remove("comandononvalido");
		this.elencoComandi=Temp;
	}
	
	public Set<String> getElencoComandi() {
		return elencoComandi;
	}

}
