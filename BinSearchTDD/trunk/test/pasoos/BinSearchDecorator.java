package pasoos;

import java.util.ArrayList;
import java.util.List;

public class BinSearchDecorator extends BinSearch implements MidPointCalculator {

    private MidPointCalculator original;

    private List<int[]> list = new ArrayList<int[]>();

    public BinSearchDecorator() {
        original = midPointCalculator;
    }

    public void decorateBinSearch() {
        midPointCalculator = this;
    }

    public void undoDecorateBinSearch() {
        midPointCalculator = original;
    }

    public int getMidPoint(int min, int max) {
        int result = original.getMidPoint(min, max);
        list.add(new int[]{min, max, result});
        return result;
    }

    public List<int[]> getList() {
        return list;
    }

}
