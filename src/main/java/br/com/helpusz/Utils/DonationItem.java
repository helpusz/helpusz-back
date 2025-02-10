package br.com.helpusz.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationItem {
	private String name;
	private String description;
	private Integer quantity;
}
