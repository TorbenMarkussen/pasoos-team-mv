package pasoos.hotgammon.rules.diceroller;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TestRandomDiceRoller {
    private RandomDiceRoller diceroller;
    private int[] dices;
    private static final Set<Integer> validDices = new HashSet<Integer>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6}));

    @Before
    public void setUp() throws Exception {
        diceroller = new RandomDiceRoller();
    }

    @Test
    public void should_return_two_dices() {
        for (int i = 0; i < 100; i++) {
            dices = diceroller.roll();
            assertEquals(2, dices.length);
        }
    }

    @Test
    public void should_return_valid_dice_values() {
        for (int i = 0; i < 100; i++) {
            dices = diceroller.roll();
            assertTrue(validDices.contains(dices[0]));
            assertTrue(validDices.contains(dices[1]));
        }
    }

    private static boolean isInList(final List<int[]> list, final int[] candidate) {
        for (final int[] item : list) {
            if (Arrays.equals(item, candidate)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void should_be_able_to_create_all_36_dice_combinations() {
        List<int[]> a = new ArrayList<int[]>();
        for (int i = 0; i < 1000; i++) {
            dices = diceroller.roll();
            if (!isInList(a, dices))
                a.add(dices);
        }
        assertEquals(36, a.size());
    }
}
