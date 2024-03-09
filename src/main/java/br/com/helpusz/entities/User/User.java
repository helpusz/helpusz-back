package br.com.helpusz.entities.User;

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

}
