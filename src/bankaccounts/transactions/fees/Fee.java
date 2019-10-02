package bankaccounts.transactions.fees;

import bankaccounts.transactions.Withdrawal;
import currency.CurrencyAmount;

import java.time.LocalDateTime;

public class Fee extends Withdrawal {

    public Fee(CurrencyAmount amount, LocalDateTime dateTime) {
        this(amount, dateTime, "Fee");
    }

    public Fee(CurrencyAmount amount, LocalDateTime dateTime, String description) {
        super(amount, dateTime, description);
    }

}
