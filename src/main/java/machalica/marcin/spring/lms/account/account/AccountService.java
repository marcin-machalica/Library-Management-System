package machalica.marcin.spring.lms.account.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountService {
	private final AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Account createAccount(Account account) {
		account.setDate(new Date(System.currentTimeMillis()));
		return accountRepository.save(account);
	}

	public Account deleteAccountById(long accountId) {
		Account account = getAccountById(accountId);
		accountRepository.delete(account);
		return account;
	}

	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}

	public Account getAccountById(long accountId) {
		return accountRepository.findById(accountId)
				.orElseThrow(() -> new IllegalArgumentException("Account of id " + accountId + " was not found"));
	}
	
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		accountRepository.findAll().forEach(accounts::add);
		return accounts;
	}

	public Account updateAccount(Account account, long id) {
		Account acc = getAccountById(id);
		acc.setDate(account.getDate());
		acc.setEmail(account.getEmail());
		acc.setLogin(account.getLogin());
		acc.setPassword(account.getPassword());
		acc.setPersonalInfo(account.getPersonalInfo());
		
		return accountRepository.save(acc);
	}

}
