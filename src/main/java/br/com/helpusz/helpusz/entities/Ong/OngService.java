package br.com.helpusz.helpusz.entities.Ong;

import org.springframework.stereotype.Service;

@Service
public interface OngService {
  
  void register(Ong ong);

  String login(Ong ong);
  
}
