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

	@GetMapping("/admin/seat")
	public ModelAndView form(@RequestParam("roomId") Integer roomId, SeatForm seatDto) {

		seatDto.setRoomId(roomId);

		ModelAndView view = new ModelAndView("seat/seat");

		view.addObject("seatDto", seatDto);

		return view;
	}

	@PostMapping("/admin/seat")
	@Transactional
	public ModelAndView save(@Valid SeatForm seatDto, BindingResult result) {

		if (result.hasErrors())
			return form(seatDto.getRoomId(), seatDto);

		Integer roomId = seatDto.getRoomId();

		Seat seat = seatDto.toLugar();
		seatDao.save(seat);

		Room room = roomDao.findOne(roomId);
		room.add(seat);

		roomDao.save(room);

		return new ModelAndView("redirect:/admin/room/" + roomId + "/seats/");
	}

}
