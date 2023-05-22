package it.uniroma3.diadia.comandi;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	@Override
	public Comando costruisciComando(String istruzione){
		String[] istruzioni;
		istruzione = istruzione.toLowerCase();
		istruzioni = istruzione.split("[\\W]+");
		StringBuilder base = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		String nomeComando="";
		String Parametro=null;
		Comando comando = null;
		if(istruzioni.length>0) {
			nomeComando=istruzioni[0];
		}
		if(istruzioni.length>1) {
			Parametro=istruzioni[1];
		}
		try{
			
			base.append(Character.toUpperCase(nomeComando.charAt(0)));
			base.append(nomeComando.substring(1));
			comando = (Comando)Class.forName(base.toString()).getDeclaredConstructor().newInstance();
			comando.setParametro(Parametro);
		}
		catch (InstantiationException|IllegalAccessException| NoSuchMethodException e) {
			System.out.println("CONTROLLA CHE ESISTA UN COSTRUTTORE NO-ARGS IN TUTTE LE CLASSI DI COMANDO E CHE SIA PUBLIC");
			}
		catch (InvocationTargetException e) {
			System.out.println("CONTROLLA LE ECCEZIONI SOLLEVATE DAI COSTRUTTORI");
			}
		catch(Exception e){
			comando= new ComandoNonValido();
		}
		return comando;
	}
}
