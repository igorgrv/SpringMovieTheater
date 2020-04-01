<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="movieTheater"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<movieTheater:template>
	<jsp:body>
		<div class=" col-md-6 col-md-offset-3">
		<table class="table table-hover ">
			<thead>
				<tr>
					<th>Name</th>
					<th>Duration</th>
					<th>Price</th>
					<th colspan="2" class="text-center">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="movie" items="${movies}">
					<tr>
						<td>${movie.name}</td>
						<td>${movie.duration.toMinutes()}</td>
						<td>${movie.price}</td>						
						<td>
							<a onclick="excluir(${movie.id})" class="btn btn-danger">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="col-md-6 col-md-offset-3">
			<a href="/admin/movie" class="btn btn-block btn-info">New</a>
		</div>
		</div>
		<script>
			function excluir(id) {
				var url = window.location.href;
				$.ajax({
					url:"/admin/movie/" + id,
					type: 'DELETE',
					success: function (result) {
						console.log(result);

						window.location.href = url;
					}
				});
			}
		</script>
	</jsp:body>
</movieTheater:template>
