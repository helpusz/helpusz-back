package br.com.helpusz.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  
  private String name;
  private String email;
  private String password;
  private TypeAccountEnum typeAccount;

  // Volunteer
  private String phone;

  // Ong
  private String cnpj;

}
