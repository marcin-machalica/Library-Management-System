package machalica.marcin.spring.lms.account.personalinfo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import machalica.marcin.spring.lms.account.account.Account;
import machalica.marcin.spring.lms.account.address.Address;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "personal_info")
public class PersonalInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "personal_info_id")
	private long personalInfoId;
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "first_name")
	private String firstName;
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "last_name")
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
	@NotNull
	private Address address;
	
	@OneToOne(mappedBy = "personalInfo")
	@JsonIgnore
	private Account account;

	protected PersonalInfo() { }

	public PersonalInfo(@NotNull String firstName, @NotNull String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "PersonalInfo [personalInfoId=" + personalInfoId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + "]";
	}

}
