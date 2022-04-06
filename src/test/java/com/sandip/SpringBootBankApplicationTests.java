package com.sandip;
import com.sandip.controllers.AccountStatementController;
import com.sandip.dao.impl.AccountDaoImpl;
import com.sandip.exception.AccountFoundException;
import com.sandip.model.Account;
import com.sandip.model.Statement;
import com.sandip.service.impl.AccountStatementServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SpringBootBankApplicationTests{

	@Mock
	AccountDaoImpl accountDao;

	@Mock
	AccountStatementController controller;

	@Mock
	private AccountStatementServiceImpl service;

	private static final String ACC_NUMBER = "0012250016001";

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUserStatements() throws AccountFoundException {
		Account userAccount = new Account();
		userAccount.setID(1l);
		userAccount.setAccount_number("0012250016001");
		userAccount.setAccount_type("Saving");

		Statement statement1 = new Statement();
		statement1.setID(10l);
		statement1.setDatefield("09.08.2020");
		statement1.setAccountOBj(userAccount);
		statement1.setAmount("100.90");

		Statement statement2 = new Statement();
		statement2.setID(10l);
		statement2.setDatefield("09.08.2020");
		statement2.setAccountOBj(userAccount);
		statement2.setAmount("100.90");

		List<Statement> statements = new ArrayList<>();
		statements.add(statement1);
		statements.add(statement2);

		Account account = new Account();
		account.setAccount_number(ACC_NUMBER);
		when(accountDao.getBankStatementByID(ACC_NUMBER)).thenReturn(statements);
		List<Statement> outputStatements = accountDao.getBankStatementByID(ACC_NUMBER);

		when(service.getBankStatementByID(ACC_NUMBER)).thenReturn(statements);
		List<Statement> result = service.getBankStatementByID(ACC_NUMBER);
		assertEquals(2, result.size());
		verify(service).getBankStatementByID(ACC_NUMBER);

		assertEquals(2,outputStatements.size());
		verify(accountDao).getBankStatementByID(ACC_NUMBER);
	}

	@Test
	public void testProcessRequest() throws AccountFoundException {
		Account userAccount = new Account();
		userAccount.setID(1l);
		userAccount.setAccount_number("0012250016001");
		userAccount.setAccount_type("Saving");

		Statement statement1 = new Statement();
		statement1.setID(10l);
		statement1.setDatefield("09.08.2020");
		statement1.setAccountOBj(userAccount);
		statement1.setAmount("100.90");

		Statement statement2 = new Statement();
		statement2.setID(12l);
		statement2.setDatefield("09.08.2021");
		statement2.setAccountOBj(userAccount);
		statement2.setAmount("200.90");

		List<Statement> statements = new ArrayList<>();
		statements.add(statement1);
		statements.add(statement2);

		Statement statementInput = new Statement();
		statementInput.setAccount_number(ACC_NUMBER);
		statementInput.setFromAmt(BigDecimal.valueOf(100.00));
		statementInput.setToAmt(BigDecimal.valueOf(300.00));
		statementInput.setStartDate("09.01.2020");
		statementInput.setEndDate("09.01.2022");

		when(accountDao.getBankStatement(statementInput)).thenReturn(statements);
		List<Statement> outputStatements = accountDao.getBankStatement(statementInput);

		when(service.getBankStatement(statementInput)).thenReturn(statements);
		List<Statement> result = service.getBankStatement(statementInput);
		assertEquals(2, result.size());
		verify(service).getBankStatement(statementInput);

		assertEquals(2,outputStatements.size());
		verify(accountDao).getBankStatement(statementInput);
	}

	@Test(expected = AccountFoundException.class)
	public void findAccountByNumber_Should_Throw_AccountFoundException() throws AccountFoundException {
		Account account = new Account();
		when(accountDao.findAccountByNumber(ACC_NUMBER)).thenThrow(AccountFoundException.class);
		Optional<Account> result = accountDao.findAccountByNumber(ACC_NUMBER);
		boolean flag = true;
		if(!result.isPresent()){
			flag =false;
		}
		assertFalse(flag);

	}

	/*@Test
	public void testDeleteInterest(){
		doNothing().when(interestRepository).deleteById(123);
		controller.deleteInterest(123);
		verify(interestRepository).deleteById(123);
	}

	@Test
	public void testFindMatches(){
		UserAccount userAccount = new UserAccount();
		userAccount.setId(123);
		userAccount.setAge(20);
		userAccount.setCity("Austin");
		userAccount.setCountry("USA");

		ArrayList<UserAccount> users = new ArrayList<>();
		users.add(userAccount);
		users.add(userAccount);


		when(userAccountRepository.findById(123)).thenReturn(Optional.of(userAccount));
		when(userAccountRepository.findMatches(20,"Austin","USA",123)).thenReturn(users);

		List<UserAccount> outputMatches = controller.findMatches(123);
		verify(userAccountRepository).findById(123);
		verify(userAccountRepository).findMatches(20,"Austin","USA",123);

		assertNotNull(outputMatches);
		assertEquals(2,outputMatches.size());

	}

*/
}
