<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="movieTheater" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<movieTheater:template>
    <jsp:body>
		<div class=" col-md-6 col-md-offset-3">
        <c:set var="bindingResult" value="${requestScope['org.springframework.validation.BindingResult.sala']}"/>

        <form action='/admin/sala' method="post">
            <div class="form-group">
                <input type="hidden" name="roomId" value="${salaForm.roomId}">
                <c:forEach items="${salaForm.seats}" var="lugar" varStatus="status">
                    <input type="hidden" name="seats[${status.index}].id" value="${lugar.id}">
                </c:forEach>

                <label for="name">Name:</label>
                <input id="name" type="text" name="name" class="form-control" value="${salaForm.name}">

                <c:forEach items="${bindingResult.getFieldErrors('name')}" var="error">
                    <span class="text-danger">${error.defaultMessage}</span>
                </c:forEach>

            </div>

         
             <div class="form-group">
                <label for="price">Preço:</label>
                <input id="price" type="text" name="price"
						class="form-control" value="${salaForm.price}" />
                <c:forEach items="${bindingResult.getFieldErrors('price')}" var="error">
					<span class="text-danger">${error.defaultMessage}</span>
				</c:forEach>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
        </div>
    </jsp:body>
</movieTheater:template>
