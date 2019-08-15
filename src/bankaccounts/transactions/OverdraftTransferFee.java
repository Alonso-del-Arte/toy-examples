package bankaccounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

public class OverdraftTransferFee extends Fee {

    public static final CurrencyAmount OVERDRAFT_TRANSFER_FEE_AMOUNT = new CurrencyAmount(-3100L);

    public OverdraftTransferFee(LocalDateTime feeDate) {
        super(OVERDRAFT_TRANSFER_FEE_AMOUNT, feeDate);
    }

}
