package bankaccounts;

import currency.CurrencyAmount;
import entities.Entity;

public class Check {

    private final CheckingAccount checkingAccount;
    private final short checkNumber;
    private final Entity checkPayee;
    private final CurrencyAmount checkAmount;

    public Check(CheckingAccount account, short number, Entity payee, CurrencyAmount amount) {
        this.checkingAccount = account;
        this.checkNumber = number;
        this.checkPayee = payee;
        this.checkAmount = amount;
    }

}
