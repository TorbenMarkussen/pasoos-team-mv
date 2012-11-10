package minidraw.visual;

import javax.swing.JTextField;

import minidraw.framework.DrawingEditor;

/** A factory that creates the status line as well.
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
class Test2Factory extends TestFactory {

  public JTextField createStatusField(DrawingEditor editor) {
    return new JTextField("Aloha...");
  }
}