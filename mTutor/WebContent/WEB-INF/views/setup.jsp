<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>FMNA Simulator</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="../assets/css/bootstrap.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>

	<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>
        <a class="brand" href="#"> Mtutor</a>
        <div class="nav-collapse">
          <ul class="nav">
            <li class="active"><a href="#">Home</a></li>
          
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Quiz <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="topic/createTopic.jsp"><i class="icon-edit"/></i> New Topic</a></li>
             
              </ul>
            </li>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Questions <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li>
                                      <a href="question/createQuestion.jsp"><i class="icon-edit"/></i> New Question</a>
                                    </li>
                                    <li>
                                      <a href="quiz/createQuiz.jsp"><i class="icon-list-alt"/></i> New Quiz</a>
                                    </li>
                                    
              </ul>
            </li>
          </ul>
        
          <ul class="nav pull-right">
           
            <li class="divider-vertical"></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user icon-white"></i> Username<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#"><i class="icon-off"></i> Logout</a></li>
               
              </ul>
            </li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div>
    </div><!-- /navbar-inner -->
  </div><!-- /navbar -->

	<div class="container">


		<div class="row-fluid">
			<div class="span12">

				<div class="row-fluid">
					<div class="span6">
						<form class="form-horizontal" action="/mTutor/setup/process">
							<fieldset>
								<legend>Initialise Mtutor</legend>
								<div class="form-actions">
									<button type="submit" class="btn btn-primary">Save</button>
								</div>
								
							</fieldset>
						</form>
					</div>
					<div class="span6">
						
					</div>
				</div>
			</div>
		</div>



	</div>
	<!-- /container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="../assets/js/jquery.js"></script>
	<script src="../assets/js/bootstrap-transition.js"></script>
	<script src="../assets/js/bootstrap-alert.js"></script>
	<script src="../assets/js/bootstrap-modal.js"></script>
	<script src="../assets/js/bootstrap-dropdown.js"></script>
	<script src="../assets/js/bootstrap-scrollspy.js"></script>
	<script src="../assets/js/bootstrap-tab.js"></script>
	<script src="../assets/js/bootstrap-tooltip.js"></script>
	<script src="../assets/js/bootstrap-popover.js"></script>
	<script src="../assets/js/bootstrap-button.js"></script>
	<script src="../assets/js/bootstrap-collapse.js"></script>
	<script src="../assets/js/bootstrap-carousel.js"></script>
	<script src="../assets/js/bootstrap-typeahead.js"></script>

</body>
</html>
