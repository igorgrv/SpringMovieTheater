<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="movieTheater" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<movieTheater:template>
    <jsp:body>
		<div class=" col-md-6 col-md-offset-3">
        <table class="table table-hover ">
            <thead>
            <tr>
                <th class="text-center">Name</th>
	       <th class="text-center">Price</th>	 
                <th colspan="4" class="text-center">Actions</th>
            </thead>
            <tbody>
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td class="text-center">${room.name}</td>
		   <td class="text-center">${room.price}</td>
                    <td class="col-md-1">
                        <a href="/admin/room/${room.id}/sessoes/" class="btn btn-primary">
                            <span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span> Sessions
                        </a>
                    </td>
                    <td class="col-md-1">
                        <a href="/admin/room/${room.id}/lugares/" class="btn btn-warning">
                            <span class="glyphicon glyphicon-th" aria-hidden="true"></span> Seats
                        </a>
                    </td>
                    <td>
                        <a onclick="excluir(${room.id})" class="btn btn-danger">Delete</a>
                    </td>
                    <td>
                        <a href="/admin/room/${room.id}" class="btn btn-info">Change</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="col-md-6 col-md-offset-3">
            <a href="/admin/room" class="btn btn-block btn-info">New</a>
        </div>
		</div>
        <script>
            function excluir(id) {
                var url = window.location.href;
                $.ajax({
                    url: "/admin/room/" + id,
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
