package com.movieTheater.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.movieTheater.dao.MovieDao;
import com.movieTheater.dao.RoomDao;
import com.movieTheater.model.Movie;
import com.movieTheater.model.Room;
import com.movieTheater.model.Session;

public class SessionForm {
	@NotNull
	private Integer roomId;
	@NotNull
	private Integer movieId;
	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime time;

	public Session toSession(MovieDao movieDao, RoomDao roomDao) {
		Movie filme = movieDao.findOne(movieId);
		Room room = roomDao.findOne(roomId);
		Session session = new Session(filme, room, this.time);
		return session;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

}
