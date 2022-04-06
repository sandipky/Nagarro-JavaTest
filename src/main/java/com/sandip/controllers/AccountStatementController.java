package com.sandip.controllers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.sandip.exception.AccountFoundException;
import com.sandip.model.Account;
import com.sandip.model.Statement;
import com.sandip.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AccountStatementController {

	@Autowired
	AccountService accountService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping("/welcome")
	public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}

	@RequestMapping("/showAdminStatements")
	public ModelAndView adminPage(@ModelAttribute("statement") Statement statement) {
		statement = resetStatement(statement);

		return new ModelAndView("adminStatements").addObject(statement);
	}
	public Statement resetStatement(Statement statement){
		SimpleDateFormat dateFormat = new
				SimpleDateFormat("dd.MM.yyyy");
		dateFormat.setLenient(false);
		String dateString = dateFormat.format(new Date());

		statement.setID(0l);
		statement.setFromAmt(BigDecimal.ZERO);
		statement.setToAmt(BigDecimal.ZERO);
		statement.setStartDate(dateString);
		statement.setEndDate(dateString);
		statement.setAccount_number("");
		return statement;
	}

	public Account resetAccount(Account account){
		account.setAccount_number("");
		account.setAccount_type("");
		account.setID(0l);
		return account;
	}
	@RequestMapping("/showUserStatements")
	public ModelAndView userPage(@ModelAttribute("account") Account account) {
		account = resetAccount(account);
		return new ModelAndView("userStatement").addObject(account);
	}

	@RequestMapping(value = "/getAdminStatements", method = RequestMethod.POST)
	public ModelAndView processRequest(@Valid Statement statement,BindingResult result, ModelAndView model) throws AccountFoundException {

		if (result.hasErrors()) {
			return new ModelAndView("adminStatements");
		}

		if(!accountService.isAccountNumberValid(statement.getAccount_number())){
			FieldError fieldError =new FieldError("Statement","account_number",messageSource.getMessage("Invalid.account.number", new String[]{statement.getAccount_number()}, Locale.getDefault()));
			result.addError(fieldError);
			return new ModelAndView("adminStatements");
		}

		List<Statement> bankStatement = accountService.getBankStatement(statement);
		model = new ModelAndView("success");
		model.addObject("bankStatement", bankStatement);
		return model;
	}

	@RequestMapping(value = "/getUserStatements", method = RequestMethod.POST)
	public ModelAndView getUserStatements(@Valid Account account, BindingResult result,
										  ModelAndView model) throws AccountFoundException {

		if (result.hasErrors()) {
			return new ModelAndView("userStatement");
		}

		if(!accountService.isAccountNumberValid(account.getAccount_number())){
			FieldError fieldError =new FieldError("Account","account_number",messageSource.getMessage("Invalid.account.number", new String[]{account.getAccount_number()}, Locale.getDefault()));
			result.addError(fieldError);
			return new ModelAndView("userStatement");
		}

		List<Statement> accountStatement = accountService.getBankStatementByID(account.getAccount_number());
		//model.addAttribute("success", "accountStatement " + account.getAccount_number() + " generated successfully");
		model = new ModelAndView("success");
		model.addObject("accountStatement", accountStatement);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}

}
