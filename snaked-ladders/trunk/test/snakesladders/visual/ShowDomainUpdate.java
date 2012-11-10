package snakesladders.visual;

import snakesladders.domain.*;
import snakesladders.view.*;

import minidraw.standard.*;
import minidraw.framework.*;
import minidraw.boardgame.*;

import java.awt.event.*;

/** Show domain update.
 * 
<#if type == "code">
<#include "/data/author.txt">
</#if>
 */
public class ShowDomainUpdate {
  
  public static void main(String[] args) {
    Game game = new GameImpl();
    DrawingEditor editor = 
      new MiniDrawApplication( "Verify Domain Updates...",  
                               new SnakesAndLaddersFactory(game) );
    editor.open();
    editor.setTool( new DomainUpdateTool(game) );
    BoardDrawing<Square> drawing = (BoardDrawing<Square>) editor.drawing();
    game.addObserver( drawing );
  }
}

class DomainUpdateTool extends NullTool {
  private Game game;
  public DomainUpdateTool(Game game) { this.game = game; }
  int clickCount = 0;
  public void mouseDown(MouseEvent e, int x, int y) {
    switch (clickCount) {
    // move player 0's token from square 1 to 3
    case 0: game.move(0, new Square(1), new Square(3)); break;
    case 1: game.move(0, new Square(3), new Square(18)); break;
    default:  game.rollDie();
    }
    clickCount++;
  }
}





