package pasoos.hotgammon;

/**
 * A game observer is notified whenever the state changes of the
 * Game. GameObserver is the observer role of the Observer pattern.
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 */
public interface GameObserver {
    /**
     * this method is invoked whenever a checker is moved from one
     * position to another
     *
     * @param from the position the checker had before
     * @param to   the new position of the checker
     */
    void checkerMove(Location from, Location to);

    /**
     * this method is invoked whenever the dice are rolled.
     *
     * @param values the values of the two dice
     */
    void diceRolled(int[] values);
}
