package pasoos;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinSearchTest {

    int[] oddNumberedArray = new int[]{1, 3, 5, 7, 9};
    int[] oddNumberedArray10Numbers = new int[]{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    BinSearchTestChild binSearchTest;

    private void assertMidpointValues(int min, int max, int midPoint, int[] actual) {
        assertEquals(min, actual[0]);// min
        assertEquals(max, actual[1]);// max
        assertEquals(midPoint, actual[2]);// Midpoint
    }


    @Before
    public void setup() {
        binSearchTest = new BinSearchTestChild();
    }

    @Test
    public void should_return_neg_1_when_searching_an_empty_array() {
        assertEquals(-1, BinSearch.search(new int[]{}, 5));
    }


    @Test
    public void should_return_neg_1_when_searching_for_a_not_existing_value() {
        assertEquals(-1, BinSearch.search(oddNumberedArray, 6));
    }

    @Test
    public void should_find_the_midpoint_of_an_array_with_odd_number_of_numbers() {
        assertEquals(2, binSearchTest.getMidPoint(0, 4));
    }

    @Test
    public void should_find_the_midpoint_of_an_array_with_equal_number_of_numbers() {
        assertEquals(10, binSearchTest.getMidPoint(7, 14));
    }

    @Test
    public void should_find_the_midpoint_of_an_array_with_only_one_number() {
        assertEquals(4, binSearchTest.getMidPoint(4, 4));
    }

    @Test
    public void should_find_the_midpoint_of_an_array_with_max_int() {
        assertEquals(Integer.MAX_VALUE/2, binSearchTest.getMidPoint(0, Integer.MAX_VALUE));
        assertEquals(1610612735, binSearchTest.getMidPoint((Integer.MAX_VALUE/2)+1, Integer.MAX_VALUE));
    }

    @Test
    public void should_return_index_0_when_the_array_only_has_one_element_and_searching_for_element_0() {
        assertEquals(0, binSearchTest.search(new int[]{5}, 5));
    }

    @Test
    public void should_return_neg_1_when_the_array_only_has_one_element_and_not_searching_for_element_0() {
        assertEquals(-1, binSearchTest.search(new int[]{5}, 7));
    }

    @Test
    public void should_return_index_0_when_searching_for_the_1st_element() {
        assertEquals(0, BinSearchTestChild.search(oddNumberedArray10Numbers, 1));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(3, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(0, 3, 1, resultList.get(1));
        assertMidpointValues(0, 0, 0, resultList.get(2));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_1_when_searching_for_the_2nd_element() {
        assertEquals(1, BinSearchTestChild.search(oddNumberedArray10Numbers, 3));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(2, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(0, 3, 1, resultList.get(1));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_2_when_searching_for_the_3rd_element() {
        assertEquals(2, BinSearchTestChild.search(oddNumberedArray10Numbers, 5));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(3, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(0, 3, 1, resultList.get(1));
        assertMidpointValues(2, 3, 2, resultList.get(2));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_3_when_searching_for_the_4rd_element() {
        assertEquals(3, BinSearchTestChild.search(oddNumberedArray10Numbers, 7));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(4, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(0, 3, 1, resultList.get(1));
        assertMidpointValues(2, 3, 2, resultList.get(2));
        assertMidpointValues(3, 3, 3, resultList.get(3));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_4_when_searching_for_the_5rd_element() {
        assertEquals(4, BinSearchTestChild.search(oddNumberedArray10Numbers, 9));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(1, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_5_when_searching_for_the_6rd_element() {
        assertEquals(5, BinSearchTestChild.search(oddNumberedArray10Numbers, 16));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(3, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        assertMidpointValues(5, 6, 5, resultList.get(2));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_6_when_searching_for_the_7rd_element() {
        assertEquals(6, BinSearchTestChild.search(oddNumberedArray10Numbers, 39));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(4, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        assertMidpointValues(5, 6, 5, resultList.get(2));
        assertMidpointValues(6, 6, 6, resultList.get(3));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_7_when_searching_for_the_8rd_element() {
        assertEquals(7, BinSearchTestChild.search(oddNumberedArray10Numbers, 456));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(2, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_8_when_searching_for_the_9th_element() {
        assertEquals(8, BinSearchTestChild.search(oddNumberedArray10Numbers, 999));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(3, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        assertMidpointValues(8, 9, 8, resultList.get(2));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
    }

    @Test
    public void should_return_index_9_when_searching_for_the_10th_element() {
        assertEquals(9, BinSearchTestChild.search(oddNumberedArray10Numbers, 1250));
        List<int[]> resultList = binSearchTest.getList();
        assertEquals(4, resultList.size());
        assertMidpointValues(0, 9, 4, resultList.get(0));
        assertMidpointValues(5, 9, 7, resultList.get(1));
        assertMidpointValues(8, 9, 8, resultList.get(2));
        assertMidpointValues(9, 9, 9, resultList.get(3));
        //{1, 3, 5, 7, 9, 16, 39, 456, 999, 1250};
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
