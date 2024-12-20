package br.com.helpusz.entities.Activity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import br.com.helpusz.entities.Ong.OngCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Activity {

	@Id
	private String id;
	private String ongId;
	private OngCategoryEnum ongCategory;
	private String name;
	private String description;
	private String location;
	private Date startDate;
	private Date endDate;
	private Date limitInscriptionDate;
	private Number quantityVolunteersAvailable;
	private List<String> volunteers;
	private ActivityStatusEnum actitivityStatusEnum;
	private ActivityVisibilityEnum ActivityVisibilityEnum;
	private List<String> tags;
	private String imageURL;

}
