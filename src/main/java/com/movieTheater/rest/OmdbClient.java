package com.movieTheater.rest;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.movieTheater.model.MovieDetails;
import com.movieTheater.model.Movie;

/**
 * 
 * @author igorg
 *
 */
@Component
public class OmdbClient {

	public Optional<MovieDetails> request(Movie movie) {
		String uri = "https://omdb-fj22.herokuapp.com/movie?title=%s";
		String title = movie.getName().replace(" ", "+");
		String concatenate = String.format(uri, title);

		RestTemplate client = new RestTemplate();
		try {
			MovieDetails movieDetails = client.getForObject(concatenate, MovieDetails.class);
			return Optional.ofNullable(movieDetails);
		} catch (RestClientException e) {
			System.out.println(concatenate + " - erro: " + e);
			return Optional.empty();
		}
	}
}