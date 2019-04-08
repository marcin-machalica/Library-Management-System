package machalica.marcin.spring.lms.account.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import machalica.marcin.spring.lms.account.personalinfo.PersonalInfo;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private long addressId;
	@NotNull
	@Size(min = 1, max = 30)
	private String country;
	@NotNull
	@Size(min = 1, max = 50)
	private String city;
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "postal_code")
	private String postalCode;
	@NotNull
	@Size(min = 1, max = 100)
	private String street;

	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private PersonalInfo personalInfo;

	protected Address() { }

	public Address(@NotNull String country, @NotNull String city, @NotNull String postalCode, @NotNull String street) {
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.street = street;
	}

	@Override
	public String toString() {
		return "Address [address_id=" + addressId + ", country=" + country + ", city=" + city + ", postalCode="
				+ postalCode + ", street=" + street + "]";
	}

}
