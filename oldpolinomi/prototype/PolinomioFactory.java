package is.polinomi.prototype;

import is.polinomi.Polinomio;

public final class PolinomioFactory {

    private PolinomioFactory(){}

    private static PolinomioAstratto  prototype;

    static {
        registerPrototype(new PolinomioLL());
    }
    static synchronized void registerPrototype(PolinomioAstratto p){
        prototype=p;
    }
    public  static synchronized Polinomio createPolinomio() {
        return (Polinomio) prototype.clone();
    }
}
