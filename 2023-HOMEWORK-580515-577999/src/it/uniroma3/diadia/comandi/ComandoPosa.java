package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;

public class ComandoPosa implements Comando{
	private String Oggetto;

	public ComandoPosa(){
		this.Oggetto = null;
	}

	@Override
	public void setParametro(String parametro) {
		this.Oggetto=parametro;
	}

	@Override
	public StringBuilder esegui(Partita partita){
		StringBuilder output=new StringBuilder("");
		output.append(partita.getPlayer().RemoveAttrezzo( this.Oggetto , partita.getStanzaCorrente()));
		Boolean Bool = output.toString().equals("Ho toccato il fondo, ma "+Oggetto+" non l'ho trovato");
		Boolean Bool2 =output.toString().equals("C'è già troppo disordine in questa stanza!\nNon vorrei crearne altro");
		Boolean Bool3 = output.toString().equals("Che attrezzo vuoi posare?");
		Boolean Bool4 = output.toString().equals("Stanza innesistente");
		if((Bool||Bool2||Bool3||Bool4)==false)  {
			partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
			return output.append("\nCFU rimasti: "+ partita.getPlayer().getCfu());
		}
		else 
			return output;
	}
	
	@Override
	public String getNome() {
		return "ComandoPosa";
	}
	
	@Override
	public String getParametro() {
		return this.Oggetto;
	}
}
