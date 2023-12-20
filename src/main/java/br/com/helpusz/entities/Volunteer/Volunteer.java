package br.com.helpusz.entities.Volunteer;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer {
  
  @Id
  private String id;
  private String name;
  private String email;
  private String password;
  private String phone;

  public Volunteer(String name, String email, String password, String phone) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phone = phone;
  }
  
}
