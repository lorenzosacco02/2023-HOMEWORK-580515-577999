package it.uniroma3.diadia.attrezzi;

/**
 * Una semplice classe che modella un'Attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  580515, 577999
 * @see Stanza
 * @version 1.0
 */

public class Attrezzo implements Comparable<Attrezzo>{

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 */
	public Attrezzo() {
	}

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		if(this.nome==null) {
			this.nome="";
		}
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Permette di impostare il peso dell'attrezzo
	 * @param intero con il peso desiderato
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * Permette di impostare il nome dell'attrezzo
	 * @param Stringa con il nome desiderato
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	@Override 
	public boolean equals(Object o){
		Attrezzo that=(Attrezzo)o;
		return this.getNome().equals(that.getNome());
	}
	@Override
	public int hashCode() {
		return this.peso+this.getNome().hashCode();
	}
	
	@Override
	public int compareTo(Attrezzo o) {
		return this.getNome().compareTo(o.getNome());
	}
}