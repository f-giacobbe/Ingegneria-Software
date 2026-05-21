package calculator;

public class Calculator {
    public double add(double a, double b) {
        return a + b;
    }
    public double subtract(double a, double b) {
        return a - b;
    }
    public double multiply(double a, double b) {
        return a * b;
    }
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisione per zero non consentita");
        }
        return a / b;
    }

    // Estensione
    public double power(double base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException();
        }
        return Math.pow(base, exp);
    }

    public double sqrt(double n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        return Math.sqrt(n);
    }

    public double remainder(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException();
        }
        return a % b;
    }
}
