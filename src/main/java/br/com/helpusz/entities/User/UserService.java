package br.com.helpusz.entities.User;

import org.springframework.stereotype.Service;

import br.com.helpusz.Utils.Email;
import br.com.helpusz.Utils.SocialLinks;

@Service
public interface UserService {

  void register(User user);

  String getToken(User user);

	User getByEmail(Email email);

	void update(User user);

	void updateSocialLinks(String userId, SocialLinks socialLinks);

}
