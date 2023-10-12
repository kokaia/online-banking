package ge.softlab.lessons.onlinebanking;

import ge.softlab.lessons.onlinebanking.models.Calc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
public class CalculatorTest {

    private static Calc calc;

    @BeforeAll
    static void  ba(){
        System.out.println("\n beforeAll \n");
        calc = new Calc();
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("\nBeforeEach\n");
    }

    @Test
    void sumTwoNumbers() {
        assertEquals(3, calc.mimateba(1, 2), "1 + 2 should be 3");
    }

    @Test
    void sumFirstIsNull() {
        assertThrows( java.lang.IllegalArgumentException.class, () -> calc.mimateba(null, 1) );
    }

    @Test
    void sumSecondIsNull() {
        Calc calc = new Calc();
        assertThrows( java.lang.IllegalArgumentException.class, () -> calc.mimateba(1, null) );
    }

    @Test
    void testSumWithZero() {
        assertEquals(1, calc.mimateba(0, 1), "0 + 1 should be 1");
    }


    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
        "0,    1,    1",
        "3,    4,    7",
        "12,   3,    15",
    })
    void add(int a, int b, int ans) {
        assertEquals(ans, calc.mimateba(a, b),  String.format("%d + %d should be %d", a,b,ans));
    }

}
