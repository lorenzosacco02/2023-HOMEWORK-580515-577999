package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;

public class ComandoPosa extends AbstractComando{

	public ComandoPosa(){
		this.parametro = null;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}

	@Override
	public String esegui(Partita partita){
		StringBuilder output=new StringBuilder("");
		output.append(partita.getPlayer().RemoveAttrezzo( this.getParametro() , partita.getStanzaCorrente()));
		Boolean Bool = output.toString().equals("Ho toccato il fondo, ma "+this.getParametro()+" non l'ho trovato");
		Boolean Bool2 =output.toString().equals("C'è già troppo disordine in questa stanza!\nNon vorrei crearne altro");
		Boolean Bool3 = output.toString().equals("Che attrezzo vuoi posare?");
		Boolean Bool4 = output.toString().equals("Stanza innesistente");
		if((Bool||Bool2||Bool3||Bool4)==false)  {
			partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
			return output.append("\n"+ partita.getPlayer().getDescrizione()).toString();
		}
		else 
			return output.toString();
	}
	
	@Override
	public String getNome() {
		return "ComandoPosa";
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
}
