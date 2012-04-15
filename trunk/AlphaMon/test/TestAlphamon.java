import java.util.Arrays;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Testing skeleton for AlphaMon.
 * <p/>
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class TestAlphamon {
    private Game game;

    private void assertColorAndCount(Location location, Color color, int count) {
        assertEquals(color, game.getColor(location));
        assertEquals(count, game.getCount(location));
    }

    @Before
    public void setup() {
        game = new GameImpl();
        game.newGame();
    }

    @Test
    public void should_Have_No_Player_In_Turn_After_New_Game() {
        assertEquals(Color.NONE, game.getPlayerInTurn());
    }

    @Test
    public void should_Have_Black_Player_In_Turn_After_First_Turn_And_Die_Values_Are_1_And_2() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        assertEquals(Color.BLACK, game.getPlayerInTurn());
        assertTrue(Arrays.equals(new int[]{1, 2}, game.diceThrown()));
    }

    @Test
    public void should_Have_Red_Player_In_Turn_After_Secound_Turn_And_Die_Values_Are_3_And_4() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        game.nextTurn(); // will throw [3,4] and red is in turn
        assertEquals(Color.RED, game.getPlayerInTurn());
        assertTrue(Arrays.equals(new int[]{3, 4}, game.diceThrown()));
    }

    @Ignore
    @Test
    public void should_Have_Die_Values_1_And_2_After_First_Turn() {
        game.nextTurn(); // will throw [1,2] and thus black starts
        assertTrue(Arrays.equals(new int[]{1, 2}, game.diceThrown()));
    }

    @Test
    public void should_Have_Die_Values_1_And_2_After_4th_Turn() {
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        game.nextTurn();
        assertTrue(Arrays.equals(new int[]{1, 2}, game.diceThrown()));
    }

    @Ignore
    @Test
    public void should_Have_Two_Black_Checkers_On_R1_After_New_Game() {
        assertColorAndCount(Location.R1, Color.BLACK, 2);
    }

    @Test
    public void check_Initial_Setup() {
        assertColorAndCount(Location.R1, Color.BLACK, 2);
        assertColorAndCount(new Location[]{Location.R2, Location.R3, Location.R4, Location.R5, Location.R7, Location.R9}, Color.NONE, 0);
        assertColorAndCount(Location.R6, Color.RED, 5);
        assertColorAndCount(Location.R8, Color.RED, 3);
        assertColorAndCount(Location.R10, Color.NONE, 0);
        assertColorAndCount(Location.R11, Color.NONE, 0);
        assertColorAndCount(Location.R12, Color.BLACK, 5);

        assertColorAndCount(Location.B1, Color.RED, 2);
        assertColorAndCount(Location.B2, Color.NONE, 0);
        assertColorAndCount(Location.B3, Color.NONE, 0);
        assertColorAndCount(Location.B4, Color.NONE, 0);
        assertColorAndCount(Location.B5, Color.NONE, 0);
        assertColorAndCount(Location.B6, Color.BLACK, 5);
        assertColorAndCount(Location.B7, Color.NONE, 0);
        assertColorAndCount(Location.B8, Color.BLACK, 3);
        assertColorAndCount(Location.B9, Color.NONE, 0);
        assertColorAndCount(Location.B10, Color.NONE, 0);
        assertColorAndCount(Location.B11, Color.NONE, 0);
        assertColorAndCount(Location.B12, Color.RED, 5);

        assertColorAndCount(Location.B_BAR, Color.NONE, 0);
        assertColorAndCount(Location.R_BAR, Color.NONE, 0);
        assertColorAndCount(Location.B_BEAR_OFF, Color.NONE, 0);
        assertColorAndCount(Location.R_BEAR_OFF, Color.NONE, 0);
    }

    private void assertColorAndCount(Location[] locations, Color color, int count) {
        for (Location l : locations) {
            assertColorAndCount(l, color, count);
        }
    }

    // Moving a checker from R1 to R2 at the start of the game is valid
    // according to the AlphaMon rules. After the move there is only one move
    // left for Black to make.
    @Test
    public void should_Have_One_Black_Checker_On_R1_And_R2_After_First_Move() {
        game.nextTurn();
        assertTrue(game.move(Location.R1, Location.R2));
        assertColorAndCount(Location.R1, Color.BLACK, 1);
        assertColorAndCount(Location.R2, Color.BLACK, 1);
        assertEquals(1, game.getNumberOfMovesLeft());
    }

    //After moving the two black checkers, the number of moves left is 0
    @Test
    public void should_Have_Zero_Moves_Left_After_Black_Moves_Two_Checkers() {
        game.nextTurn();
        assertTrue(game.move(Location.R1, Location.R2));
        assertTrue(game.move(Location.R1, Location.R3));
        assertEquals(0, game.getNumberOfMovesLeft());
    }


}
