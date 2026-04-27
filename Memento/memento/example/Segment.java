package memento.example;

import java.awt.geom.Point2D;

import is.memento.Memento;
import is.memento.Originator;

public class Segment implements Originator {
	private Point2D p1;
	private Point2D p2;

	public Segment(Point2D p1, Point2D p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public Memento getMemento() {
		return new LineMemento();
	}

	@Override
	public void setMemento(Memento m) {
		if (!(m instanceof LineMemento))
			throw new IllegalArgumentException();

		LineMemento lm = (LineMemento) m;
		
		if (this != lm.getOriginator())
			throw new IllegalArgumentException();

		p1.setLocation(lm.p1);
		p2.setLocation(lm.p2);

	}

//	Private modifier only lets the Originator access the full memento data, so as to not violate encapsulation.
	private class LineMemento implements Memento {

		Point2D p1;
		Point2D p2;

		Segment getOriginator() {
			return Segment.this;
		}

		LineMemento() {

			this.p1 = (Point2D) Segment.this.p1.clone();
			this.p2 = (Point2D) Segment.this.p2.clone();
		}

	}
}
