package ge.softlab.lessons.onlinebanking.test;

import ge.softlab.lessons.onlinebanking.models.Caclulator;
import ge.softlab.lessons.onlinebanking.models.Calc;

public class lambdaFunctions {

    void run(){
        int a = 50;
        int b = 10;

        Caclulator mimateba = Calc::mimateba;
        Caclulator gamokleba = (x, y) -> x - y;
        Caclulator gamravleba = (x, y) -> x * y;
        Caclulator gayofa = (x, y) -> x / y;

        System.out.println(printIt(mimateba, a, b));
        System.out.println(printIt(gamokleba, a, b));
        System.out.println(printIt(gayofa, a, b));

    }

    private Integer printIt(Caclulator c, Integer k, Integer l){
        return c.calculate(k, l);
    }

}
