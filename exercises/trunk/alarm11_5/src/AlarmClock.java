/** Interface for a simple alarm clock.

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
public interface AlarmClock {
  /** return the contents of the display depending on the
   * state of the alarm clock.
   * @return the display contents
   */
  public String readDisplay();

  /** press the "mode" button on the clock */
  public void mode();

  /** press the "increase" (+) button on the clock */
  public void increase();

  /** press the "decrease" (-) button on the clock */
  public void decrease();
}