package minidraw.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;

import javax.swing.*;

/** Demonstrate multiple views.
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
public class MultiView {
  
  public static void main(String[] args) {
    Factory f = new TestFactory();
    DrawingEditor editor = 
      new MiniDrawApplication( "Multi view", f );
    editor.open();
    
    Figure blackKing = new ImageFigure("bking", new Point(14+3*40, 14+0*40));
    editor.drawing().add(blackKing);

    Figure whiteKing = new ImageFigure("wking", new Point(14+3*40, 14+7*40));
    editor.drawing().add(whiteKing);

    editor.setTool( new SelectionTool(editor) );

    // create second view
    JFrame newWindow = new JFrame("Extra View");
    JFrame.setDefaultLookAndFeelDecorated(true);
    newWindow.setLocation( 620, 20 );
    newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    DrawingView extraView = f.createDrawingView(editor);
    JPanel panel = (JPanel) extraView;
    newWindow.getContentPane().add(panel);
    newWindow.pack();
    newWindow.setVisible(true);
  }
}
