package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoPrendi implements Comando{
	private String Oggetto;
	public ComandoPrendi(){
		Oggetto = null;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.Oggetto=parametro;
	}
	
	@Override
	public StringBuilder esegui(Partita partita){
		StringBuilder output= new StringBuilder("");
		output.append(partita.getPlayer().PrendiAttrezzo(this.Oggetto, partita.getStanzaCorrente()));
		Boolean Bool = output.toString().equals("Il tuo attrezzo non esiste");
		Boolean Bool2 =output.toString().equals("La tua borsa è troppo pesante prova a lasciare qualche attrezzo a terra");
		Boolean Bool3 = output.toString().equals("Che attrezzo vuoi prendere?");
		if((Bool||Bool2||Bool3)==true) {
			return output;
		}
		else {
			partita.getPlayer().setCfu(partita.getPlayer().getCfu()-1);
			return output.append("\nCFU rimasti: "+ partita.getPlayer().getCfu());
		}
	}
	
	@Override
	public String getNome() {
		return "ComandoPrendi";
	}
	
	@Override
	public String getParametro() {
		return this.Oggetto;
	}
}
