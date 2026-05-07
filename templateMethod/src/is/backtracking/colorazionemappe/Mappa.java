package is.backtracking.colorazionemappe;

import is.backtracking.Problema;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Mappa extends Problema<Integer, Colore> {

	private int n;
	private Set<Integer>[] confinanti;

	private Map<Colore, Set<Integer>> stessoColore;

	// private Set<Integer>[] stessoColore;
	public Mappa(Set<Integer>[] confinanti) {
		this(confinanti, 1);
	}

	public Mappa(Set<Integer>[] confinanti, int max_sol) {
		super(max_sol); // ci si accontenta della prima soluzione
		this.n = confinanti.length;
		this.confinanti = confinanti;
		stessoColore = new EnumMap<>(Colore.class);
		for (Colore c : Colore.values())
			stessoColore.put(c, new HashSet<Integer>());

	}// costruttore

	protected void scriviSoluzione(int nr_sol) {
		System.out.println("Soluzione nr " + nr_sol);
		for (int nazione = 0; nazione < n; nazione++)
			for (Colore colore : Colore.values())
				if (stessoColore.get(colore).contains(nazione)) {
					System.out.println("Nazione =" + nazione + " colore=" + colore);

				}
		System.out.println();
	}// scriviSoluzione

	@Override
	protected Integer primoPuntoDiScelta() {
		return 0;
	}

	@Override
	protected Integer prossimoPuntoDiScelta(Integer nazione) {
		return nazione + 1;
	}

	@Override
	protected Integer ultimoPuntoDiScelta() {
		return n - 1;
	}

	@Override
	protected Colore primaScelta(Integer nazione) {

		return Colore.ROSSO;
	}

	@Override
	protected Colore prossimaScelta(Colore colore) {
		return Colore.values()[colore.ordinal() + 1];
	}

	@Override
	protected Colore ultimaScelta(Integer nazione) {
		return Colore.BLU;
	}

	@Override
	protected boolean assegnabile(Colore colore, Integer nazione) {
		// e' assegnabile colore a nazione se e' vuota l'intersezione tra
		// confinanti di nazione e stessocolore di colore
		Set<Integer> intersezione = new HashSet<Integer>();
		intersezione.addAll(confinanti[nazione]);
		// rimuovi tutti gli elementi da intersezione
		// tranne quelli di stessoColore[colore]
		intersezione.retainAll(stessoColore.get(colore));
		return intersezione.isEmpty();
	}// assegnabile

	@Override
	protected void assegna(Colore colore, Integer nazione) {
		stessoColore.get(colore).add(nazione);

	}// assegna

	@Override
	protected void deassegna(Colore colore, Integer nazione) {
		stessoColore.get(colore).remove(nazione);
	}// deassegna

	@Override
	protected Integer precedentePuntoDiScelta(Integer nazione) {
		return nazione - 1;
	}

	@Override
	protected Colore ultimaSceltaAssegnataA(Integer nazione) {
		for (Colore col : Colore.values()) {
			if (stessoColore.get(col).contains(nazione))
				return col;
		}

		throw new IllegalStateException(); // non dovrebbe accadere mai
	}// ultimaSceltaAssegnataA

}
