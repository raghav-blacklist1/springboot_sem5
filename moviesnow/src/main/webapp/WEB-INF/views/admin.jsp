<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>All users</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/movie-projector-icon.png" />
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/ui-darkness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<style>
    body {
    padding-top: 70px;
    padding-bottom: 60px;
}

footer{
    height: 50px;
}

.movie-card {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 10px;
  align-items: stretch;
}

.card {
    flex: 1 1 auto;
}

.movie-cards {
  padding: 20px 0;
  display: flex;
  justify-content: space-around;
  align-items: stretch;
  flex-wrap: nowrap;
  overflow-x: auto;
}

ul li {
  display: inline-block;
}

.booking-seats {
  animation-name: show;
  animation-duration: 10s;
  animation-timing-function: linear;
  animation-fill-mode: forwards;
  margin: 10% 50%;
}

@keyframes show {
    from { visibility: visible; }
    to { visibility: hidden; width: 0px; height: 0px;}
}

.booking-success {
  animation-name: hide;
  animation-duration: 10s;
  animation-timing-function: linear;
  animation-fill-mode: forwards;
  visibility: hidden;
  opacity: 0;
  margin: 10% 50%;
}

@keyframes hide {
    99% { visibility: hidden; opacity: 0;}
    100% { visibility: visible; opacity: 1;}
}

.loader {
    border: 16px solid #f3f3f3; /* Light grey */
    border-top: 16px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 120px;
    height: 120px;
    animation: spin 2s linear 5;
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}

.form-signin {
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
}

.form-signin .form-control {
    position: relative;
    height: auto;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    padding: 10px;
    font-size: 16px;
}

.form-signin .form-control:focus {
    z-index: 2;
}

.form-signin input {
    margin-top: 10px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
}

.form-signin button {
    margin-top: 10px;
}
</style>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <span style="font-family:cursive;display:inline-block;color:yellow">MoviesNow</span>
                </a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="/">Back to Movies</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                
                <li class="nav-item">
                    <a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <%
        String message = (String)session.getAttribute("message");
        if(message != null)  {
    %> 

    <div>
        <div class="container main-content">
            <ul class="list-group">
                <li class="list-group-item list-group-item-info"><h3>${message}</h3></li>
            </ul>
        </div>
    </div>

    <%
    session.setAttribute("message",null);       
    }
    %>

    <div class="container movie-details">
        <h4> All Profiles </h4>
    </div>
    <div class="container">

    </div>
    <div class="container">
        <table class="table">
            <tbody>
                <c:forEach items="${people}" var="person">
                <tr>
                    <td class="col-md-4"> <strong>${person.f_name} ${person.l_name} </td>
                    <td class="col-md-4"> ${person.email} </td>
                
                    <c:choose> 
                        <c:when test="${person.priv_lvl == 2}"> 

                            <td><a href="/admin/revoke/${person.email}" style="background-color: red; border-color: red;" class="btn btn-primary btn-sm" role="button">
                                Revoke Admin access
                                </a></td>
                        </c:when>

                        <c:otherwise>

                            <td>    <a href="/admin/grant/${person.email}" class="btn btn-primary btn-sm" role="button">
                                Grant Admin access
                                </a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <footer class="footer navbar-inverse navbar-fixed-bottom">
    </footer>

    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>