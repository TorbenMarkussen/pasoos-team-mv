package pasoos;

public class BinSearch {

    protected static BinarySearchHelper searchHelper = new Helper();

    public static int search(int[] a, int target) {
        if (a.length == 0)
            return -1;

        int min = 0;
        int max = a.length - 1;

        while (max >= min) {
            int midPoint = searchHelper.getMidPoint(min, max);
            if (a[midPoint] == target)
                return midPoint;
            if (a[midPoint] > target)
                max = midPoint - 1;
            else
                min = midPoint + 1;
        }

        return -1;
    }

    protected static class Helper implements BinarySearchHelper {
        @Override
        public int getMidPoint(int min, int max) {
            return (max - min) / 2 + min;
        }
    }
}
