package com.movieTheater.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movieTheater.dao.MovieDao;
import com.movieTheater.dao.RoomDao;
import com.movieTheater.dao.SessionDao;
import com.movieTheater.model.ShopCart;
import com.movieTheater.model.Movie;
import com.movieTheater.model.MovieDetails;
import com.movieTheater.model.Room;
import com.movieTheater.model.Session;
import com.movieTheater.model.form.SessionForm;
import com.movieTheater.model.typeOfTickets.TypeOfTickets;
import com.movieTheater.rest.OmdbClient;
import com.movieTheater.validations.SessionManagement;


/**
 * 
 * @author igorg
 *
 */
@Controller
@Transactional
public class SessionController {
	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private OmdbClient client;

	@Autowired
	private ShopCart shopCart;

	@GetMapping("/admin/session")
	public ModelAndView form(@RequestParam("roomId") Integer roomId) {
		ModelAndView mv = new ModelAndView("session/session");
		List<Movie> movies = movieDao.findAll();
		Room room = roomDao.findOne(roomId);
		mv.addObject("movies", movies);
		mv.addObject("room", room);
		return mv;
	}

	@PostMapping("/admin/session")
	public ModelAndView salvarSession(@Valid SessionForm form, BindingResult result) {
		if (result.hasErrors()) {
			return form(form.getRoomId());
		}
		Session session = form.toSession(movieDao, roomDao);
		List<Session> sessions = sessionDao.listDeSessionsPorRoom(session.getRoom());
		SessionManagement validation = new SessionManagement(sessions);

		if (validation.fit(session)) {
			sessionDao.save(session);
			return new ModelAndView("redirect:/admin/room/" + form.getRoomId() + "/sessoes/");
		} else {
			return form(form.getRoomId());
		}

	}

	@GetMapping("/session/{id}/seats")
	public ModelAndView selecionaSeats(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("session/seats");
		Session session = sessionDao.findOne(id);
		Optional<MovieDetails> details = client.request(session.getMovie());
		mv.addObject("session", session);
		mv.addObject("shopCart", shopCart);
		mv.addObject("imagemCapa", details.orElse(new MovieDetails()));
		mv.addObject("typeOfTickets", TypeOfTickets.values());
		return mv;
	}
}
