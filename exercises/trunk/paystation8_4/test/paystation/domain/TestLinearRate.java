package paystation.domain;

import org.junit.*;
import static org.junit.Assert.*;

/** Test the linear rate strategy.
 
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
public class TestLinearRate {
  /** Test a single hour parking */
  @Test public void shouldDisplay120MinFor300cent() {
    RateStrategy rs = new LinearRateStrategy();
    assertEquals( 300 / 5 * 2, rs.calculateTime(300) ); 
  }
} 
