package pasoos;

import java.util.ArrayList;
import java.util.List;

public class BinSearchTestChild extends BinSearch implements BinarySearchHelper {

    private BinarySearchHelper original;

    public BinSearchTestChild() {
        original = new Helper();
        searchHelper = this;
    }

    private List<int[]> list = new ArrayList<int[]>();

    @Override
    public int getMidPoint(int min, int max) {
        int result = original.getMidPoint(min, max);
        list.add(new int[]{min, max, result});
        return result;
    }

    public void clear() {
        list = new ArrayList<int[]>();
    }

    public List<int[]> getList() {
        return list;
    }

}
