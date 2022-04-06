package com.sandip.dao;

import com.sandip.exception.AccountFoundException;
import com.sandip.model.Account;
import com.sandip.model.Statement;

import java.util.List;
import java.util.Optional;

public interface AccountStateDao {

	List<Statement> getBankStatementByID(String account_number) throws AccountFoundException;
	List<Statement> getBankStatement(Statement statement) throws AccountFoundException;

	Optional<Account> findAccountByNumber(String code) throws AccountFoundException;
}