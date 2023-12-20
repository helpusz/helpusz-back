package br.com.helpusz.entities.Ong;

import org.springframework.data.annotation.Id;

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
  private String email;
  @NonNull
  private String password;
  @NonNull
  private String cnpj;

  public Ong(@NonNull String name, @NonNull String email, @NonNull String password, @NonNull String cnpj) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.cnpj = cnpj;
  }
  
}
