package com.movieTheater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.movieTheater.dao.MovieDao;
import com.movieTheater.model.Movie;

import javax.validation.Valid;
import java.util.Optional;


/**
 * 
 * @author igorg
 * 
 */
@Controller
public class MovieController {


    @Autowired
    private MovieDao movieDao;


    @GetMapping({"/admin/filme", "/admin/filme/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Movie movie){

        ModelAndView modelAndView = new ModelAndView("filme/filme");

        if (id.isPresent()){
            movie = movieDao.findOne(id.get());
        }

        modelAndView.addObject("filme", movie);

        return modelAndView;
    }


    @PostMapping("/admin/filme")
    @Transactional
    public ModelAndView salva(@Valid Movie movie, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(movie.getId()), movie);
        }

        movieDao.save(movie);

        ModelAndView view = new ModelAndView("redirect:/admin/filmes");

        return view;
    }


    @GetMapping(value="/admin/filmes")
    public ModelAndView lista(){

        ModelAndView modelAndView = new ModelAndView("filme/lista");

        modelAndView.addObject("filmes", movieDao.findAll());

        return modelAndView;
    }


    @DeleteMapping("/admin/filme/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        movieDao.delete(id);
    }

}
