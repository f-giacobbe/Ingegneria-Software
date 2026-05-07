package is.backtracking.colorazionemappe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class QuattroColori {
	public static void main(String[] args) throws IOException {
		Mappa m;
		System.out.println("Problema dei 4 colori");
		System.out.print("Quante nazioni ? ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Set<Integer>[] confinanti = new Set[n];
		for (int i = 0; i < n; i++)
			confinanti[i] = new HashSet<Integer>();
		System.out.println("Introduci ora " + n + " righe/nazioni");
		System.out.println("specificando sulla riga h-esima i numeri");
		System.out.println("delle nazioni confinanti con h.");
		System.out.println("I numeri delle nazioni vanno da 0 a " + (n - 1));
		String linea;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int nazione1 = 0; nazione1 < n; nazione1++) {
			linea = br.readLine();
			st = new StringTokenizer(linea, " ");
			while (st.hasMoreTokens()) {
				int nazione2 = Integer.parseInt(st.nextToken());
				confinanti[nazione1].add(nazione2);
			}
		}
		m = new Mappa(confinanti);
		m.risolvi();
	}
}// QuattroColori
