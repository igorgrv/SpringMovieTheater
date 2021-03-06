<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="movieTheater" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<movieTheater:template>
    <jsp:body>
		<div class=" col-md-6 col-md-offset-3">
        <c:set var="bindingResult" value="${requestScope['org.springframework.validation.BindingResult.movie']}"/>

        <form action='/admin/movie' method="post">
            <input type="hidden" name="id" value="${movie.id}">

            <div class="form-group">
                <label for="name">Name:</label>
                <input id="name" type="text" name="name" class="form-control" value="${movie.name}">
                <c:forEach items="${bindingResult.getFieldErrors('name')}" var="error">
                    <span class="text-danger">${error.defaultMessage}</span>
                </c:forEach>
            </div>

            <div class="form-group">
                <label for="genre">Movie genre:</label>
                <input id="genre" type="text" name="genre" class="form-control" value="${movie.genre}" placeholder="Action, comedy, SCI-FI">
                <c:forEach items="${bindingResult.getFieldErrors('genre')}" var="error">
                    <span class="text-danger">${error.defaultMessage}</span>
                </c:forEach>
            </div>

            <div class="form-group">
                <label for="duration">Duration:</label>
                <input id="duration" type="text" name="duration" class="form-control"
                       value="${movie.duration.toMinutes()}" placeholder="Duration in minutes">
                <c:forEach items="${bindingResult.getFieldErrors('duration')}" var="error">
                    <span class="text-danger">${error.defaultMessage}</span>
                </c:forEach>
            </div>
		
		
			<div class="form-group">
				<label for="price">Price:</label>
				<input id=price type="text" name="price" class="form-control" value="${movie.price}">
				<c:forEach items="${bindingResult.getFieldErrors('price')}" var="error"> 
					<span class="text-danger">${error.defaultMessage}</span>
				</c:forEach>
			</div>

            <button type="submit" class="btn btn-primary">Save</button>
        </form>
        </div>
    </jsp:body>
</movieTheater:template>
