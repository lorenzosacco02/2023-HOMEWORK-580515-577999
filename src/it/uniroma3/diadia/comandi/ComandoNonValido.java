package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{
	
	public ComandoNonValido(){
	}
	
	@Override
	public String esegui(Partita partita){
		StringBuilder string = new StringBuilder("Comando sconosciuto");
		return string.toString();
	}
	
	@Override
	public String getNome() {
		return "ComandoNonValido";
	}
}
