package lk.codelabs.sample.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomListExample {

    public static void main(String[] args) {

        List<FunctionalTest> functionalTests=new ArrayList<>();

        FunctionalTest functionalTest= new FunctionalTest(1, "T001",  true);
        functionalTests.add(functionalTest);

        FunctionalTest functionalTest1= new FunctionalTest(2, "T10",  false);
        functionalTests.add(functionalTest1);

        functionalTests.add(new FunctionalTest( 3,  "T11",  false));
        functionalTests.add(new FunctionalTest( 5, "T15",  true));
        functionalTests.add(new FunctionalTest( 6, "T17",  true));

        for(FunctionalTest functionalTestx:functionalTests){
            System.out.println(functionalTestx);
        }

        Collections.sort(functionalTests);
    }

}
