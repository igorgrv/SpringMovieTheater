package com.movieTheater.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author igorg
 *
 */
public class MovieDetails {

	@JsonProperty("Title")
	private String title;

	@JsonProperty("Poster")
	private String poster;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("Director")
	private String directors;

	@JsonProperty("Writer")
	private String writers;

	@JsonProperty("Actors")
	private String actors;

	@JsonProperty("Plot")
	private String description;

	@JsonProperty("imdbRating")
	private String rating;

	// -----------------------------------------------------------------
	// Getters and Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirecots(String direcots) {
		this.directors = direcots;
	}

	public String getWriters() {
		return writers;
	}

	public void setWriters(String writers) {
		this.writers = writers;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

}
