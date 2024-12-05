package br.com.helpusz.entities.User;

import org.springframework.data.annotation.Id;

import br.com.helpusz.entities.Utils.CNPJ;
import br.com.helpusz.entities.Utils.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
	private String id;
  @NonNull
  private String name;
  @NonNull
  private Email email;
  @NonNull
  private String password;
  @NonNull
  private TypeAccountEnum typeAccount;

  // Volunteer
  private String phone;

  // Ong
  private CNPJ cnpj;
	private String validationCode;
	private boolean isValid;

	public User(String name2, Email email2, String password2, String phone2) {
		this.name = name2;
		this.email = email2;
		this.password = password2;
		this.phone = phone2;
	}

	public User(String name2, Email email2, String password2, CNPJ cnpj2) {
		this.name = name2;
		this.email = email2;
		this.password = password2;
		this.cnpj = cnpj2;
  }

}
