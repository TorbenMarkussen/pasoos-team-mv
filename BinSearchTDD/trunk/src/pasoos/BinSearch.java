package pasoos;

public class BinSearch {

    protected static MidPointCalculator midPointCalculator = new MidPointCalculatorImpl();

    public static int search(int[] a, int target) {
        int min = 0;
        int max = a.length - 1;

        while (max >= min) {
            int midPoint = midPointCalculator.getMidPoint(min, max);

            if(a[midPoint] == target)
                return midPoint;
            if (a[midPoint] > target)
                max = midPoint - 1;
            else
                min = midPoint + 1;
        }

        return -1;
    }

    protected interface MidPointCalculator {
        int getMidPoint(int min, int max);
    }

   private static class MidPointCalculatorImpl implements MidPointCalculator {

        public int getMidPoint(int min, int max) {
            return (max - min) / 2 + min;
        }
    }
}
