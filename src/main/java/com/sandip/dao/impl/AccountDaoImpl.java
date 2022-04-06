package com.sandip.dao.impl;

import com.sandip.dao.AccountStateDao;
import com.sandip.exception.AccountFoundException;
import com.sandip.model.Account;
import com.sandip.model.Statement;
import com.sandip.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountDaoImpl extends JdbcDaoSupport implements AccountStateDao {

	@Autowired 
	DataSource dataSource;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}

	@Override
	public List<Statement> getBankStatementByID(String account_number) throws AccountFoundException {
		Optional<Account> accounts = findAccountByNumber(account_number);

		if(accounts.isPresent()){
			System.out.println("accID : " + accounts.get().getID());
		}

		Long accId =  accounts.get().getID();

		List<Statement> statements = jdbcTemplate.query(
				"select ID , datefield , amount ,account_id  from statement where account_id = ?",
				new Object[] {accId},
				new RowMapper<Statement>() {
					public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
						Statement c = new Statement();
						c.setID(rs.getLong(1));
						c.setDatefield(rs.getString(2));
						c.setAmount(rs.getString(3));

						if(accounts.isPresent()){
							Account account =Utility.maskNumber(accounts.get(),"xxxxxxxxx####");
							c.setAccountOBj(account);
						}

						return c;
					}
				});
		System.out.println(statements.toString() + statements.size());
		return statements;
	}

	public Optional<Account> findAccountByNumber(String account_number) throws AccountFoundException {
		System.out.println(account_number);
		Optional<Account> account = null;
		try {
			account = namedParameterJdbcTemplate.queryForObject(
					"select * from account where account_number = :account_number",
					new MapSqlParameterSource("account_number", account_number),
					(rs, rowNum) ->
							Optional.of(new Account(
									rs.getLong("ID"),
									rs.getString("account_type"),
									rs.getString("account_number")
							))
			);
		}catch (Exception e){
			throw new AccountFoundException("Account not found with number : " + account_number);
		}
		if(account.isPresent()){
			return account;
		}else{
			new AccountFoundException("Account not found with number : " + account_number);
		}
		return account;
	}

	@Override
	public List<Statement> getBankStatement(Statement statement) throws AccountFoundException {
		Optional<Account> accounts = findAccountByNumber(statement.getAccount_number());
		if(accounts.isPresent()){
			System.out.println("accID : " + accounts.get().getID());
		}

		Long accId =  accounts.get().getID();

		List<Statement> statements = jdbcTemplate.query(
				"SELECT ID , datefield , amount ,account_id  FROM statement WHERE account_id = ? " +
				" AND datefield between ? AND ? " +
				" AND amount between ? AND ? ",
				new Object[] {accId,statement.getStartDate(),statement.getEndDate(),statement.getFromAmt(),statement.getToAmt()},
				new RowMapper<Statement>() {
					public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
						Statement c = new Statement();
						c.setID(rs.getLong(1));
						c.setDatefield(rs.getString(2));
						c.setAmount(rs.getString(3));
						if(accounts.isPresent()){
							Account account =Utility.maskNumber(accounts.get(),"xxxxxxxxx####");
							c.setAccountOBj(account);
						}
						return c;
					}
				});
		System.out.println(statements.toString() + statements.size());
		return statements;
	}
}