package is.polinomi;

/**
 * Questa intefaccia definisce il tipo di dato astratto Polinomio.
 * 
 * <p>
 * Introduce i metodi corrispondenti alle operazioni algebriche tra polinomi.
 * </p>
 * 
 * @author Angelo Furfaro
 * 
 */
public interface Polinomio extends Iterable<Monomio> {

	double EPS = 1e-30;

	/**
	 * Resituisce il numero dei monomi che formano il polinomio
	 * 
	 * @return il numero dei monomi da cui &egrave; formato il polinomio
	 */
	default int size() {
		int k = 0;
		for (@SuppressWarnings("unused") Monomio m : this) {
			++k;
		}
		return k;
	}

	/**
	 * Aggiunge il monomio m al Polinomio this.
	 * <p>
	 * Questo &egrave; un metododo mutarore: l'aggiunta di un monomio modifica
	 * il Polinomio this
	 * <p>
	 * 
	 * @param m
	 *            il monomio da aggiungere
	 */
	void add(Monomio m);

	/**
	 * Somma al Polinomio this il Polinomio ricevuto come argomento e
	 * restituisce il polinomio risultate dall'operazione
	 * 
	 * @param p
	 *            il Polinomio da aggiungere a this
	 * @return il polinomio risultante
	 */
	Polinomio add(Polinomio p);

	/**
	 * Moltiplica il Polinomio this con il Monomio ricevuto come argomento e
	 * restituisce il polinomio risultate dall'operazione
	 * 
	 * @param m
	 *            il monomio per cui moltiplicare il Polinomio this
	 * @return il polinomio risultante
	 */
	Polinomio mul(Monomio m);

	/**
	 * Moltiplica il Polinomio this con lo scalare ricevuto come argomento e
	 * restituisce il polinomio risultate dall'operazione
	 * 
	 * @param s
	 *            lo scalare per cui moltiplicare il Polinomio this
	 * @return il polinomio risultante
	 */
	Polinomio mul(double s);

	/**
	 * Moltiplica il Polinomio this con il Polinomio ricevuto come argomento e
	 * restituisce il polinomio risultate dall'operazione
	 * 
	 * @param p
	 *            il polinomio per cui moltiplicare il Polinomio this
	 * @return il polinomio risultante
	 */
	Polinomio mul(Polinomio p);

	/**
	 * Restituisce il Polinomio ottenuto come derivata del Polinomio this
	 * 
	 * @return il polinomio risultante dalla derivazione del Polinomio this
	 */
	Polinomio derive();
}
