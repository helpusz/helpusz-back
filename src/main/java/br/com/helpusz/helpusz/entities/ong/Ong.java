package br.com.helpusz.helpusz.entities.ong;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ong {
  
  @Id
  private String id;
  private String name;
  private String email;
  private String password;
  
}
