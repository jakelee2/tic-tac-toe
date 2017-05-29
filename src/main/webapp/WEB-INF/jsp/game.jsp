<!DOCTYPE html>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--[if IE 9 ]>
<html class="ie9">
<![endif]-->
<html>
<head>
<jsp:include page="./fragments/headStatics.jsp" />
</head>
<body id="skin-blur-ocean">
  <header id="header" class="media">
    <a href="javascript:;" id="menu-toggle"></a> <a class="logo pull-left" href="javascript:;"><h3>TIC-TAC-TOE GAME!!!</h3></a>
  </header>
  <div class="clearfix"></div>
  <section id="main" class="p-relative" role="main">
    <!-- Content -->
    <section id="content" class="container">
      <h3 class="page-title">CROSS(X) vs NOUGHT(O) </h3>
      <h4 id="inputAgain"></h4>
      <!-- Dashboard Body -->
      <div class="block-area">
        <!-- Row Container -->
        <div class="row m-container">
			<div id="printBoard"></div>
			<div><h4 id = "resultMessage"></h4></div>
			<div class="form-group">
				<table>
					<tr>
						<td>
				          <label for="selectRowLabel">Select Row</label>
							<select id="selectRow">
							  <option value="0" id ="rowOne">Select Row</option>
							  <option value="1" id ="rowOne">1</option>
							  <option value="2" id ="rowTwo">2</option>
							  <option value="3" id ="rowThree">3</option>
							</select>
						</td>
						<td>
				          <label for="selectColumnLabel">Select Column</label>
							<select id="selectColumn">
							  <option value="0" id ="rowOne">Select Column</option>
							  <option value="1" id ="columnOne">1</option>
							  <option value="2" id ="columnTwo">2</option>
							  <option value="3" id ="columnThree">3</option>
							</select>
						</td>
						<td>
							<input id = "moveBtn" type="submit" value="Move" onclick="GAME_MOVE.makeMove()"/>
						</td>
					<tr/>
					<tr>
						<td>
							<input id = "restartBtn" type="submit" value="Restart" onclick="GAME_MOVE.restart()"/>
						</td>					
					</tr>
				</table>
	        </div>
        </div>
        <!-- End of row container -->
      </div>
      <!-- End of Dashboard Body -->
    </section>
  </section>
  <jsp:include page="./fragments/footStatics.jsp" />
  <script src="<spring:url value="/resources/js/game.js" htmlEscape="true" />"></script>
</body>
</html>