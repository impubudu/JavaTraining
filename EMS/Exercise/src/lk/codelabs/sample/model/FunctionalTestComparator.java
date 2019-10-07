package lk.codelabs.sample.model;

import java.util.Comparator;

public class FunctionalTestComparator implements Comparator<FunctionalTest> {

    @Override
    public int compare(FunctionalTest f1, FunctionalTest f2) {
        if (f1.getId() > f2.getId()){
            return 1;
        } else if(f1.getId() < f2.getId()){
            return -1;
        } else
            return 0;
    }
}
