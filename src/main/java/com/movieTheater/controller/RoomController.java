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


    @GetMapping({"/admin/room", "/admin/room/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, RoomForm roomForm) {
        ModelAndView modelAndView = new ModelAndView("room/room");
        if (id.isPresent()){
            Room room = roomDao.findOne(id.get());
            roomForm = new RoomForm(room);
        }
        modelAndView.addObject("roomForm", roomForm);

        return modelAndView;
    }


    @PostMapping("/admin/room")
    @Transactional
    public ModelAndView save(@Valid RoomForm roomForm, BindingResult result) {
        Room room = roomForm.toSala();
        if (result.hasErrors()){
            return form(Optional.empty(), roomForm);
        }
        System.out.println(room.getSeat().size());
        roomDao.save(room);
        return new ModelAndView("redirect:/admin/rooms");
    }

    @GetMapping("/admin/rooms")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("room/list");

        modelAndView.addObject("rooms", roomDao.findAll());

        return modelAndView;
    }


    @GetMapping("/admin/room/{id}/sessoes")
    public ModelAndView listSessions(@PathVariable("id") Integer id) {

        Room room = roomDao.findOne(id);

        ModelAndView view = new ModelAndView("session/list");
        view.addObject("room", room);
        view.addObject("sessions", sessionDao.listDeSessionsPorRoom(room));
        return view;
    }

    @GetMapping("/admin/room/{id}/seats/")
    public ModelAndView listSeats(@PathVariable("id") Integer id) {

        ModelAndView modelAndView = new ModelAndView("seat/list");

        Room room = roomDao.findOne(id);
        modelAndView.addObject("room", room);

        return modelAndView;
    }


    @DeleteMapping("/admin/room/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        roomDao.delete(id);
    }
}
