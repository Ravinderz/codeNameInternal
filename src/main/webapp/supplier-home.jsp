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

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="css/form-elements-style.css">
<link rel="stylesheet" href="css/form-style.css">
<link rel="stylesheet" href="css/home.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<script src="js/jquery.min.js" type="text/javascript"></script>
<!-- <script src="http://code.jquery.com/jquery-1.10.0.min.js" type="text/javascript"></script> -->
<script type="text/javascript">

	$(document).ready(function() {
						$('body').hide();
						var user = sessionStorage.getItem("user");
						user = $.parseJSON(user);
						console.log(user);
						if(user.role === "supplier"){
						$('body').show();	
						$("#user-name").text(user.firstname+" "+user.lastname);
						}else if(user.role === "contractor"){
							$(location).attr('href','unauthorized.html');
						}
						var quotes = [];
						var requirements = [];
						
						
						$.ajax({
							url:"contractor/getmappedrequirements/"+user.userId,
							type:"get",
							data:{},
							success:function(response){
								var reqList="";
								response = $.parseJSON(response);
								$.each(response,function(index,value){
									requirements[value.requirementId] = value;
									reqList += '<button type="button" class="btn btn-block btn-lg btn-warning" id='+value.requirementId+'>'+value.title+'</button>';
								});
								$('#requirement-list').append(reqList);
							}
						});
						
					
						$(document).on("click","#btn-quote",function() {
									var reqId = $(this).val();
									console.log($(this).val());
									$(location).attr('href','quote-form.html?reqId=' + reqId);
								});

						$("#add-machine").click(function() {
									$(location).attr('href','addEquipment.html')
								});
						$("#view-machine").click(function() {
							$(location).attr('href','viewAllEquipments.jsp')
						});

						/* $(document).delegate( "#panelReq button[type='button']", "click",
						 function(e){
						 $(location).attr('href','contractorRequirement.html');
						 }); */

						$(document).delegate("#requirement-list button[type='button']","click",
										function(e) {
											var quotePanelContent;
											var inputId = this.id;
											console.log(inputId);
											$('#content').html("");

											var reqPanelContent = '<div class="panel-group" id=panelReq><div class="panel panel-default"><div class="panel-heading">'
													+ '<a data-toggle="collapse" data-parent="#panelReq" href="#reqPanelCollapseId"><div class = "panel-heading-content">'
													+ '<h4 class="panel-title">requirement '
													+ requirements[inputId].requirementId
													+ '</h4></div></a></div><div id="reqPanelCollapseId" class="panel-collapse collapse in">'
													+ '<div class="panel-body">'
													+ requirements[inputId].title
													+ '<button class="btn btn-warning pull-right" id="btn-quote" value='+requirements[inputId].requirementId+'>Quote</button></div>'
													+ '</div></div></div>';

											var html = reqPanelContent;
											$('#content').append(html);


										});

					});
</script>
</head>
<body>
	<div class="container-fluid">
		<%@include file="header.html"%>
		<div class="landing-main-content">
			<div class="row">
				<div class="col-md-4">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab5" data-toggle="tab">Home</a>
						</li>
						<li style="cursor: pointer"><a id="add-machine">Add
								Machine</a></li>
						<li style="cursor: pointer"><a id="view-machine">View Machines</a></li>		
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tab5">
							<ul id="requirement-list">
								<!--We will have the requirement list dynamically added here -->
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div id="content">
						<!-- For the quotations collapse feature -->
					</div>
				</div>
			</div>
		</div>
		<%@include file="footer.html"%>
	</div>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/retina-1.1.0.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>