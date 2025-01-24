package br.com.helpusz.entities.User;

import org.springframework.data.annotation.Id;

import br.com.helpusz.Utils.CNPJ;
import br.com.helpusz.Utils.Email;
import br.com.helpusz.Utils.SocialLinks;
import br.com.helpusz.entities.Ong.OngCategoryEnum;
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

	private SocialLinks socialLinks;

  // Volunteer
  private String phone;

  // Ong
  private CNPJ cnpj;
	private String validationCode;
	private Boolean isValid;
	private OngCategoryEnum category;
	private String profilePhotoUrl;

	// Volunteer
	public User(String name2, Email email2, String password2, TypeAccountEnum typeAccount2, String phone2) {
		this.name = name2;
		this.email = email2;
		this.password = password2;
		this.typeAccount = typeAccount2;
		this.phone = phone2;
	}

	// Ong
	public User(String name2, Email email2, String password2, TypeAccountEnum typeAccount2, CNPJ cnpj2, OngCategoryEnum category2) {
		this.name = name2;
		this.email = email2;
		this.password = password2;
		this.typeAccount = typeAccount2;
		this.cnpj = cnpj2;
		this.category = category2;
  }

}
