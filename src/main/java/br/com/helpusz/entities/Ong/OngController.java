package br.com.helpusz.entities.Ong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ong")
public class OngController {
  
  @Autowired
  private OngService ongService;

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody Ong ong) {
    this.ongService.register(ong);
    
    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody Ong ong) {
    this.ongService.login(ong);
    
    return ResponseEntity.ok().build();
  }
  
  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }
  
}
