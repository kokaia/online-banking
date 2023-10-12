package ge.softlab.lessons.onlinebanking.models;

import jakarta.persistence.criteria.CriteriaBuilder;

public class Calc {

    public static Integer mimateba(Integer a, Integer b){
        if (a == null || b== null ) {
            throw new IllegalArgumentException("null is not allowed");
        }
        return a + b;
    }

}
