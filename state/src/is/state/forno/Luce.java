package is.state.forno;

public class Luce implements LuceIF {
	// Luce con automa alla Mealy ed enumeration
	private interface StatoLuceIF {
		default void on(Luce l) {
		}

		default void off(Luce l) {
		}
	}

	private enum StatoLuce implements StatoLuceIF {
		ON {

			@Override
			public void off(Luce l) {
				System.out.println("Luce off");
				l.transition(OFF);
			}
		},
		OFF {
			@Override
			public void on(Luce l) {
				System.out.println("Luce on");
				l.transition(ON);

			}

		}
	}

	private void transition(StatoLuceIF next) {
		current = next;
	}

	private StatoLuceIF current = StatoLuce.OFF;

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.state.forno.LuceIF#switchOn()
	 */
	@Override
	public void switchOn() {
		current.on(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.state.forno.LuceIF#switchOff()
	 */
	@Override
	public void switchOff() {
		current.off(this);
	}
}
