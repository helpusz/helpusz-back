package br.com.helpusz.entities.Ong;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ong {
  
  @Id
  private String id;
  private String name;
  private String email;
  private String password;
  private String cnpj;

  public Ong(String name, String email, String password, String cnpj) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.cnpj = cnpj;
  }
  
}
