package machalica.marcin.spring.lms.account;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonalInfoService {
	private final AddressRepository addressRepository;
	private final PersonalInfoRepository personalInfoRepository;

	@Autowired
	public PersonalInfoService(AddressRepository addressRepository, PersonalInfoRepository personalInfoRepository) {
		this.addressRepository = addressRepository;
		this.personalInfoRepository = personalInfoRepository;
	}

	public PersonalInfo createPersonalInfo(PersonalInfo personalInfo) {
		return personalInfoRepository.save(personalInfo);
	}

	public PersonalInfo deletePersonalInfoById(long personalInfoId) {
		PersonalInfo personalInfo = getPersonalInfoById(personalInfoId);
		personalInfoRepository.delete(personalInfo);
		return personalInfo;
	}

	public PersonalInfo updatePersonalInfo(PersonalInfo personalInfo) {
		return personalInfoRepository.save(personalInfo);
	}

	public PersonalInfo getPersonalInfoById(long personalInfoId) {
		return personalInfoRepository.findById(personalInfoId).orElseThrow(
				() -> new IllegalArgumentException("PersonalInfo of id " + personalInfoId + " was not found"));
	}

}
