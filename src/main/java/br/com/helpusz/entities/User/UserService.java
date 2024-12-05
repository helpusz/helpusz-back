package br.com.helpusz.entities.User;

import org.springframework.stereotype.Service;

import br.com.helpusz.entities.Utils.Email;

@Service
public interface UserService {

  void register(User user);

  String getToken(User user);

	User getByEmail(Email email);

	void update(User user);

}
