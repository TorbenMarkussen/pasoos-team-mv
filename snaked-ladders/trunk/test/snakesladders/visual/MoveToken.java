package snakesladders.visual;

import snakesladders.view.*;

import minidraw.standard.*;
import minidraw.framework.*;
import minidraw.boardgame.*;


/** Move a token.
 * 
<#if type == "code">
<#include "/data/author.txt">
</#if>
 */
public class MoveToken {
  
  public static void main(String[] args) {
    DrawingEditor editor = 
      new MiniDrawApplication( "Move a token...",  
                               new SnakesAndLaddersFactory(null) );
    editor.open();

    editor.setTool( new BoardActionTool(editor) );
  }
}






