package pasoos;

import java.util.ArrayList;
import java.util.List;

public class BinSearchTestChild extends BinSearch implements BinarySearchHelper {

    private static BinarySearchHelper original;

    private List<int[]> list = new ArrayList<int[]>();

    public BinSearchTestChild() {
        original = searchHelper;
    }


    public void decorateBinSearch() {
        searchHelper = this;
    }

    public void undoDecorateBinSearch() {
        searchHelper = original;
    }

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
