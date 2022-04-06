package com.sandip.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Statement")
public class Statement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@Override
	public String toString() {
		return "Statement{" +
			   "ID=" + ID +
			   ", datefield='" + datefield + '\'' +
			   ", amount='" + amount + '\'' +
			   ", accountOBj=" + accountOBj +
			   '}';
	}

	@NotEmpty
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private String startDate;
	@NotEmpty
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private String endDate;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal fromAmt;
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal toAmt;

	private String datefield;
	private String amount;
	@NotEmpty
	@Column(name = "account_number", unique=true, nullable = false)
	private String account_number;
	private Long accId;
	@ManyToOne
	@JoinColumn(name = "account_id")
	Account accountOBj;

	public Statement() {}

	public Statement(Long ID, String datefield, String amount, Account accountOBj) {
		this.ID = ID;
		this.datefield = datefield;
		this.amount = amount;
		this.accountOBj = accountOBj;
	}

	public Statement(Long ID, String startDate, String endDate, BigDecimal fromAmt, BigDecimal toAmt, String amount, String account_number) {
		this.ID = ID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fromAmt = fromAmt;
		this.toAmt = toAmt;
		this.amount = amount;
		this.account_number = account_number;
	}

	// Generate Getters and Setters...


	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getDatefield() {
		return datefield;
	}

	public void setDatefield(String datefield) {
		this.datefield = datefield;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getFromAmt() {
		return fromAmt;
	}

	public void setFromAmt(BigDecimal fromAmt) {
		this.fromAmt = fromAmt;
	}

	public BigDecimal getToAmt() {
		return toAmt;
	}

	public void setToAmt(BigDecimal toAmt) {
		this.toAmt = toAmt;
	}

	public Account getAccountOBj() {
		return accountOBj;
	}

	public void setAccountOBj(Account accountOBj) {
		this.accountOBj = accountOBj;
	}

}
