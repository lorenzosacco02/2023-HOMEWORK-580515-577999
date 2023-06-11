package it.uniroma3.diadia.ambienti;

public enum Direzioni {
	nord,est,sud,ovest;

	public static Direzioni getOpposta(Direzioni a) {
		return Direzioni.values()[(a.ordinal()+2)%4];
	}
}
