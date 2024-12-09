package br.com.helpusz.entities.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterActivityRequest {
	private String userId;
	private String activityId;
}
