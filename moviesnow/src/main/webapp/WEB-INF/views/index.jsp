<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Movie Reservations</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/movie-projector-icon.png" />
    <link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.12.1/themes/ui-darkness/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
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
                <li><a href="#">Movies</a></li>
                <%
                int flag = (int)session.getAttribute("login_flag");
                if(flag == 0)  {
                %> 
                <li><a href="/register">Register</a></li>
                <%
                }
                %>
                <%
                if(flag!=0){

                    int priv = (int)session.getAttribute("priv_lvl");
                    if(priv == 2)  {
                %> 
                <li><a href="/addmovie">Add Movie</a></li>
                <li><a href="/admins">Admins</a></li>
                <%
                    }   
                }
                %>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <%
                    int is_logged = (int)session.getAttribute("login_flag");
                    if(is_logged==0)  {
                %> 

                <li class="nav-item">
                    <a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a>
                </li>

                <%
                    } else {
                %>

                <li><a>Welcome, ${user_name} </a></li>
                <li class="nav-item">
                    <a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
                </li>
                <%
                    }
                %>
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
    
    <div class="container">
        <h3>Movies</h3>
        <div class="movie-cards">
            <div class="movie-card">
                <div class="col-sm-12">
                    <h5 style="font-weight:600" class="card">Spider-Man: Far from Home</h5>
                    <h6 style="font-weight:300" class="card">Action, Thriller</h6>
                    <button class="btn btn-primary btn-sm btn-block">Book</button>
                </div>
            </div>

                <div class="movie-card">
                        <div class="col-sm-12">
                            <h5 style="font-weight:600" class="card">Avengers: Endgame</h5>
                            <h6 style="font-weight:300" class="card">Action, Thriller</h6>
                            <button class="btn btn-primary btn-sm btn-block">Book</button>
                </div>
            </div>

                <div class="movie-card">
                        <div class="col-sm-12">
                            <h5 style="font-weight:600" class="card">Weathering with You</h5>
                            <h6 style="font-weight:300" class="card">Sensational</h6>
                            <button class="btn btn-primary btn-sm btn-block">Book</button>
                </div>
            </div>
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
            <input type="text" class="form-control" id="datepicker" aria-describedby="basic-addon1"/>
        </div>
    </div>
    <footer class="footer navbar-inverse navbar-fixed-bottom">
    </footer>

    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/site.js"></script>

</body>
</html>