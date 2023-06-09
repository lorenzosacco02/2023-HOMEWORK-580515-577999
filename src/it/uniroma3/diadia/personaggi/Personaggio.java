package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class Personaggio {
	private String nome;
	private String presentazione;
	private Boolean haSalutato;

	public Personaggio(String nome, String presentaz) {
		this.nome = nome;
		this.presentazione = presentaz;
		this.haSalutato = false;
	}

	public String getNome() {
		return this.nome;
	}
	public String getPresentazione() {
		return this.presentazione;
	}
	
	public void setNome(String nome) {
		if(nome!=null)
		this.nome=nome;
	}
	
	public void setPresentazione(String pres) {
		if(pres!=null)
		this.presentazione=pres;
	}
	
	public boolean haSalutato() {
		return this.haSalutato;
	}
	public String saluta() {
		StringBuilder risposta =

				new StringBuilder("Ciao, io sono ");

		risposta.append(this.getNome()+"\n");
		if (!haSalutato)

			risposta.append(this.getPresentazione());

		else

			risposta.append("Ci siamo gia' presentati!");

		this.haSalutato = true;
		return risposta.toString();
	}

	abstract public String agisci(Partita partita);
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
	abstract public void setter(String nome, String presentazione, Attrezzo attrezzo, Attrezzo preferito);
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
