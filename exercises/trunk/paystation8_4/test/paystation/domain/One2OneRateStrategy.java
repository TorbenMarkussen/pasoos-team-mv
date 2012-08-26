package paystation.domain;
/** A simple one cent = one minute rate strategy for simplifying
    unit testing the pay station.

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
public class One2OneRateStrategy implements RateStrategy {
  public int calculateTime( int amount ) {
    return amount;
  }
}

