package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;

public class ComandoAiuto implements Comando{

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "borsa"};
	private String parametro;
	
	
	public ComandoAiuto(){
		parametro = null;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}

	@Override
	public StringBuilder esegui(Partita partita){
		StringBuilder string = new StringBuilder("");
		if(this.parametro==null)
			this.setParametro("");
		switch (this.parametro) {
		case "aiuto":
			string.append("Il comando aiuto ti da informazioni su tutti i comandi del gioco\nsi scriva aiuto e una di queste opzioni ");
		case "":
			string.append("[");
			for (int i = 0; i < elencoComandi.length; i++) {
				if(i==0)
					string.append(elencoComandi[i]);
				else{
					string.append(" ");
					string.append(elencoComandi[i]);
				}
			}
			string.append("]");
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
		case "borsa":
			string.append("Il comando borsa ti permette di vedere quali oggetti hai nella tua borsa con i relativi pesi");
			break;
		default:
			string.append("non esiste il comando:\n");
			string.append(parametro);
			break;
		}
		return string;
	}
	
	@Override
	public String getNome() {
		return "ComandoAiuto";
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
}
