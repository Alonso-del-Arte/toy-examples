package bankaccounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

public class Fee extends Withdrawal {

    public Fee(CurrencyAmount amount, LocalDateTime dateTime) {
        super(amount, dateTime, "Fee");
    }

}
