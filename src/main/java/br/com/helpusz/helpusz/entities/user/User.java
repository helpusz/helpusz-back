package br.com.helpusz.helpusz.entities.user;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  
  @Id
  private String id;
  private String name;
  private String email;
  private String password;
  
}
