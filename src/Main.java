import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println((int)(Math.random()*5));

        System.out.println(Arrays.toString("%A%_%B%_%C%".split("%")));

        /*System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.now()).compareTo(LocalDateTime.of(LocalDate.now(),LocalTime.now().minusSeconds(10))));
        TreeSet<Integer> S = new TreeSet<>();
        S.add(10);
        S.add(20);
        S.add(30);
        System.out.println(S.ceiling(0));
        System.out.println(S.ceiling(1));

        System.out.println(3%3);*/

        List<String> list = List.of("ABC","XYZ");
        var result = list.stream().filter(e->e.equals("ABC")).map(e->e.charAt(1)).collect(Collectors.toList());
        //System.out.println(result);
        System.out.println(Math.min(2,3)&1);
        int v=2;
        v+=++v;
        System.out.println(v);


    }
}
