package com.sandip.utility;

import com.sandip.model.Account;

public class Utility {
    /**
     * @param Account account number in plain format
     * @param mask The  mask pattern.
     *    Use # to include the digit from the position.
     *    Use x to mask the digit at that position.
     *    Any other char will be inserted.
     *
     * @return The masked account number
     */
    public static Account maskNumber(Account account, String mask) {
        int index = 0;
        StringBuilder masked = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                masked.append(account.getAccount_number().charAt(index));
                index++;
            } else if (c == 'x') {
                masked.append(c);
                index++;
            } else {
                masked.append(c);
            }
        }
        account.setAccount_number(masked.toString());
        return account;
    }
}
