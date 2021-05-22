import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(23%2);
        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.now()).compareTo(LocalDateTime.of(LocalDate.now(),LocalTime.now().minusSeconds(10))));
        TreeSet<Integer> S = new TreeSet<>();
        S.add(10);
        S.add(20);
        S.add(30);
        System.out.println(S.ceiling(0));
        System.out.println(S.ceiling(1));

        System.out.println(3%3);
    }
}
