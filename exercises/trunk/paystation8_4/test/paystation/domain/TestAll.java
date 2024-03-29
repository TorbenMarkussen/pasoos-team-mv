package paystation.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** Test suite for this package.

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

@RunWith ( Suite.class )
  @Suite.SuiteClasses(
   { TestPayStation.class,
     TestLinearRate.class,
     TestProgressiveRate.class,
     TestIntegration.class } )
public class TestAll {
  // Dummy - it is the annotations that tell JUnit what to do...
}
