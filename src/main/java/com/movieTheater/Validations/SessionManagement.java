package com.movieTheater.Validations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.movieTheater.model.Session;

/**
 * 
 * @author igorg 
 * The Management must ensure that no session is created at the same time as an existing session!
 */
public class SessionManagement {
	private List<Session> sessions;
	
	public SessionManagement(List<Session> sessions) {
		this.sessions = sessions;
	}

	public boolean fit(Session newSession) {
		if (endUpTomorrow(newSession)) {
			return false;
		}
		
		//will return true if NO time is conflicting
		//will return false if ANY time is conflicting
		return sessions.stream().noneMatch(existingSession -> timeIsConflicting(newSession, existingSession));
		
	}

	private boolean timeIsConflicting(Session newSession, Session existingSession) {
		LocalDateTime beginningOfNewSession = getBeginningOfSession(newSession);
		LocalDateTime endOfNewSession = getEndOfSession(newSession);
		LocalDateTime beginningOfExistingSession = getBeginningOfSession(existingSession);
		LocalDateTime endOfExistingSession = getEndOfSession(existingSession);

		boolean endOfNewSessionIsBeforeBeginningSessionExisting = endOfNewSession.isBefore(beginningOfExistingSession);
		boolean endOfExistingSessionIsBeforeBeginningSessionNew = endOfExistingSession.isBefore(beginningOfNewSession);

		if (endOfNewSessionIsBeforeBeginningSessionExisting || endOfExistingSessionIsBeforeBeginningSessionNew) {
			return false;
		}
		return true;
	}

	private boolean endUpTomorrow(Session newSession) {
		LocalDateTime endOfSession = getEndOfSession(newSession);
		LocalDateTime lastSecondDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		if (endOfSession.isAfter(lastSecondDay)) {
			return true;
		}
		return false;
	}

	private LocalDateTime getEndOfSession(Session newSession) {
		LocalDateTime beginningDaSession = getBeginningOfSession(newSession);
		return beginningDaSession.plus(newSession.getMovie().getDuration());
	}

	private LocalDateTime getBeginningOfSession(Session newSession) {
		LocalDate today = LocalDate.now();
		LocalTime beginningOfSaao = newSession.getTime();
		return beginningOfSaao.atDate(today);
	}
}
