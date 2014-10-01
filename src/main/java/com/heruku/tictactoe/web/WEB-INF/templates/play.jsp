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

        <link rel="stylesheet" href="/stylesheets/normalize.min.css">
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
                    <option value='human_vs_human'>Human vs Human</option>
                    <option value='human_goes_first'>Human vs Computer</option>
                    <option value='computer_goes_first'>Computer vs Human</option>
                    <option value='computer_vs_computer'>Computer vs Computer</option>
                  </select>
                  <input type="submit" value="Start" class="button">
                </form>
              </div>

              <div class="game-container">
                <div class="grid-container">
                  <div class="grid-row">
                    <c:forEach var="mark" items="${board.substring(0,3).toCharArray()}" varStatus="loop">
                      <div class="grid-cell">
                        <a href="/play/make_move?move=${loop.index}"><span>${mark}</span></a>
                      </div>
                    </c:forEach>
                  </div>

                  <div class="grid-row">
                    <c:forEach var="mark" items="${board.substring(3,6).toCharArray()}" varStatus="loop">
                      <div class="grid-cell">
                        <a href="/play/make_move?move=${3 + loop.index}"><span>${mark}</span></a>
                      </div>
                    </c:forEach>
                  </div>

                   <div class="grid-row">
                     <c:forEach var="mark" items="${board.substring(6,9).toCharArray()}" varStatus="loop">
                       <div class="grid-cell">
                         <a href="/play/make_move?move=${6 + loop.index}"><span>${mark}</span></a>
                       </div>
                     </c:forEach>
                   </div>
               </div> <!-- #board -->
            </div> <!-- #main -->
        </div> <!-- #main-container -->
    </body>
</html>
