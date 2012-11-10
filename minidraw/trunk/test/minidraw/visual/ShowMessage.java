package minidraw.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.event.*;

/** Demonstrate different messages in the status bar.
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
public class ShowMessage {
  
  public static void main(String[] args) {
    DrawingEditor window = 
      new MiniDrawApplication( "Click to see messages in status field", 
                               new Test2Factory() );
    window.open();

    window.setTool( new DisplayMessageTool( window ) );

  }
}

class DisplayMessageTool extends NullTool {
  DisplayMessageTool(DrawingEditor e) {
    editor = e;
  }
  DrawingEditor editor;
  public void mouseUp(MouseEvent e, int x, int y) { 
    editor.showStatus( "Mouse Up event at ("+x+","+y+")" );
  }
}

