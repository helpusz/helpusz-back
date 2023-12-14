package br.com.helpusz.model;

import org.springframework.data.annotation.Id;

import br.com.helpusz.util.ONGActivityAreaENUM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ong {
	@Id
	private String id;
	private String name;
	private String cnpj;
	private String address;
	private String phone;
	private ONGActivityAreaENUM activityArea;
	private String email;
	private String password;

	public Ong(String name, String cnpj, String address, String phone, ONGActivityAreaENUM activityArea, String email, String password) {
		this.name = name;
		this.cnpj = cnpj;
		this.address = address;
		this.phone = phone;
		this.activityArea = activityArea;
		this.email = email;
		this.password = password;
	}
}
