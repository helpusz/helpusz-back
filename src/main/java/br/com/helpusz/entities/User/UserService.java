package br.com.helpusz.entities.User;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.helpusz.Utils.DonationItem;
import br.com.helpusz.Utils.Email;
import br.com.helpusz.Utils.SocialLinks;

@Service
public interface UserService {

  void register(User user);

  String getToken(User user);

	User getByEmail(Email email);

	void update(User user);

	void updateSocialLinks(String userId, SocialLinks socialLinks);

  String uploadProfilePhoto(String id, MultipartFile file) throws Exception;

  List<User> getAllVolunteers(String activityId);

  User addDonationItem(String userId, DonationItem item);


}
