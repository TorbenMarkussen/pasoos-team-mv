package minidraw.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;

/** Demonstrate putting several 
 * figures into a composite figure.
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
public class ShowCompositeFigure {
  
  public static void main(String[] args) {
    DrawingEditor window = 
      new MiniDrawApplication( "CompositeFigure test: moves as a unit",
                               new TestFactory() );
    window.open();
    
    Figure blackKing = new ImageFigure("bking", new Point(14+3*40, 14+0*40));
    Figure blackPawn = new ImageFigure("bpawn", new Point(14+4*40, 14+1*40));

    CompositeFigure composite = new GroupFigure();

    composite.add(blackKing);
    composite.add(blackPawn);

    window.setTool( new SelectionTool(window) );

    window.drawing().add(composite);
  }
}
