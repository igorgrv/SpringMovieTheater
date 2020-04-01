package com.movieTheater.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.movieTheater.dao.MovieDao;
import com.movieTheater.dao.SessionDao;
import com.movieTheater.model.Movie;
import com.movieTheater.model.MovieDetails;
import com.movieTheater.model.Session;
import com.movieTheater.rest.OmdbClient;


/**
 * 
 * @author igorg
 * 
 */
@Controller
public class MovieController {


    @Autowired
    private MovieDao movieDao;
    
    @Autowired
	private OmdbClient client;
    
    @Autowired
	private SessionDao sessionDao;


    @GetMapping({"/admin/movie", "/admin/movie/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Movie movie){

        ModelAndView modelAndView = new ModelAndView("movie/movie");

        if (id.isPresent()){
            movie = movieDao.findOne(id.get());
        }

        modelAndView.addObject("movie", movie);

        return modelAndView;
    }


    @PostMapping("/admin/movie")
    @Transactional
    public ModelAndView salva(@Valid Movie movie, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(movie.getId()), movie);
        }

        movieDao.save(movie);

        ModelAndView view = new ModelAndView("redirect:/admin/movies");

        return view;
    }


    @GetMapping(value="/admin/movies")
    public ModelAndView list(){

        ModelAndView modelAndView = new ModelAndView("movie/list");

        modelAndView.addObject("movies", movieDao.findAll());

        return modelAndView;
    }


    @DeleteMapping("/admin/movie/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        movieDao.delete(id);
    }
    
    @GetMapping("/movie/in-theaters")
	public ModelAndView inTheaters() {
		ModelAndView mv = new ModelAndView("movie/in-theaters");
		List<Movie> movies = movieDao.findAll();
		mv.addObject("movies", movies);
		return mv;
		
	}

	@GetMapping("/movie/{id}/detail")
	public ModelAndView detailsMovie(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("movie/detail");
		Movie movie = movieDao.findOne(id);
		List<Session> sessions = sessionDao.listDeSessionsPorMovie(movie);
		mv.addObject("sessions", sessions);
		
		Optional<MovieDetails> movieDetails = client.request(movie);
		mv.addObject("details", movieDetails.orElse(new MovieDetails()));
		return mv;
	}

}
