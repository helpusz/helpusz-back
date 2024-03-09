package br.com.helpusz.entities.Volunteer;

import org.springframework.data.annotation.Id;

import br.com.helpusz.entities.Utils.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer {
  
  @Id
  private String id;
  @NonNull
  private String name;
  @NonNull
  private Email email;
  @NonNull
  private String password;
  @NonNull
  private String phone;

  public Volunteer(@NonNull String name, @NonNull Email email, @NonNull String password, @NonNull String phone) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phone = phone;
  }
  
}
