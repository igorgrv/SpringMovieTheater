<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="movieTheater" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<movieTheater:template>
    <jsp:body>
		<div class=" col-md-6 col-md-offset-3">
        <c:set var="bindingResult" value="${requestScope['org.springframework.validation.BindingResult.sessionForm']}"/>

        <h3>New session for the room: ${room.name}</h3>

        <form action='/admin/sessao' method="post">
            <input type="hidden" name="roomId" value="${room.id}">

            <div class="form-group">
                <label for="time">Time:</label>
                <input id="time" type="text" name="time" class="form-control" value="${form.time}">
                <c:forEach items="${bindingResult.getFieldErrors('time')}" var="error">
                    <span class="text-danger">${error.defaultMessage}</span>
                </c:forEach>

            </div>

            <div class="form-group">
                <label for="movie">Movie:</label>
                <select id="movie" name="movieId" class="form-control">
                    <option value="">Select the movie</option>
                    <c:forEach var="movie" items="${movies}">
                        <option value="${movie.id}" ${movie.id.equals(form.movieId)? "selected": ""}>${movie.name}</option>
                    </c:forEach>
                </select>
                <c:forEach items="${bindingResult.getFieldErrors('movieId')}" var="error">
                    <span class="text-danger">${error.defaultMessage}</span>
                </c:forEach>
            </div>


            <button type="submit" class="btn btn-primary">Save</button>
        </form>
        </div>
    </jsp:body>
</movieTheater:template>