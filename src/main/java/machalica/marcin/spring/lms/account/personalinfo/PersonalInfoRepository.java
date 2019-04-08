package machalica.marcin.spring.lms.account.personalinfo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends CrudRepository<PersonalInfo, Long> {

}
