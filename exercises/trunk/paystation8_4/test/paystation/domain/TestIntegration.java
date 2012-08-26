package paystation.domain;

import org.junit.*;
import static org.junit.Assert.*;

/** Integration testing of the configurations of the pay station.
 
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
public class TestIntegration {
  private PayStation ps;
  
  /**
   * Integration testing for the linear rate configuration
   */
  @Test 
  public void shouldIntegrateLinearRateCorrectly() 
    throws IllegalCoinException {
    // Configure pay station to be the progressive rate pay station
    ps = new PayStationImpl( new LinearRateStrategy(), new UsCentValidatorStrategyImpl() );
    // add $ 2.0: 
    addOneDollar(); addOneDollar();
    
    assertEquals( "Linear Rate: 2$ should give 80 min ",
                  80 , ps.readDisplay() );
  }
  /**
   * Integration testing for the progressive rate configuration
   */
  @Test 
  public void shouldIntegrateProgressiveRateCorrectly() 
    throws IllegalCoinException {
    // reconfigure ps to be the progressive rate pay station
    ps = new PayStationImpl( new ProgressiveRateStrategy(), new UsCentValidatorStrategyImpl() );
    // add $ 2.0: 1.5 gives 1 hours, next 0.5 gives 15 min
    addOneDollar(); addOneDollar();
    
    assertEquals( "Progressive Rate: 2$ should give 75 min ",
                  75 , ps.readDisplay() );
  }

  private void addOneDollar() throws IllegalCoinException {
    ps.addPayment(25); ps.addPayment(25); 
    ps.addPayment(25); ps.addPayment(25); 
  }
}