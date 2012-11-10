package minidraw.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import javax.swing.*;

/** A (very small) jigsaw puzzle on the Aarhus University Logo.
 * A demonstration of a "minimal" MiniDraw application.
 * 
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
public class LogoPuzzle {
  
  public static void main(String[] args) {
    DrawingEditor editor = 
      new MiniDrawApplication( "Put the pieces into place",
                               new PuzzleFactory() );
    editor.open();
    editor.setTool( new SelectionTool(editor) );

    Drawing drawing = editor.drawing();
    drawing.add(  new ImageFigure( "11", new Point(5, 5)) );
    drawing.add(  new ImageFigure( "12", new Point(10, 10)) );
    drawing.add(  new ImageFigure( "13", new Point(15, 15)) );
    drawing.add(  new ImageFigure( "21", new Point(20, 20)) );
    drawing.add(  new ImageFigure( "22", new Point(25, 25)) );
    drawing.add(  new ImageFigure( "23", new Point(30, 30)) );
    drawing.add(  new ImageFigure( "31", new Point(35, 35)) );
    drawing.add(  new ImageFigure( "32", new Point(40, 40)) );
    drawing.add(  new ImageFigure( "33", new Point(45, 45)) );
  }
}

class PuzzleFactory implements Factory {

  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new StdViewWithBackground(editor, "au-logo");
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new StandardDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return null;
  }
}
