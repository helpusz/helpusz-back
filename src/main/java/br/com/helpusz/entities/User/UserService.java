package br.com.helpusz.entities.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

  void register(User user);

  String getToken(User user);

	User getByEmail(String email);

	void update(User user);

}
