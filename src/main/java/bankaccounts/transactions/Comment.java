package bankaccounts.transactions;

import bankaccounts.BankAccount;

import java.time.LocalDateTime;

public class Comment extends Transaction {

    private final String commentMessage;

    public static final int MAXIMUM_COMMENT_LENGTH = 512;

    public String getMessage() {
        return this.commentMessage;
    }

    public Comment(String message, LocalDateTime dateTime) {
        super(BankAccount.INITIALIZATION_ACCOUNT_BALANCE, dateTime, "Comment");
        if (message.length() > MAXIMUM_COMMENT_LENGTH) {
            this.commentMessage = message.substring(0, MAXIMUM_COMMENT_LENGTH);
        } else {
            this.commentMessage = message;
        }
    }

}
