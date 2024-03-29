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
                    <c:forEach var="gameType" items="${gameTypes}">
                      <option value="${gameType}">${gameType.getName()}</option>
                    </c:forEach>
                  </select>
                  <input type="submit" value="Start" class="button">
                </form>
              </div>

              <div class="messagebox">
                <h3> ${notification} </h3>
              </div>

              <div class="game-container">
                <div class="grid-container">
                    ${boardMarkup}
                </div>
              </div>
            </div> <!-- #main -->
        </div> <!-- #main-container -->

        <c:if test="${autoMove}">
          <script>
            window.location = "/make_move/${gameId}?move=-1"
          </script>
        </c:if>
    </body>
</html>
