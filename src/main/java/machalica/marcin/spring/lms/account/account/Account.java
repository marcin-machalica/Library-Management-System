package machalica.marcin.spring.lms.account.account;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import machalica.marcin.spring.lms.account.personalinfo.PersonalInfo;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private long accountId;
	@NotNull
	@Size(min = 1, max = 20)
	private String login;
	@NotNull
	@Size(min = 1, max = 20)
	private String password;
	@NotNull
	@Email
	private String email;
	@NotNull
	@PastOrPresent
	private Date date;

	// books

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personal_info_id", referencedColumnName = "personal_info_id", nullable = false)
	@NotNull
	private PersonalInfo personalInfo;

	protected Account() {
	}

	public Account(@NotNull String login, @NotNull String password, @NotNull String email) {
		this.login = login;
		this.password = password;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", login=" + login + ", password=" + password + ", email=" + email
				+ ", personalInfo=" + personalInfo + "]";
	}

}
