import calculator.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private Calculator calculator;
    private static final double DELTA = 1e-9;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }


    @Nested
    @DisplayName("Test di addizione")
    @Tag("fast")
    class AddTests {
        @Test
        public void shouldSum2plus3() {
            assertEquals(5.0, calculator.add(2, 3), DELTA);
        }

        @Test
        public void shouldSub10minus4() {
            assertEquals(6.0, calculator.subtract(10, 4), DELTA);
        }

        @Test
        public void shouldSumNegativeNumbers() {
            assertEquals(-7, calculator.add(-3, -4), DELTA);
        }

        @ParameterizedTest(name = "{0} + {1} = {2}")
        @CsvFileSource(resources = "/calculator_test_add.csv")
        public void csvAdd(double a, double b, double res) {
            assertEquals(res, calculator.add(a, b), DELTA);
        }
    }


    @DisplayName("Test di sottrazione")
    @Nested
    @Tag("fast")
    class SubtractTests {
        @ParameterizedTest
        @ValueSource(ints = {42})
        public void xMinusX(int x) {
            assertEquals(0, calculator.subtract(x, x), DELTA);
        }

        @Disabled
        public void testSqrt() {
            //assertEquals(5, calculator.sqrt(25), DELTA);
        }
    }


    @DisplayName("Test di moltiplicazione")
    @Tag("fast")
    @Nested
    class MultiplyTests {
        @Test
        @Tag("edge-case")
        public void mulByZero() {
            assertEquals(0, calculator.multiply(12, 0), DELTA);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/calculator_test_mul.csv")
        public void csvMul(double a, double b, double res) {
            assertEquals(res, calculator.multiply(a, b), DELTA);
        }
    }


    @DisplayName("Test di divisione")
    @Nested
    class DivisionTests {
        @Test
        public void decimalResDivision() {
            assertEquals(0.25, calculator.divide(1, 4), DELTA);
        }

        @Test
        public void zeroDivided() {
            assertEquals(0, calculator.divide(0, 98), DELTA);
        }

        @Tag("edge-case")
        @Test
        public void divisionByZeroShouldThrowException() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
                calculator.divide(5, 0);
            });

            assertEquals("Divisione per zero non consentita", e.getMessage());
        }

        @Tag("edge-case")
        @Test
        public void divideZeroByZero() {
            assertThrows(IllegalArgumentException.class, () -> {
                calculator.divide(0, 0);
            });
        }

        @ParameterizedTest
        @ValueSource(doubles = {1, 2, -5, 0.1, 1000})
        public void paramDiv100(double b) {
            assertDoesNotThrow(() -> {
                calculator.divide(100, b);
            });
        }
    }


    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    public void verificaTutteLeOperazioni() throws InterruptedException {
        // Thread.sleep(600);   fa fallire il test perché va oltre il timeout
        double a = 6;
        double b = 3;

        assertAll(() -> {assertEquals(a+b, calculator.add(a, b), DELTA);},
                () -> {assertEquals(a-b, calculator.subtract(a, b), DELTA);},
                () -> {assertEquals(a*b, calculator.multiply(a, b), DELTA);},
                () -> {assertEquals(a/b, calculator.divide(a, b), DELTA);});
    }
}
