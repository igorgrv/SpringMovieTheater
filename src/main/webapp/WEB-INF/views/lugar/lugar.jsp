<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="ingresso"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ingresso:template>
	<jsp:body>
		<div class=" col-md-6 col-md-offset-3">
		<form action="/admin/lugar" method="post">
			<input type="hidden" name="roomId" value="${lugarDto.roomId}">

			<div class="form-group">
	            <label for="fileira">Row:</label>
	            <input id="row" type="text" name="row" class="form-control" value="${lugarDto.row}">
        	</div>
			<div class="form-group">
	            <label for="posicao">Line:</label>
	            <input id="line" type="text" name="line" class="form-control" value="${lugarDto.line}">
        	</div>


			<div class="form-group">
         		<button type="submit" class="btn btn-primary">Save</button>
			</div>
		</form>
		</div>

	</jsp:body>

</ingresso:template>