package com.sandip.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	private String account_type;

	@NotEmpty
	@Column(name = "account_number", unique=true, nullable = false)
	private String account_number;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Statement> statements = new HashSet<>();

	// Generate Getters and Setters...


	public Account() {}

	public Account(Long ID, String account_type, String account_number) {
		this.ID = ID;
		this.account_type = account_type;
		this.account_number = account_number;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Long getID() {
		return ID;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public Set<Statement> getStatements() {
		return statements;
	}

	public void setStatements(Set<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public String toString() {
		return "Account{" +
			   "ID=" + ID +
			   ", account_type='" + account_type + '\'' +
			   ", account_number='" + account_number + '\'' +
			   '}';
	}
}
