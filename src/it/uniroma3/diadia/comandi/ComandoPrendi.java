package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoPrendi extends AbstractComando{
	public ComandoPrendi(){
		this.parametro = null;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	@Override
	public String esegui(Partita partita){
		StringBuilder output= new StringBuilder("");
		output.append(partita.getPlayer().PrendiAttrezzo(this.getParametro(), partita.getStanzaCorrente()));
		Boolean Bool = output.toString().equals("Il tuo attrezzo non esiste");
		Boolean Bool2 =output.toString().equals("La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra");
		Boolean Bool3 = output.toString().equals("Che attrezzo vuoi prendere?");
		if((Bool||Bool2||Bool3)==true) {
			return output.toString();
		}
		else {
			partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
			return output.append("\n"+partita.getPlayer().getDescrizione()).toString();
		}
	}
	
	@Override
	public String getNome() {
		return "ComandoPrendi";
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
}
