package is.state.forno;

public class Tubo {

	private interface StatoTuboIF {
		default void entryAction() {
		}

		default void exitAction() {

		}

		default void on() {
		}

		default void off() {
		}

	}

	private final class TuboOn implements StatoTuboIF {

		@Override
		public void entryAction() {
			System.out.println("Tubo on");
		}

		@Override
		public void off() {
			transition(OFF);
		}
	}

	private final class TuboOff implements StatoTuboIF {

		@Override
		public void entryAction() {
			System.out.println("Tubo off");
		}

		@Override
		public void on() {
			transition(ON);
		}
	}

	private final StatoTuboIF ON = new TuboOn();
	private final StatoTuboIF OFF = new TuboOff();
	private StatoTuboIF current = OFF;

	private void transition(StatoTuboIF next) {
		current.exitAction();
		current = next;
		current.entryAction();
	}

	public void switchOn() {
		current.on();
	}

	public void switchOff() {
		current.off();
	}
}
