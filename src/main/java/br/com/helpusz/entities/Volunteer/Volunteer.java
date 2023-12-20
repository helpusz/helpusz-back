package br.com.helpusz.entities.Volunteer;

import org.springframework.data.annotation.Id;

import br.com.helpusz.entities.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer extends User {
  
  @Id
  private String id;
  private String name;
  private String phone;
  
}
