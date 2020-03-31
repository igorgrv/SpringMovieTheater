package com.movieTheater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.movieTheater.dao.RoomDao;
import com.movieTheater.dao.SessionDao;
import com.movieTheater.model.Room;
import com.movieTheater.model.form.RoomForm;

import javax.validation.Valid;
import java.util.Optional;

/**
 * 
 * @author igorg
 *
 */
@Controller
public class RoomController {

    @Autowired
    private RoomDao roomDao;
    
    @Autowired
    private SessionDao sessionDao;


    @GetMapping({"/admin/sala", "/admin/sala/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, RoomForm roomForm) {
        ModelAndView modelAndView = new ModelAndView("sala/sala");
        if (id.isPresent()){
            Room room = roomDao.findOne(id.get());
            roomForm = new RoomForm(room);
        }
        modelAndView.addObject("salaForm", roomForm);

        return modelAndView;
    }


    @PostMapping("/admin/sala")
    @Transactional
    public ModelAndView salva(@Valid RoomForm roomForm, BindingResult result) {
        Room room = roomForm.toSala();
        if (result.hasErrors()){
            return form(Optional.empty(), roomForm);
        }
        System.out.println(room.getSeat().size());
        roomDao.save(room);
        return new ModelAndView("redirect:/admin/salas");
    }

    @GetMapping("/admin/salas")
    public ModelAndView lista(){
        ModelAndView modelAndView = new ModelAndView("sala/lista");

        modelAndView.addObject("salas", roomDao.findAll());

        return modelAndView;
    }


    @GetMapping("/admin/sala/{id}/sessoes")
    public ModelAndView listaSessoes(@PathVariable("id") Integer id) {

        Room room = roomDao.findOne(id);

        ModelAndView view = new ModelAndView("sessao/lista");
        view.addObject("room", room);
        view.addObject("sessions", sessionDao.listaDeSessoesPorRoom(room));
        return view;
    }

    @GetMapping("/admin/sala/{id}/lugares/")
    public ModelAndView listaLugares(@PathVariable("id") Integer id) {

        ModelAndView modelAndView = new ModelAndView("lugar/lista");

        Room room = roomDao.findOne(id);
        modelAndView.addObject("sala", room);

        return modelAndView;
    }


    @DeleteMapping("/admin/sala/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        roomDao.delete(id);
    }
}
