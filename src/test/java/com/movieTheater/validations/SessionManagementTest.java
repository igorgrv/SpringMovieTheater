package com.movieTheater.validations;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.movieTheater.model.Movie;
import com.movieTheater.model.Room;
import com.movieTheater.model.Session;

public class SessionManagementTest {

	private Movie rogueOne;
	private Room room3D;
	private Session sessionTenOClock;
	private Session sessionThirteenOClock;
	private Session sessionEighteenOClock;

	@Before
	public void executedBefore() {
		this.rogueOne = new Movie("Rogue One", Duration.ofMinutes(120), "Action", BigDecimal.TEN);
		this.room3D = new Room("3DRoom", BigDecimal.TEN);

		this.sessionTenOClock = new Session(rogueOne, room3D, LocalTime.parse("10:00:00"));
		this.sessionThirteenOClock = new Session(rogueOne, room3D, LocalTime.parse("13:00:00"));
		this.sessionEighteenOClock = new Session(rogueOne, room3D, LocalTime.parse("18:00:00"));
	}

	@Test
	public void garanteQueNaoDevePermitirSessionNoMesmoHorario() {
		List<Session> sessoes = Arrays.asList(sessionTenOClock);
		SessionManagement management = new SessionManagement(sessoes);
		Assert.assertFalse(management.fit(sessionTenOClock));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessionJaExistente() {
		List<Session> sessoes = Arrays.asList(sessionTenOClock);
		Session sessao = new Session(rogueOne, room3D, sessionTenOClock.getTime().minusHours(1));
		SessionManagement management = new SessionManagement(sessoes);
		Assert.assertFalse(management.fit(sessao));
	}

	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessionJaExistente() {
		List<Session> sessoesDaSala = Arrays.asList(sessionTenOClock);
		SessionManagement management = new SessionManagement(sessoesDaSala);
		Session sessao = new Session( rogueOne, room3D, sessionTenOClock.getTime().plusHours(1));
		Assert.assertFalse(management.fit(sessao));
	}

	@Test
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes() {
		List<Session> sessoes = Arrays.asList(sessionTenOClock, sessionEighteenOClock);
		SessionManagement management = new SessionManagement(sessoes);
		Assert.assertTrue(management.fit(sessionThirteenOClock));
	}

	@Test
	public void mustAddIfSessionListIsEmpty() {
		List<Session> sessions = Collections.emptyList();
		SessionManagement management = new SessionManagement(sessions);
		boolean fit = management.fit(sessionTenOClock);
		Assert.assertTrue(fit);
	}
}
