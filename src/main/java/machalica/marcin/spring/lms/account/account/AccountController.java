package machalica.marcin.spring.lms.account.account;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
	private AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping("/accounts")
	public List<Account> getAccounts() {
		return accountService.getAllAccounts();
	}
	
	@RequestMapping("/accounts/{id}")
	public Account getAccount(@PathVariable long id) {
		return accountService.getAccountById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/accounts")
	public Account addAccount(@Valid @RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/accounts/{id}")
	public Account updateAccount(@RequestBody Account account, @PathVariable long id) {
		return accountService.updateAccount(account, id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/accounts/{id}")
	public Account deleteAccount(@PathVariable long id) {
		return accountService.deleteAccountById(id);
	}
}
