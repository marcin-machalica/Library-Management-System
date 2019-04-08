package machalica.marcin.spring.lms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import machalica.marcin.spring.lms.account.address.Address;
import machalica.marcin.spring.lms.account.address.AddressRepository;
import machalica.marcin.spring.lms.account.personalinfo.PersonalInfo;
import machalica.marcin.spring.lms.account.personalinfo.PersonalInfoRepository;
import machalica.marcin.spring.lms.account.personalinfo.PersonalInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalInfoTest {
	@InjectMocks
	private PersonalInfoService personalInfoService;
	@Mock
	private AddressRepository addressRepository;
	@Mock
	private PersonalInfoRepository personalInfoRepository;

	private final long id = 5L;

	@Test
	public void getPersonalInfoById() {
		when(personalInfoRepository.findById(id)).thenReturn(createDummyPersonalInfoOptional(id));
		PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(id);
		verify(personalInfoRepository).findById(id);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getPersonalInfoById_IllegalArgumentException() {
		PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(id);
	}

	@Test
	public void deletePersonalInfoById() {
		when(personalInfoRepository.findById(id)).thenReturn(createDummyPersonalInfoOptional(id));
		PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(id);
		PersonalInfo result = personalInfoService.deletePersonalInfoById(id);
		assertEquals(personalInfo, result);
		verify(personalInfoRepository, times(2)).findById(id);
		verify(personalInfoRepository).delete(personalInfo);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deletePersonalInfoById_IllegalArgumentException() {
		PersonalInfo personalInfo = personalInfoService.deletePersonalInfoById(id);
	}

	@Test
	public void createPersonalInfo() {
		PersonalInfo personalInfo = createDummyPersonalInfo(id);
		when(personalInfoRepository.save(personalInfo)).thenReturn(personalInfo);
		PersonalInfo result = personalInfoService.createPersonalInfo(personalInfo);
		assertEquals(personalInfo, result);
		verify(personalInfoRepository).save(personalInfo);
	}

	@Test
	public void updatePersonalInfo() {
		when(personalInfoRepository.findById(id)).thenReturn(createDummyPersonalInfoOptional(id));
		PersonalInfo personalInfo = personalInfoService.getPersonalInfoById(id);
		personalInfo.setFirstName("Andrew");
		personalInfo.getAddress().setCity("Warsaw");
		when(personalInfoRepository.save(personalInfo)).thenReturn(personalInfo);
		PersonalInfo result = personalInfoService.updatePersonalInfo(personalInfo);
		assertEquals(personalInfo, result);
		verify(personalInfoRepository).save(personalInfo);
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

	private Optional<PersonalInfo> createDummyPersonalInfoOptional(long id) {
		return Optional.of(createDummyPersonalInfo(id));
	}
}
