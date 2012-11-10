package minidraw.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;

/** Demonstrates how figures can be added and removed
 * from the drawing and demonstrates how a tool can
 * be implemented for driving a test.
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
public class AddRemoveFigures {
  
  public static void main(String[] args) {
    DrawingEditor window = 
      new MiniDrawApplication( "Add + Remove figures: Click for action",
                               new TestFactory() );
    window.open();
    
    window.setTool( new AddRemoveTool(window) );
  }
}

class AddRemoveTool extends NullTool {
  private DrawingEditor editor;
  Figure[] list;
  public AddRemoveTool(DrawingEditor e) {
    editor = e;
    list = new Figure[6];
  }
  private int tick = 0;
  public void mouseUp(MouseEvent e, int x, int y) {
    System.out.println( "MD: "+tick );
    if ( tick < 6 ) {
      list[tick] = new ImageFigure( "bpawn", new Point(14+tick*40,14+tick*40));
      editor.drawing().add(list[tick]);
    } else {
      editor.drawing().remove(list[tick-6]);
    }
    tick++;
    if ( tick == 12 ) {tick = 0;}
  }
}
