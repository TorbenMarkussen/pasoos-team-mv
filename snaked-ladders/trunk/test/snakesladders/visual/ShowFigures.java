package snakesladders.visual;

import snakesladders.view.*;

import minidraw.standard.*;
import minidraw.framework.*;

/** Show visual appearance of game.
 * 
<#if type == "code">
<#include "/data/author.txt">
</#if>
 */
public class ShowFigures {
  
  public static void main(String[] args) {
    DrawingEditor editor = 
      new MiniDrawApplication( "Show Layout...",  
                               new SnakesAndLaddersFactory(null) );
    editor.open();
  }
}






