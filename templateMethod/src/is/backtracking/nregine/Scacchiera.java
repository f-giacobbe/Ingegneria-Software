package is.backtracking.nregine;

import is.backtracking.ProblemaJ8;

public class Scacchiera implements ProblemaJ8<Integer, Integer> {
	private final int N;
	private int[] board;
	private int max_sol = 0;

	public Scacchiera(int NRegine, int max_sol) {
		this.max_sol = max_sol;
		N = NRegine;
		board = new int[N];
		for (int i = 0; i < N; ++i)
			board[i] = -1;
	}

	@Override
	public void assegna(Integer col, Integer rig) {
		board[rig] = col;

	}

	@Override
	public boolean assegnabile(Integer col, Integer rig) {
		// verifica a nord
		for (int i = rig - 1; i >= 0; i--)
			if (board[i] == col)
				return false;
		// verifica a nord-ovest
		int d = rig - col;
		for (int i = rig - 1; i >= 0; i--)
			if (i - board[i] == d)
				return false;

		d = rig + col;
		// verifica a nord-est
		for (int i = rig - 1; i >= 0; i--)
			if (i + board[i] == d)
				return false;

		return true;
	}// assegnabile

	@Override
	public void deassegna(Integer col, Integer rig) {
		board[rig] = -1;

	}

	@Override
	public Integer primoPuntoDiScelta() {
		return Integer.valueOf(0);
	}

	@Override
	public Integer prossimoPuntoDiScelta(Integer ps) {

		return Integer.valueOf(ps + 1);
	}

	@Override
	public Integer ultimoPuntoDiScelta() {

		return Integer.valueOf(N - 1);
	}

	@Override
	public Integer primaScelta(Integer ps) {

		return Integer.valueOf(0);
	}

	@Override
	public Integer prossimaScelta(Integer s) {
		return Integer.valueOf(s + 1);
	}

	@Override
	public Integer ultimaScelta(Integer ps) {

		return Integer.valueOf(N - 1);
	}

	@Override
	public Integer precedentePuntoDiScelta(Integer rig) {

		return Integer.valueOf(rig - 1);
	}

	@Override
	public Integer ultimaSceltaAssegnataA(Integer rig) {

		return board[rig];
	}

	@Override
	public void scriviSoluzione(int nr_sol) {
		System.out.println("Soluzione nr " + nr_sol);
		for (int r = 0; r < N; r++) {
			int rCol = board[r];
			for (int col = 0; col < rCol; col++) {
				System.out.print('.');
			}
			System.out.print('*');
			for (int col = rCol + 1; col < N; col++) {
				System.out.print('.');
			}
			System.out.println();
		}
	}

}
