package paystation.domain;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the progressive rate strategy.
 
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
public class TestProgressiveRate {
  RateStrategy rs;

  @Before public void setUp() {
    rs = new ProgressiveRateStrategy();
  }
  
  /** Test a single hour parking */
  @Test public void shouldGive60MinFor150cent() {
    // First hour: $1.5
    assertEquals( 60 /*minutes*/, rs.calculateTime(150) ); 
  }

  /** Test two hours parking */
  @Test public void shouldGive120MinFor350cent() {
    // Two hours: $1.5+2.0
    assertEquals( 2 * 60 /*minutes*/ , rs.calculateTime(350) ); 
  }

  /** Test three hours parking */
  @Test public void shouldGive180MinFor650cent() {
    // Three hours: $1.5+2.0+3.0
    assertEquals( 3 * 60 /*minutes*/ , rs.calculateTime(650) ); 
  }

  /** Test four hours parking */
  @Test public void shouldGive240MinFor950cent() {
    // Three hours: $1.5+2.0+3.0
    assertEquals( 4 * 60 /*minutes*/ , rs.calculateTime(950) ); 
  }
} 
