package br.com.helpusz.entities.Ong;

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
public class Ong {

  @Id
  private String id;
  @NonNull
  private String name;
  @NonNull
  private Email email;
  @NonNull
  private String password;
  @NonNull
  private CNPJ cnpj;
	private String validationCode;
	private boolean isValid = false;

  public Ong(@NonNull String name, @NonNull Email email, @NonNull String password, @NonNull CNPJ cnpj) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.cnpj = cnpj;
		this.isValid = false;
  }

}
