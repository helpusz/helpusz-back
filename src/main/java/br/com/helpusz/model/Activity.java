package br.com.helpusz.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
	@Id
	private String id;
	private String title;
	private String description;
	private LocalDate date;
	private String time;
	private String location;
	private int capacity;
	private Ong ong;
	private LocalDate createdAt;
	private LocalDate updatedAt;

	public Activity(String title, String description, LocalDate date, String time, String location, int capacity, Ong ong, LocalDate createdAt, LocalDate updatedAt) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.time = time;
		this.location = location;
		this.capacity = capacity;
		this.ong = ong;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
