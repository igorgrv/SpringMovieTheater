<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="movieTheater" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<movieTheater:template>
    <jsp:body>
   		<div class=" col-md-6 col-md-offset-3">
        <form action="/buy/buy" method="post">
            <table class="table table-hover ">
                <thead>
                <th>Sala</th>
                <th>Lugar</th>
                <th>Filme</th>
                <th>Horario</th>
                <th>Tipo do Ingresso</th>
                <th>Preço</th>
                </thead>

                <tbody>
                <c:forEach items="${shopCart.tickets}" var="ticket" varStatus="status">

                    <input type="hidden" name="ingressos[${status.index}].session.id" value="${ticket.session.id}">
                    <input type="hidden" name="ingressos[${status.index}].seat.id" value="${ticket.seat.id}">
                    <input type="hidden" name="ingressos[${status.index}].typeOfTickets" value="${ticket.typeOfTickets}">
                    <tr>
                        <td>${ticket.session.room.name}</td>
                        <td>${ticket.seat.row}${ticket.seat.line}</td>
                        <td>${ticket.session.movie.name }</td>
                        <td>${ticket.session.time}</td>
                        <td>${ticket.typeOfTickets.description}</td>
                        <td>${ticket.price}</td>
                    </tr>
                </c:forEach>
                </tbody>

                <tfoot>
                <td colspan="5" class="text-right"><strong>TOTAL</strong></td>
                <td><strong>${shopCart.total}</strong></td>
                </tfoot>

            </table>

            <div class="form-group">
                <div class="col-md-6">
                    <label for="name">Name:</label>
                    <input id="name" type="text" name="name" class="form-control">
                </div>

                <div class="col-md-6">
                    <label for="lastName">Last Name:</label>
                    <input id="lastName" type="text" name="lastName" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <label for="cpf">CPF:</label>
                    <input id="cpf" type="text" name="cpf" class="form-control">
                </div>
            </div>


            <div class="form-group">
                <div class="col-md-8">
                    <label for="cardNumber">Credit Card:</label>
                    <input id="cardNumber" type="text" name="cardNumber" class="form-control">
                </div>

                <div class="col-md-4">
                    <label for="cvv">CVV:</label>
                    <input id="cvv" type="text" name="cvv" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <label for="expires">Expires:</label>
                    <input id="expires" type="text" name="expires" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary">Buy!</button>
                </div>
            </div>

        </form>
		</div>
    </jsp:body>
</movieTheater:template>