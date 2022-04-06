package com.sandip.service.impl;

import com.sandip.dao.AccountStateDao;
import com.sandip.exception.AccountFoundException;
import com.sandip.model.Account;
import com.sandip.model.Statement;
import com.sandip.service.AccountService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AccountStatementServiceImpl implements AccountService {

    @Autowired
    AccountStateDao accountStateDao;

    @Override
    public List<Statement> getBankStatementByID(String account_number) throws AccountFoundException {
        return accountStateDao.getBankStatementByID(account_number);
    }

    @Override
    public List<Statement> getBankStatement(Statement statement) throws AccountFoundException {
        return accountStateDao.getBankStatement(statement);
    }

    @Override
    public Optional<Account> findAccountByNumber(String code) throws AccountFoundException {
        return accountStateDao.findAccountByNumber(code);
    }

    @Override
    public boolean isAccountNumberValid(String account_number) throws AccountFoundException {
        try{
            Optional<Account> account = findAccountByNumber(account_number);
            if(account.isPresent()) {
                return (account.isPresent() || ((account_number != null) && (account.get().getAccount_number() == account_number)));
            }else{
               // throw new AccountFoundException("Account not found with number : " + account_number);
                return false;
            }
        }catch (Exception ex){
            return false;
        }



    }
}
