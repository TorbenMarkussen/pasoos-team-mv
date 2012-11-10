package minidraw.breakthrough;

import java.awt.Point;

import minidraw.boardgame.PositioningStrategy;

/** The strategy for positioning chess pawns properly in the
 * center of a square on the chess board.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ChessBoardPositioningStrategy implements
    PositioningStrategy<Position> {

  public Point calculateFigureCoordinatesIndexedForLocation(Position location,
      int index) {
    // ignore index, there is only one piece at a time on any square
    return new Point( location.c * Constants.SQUARE_SIZE + Constants.SQUARE_OFFSET_X, 
        location.r * Constants.SQUARE_SIZE + Constants.SQUARE_OFFSET_Y);
  }
  
  public Point calculateFigureCoordinatesForProps(String keyOfProp) {
    // no props are used.
    return null;
  }

}
