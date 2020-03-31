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
import com.movieTheater.model.Carrinho;
import com.movieTheater.model.Movie;
import com.movieTheater.model.MovieDetails;
import com.movieTheater.model.Room;
import com.movieTheater.model.Session;
import com.movieTheater.model.ShopCart;
import com.movieTheater.model.form.SessionForm;
import com.movieTheater.model.typeOfTickets.TypeOfTickets;
import com.movieTheater.rest.OmdbClient;
import com.movieTheater.validations.SessionManagement;

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
<<<<<<< HEAD
	private ShopCart shopCart;
=======
	private Carrinho shopCart;
>>>>>>> work

	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("roomId") Integer roomId) {
		ModelAndView mv = new ModelAndView("sessao/sessao");
		List<Movie> movies = movieDao.findAll();
		Room room = roomDao.findOne(roomId);
		mv.addObject("movies", movies);
		mv.addObject("room", room);
		return mv;
	}

	@PostMapping("/admin/sessao")
	public ModelAndView salvarSession(@Valid SessionForm form, BindingResult result) {
		if (result.hasErrors()) {
			return form(form.getRoomId());
		}
		Session session = form.toSession(movieDao, roomDao);
		List<Session> sessions = sessionDao.listaDeSessoesPorRoom(session.getRoom());
		SessionManagement validation = new SessionManagement(sessions);

		if (validation.fit(session)) {
			sessionDao.save(session);
			return new ModelAndView("redirect:/admin/sala/" + form.getRoomId() + "/sessoes/");
		} else {
			return form(form.getRoomId());
		}

	}

	@GetMapping("/sessao/{id}/lugares")
	public ModelAndView selecionaLugares(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("sessao/lugares");
		Session session = sessionDao.findOne(id);
		Optional<MovieDetails> details = client.request(session.getMovie());
<<<<<<< HEAD
		mv.addObject("sessao", session);
=======
		mv.addObject("session", session);
>>>>>>> work
		mv.addObject("carrinho", shopCart);
		mv.addObject("imagemCapa", details.orElse(new MovieDetails()));
		mv.addObject("tiposDeIngressos", TypeOfTickets.values());
		return mv;
	}
}
