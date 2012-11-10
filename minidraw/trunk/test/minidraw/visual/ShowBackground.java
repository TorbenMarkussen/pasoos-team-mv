package minidraw.visual;

import minidraw.standard.*;
import minidraw.framework.*;

/*
 * Basic demo of opening window with a static image background.

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

public class ShowBackground {
  
  public static void main(String[] args) {
    DrawingEditor window = 
      new MiniDrawApplication( "Static background image load testing", 
                               new TestFactory() );
    window.open();
  }
}
