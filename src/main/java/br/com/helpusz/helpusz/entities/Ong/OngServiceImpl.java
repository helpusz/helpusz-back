package br.com.helpusz.helpusz.entities.Ong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OngServiceImpl implements OngService {

  @Autowired
  private OngRepository ongRepository;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
  @Override
  public void register(Ong ong) {
    if(this.ongRepository.existsByEmail(ong.getEmail())) {
      throw new RuntimeException("Conta já existente");
    } 

    ong.setPassword(passwordEncoder.encode(ong.getPassword()));
    
    this.ongRepository.save(ong);
  }

  @Override
  public void login(Ong ong) {
    if(!this.ongRepository.existsByEmail(ong.getEmail())) {
      throw new RuntimeException("Conta não existente");
    }

    Ong existingOng = this.ongRepository.findByEmail(ong.getEmail());

    if(!passwordEncoder.matches(ong.getPassword(), existingOng.getPassword())) {
      throw new RuntimeException("Senha inválida");
    }

    System.out.println("Login efetuado");
  }
  
}
