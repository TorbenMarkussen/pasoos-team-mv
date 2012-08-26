package paystation.domain;

/** Implementation of the pay station.

   Responsibilities:
			
   1) Accept payment;
   2) Calculate parking time based on payment;
   3) Know earning, parking time bought;
   4) Issue receipts;
   5) Handle buy and cancel events.
 
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

public class PayStationImpl implements PayStation {
  private int insertedSoFar;
  private int timeBought;

  /** the strategy for rate calculations */
  private RateStrategy rateStrategy;
  private CoinValidatorStrategy coinValidator;

    /** Construct a pay station instance with the given
      rate calculation strategy.
      @param rateStrategy the rate calculation strategy to use
  */
  public PayStationImpl( RateStrategy rateStrategy, CoinValidatorStrategy coinValidator ) {
    this.rateStrategy = rateStrategy;
    this.coinValidator = coinValidator;
  }

  public void addPayment( int coinValue ) 
    throws IllegalCoinException {

    coinValidator.validate(coinValue);
    insertedSoFar += coinValue;
    timeBought = rateStrategy.calculateTime(insertedSoFar);
  }
  public int readDisplay() {
    return timeBought;
  }
  public Receipt buy() {
    Receipt r = new ReceiptImpl(timeBought);
    reset();
    return r;
  }
  public void cancel() {
    reset();
  }
  private void reset() {
    timeBought = insertedSoFar = 0;
  }
}

