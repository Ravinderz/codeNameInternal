<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>ConGun</title>

<meta name="description" content="Heavy Machinery Rentals">
<meta name="author" content="Congun">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/home.css" rel="stylesheet">
<script src="js/jquery.min.js" type="text/javascript"></script>
<!-- <script src="http://code.jquery.com/jquery-1.10.0.min.js" type="text/javascript"></script> -->
<script type="text/javascript">
$(document).ready(function(){
$.ajax({
	url:"user/reqtest",
	type:"get",
	data:{},
	dataType:"json",
	success:function(response){
		var reqList = "";
		console.log(response);
		$.each(response,function(index, value){
			reqList += '<button type="button" class="btn btn-block btn-lg btn-warning" id='+value.id+'>'+value.description+'<br>No Of Quotes : <span class="badge">'+value.number+'</span></button>';
		/* reqList += '<a href="#requirement-details" id='+value.id+' class="btn btn-lg btn-block btn-warning" type="button">'+value.description+'</a>'; */
		});
		console.log(response);
		console.log(reqList);

		$('#requirement-list').append(reqList);

	}
});

 $(document).delegate( "#requirement-list button[type='button']", "click",
	    function(e){
	    var inputId = this.id;
	    console.log( inputId );
	    }
	);

});
</script>
</head>
<body>
<div class="container-fluid">
<%@include file="header.html" %>
		<div class="main-content"> 		
			<div class="row">
                <div class="col-md-4"> 
                    <ul class="nav nav-tabs"> 
                        <li class="active">
                            <a href="#tab5" data-toggle="tab">Home</a>
                        </li>                         
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab5">
                            <ul id="requirement-list"> 
                            </ul>
                        </div>
                    </div>                     
                </div>
                <div class="col-md-8">
                    <div class="panel-group" id="panels1"> 
                        <div class="panel panel-default"> 
                            <div class="panel-heading">
								<a data-toggle="collapse" data-parent="#panels1" href="#collapse1"><div class = "panel-heading-content">
                                <h4 class="panel-title"> Requirement 1</h4> 
							</div></a> 
							</div>                             
                            <div id="collapse1" class="panel-collapse collapse in"> 
                                <div class="panel-body">This is the requirement details for the requirement 1</div>                                 
                            </div>                             
                        </div>                         
                    </div>
                    <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
					
					<!-- For the quotations collapse feature -->
					<div class="panel-group" id="panels2"> 
                        <div class="panel panel-default"> 
                            <div class="panel-heading">
								<a data-toggle="collapse" data-parent="#panels2" href="#collapse2"><div class = "panel-heading-content">
                                <h4 class="panel-title"> Quote 1</h4> 
							</div></a> 
							</div>                             
                            <div id="collapse2" class="panel-collapse collapse in"> 
                                <div class="panel-body">This is the Quote 1 details for the requirement 1</div>                                 
                            </div>                             
                        </div>                         
                    </div>
					<div class="panel-group" id="panels3"> 
                        <div class="panel panel-default"> 
                            <div class="panel-heading">
								<a data-toggle="collapse" data-parent="#panels3" href="#collapse3"><div class = "panel-heading-content">
                                <h4 class="panel-title"> Quote 2</h4> 
							</div></a> 
							</div>                             
                            <div id="collapse3" class="panel-collapse collapse in"> 
                                <div class="panel-body">This is the Quote 2 details for the requirement 1</div>                                 
                            </div>                             
                        </div>                         
                    </div>
					<div class="panel-group" id="panels4"> 
                        <div class="panel panel-default"> 
                            <div class="panel-heading">
								<a data-toggle="collapse" data-parent="#panels4" href="#collapse4"><div class = "panel-heading-content">
                                <h4 class="panel-title"> Quote 3</h4> 
							</div></a> 
							</div>                             
                            <div id="collapse4" class="panel-collapse collapse in"> 
                                <div class="panel-body">This is the Quote 3 details for the requirement 1</div>                                 
                            </div>                             
                        </div>                         
                    </div>
					<!-- For the quotations collapse feature -->
                </div>
              </div>
         </div>   
<%@include file="footer.html" %>
</div>
<script src="js/bootstrap.min.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>