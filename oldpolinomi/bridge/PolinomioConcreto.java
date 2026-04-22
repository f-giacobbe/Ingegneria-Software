package is.polinomi.bridge;

import is.polinomi.Monomio;
import is.polinomi.Polinomio;

import java.util.Iterator;


/**
 * 
 * Questa classe astratta fornisce un'implementazione parziale dell'interfaccia
 * {@link Polinomio}. I metodi concreti codificano gli algoritmi per le
 * corrispondenti operazioni algebriche tra polinomi e si basano sull'uso degli
 * iteratori.
 * 
 * @author Angelo Furfaro
 * 
 */
public class PolinomioConcreto implements Polinomio {

	// setta il factory predefinito
	private static PolinomioImplFactory factory = new PolinomioLLFactory();

	// consente di modificare il factory
	public static void setPolinomioImplFactory(PolinomioImplFactory factory) {
		PolinomioConcreto.factory = factory;
	}
	
    //bridge
	private final PolinomioImpl polinomioImpl;

	public PolinomioConcreto() {
		polinomioImpl = factory.createPolinomioImpl();
	}

	@Override public int size(){
		return polinomioImpl.size();
  	}

	@Override
	public void add(Monomio m) {
		polinomioImpl.addMonomio(m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poo.polinomi.Polinomio#add(poo.polinomi.Polinomio)
	 */
	@Override
	public Polinomio add(Polinomio p) {

		Polinomio somma = new PolinomioConcreto();// crea un nuovo
													// polinomio
		for (Monomio m : this)
			somma.add(m);// aggiunge ciascun monomio di this al polinomio somma

		for (Monomio m : p)
			somma.add(m);// aggiunge ciascun monomio di p al polinomio somma

		return somma;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poo.polinomi.Polinomio#mul(poo.polinomi.Monomio)
	 */
	@Override
	public Polinomio mul(Monomio m) {
		Polinomio prodotto = new PolinomioConcreto();// crea un nuovo
														// polinomio
		for (Monomio m1 : this)
			// moltiplica ciascun monomio m1 di this per m ed aggiunge il
			// risultato al polinomio prodotto
			prodotto.add(m1.mul(m));
		return prodotto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poo.polinomi.Polinomio#mul(double)
	 */
	@Override
	public Polinomio mul(double s) {
		// moltiplica this per il monomio di grado zero avente coefficiente s
		return mul(new Monomio(s, 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poo.polinomi.Polinomio#mul(poo.polinomi.Polinomio)
	 */
	@Override
	public Polinomio mul(Polinomio p) {
		Polinomio prodotto = new PolinomioConcreto();// crea un nuovo
														// PolinomioF

		for (Monomio m : polinomioImpl) {
			Polinomio tmp = p.mul(m);
			for (Monomio mr : tmp)
				prodotto.add(mr);
		}
		return prodotto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poo.polinomi.Polinomio#derive()
	 */
	@Override
	public Polinomio derive() {
		Polinomio p = new PolinomioConcreto();// crea un nuovo
												// polinomio
		for (Monomio m : polinomioImpl) {
			int grado = m.grado();
			if (grado > 0) {// se il grado e' positivo calcola la derivata del
				// monomioF
				p.add(new Monomio(m.coeff() * grado, grado - 1));
			}
		}
		return p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder(10 * size());
		Iterator<Monomio> i = this.iterator();
		boolean first = true;
		while (i.hasNext()) {
			Monomio m = i.next();
			if (!first && m.coeff() > 0)
				sb.append('+');
			sb.append(m);
			first = false;
		}
		return sb.toString();
	}

	@Override
	public Iterator<Monomio> iterator() {

		return polinomioImpl.iterator();
	}
}
