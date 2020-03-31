<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="ingresso" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ingresso:template>
    <jsp:body>
		<div class=" col-md-6 col-md-offset-3">
        <h3>Sessions of the room: ${sala.name}</h3>

        <table class="table table-hover ">
            <thead>
            <tr>
                <th>Time</th>
                <th>Movie</th>
                <th>Duration</th>
                <th>Price</th>

                <th colspan="2" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="session" items="${sessions}">
                <tr>
                    <td>${session.time}</td>
                    <td>${session.movie.name}</td>
                    <td>${session.movie.duration.toMinutes()}</td>
                    <td>${session.price}</td>

                    <td>
                        <a onclick="excluir(${session.id})" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="col-md-6 col-md-offset-3">
            <a href="/admin/sessao?roomId=${room.id}" class="btn btn-block btn-info">New</a>
        </div>
		</div>
        <script>
            function excluir(id) {
                var url = window.location.href;
                $.ajax({
                    url:"/admin/sessao/" + id,
                    type: 'DELETE',
                    success: function (result) {
                        console.log(result);

                        window.location.href = url;
                    }
                });
            }
        </script>
    </jsp:body>
</ingresso:template>
