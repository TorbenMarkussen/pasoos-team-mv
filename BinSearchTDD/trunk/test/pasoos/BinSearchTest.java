package pasoos;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinSearchTest {

    int[] oddNumberedArray = new int[]{1, 3, 5, 7, 9};
    int[] evenNumberedArray10Numbers = new int[]{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    BinSearchDecorator binSearchDecorator;

    private void assertMidpointValues(int min, int max, int midPoint, int[] actual) {
        assertEquals(min, actual[0]);// min
        assertEquals(max, actual[1]);// max
        assertEquals(midPoint, actual[2]);// Midpoint
    }

    @Before
    public void setup() {
        binSearchDecorator = new BinSearchDecorator();
        binSearchDecorator.decorateBinSearch();
    }

    @After
    public void cleanup() {
        binSearchDecorator.undoDecorateBinSearch();
    }

    @Test
    public void should_return_neg_1_when_searching_an_empty_array() {
        assertEquals(-1, BinSearch.search(new int[]{}, 5));
        assertEquals(-1, BinSearchDecorator.search(new int[]{}, 5));
    }


    @Test
    public void should_return_neg_1_when_searching_for_a_not_existing_value() {
        assertEquals(-1, BinSearch.search(oddNumberedArray, 6));
    }

    @Test
    public void should_find_the_midpoint_of_an_array_with_odd_number_of_numbers() {
        assertEquals(2, binSearchDecorator.getMidPoint(0, 4));
    }

    @Test
    public void should_find_the_midpoint_of_an_array_with_equal_number_of_numbers() {
        assertEquals(10, binSearchDecorator.getMidPoint(7, 14));
    }

    @Test
    public void should_find_the_midpoint_of_an_array_with_only_one_number() {
        assertEquals(4, binSearchDecorator.getMidPoint(4, 4));
    }

    @Test
    public void should_find_the_midpoint_of_an_array_with_max_int() {
        assertEquals(Integer.MAX_VALUE / 2, binSearchDecorator.getMidPoint(0, Integer.MAX_VALUE));
        assertEquals(1610612735, binSearchDecorator.getMidPoint((Integer.MAX_VALUE/2)+1, Integer.MAX_VALUE));
    }

    @Test
    public void should_return_index_0_when_the_array_only_has_one_element_and_searching_for_element_0() {
        assertEquals(0, BinSearch.search(new int[]{5}, 5));
    }

    @Test
    public void should_return_neg_1_when_the_array_only_has_one_element_and_not_searching_for_element_0() {
        assertEquals(-1, BinSearch.search(new int[]{5}, 7));
    }


    @Test
    public void should_return_index_0_when_searching_for_the_1st_element() {
        assertEquals(0, BinSearchDecorator.search(evenNumberedArray10Numbers, 1));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(3, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(0, 3, 1, resultList.get(1));
        assertMidpointValues(0, 0, 0, resultList.get(2));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_1_when_searching_for_the_2nd_element() {
        assertEquals(1, BinSearchDecorator.search(evenNumberedArray10Numbers, 3));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(2, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(0, 3, 1, resultList.get(1));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_2_when_searching_for_the_3rd_element() {
        assertEquals(2, BinSearchDecorator.search(evenNumberedArray10Numbers, 5));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(3, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(0, 3, 1, resultList.get(1));
        assertMidpointValues(2, 3, 2, resultList.get(2));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};

    }

    @Test
    public void should_return_index_3_when_searching_for_the_4rd_element() {
        assertEquals(3, BinSearchDecorator.search(evenNumberedArray10Numbers, 7));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(4, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(0, 3, 1, resultList.get(1));
        assertMidpointValues(2, 3, 2, resultList.get(2));
        assertMidpointValues(3, 3, 3, resultList.get(3));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_4_when_searching_for_the_5rd_element() {
        assertEquals(4, BinSearchDecorator.search(evenNumberedArray10Numbers, 9));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(1, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_5_when_searching_for_the_6rd_element() {
        assertEquals(5, BinSearchDecorator.search(evenNumberedArray10Numbers, 16));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(3, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        assertMidpointValues(5, 6, 5, resultList.get(2));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_6_when_searching_for_the_7rd_element() {
        assertEquals(6, BinSearchDecorator.search(evenNumberedArray10Numbers, 39));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(4, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        assertMidpointValues(5, 6, 5, resultList.get(2));
        assertMidpointValues(6, 6, 6, resultList.get(3));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_7_when_searching_for_the_8rd_element() {
        assertEquals(7, BinSearchDecorator.search(evenNumberedArray10Numbers, 456));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(2, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_8_when_searching_for_the_9th_element() {
        assertEquals(8, BinSearchDecorator.search(evenNumberedArray10Numbers, 999));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(3, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        assertMidpointValues(8, 9, 8, resultList.get(2));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_9_when_searching_for_the_10th_element() {
        assertEquals(9, BinSearchDecorator.search(evenNumberedArray10Numbers, 1250));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(4, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        assertMidpointValues(8, 9, 8, resultList.get(2));
        assertMidpointValues(9, 9, 9, resultList.get(3));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_neg_1_when_searching_for_a_not_existing_element() {
        assertEquals(-1, BinSearchDecorator.search(evenNumberedArray10Numbers, 1249));
        List<int[]> resultList = binSearchDecorator.getList();
        assertEquals(4, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        assertMidpointValues(8, 9, 8, resultList.get(2));
        assertMidpointValues(9, 9, 9, resultList.get(3));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Ignore
    @Test
    public void performance_checking() {
        binSearchDecorator.undoDecorateBinSearch();

        int[] a = new int[100000000];
        for(int i= 0; i < a.length; i++)
            a[i] = i+1;

        long start = System.nanoTime();

        for(int i= 0; i < a.length; i++)
            BinSearch.search(a, a[i]);

        long stop = System.nanoTime();
        double dt = stop - start;
        double timeMilliSec = dt/1e6;
        double iterationTimeNanoSec = dt / a.length;
        System.out.println("Iteration time " + iterationTimeNanoSec + " ns");
        System.out.println("Total time     " + timeMilliSec + " ms");
    }

        /*Testlist
         + should_return_neg_1_when_searching_an_empty_array
         + should_return_neg_1_when_searching_for_a_not_existing_value
         + should_find_the_midpoint_of_an_array_with_equal_number_of_numbers
         + should_find_the_midpoint_of_an_array_with_odd_number_of_numbers
         + should_find_the_midpoint_of_an_array_with_only_one_number
         - should_return_the_upper_part_of_the_array_when_the_number_is_in_the_upper_part
         - should_return_the_lower_part_of_the_array_when_the_number_is_in_the_lower_part
         - should_return_the_index_of_the_number_when_the_number_is_the_midpoint
         + should_return_index_0_when_the_array_only_has_one_element_and_searching_for_element_0
         + should_return_neg_1_when_the_array_only_has_one_element_and_not_searching_for_element_0
         - should_return_index_1_when_searching_for_the_2nd_element
        */
}
