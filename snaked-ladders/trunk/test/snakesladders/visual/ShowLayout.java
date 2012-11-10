package snakesladders.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import javax.swing.*;

/** Show visual appearance of game.
 * 
<#if type == "code">
<#include "/data/author.txt">
</#if>
 */
public class ShowLayout {
  
  public static void main(String[] args) {
    DrawingEditor editor = 
      new MiniDrawApplication( "Show Layout...",  
                               new SnakesAndLaddersFactory1() );
    editor.open();

    Figure die = new ImageFigure("die4", new Point(690, 40));
    editor.drawing().add(die);
    
    editor.setTool( new SelectionTool(editor) );

  }
}

class SnakesAndLaddersFactory1 implements Factory {
  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new StdViewWithBackground(editor, "snakes-and-ladders-background");
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new StandardDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    JTextField statusField = new JTextField( "Hello Snakes..." );
    statusField.setEditable(false);
    return statusField;
  }
}


