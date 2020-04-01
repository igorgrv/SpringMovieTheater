<%--
  Created by IntelliJ IDEA.
  User: nando
  Date: 20/01/17
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="movieTheater" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<movieTheater:template>
    <jsp:body>
		<div class=" col-md-6 col-md-offset-3">
	        <h1>${details.title}</h1>
	        <image src="${details.poster}" />
	
	        <div>
	            <label for="year">Year</label>
	            <span id="year">${details.year}</span>
	        </div>
	
	        <div>
	            <label for="directors">Directors</label>
	            <span id="directors">${details.directors}</span>
	        </div>
	
	        <div>
	            <label for="escritores">Writers</label>
	            <span id="escritores">${details.writers}</span>
	        </div>
	
	        <div>
	            <label for="atores">Actors</label>
	            <span id="atores">${details.actors}</span>
	        </div>
	
	        <div>
	            <label for="descricao">Description</label>
	            <span id="descricao">${details.description}</span>
	        </div>
	
	        <div>
	            <label for="avaliacao">Avaliation</label>
	            <span id="avaliacao">${details.rating}</span>
	        </div>

			<%-- <sec:authorize access="hasRole('COMPRADOR')"> --%>
				<table class="table table-hover">
					<thead>
						<th>Room</th>
						<th>Time</th>
						<th>Actions</th>
					</thead>
					<tbody>
						<c:forEach items="${sessions}" var="session">
							<tr>
								<td>${session.room.name}</td>
								<td>${session.time}</td>
								<td>
									<a href="/sessao/${session.id}/lugares" class="btn">
										Buy
										<span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			<%-- </sec:authorize> --%>
		</div>
    </jsp:body>
</movieTheater:template>
