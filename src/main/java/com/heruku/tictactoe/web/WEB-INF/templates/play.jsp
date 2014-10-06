<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="/img/favicon.ico">

        <link rel="stylesheet" href="/resources/main.css">
    </head>
    <body>
        <div class="header-container">
            <header class="wrapper clearfix">
                <h1 class="title">Tic Tac Toe</h1>
            </header>
        </div>

        <div class="main-container">
            <div class="main wrapper clearfix">
              <div class="form-container">
                <form id="start-game-form" class='basic-grey' action='/start' method="POST">
                  <select name="game_type">
                    <option value='HUMAN_VS_HUMAN'>Human vs Human</option>
                    <option value='HUMAN_VS_COMPUTER'>Human vs Computer</option>
                    <option value='COMPUTER_VS_HUMAN'>Computer vs Human</option>
                    <option value='COMPUTER_VS_COMPUTER'>Computer vs Computer</option>
                  </select>
                  <input type="submit" value="Start" class="button">
                </form>
              </div>

              <div class="messagebox">
                <h3> ${message} </h3>
              </div>

              <div class="game-container">
                <div class="grid-container">

                  <c:forEach var="row" items="${board.rows()}" varStatus="rowIndex">
                    <div class="grid-row">
                      <c:forEach var="mark" items="${row.toCharArray()}" varStatus = "columnIndex">
                      <c:set var="index" value="${rowIndex.index * 3 + columnIndex.index}"/>
                        <div class="grid-cell">
                            <a href="/play/make_move?move=${index}"><span class="mark">${mark}</span></a>
                        </div>
                      </c:forEach>
                    </div>
                  </c:forEach>

                </div>
              </div>
            </div> <!-- #main -->
        </div> <!-- #main-container -->

        <c:if test="${shouldMakeMove}">
          <script>
            window.location = "/play/make_move?move=-1"
          </script>
        </c:if>
    </body>
</html>
