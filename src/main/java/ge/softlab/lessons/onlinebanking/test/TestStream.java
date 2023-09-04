package ge.softlab.lessons.onlinebanking.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestStream {

    public static void run(){
        System.out.println("\n\n\n\n\n");

        List<Integer> arr = List.of(3, 64, 5, 746, 12, 34, 10, 54);

        Integer max = arr.get(0);
        for (Integer el : arr){
            if ( el > max){
                max = el;
            }
        }

        System.out.println(max);
        System.out.println(arr.stream().max(Integer::compare).get());

        List<Integer> ans = new ArrayList<>();
        for (Integer el : arr){
            if ( el > 10){
                ans.add(el);
            }
        }

        List<Double> ans1 = arr.stream().filter((x) -> x < 10 ).filter((x) -> (x & 1) == 1 ).map(x -> x*1.0).collect(Collectors.toList());

        System.out.println(ans);
        System.out.println(ans1);

        System.out.println(arr.stream().filter((x) -> {
            //asdasd as dfa
//            sadfasd
            return  (x & 1) == 1;
        } ).mapToInt(x-> x).max().orElse(-1));
        System.out.println(arr.stream().filter((x) -> (x & 1) == 1 ).mapToInt(x->x).sum() );

        System.out.println("\n\n");
        arr.stream().forEach(x -> System.out.println(x));
        System.out.println("\n\n");
        arr.parallelStream().forEach(x -> System.out.println(x));

        System.out.println("\n\n");
    }
}
