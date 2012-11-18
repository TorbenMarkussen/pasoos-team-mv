package minidraw.boardgame;

import java.util.*;

/**
 * Abstract Factory for the figures to be used by the
 * BoardDrawing.
 * <p/>
 * Responsible for generating the multimap of graphical
 * pieces corresponding to the initial setup of the board game.
 * This builder is only invoked during construction of the
 * BoardDrawing instance to make the initial collection of
 * BoardFigures.
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
public interface FigureFactory<LOCATION> {
    /**
     * create a multimap of all the BoardFigures that represent pieces
     * in the board game. For instance this method should create 16
     * black and 16 white board figures for a chess game.
     * POSTCONDITION: The multimap MUST contain a non-null list for
     * every LOCATION on the board game, even if the list is empty!
     * POSTCONDITON: The LOCATION type must implement methods equals()
     * and possibly hashCode() so lookups can be made in the returned
     * map.
     *
     * @return a map containing lists of boardfigures for each
     *         LOCATION of the game map.
     */
    public Map<LOCATION, List<BoardPiece>> generatePieceMultiMap();

    /**
     * create a map of all the BoardFigures that represent props
     * (immovable figures) on the board game. An example is the two die
     * figures in a backgammon game. Each unique prop/board figure must
     * be associated a string key, like "die1" and "die2" for the
     * backgammon dice example. Note that the string key will be used by
     * the BoardGame's BoardDrawing to request its coordinates using the
     * PositioningStrategy hotspot. If no props are used then the
     * returned map may be null.
     *
     * @return a map consisting of (key, figure) entries.
     */
    public Map<String, BoardPiece> generatePropMap();
}
