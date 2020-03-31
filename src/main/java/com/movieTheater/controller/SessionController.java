package com.movieTheater.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movieTheater.dao.MovieDao;
import com.movieTheater.dao.RoomDao;
import com.movieTheater.dao.SessionDao;
import com.movieTheater.model.Movie;
import com.movieTheater.model.Room;
import com.movieTheater.model.Session;
import com.movieTheater.model.form.SessionForm;

public class SessionController {
	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private MovieDao movieDao;

//	@Autowired
//	private OmdbClient client;

//	@Autowired
//	private Carrinho carrinho;

	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId) {
		ModelAndView mv = new ModelAndView("sessao/sessao");
		List<Movie> filme = movieDao.findAll();
		Room sala = roomDao.findOne(salaId);
		mv.addObject("filmes", filme);
		mv.addObject("sala", sala);
		return mv;
	}

	@PostMapping("/admin/sessao")
	public ModelAndView salvarSession(@Valid SessionForm form, BindingResult result) {
		if (result.hasErrors()) {
			return form(form.getRoomId());
		}
		Session sessao = form.toSession(movieDao, roomDao);
		List<Session> sessoes = sessionDao.listaDeSessoesPorRoom(sessao.getRoom());
		ValidaSession valida = new ValidaSession(sessoes);

		if (valida.cabe(sessao)) {
			sessionDao.save(sessao);
			return new ModelAndView("redirect:/admin/sala/" + form.getRoomId() + "/sessoes/");
		} else {
			return form(form.getRoomId());
		}

	}

//	@GetMapping("/sessao/{id}/lugares")
//	public ModelAndView selecionaLugares(@PathVariable("id") Integer id) {
//		ModelAndView mv = new ModelAndView("sessao/lugares");
//		Session sessao = sessionDao.findOne(id);
//		Optional<DetalhesMovie> imagemCapa = client.requiscao(sessao.getMovie());
//		mv.addObject("sessao", sessao);
//		mv.addObject("carrinho", carrinho);
//		mv.addObject("imagemCapa", imagemCapa.orElse(new DetalhesMovie()));
//		mv.addObject("tiposDeIngressos", TipoDeIngresso.values());
//		return mv;
//	}
}
