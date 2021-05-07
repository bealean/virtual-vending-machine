package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Funds {

    private BigDecimal funds;

    public Funds() {
        this.funds = new BigDecimal("0");
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void addFunds(BigDecimal funds) {
        this.funds = this.funds.add(funds);
    }

    public void decreaseFunds(BigDecimal funds) {
        this.funds = this.funds.subtract(funds);
    }

    public String change() {
        BigDecimal fundsHundred = this.funds.multiply(new BigDecimal("100"));
        String changeMessage = "";
        if (fundsHundred.compareTo(new BigDecimal("0")) > 0) {
            changeMessage += "Here is your change:\n";
            BigDecimal quarters = fundsHundred.divide(new BigDecimal("25"), 0, RoundingMode.DOWN);
            if (quarters.compareTo(new BigDecimal("1")) > 0) {
                changeMessage += quarters + " quarters\n";
            } else if (quarters.equals(new BigDecimal("1"))) {
                changeMessage += quarters + " quarter\n";
            }
            BigDecimal fundsMinusQuarters = fundsHundred.subtract(quarters.multiply(new BigDecimal("25")));
            BigDecimal dimes = fundsMinusQuarters.divide((new BigDecimal("10")), 0, RoundingMode.DOWN);
            if (dimes.compareTo(new BigDecimal("1")) > 0) {
                changeMessage += dimes + " dimes\n";
            } else if (dimes.equals(new BigDecimal("1"))) {
                changeMessage += dimes + " dime\n";
            }
            BigDecimal fundsMinusQuartersAndDimes = fundsMinusQuarters.subtract(dimes.multiply(new BigDecimal("10")));
            BigDecimal nickels = fundsMinusQuartersAndDimes.divide((new BigDecimal("5")), 0, RoundingMode.DOWN);
            if (nickels.equals(new BigDecimal("1"))) {
                changeMessage += nickels + " nickel\n";
            }
        } else {
            changeMessage += "No change due.\n";
        }
        return changeMessage;
    }
}
