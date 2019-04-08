package machalica.marcin.spring.lms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import machalica.marcin.spring.lms.account.account.Account;
import machalica.marcin.spring.lms.account.account.AccountRepository;
import machalica.marcin.spring.lms.account.account.AccountService;
import machalica.marcin.spring.lms.account.address.Address;
import machalica.marcin.spring.lms.account.address.AddressRepository;
import machalica.marcin.spring.lms.account.personalinfo.PersonalInfo;
import machalica.marcin.spring.lms.account.personalinfo.PersonalInfoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTest {
	@InjectMocks
	private AccountService accountService;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private PersonalInfoRepository personalInfoRepository;
	@Mock
	private AddressRepository addressRepository;

	private final long id = 5L;

	@Test
	public void getAccountById() {
		when(accountRepository.findById(id)).thenReturn(createDummyAccountOptional(id));
		Account account = accountService.getAccountById(id);
		verify(accountRepository).findById(id);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getAccountById_IllegalArgumentException() {
		Account account = accountService.getAccountById(id);
	}
	
	@Test
	public void getAllAccounts() {
		List<Account> accounts = createDummyAccountList(id);
		
	}

	@Test
	public void deleteAccountById() {
		when(accountRepository.findById(id)).thenReturn(createDummyAccountOptional(id));
		Account account = accountService.getAccountById(id);
		Account result = accountService.deleteAccountById(id);
		assertEquals(account, result);
		verify(accountRepository, times(2)).findById(id);
		verify(accountRepository).delete(account);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteAccountById_IllegalArgumentException() {
		Account account = accountService.deleteAccountById(id);
	}

	@Test
	public void createAccount() {
		Account account = createDummyAccount(id);
		when(accountRepository.save(account)).thenReturn(account);
		Account result = accountService.createAccount(account);
		assertEquals(account, result);
		verify(accountRepository).save(account);
	}

	@Test
	public void updateAccount() {
		when(accountRepository.findById(id)).thenReturn(createDummyAccountOptional(id));
		Account account = accountService.getAccountById(id);
		account.setLogin("azerty");
		account.getPersonalInfo().setFirstName("Andrew");
		account.getPersonalInfo().getAddress().setCity("Warsaw");
		when(accountRepository.save(account)).thenReturn(account);
		Account result = accountService.updateAccount(account);
		assertEquals(account, result);
		assertEquals("azerty", result.getLogin());
		assertEquals("Andrew", result.getPersonalInfo().getFirstName());
		assertEquals("Warsaw", result.getPersonalInfo().getAddress().getCity());
		verify(accountRepository).save(account);
	}

	private Address createDummyAddress(long id) {
		Address address = new Address("Poland", "Wroclaw", "50-055", "Zachodnia 99/99");
		address.setAddressId(id);
		return address;
	}

	private PersonalInfo createDummyPersonalInfo(long id) {
		Address address = createDummyAddress(id);
		PersonalInfo personalInfo = new PersonalInfo("John", "Doe");
		personalInfo.setPersonalInfoId(id);
		personalInfo.setAddress(address);
		return personalInfo;
	}

	private Account createDummyAccount(long id) {
		PersonalInfo personalInfo = createDummyPersonalInfo(id);
		Account account = new Account("qwerty", "12345", "12345@qwerty.com");
		account.setAccountId(id);
		account.setDate(new Date(System.currentTimeMillis()));
		account.setPersonalInfo(personalInfo);
		return account;
	}

	private Optional<Account> createDummyAccountOptional(long id) {
		return Optional.of(createDummyAccount(id));
	}
	
	private List<Account> createDummyAccountList(long id) {
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(createDummyAccount(id));
		accounts.add(createDummyAccount(id + 1));
		return accounts;
	}
}
