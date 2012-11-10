package minidraw.visual;

import javax.swing.JTextField;

import minidraw.framework.*;
import minidraw.standard.*;

/** A factory just for testing purposes. Defines a
 * chess board background.

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
class TestFactory implements Factory {
  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new StdViewWithBackground(editor, "chessboard");
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new StandardDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return null;
  }
}

