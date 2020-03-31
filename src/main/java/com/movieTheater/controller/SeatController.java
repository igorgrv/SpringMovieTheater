package com.movieTheater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movieTheater.dao.SeatDao;
import com.movieTheater.dao.RoomDao;
import com.movieTheater.model.Seat;
import com.movieTheater.model.Room;
import com.movieTheater.model.form.SeatForm;

import javax.validation.Valid;

/**
 * 
 * @author igorg
 *
 */
@Controller
public class SeatController {


    @Autowired
    private RoomDao roomDao;
    @Autowired
    private SeatDao seatDao;

    @GetMapping("/admin/lugar")
    public ModelAndView form(@RequestParam("roomId") Integer salaId, SeatForm lugarDto) {

        lugarDto.setRoomId(salaId);

        ModelAndView view = new ModelAndView("lugar/lugar");

        view.addObject("lugarDto", lugarDto);

        return view;
    }



    @PostMapping("/admin/lugar")
    @Transactional
    public ModelAndView salva(@Valid SeatForm lugarDto, BindingResult result) {

        if (result.hasErrors()) return form(lugarDto.getRoomId(), lugarDto);

        Integer roomId = lugarDto.getRoomId();

        Seat seat = lugarDto.toLugar();
        seatDao.save(seat);

        Room room = roomDao.findOne(roomId);
        room.add(seat);

        roomDao.save(room);

        return new ModelAndView("redirect:/admin/sala/"+roomId+"/lugares/");
    }

}
