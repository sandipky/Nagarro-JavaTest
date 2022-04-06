package com.sandip.service;

import com.sandip.exception.AccountFoundException;
import com.sandip.model.Account;
import com.sandip.model.Statement;

import java.util.List;
import java.util.Optional;

public interface AccountService {

	List<Statement> getBankStatementByID(String account_number) throws AccountFoundException;
	//List<Account> getBankStatement(Long accountID, Date startDate , Date endDate , BigDecimal fromAmt, BigDecimal toAmt);
	List<Statement> getBankStatement(Statement statement) throws AccountFoundException;
	Optional<Account> findAccountByNumber(String code) throws AccountFoundException;
    boolean isAccountNumberValid(String account_number) throws AccountFoundException;
}