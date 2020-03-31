<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="ingresso" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ingresso:template>
    <jsp:body>
		<div class=" col-md-6 col-md-offset-3">
        <h3>Sessões na sala: ${sala.name}</h3>

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
            <c:forEach var="sessao" items="${sessoes}">
                <tr>
                    <td>${sessao.time}</td>
                    <td>${sessao.filme.name}</td>
                    <td>${sessao.filme.duration.toMinutes()}</td>
                    <td>${sessao.price}</td>

                    <td>
                        <a onclick="excluir(${sessao.id})" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="col-md-6 col-md-offset-3">
            <a href="/admin/sessao?roomId=${sala.id}" class="btn btn-block btn-info">New</a>
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
