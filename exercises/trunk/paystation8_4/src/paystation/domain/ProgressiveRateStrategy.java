package paystation.domain;
/** A progressive calculation rate strategy.

  Responsibilities:
			
  1) Calculate progressive rates so
     First hour costs 150 cents per hour;
     Second hour costs 200 cents per hour;
     Third and following hours cost 300 cents per hour.
 
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
public class ProgressiveRateStrategy implements RateStrategy {
  public int calculateTime( int amount ) {
    int time = 0;
    if ( amount >= 150+200 ) { // from 2nd hour onwards
      amount -= 350;
      time = 120 /*min*/ + amount / 5;
    } else if ( amount >= 150 ) { // from 1st to 2nd hour
      amount -= 150;
      time = 60 /*min*/ + amount * 3 / 10;
    } else { // up to 1st hour
      time = amount * 2 / 5;
    }
    return time;
  }
}

