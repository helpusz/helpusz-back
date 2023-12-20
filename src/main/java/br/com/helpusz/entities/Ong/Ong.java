package br.com.helpusz.entities.Ong;

import org.springframework.data.annotation.Id;

import br.com.helpusz.entities.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ong extends User {
  
  @Id
  private String id;
  private String name;
  private String cnpj;
  
}
