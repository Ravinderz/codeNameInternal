<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view equipment</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</head>


        <link rel="stylesheet" href="css/home.css">
 <script src="js/jquery.min.js"></script>

<script>
$(document).ready(function(){
	
		var success = "successfully created";
		var globalVar = "";	
		//var data = JSON.stringify($("#viewEquipment-form").serializeObject());
		$.ajax({
			url:"supplier/getallequipments/2",
			type:"GET",
		
			data: {},success: function (response) {
			 var reqTable = '';
			 response = $.parseJSON(response)
			 globalVar = response;
		 	   $.each(response, function(index, value) {
		 		   
		 		 
		 		 reqTable += '<div > <table><tbody><tr><td>equipmentId</td><td>'+value.equipmentId+'</td></tr>'+
		 		 								   '<tr><td>equipmentCategory</td> <td>'+value.equipmentCategory+'</td></tr>'+
		 		 								   '<tr><td>equipment</td><td>'+value.equipment+'</td></tr>'+
		 		 								   '<tr><td>manufacturer</td><td>'+value.manufacturer+'</td></tr>'+
		 		 								   '<tr><td>yearOfManufacturing</td><td>'+value.yearOfManufacturing+'</td></tr>'+
		 		 								   '<tr><td>model</td><td>'+value.model+'</td></tr>'+
		 		 								   '<tr><td>capacity</td><td>'+value.capacity+'</td></tr>'+
		 		 								   '<tr><td>quantity</td><td>'+value.quantity+'</td></tr>'+
		 		 								   '<tr style="height:10px;"></tr>'+
		 		 								   '<tr><td><input type = "button" id="row_'+index+'" value ="UPDATE" /></td>'+
		 		 								   	   '<td><input type = "button" id="row1_'+index+'" value ="DELETE" /></td></tr>'+
		 		 							'</tbody></table></div><hr></br>';
		 	   });
		        
		 	 //  $('#containerId').append(reqTable);
		 	  $('#td1').append(reqTable);
			}
		    
});

			
			  
$('#td1').on('click','input',function(e){
    console.log("jhvjgvjbjhjhjb");
    var butnId = (e.target.id).split('_');

    console.log("GLOBAL : ",globalVar[butnId[1]]);
    console.log(globalVar[butnId[1]]);
	 window.location='updateEquipment.jsp?equipmentId='+encodeURIComponent(globalVar[butnId[1]].equipmentId)+
	    '&supplierId='+encodeURIComponent(globalVar[butnId[1]].supplierId)+
	    '&supplierName='+encodeURIComponent(globalVar[butnId[1]].supplierName)+
	    '&equipmentCategory='+encodeURIComponent(globalVar[butnId[1]].equipmentCategory)+
	   '&equipment='+encodeURIComponent(globalVar[butnId[1]].equipment)+
	     '&manufacturer='+encodeURIComponent(globalVar[butnId[1]].manufacturer)+
	         '&yearOfManufacturing='+encodeURIComponent(globalVar[butnId[1]].yearOfManufacturing)+
	 		'&model='+unescape(encodeURIComponent(globalVar[butnId[1]].model))+
	 		'&capacity='+encodeURIComponent(globalVar[butnId[1]].capacity)+
	 		'&quantity='+encodeURIComponent(globalVar[butnId[1]].quantity);
});
});
</script>




<style>
input[type=button], input[type=submit], input[type=reset] {
 background-color: steelblue;
 border: none;
 color: white;
 padding: 5px 5px;
 text-decoration: none;
 margin: 4px 2px;
 cursor: pointer;

}

td
{
 padding:0 50px 0 50px;

}
hr {
  
  background: grey; 
  height: 2px;
}

.toppad
{margin-top:20px;
}
</style>

<body style="padding-top: 10px">

<div class=landing-main-content>
<div class="container" id="containerId">
<div class="header"><%@include file="header.html" %></div>
<tab1e id="td1"></table>

</div>
</div>


	</body>
	</html>