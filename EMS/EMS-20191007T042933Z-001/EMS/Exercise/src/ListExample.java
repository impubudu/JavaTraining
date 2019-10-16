import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListExample {

    public static void main(String[] args) {
        List<String> data= new ArrayList<>(1000);
        data.add("BB");
        data.add("AA");
        data.add("CC");
        data.add("DD");

        System.out.println(data);
        Collections.sort(data);
        System.out.println(data);
        Collections.sort(data, Collections.reverseOrder());
        System.out.println(data);

    }
}
