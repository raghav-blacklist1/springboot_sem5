<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Movie Feedback Form</title>
    <link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.12.1/themes/ui-darkness/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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

.customform{

    max-width: 250px;
    position: relative;
    align-self: center;
    margin-left: auto;
    margin-right: auto;
    margin-top: 50px;
}

.movie-card .card{

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

.rating {
  display: inline-block;
  position: relative;
  height: 50px;
  line-height: 50px;
  font-size: 30px;
  margin-left: auto;
  margin-right: auto;
}

.rating label {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  cursor: pointer;
}

.rating label:last-child {
  position: static;
}

.rating label:nth-child(1) {
  z-index: 5;
}

.rating label:nth-child(2) {
  z-index: 4;
}

.rating label:nth-child(3) {
  z-index: 3;
}

.rating label:nth-child(4) {
  z-index: 2;
}

.rating label:nth-child(5) {
  z-index: 1;
}

.rating label input {
  position: absolute;
  top: 0;
  left: 0;
  opacity: 0;
}

.rating label .icon {
  float: left;
  color: transparent;
}

.rating label:last-child .icon {
  color: #000;
}

.rating:not(:hover) label input:checked ~ .icon,
.rating:hover label:hover input ~ .icon {
  color: #09f;
}

.rating label input:focus:not(:checked) ~ .icon:last-child {
  color: #000;
  text-shadow: 0 0 5px #09f;
}

.descrp{

    margin-top: 5px;
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
                <li><a href="/">Movies</a></li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <h2>Fill your feedback here</h2>
        <div class="customform">
        <form action="/addfeedback" method="POST">
            <div class="rating">
            <label>
              <input type="radio" name="stars" value="1" />
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
            </label>
            <label>
              <input type="radio" name="stars" value="2" />
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
            </label>
            <label>
              <input type="radio" name="stars" value="3" />
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>   
            </label>
            <label>
              <input type="radio" name="stars" value="4" />
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
            </label>
            <label>
              <input type="radio" name="stars" value="5" checked="checked"/>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
              <span class="icon"><i class="fa fa-star" aria-hidden="true"></i></span>
            </label>
            </div>
            <textarea class="form-control" rows = "5" cols="50" name="descr" type="text" placeholder="Description"></textarea>
            <input type="hidden" name="movie_id" value="${movie_id}">
            <button class="btn btn-lg btn-primary btn-block descrp" type="submit">Submit Feedback</button>
          </form>
          </div>
    </div>
    <footer class="footer navbar-inverse navbar-fixed-bottom">
    </footer>

    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>