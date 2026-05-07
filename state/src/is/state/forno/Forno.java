package is.state.forno;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Forno implements FornoIF {

	// gestione dei timer
	private final ScheduledExecutorService executor = Executors
			.newSingleThreadScheduledExecutor();

	private ScheduledFuture<?> timerControl;

	private Runnable timeoutTask;

	// definizione interfaccia stato del forno
	private interface StatoFornoIF {

		default void entryAction(Forno f) {
		}

		default void exitAction(FornoIF f) {
		}

		default void apri(Forno f) {
		}

		default void chiudi(Forno f) {
		}

		default void start(Forno f) {
		}

		default void timeout(Forno f) {
		}
	}

	private static enum StatoForno implements StatoFornoIF {
		PORTA_CHIUSA {
			@Override
			public void entryAction(Forno f) {
				System.out.println("Porta Chiusa");
				f.luceOff();
			}// action

			@Override
			public void apri(Forno f) {
				f.transition(PORTA_APERTA);
			}

			@Override
			public void start(Forno f) {
				f.transition(INIZIO_COTTURA);
			}
		},
		PORTA_APERTA {
			@Override
			public void entryAction(Forno f) {
				System.out.println("Porta Aperta");
				f.luceOn();
			}// action

			@Override
			public void chiudi(Forno f) {
				f.transition(PORTA_CHIUSA);
			}
		},
		INIZIO_COTTURA {
			@Override
			public void entryAction(Forno f) {
				System.out.println("Inizio Cottura");
				f.luceOff();
				f.tuboOn();
				f.startTimer();
			}// action

			@Override
			public void apri(Forno f) {
				f.transition(COTTURA_INTERROTTA);
			}

			@Override
			public void start(Forno f) {
				f.transition(COTTURA_ESTESA);
			}

			@Override
			public void timeout(Forno f) {
				f.transition(FINE_COTTURA);
			}
		},
		COTTURA_INTERROTTA {
			@Override
			public void entryAction(Forno f) {
				System.out.println("Cottura Interrotta");
				f.tuboOff();
				f.cancelTimer();

			}// action

			@Override
			public void chiudi(Forno f) {
				f.transition(PORTA_CHIUSA);
			}

		},
		COTTURA_ESTESA {
			public void entryAction(Forno f) {
				System.out.println("Cottura Estesa");

				f.startTimer();

			}// action

			@Override
			public void apri(Forno f) {
				f.transition(COTTURA_INTERROTTA);
			}

			@Override
			public void start(Forno f) {
				f.transition(COTTURA_ESTESA);
			}

			@Override
			public void timeout(Forno f) {
				f.transition(FINE_COTTURA);
			}
		},
		FINE_COTTURA {
			@Override
			public void entryAction(Forno f) {
				System.out.println("Fine Cottura - Beep!");
				f.luceOff();
				f.tuboOff();

			}// action

			@Override
			public void apri(Forno f) {
				f.transition(PORTA_APERTA);
			}
		}

	}

	private final LuceIF luce;
	private final Tubo tubo;
	private StatoFornoIF currentState;
	private final Lock lock = new ReentrantLock();

	public Forno(LuceIF luce, Tubo tubo) {
		this.luce = luce;
		this.tubo = tubo;

		transition(StatoForno.PORTA_CHIUSA);
	}// Forno

	private final void transition(StatoFornoIF nextState) {
		if (currentState != null)
			currentState.exitAction(this);
		currentState = nextState;
		currentState.entryAction(this);
	}// transition

	void luceOff() {
		luce.switchOff();

	}

	void luceOn() {
		luce.switchOn();

	}

	void tuboOn() {
		tubo.switchOn();

	}

	void tuboOff() {
		tubo.switchOff();

	}

	void cancelTimer() {
		timerControl.cancel(true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.state.forno.FornoIF#apri()
	 */
	@Override
	public void apri() {

		lock.lock();
		try {
			currentState.apri(this);
		} finally {
			lock.unlock();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.state.forno.FornoIF#chiudi()
	 */
	@Override
	public void chiudi() {

		lock.lock();
		try {
			currentState.chiudi(this);
		} finally {
			lock.unlock();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see is.state.forno.FornoIF#start()
	 */
	@Override
	public void start() {
		lock.lock();
		try {
			currentState.start(this);
		} finally {
			lock.unlock();

		}

	}

	private void timeout() {
		lock.lock();
		try {
			currentState.timeout(this);
		} finally {
			lock.unlock();

		}

	}

	private void startTimer() {

		if (timeoutTask == null) {
			timeoutTask = () -> timeout();

		} else {
			timerControl.cancel(true);

		}

		timerControl = executor.schedule(timeoutTask, 10, TimeUnit.SECONDS);

	}

}
