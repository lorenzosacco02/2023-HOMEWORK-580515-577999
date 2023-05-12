package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{
	
	public ComandoNonValido(){
		
	}
	
	@Override
	public void setParametro(String Parametro) {
	}
	
	@Override
	public StringBuilder esegui(Partita partita){
		StringBuilder string = new StringBuilder("Comando sconosciuto");
		return string;
	}
	
	@Override
	public String getNome() {
		return "ComandoNonValido";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
