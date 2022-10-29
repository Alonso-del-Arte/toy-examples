package bankaccounts.transactions.fees;

import bankaccounts.transactions.Deposit;
import currency.CurrencyAmount;

import java.time.LocalDateTime;

public class FeeRefund extends Deposit {

    private Fee undoneFee;

    public Fee getCauseFee() {
        return this.undoneFee;
    }

    public FeeRefund(Fee reversedFee, LocalDateTime dateTime) {
        super(reversedFee.getTransactionAmount().times((short) -1), dateTime,
                "Fee refund");
        this.undoneFee = reversedFee;
    }

}
