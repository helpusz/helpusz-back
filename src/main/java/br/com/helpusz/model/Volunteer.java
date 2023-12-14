package br.com.helpusz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volunteer {
	private int id;
	private String name;
	private String phone;
	private String email;
	private String password;

	public Volunteer(String name2, String phone2, String email2, String password2) {
		this.name = name2;
		this.phone = phone2;
		this.email = email2;
		this.password = password2;
	}
}
