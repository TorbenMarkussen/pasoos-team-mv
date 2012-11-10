package minidraw.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.event.*;

/** Display key events in the status message field.
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
public class KeyShow {
  
  public static void main(String[] args) {
    DrawingEditor window = 
      new MiniDrawApplication( "See key presses on the status bar (mouse click first)", 
                               new Test2Factory() );
    window.open();

    window.setTool( new DisplayKeyTool( window ) );

  }
}

class DisplayKeyTool extends NullTool {
  String s;
  DisplayKeyTool(DrawingEditor e) {
    editor = e; s = "";
  }
  DrawingEditor editor;
  public void keyDown(KeyEvent evt, int key) {
    s += evt.getKeyChar();
    editor.showStatus( "s="+s );
  }
}
