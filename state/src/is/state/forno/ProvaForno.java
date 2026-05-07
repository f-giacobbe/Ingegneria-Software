package is.state.forno;

import java.util.Scanner;

class Lettore implements Runnable {
	private final FornoIF f;
	private final Scanner sc;

	public Lettore(FornoIF f) {
		this.f = f;
		sc = new Scanner(System.in);

	}

	@Override
	public void run() {
		char c = 0; // iniz fittizia
		while (true) {
			if (!sc.hasNext())
				System.exit(-1);

			c = sc.next().charAt(0);

			if (c == 'a' || c == 'A')
				f.apri();
			else if (c == 'c' || c == 'C')
				f.chiudi();
			else if (c == 's' || c == 'S')
				f.start();
			else
				System.out.println("?");
		}
	}// run

}// Lettore

public class ProvaForno {
	public static void main(String[] args) {
		LuceIF l = new Luce();
		Tubo t = new Tubo();
		FornoIF f = new Forno(l, t);

		Thread lettore = new Thread(new Lettore(f));
		lettore.start();
	}
}
