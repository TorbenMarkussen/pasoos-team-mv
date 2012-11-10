package snakesladders.view;

import snakesladders.domain.*;

import minidraw.standard.*;
import minidraw.framework.*;
import minidraw.boardgame.*;


/** Simple snake game.
 * 
<#if type == "code">
<#include "/data/author.txt">
</#if>
 */
public class Snake {
  
  public static void main(String[] args) {
    Game game = new GameImpl();
    DrawingEditor editor = 
      new MiniDrawApplication( "A simple snake game...",  
                               new SnakesAndLaddersFactory(game) );
    editor.open();
    editor.setTool( new BoardActionTool(editor) );
    BoardDrawing<Square> drawing = (BoardDrawing<Square>) editor.drawing();
    game.addObserver( drawing );
  }
}






