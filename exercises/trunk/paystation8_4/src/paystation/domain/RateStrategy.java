package paystation.domain;
/** The strategy for calculating parking rates.

  Responsibilities:
			
  1) Calculate the parking time for a given payment;

  Instances of this interface plays the Strategy role
  in the Strategy design pattern.
 
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
public interface RateStrategy {
  /**
   return the number of minutes parking time the provided 
   payment is valid for.  
   @param amount payment in some currency.
   @return number of minutes parking time.
   */
  public int calculateTime( int amount );
}

