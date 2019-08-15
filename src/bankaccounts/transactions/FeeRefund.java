package bankaccounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

public class FeeRefund extends Deposit {

    public FeeRefund(CurrencyAmount amount, LocalDateTime dateTime) {
        super(amount, dateTime, "Fee refund");
    }

}
