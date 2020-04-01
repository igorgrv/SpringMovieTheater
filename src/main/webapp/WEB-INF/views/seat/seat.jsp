<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="movieTheater"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<movieTheater:template>
	<jsp:body>
		<div class=" col-md-6 col-md-offset-3">
		<form action="/admin/seat" method="post">
			<input type="hidden" name="roomId" value="${seatDto.roomId}">

			<div class="form-group">
	            <label for="fileira">Row:</label>
	            <input id="row" type="text" name="row" class="form-control" value="${seatDto.row}">
        	</div>
			<div class="form-group">
	            <label for="posicao">Line:</label>
	            <input id="line" type="text" name="line" class="form-control" value="${seatDto.line}">
        	</div>


			<div class="form-group">
         		<button type="submit" class="btn btn-primary">Save</button>
			</div>
		</form>
		</div>

	</jsp:body>

</movieTheater:template>