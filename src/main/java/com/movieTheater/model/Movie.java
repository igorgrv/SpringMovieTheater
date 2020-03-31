package com.movieTheater.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author igorg
 */
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Duration duration;
	private String genre;
	private BigDecimal price;

	/**
	 * @deprecated hibernate only
	 */
	public Movie() {

	}

	public Movie(String name, Duration duration, String gender, BigDecimal price) {
		this.name = name;
		this.duration = duration;
		this.genre = gender;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = Duration.ofMinutes(duration);
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String gender) {
		this.genre = gender;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.UP);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

}
