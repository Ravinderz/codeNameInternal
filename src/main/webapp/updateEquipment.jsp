<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Add Equipment</title>
	<meta name="description" content="Contractor Requirement">
    <meta name="author" content="congun">
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
	
	<script type="text/javascript">
$(document).ready(function(){
	
	$("#equipmentId").val("<%=request.getParameter("equipmentId")%>");
	$("#equipmentCategory").val("<%=request.getParameter("equipmentCategory")%>");
	$("#equipment").val("<%=request.getParameter("equipment")%>");
	$("#manufacturer").val("<%=request.getParameter("manufacturer")%>");
	$("#yearOfManufacturing").val("<%=request.getParameter("yearOfManufacturing")%>");
	$("#model").val("<%=request.getParameter("model")%>");
	$("#capacity").val("<%=request.getParameter("capacity")%>");
	$("#quantity").val("<%=request.getParameter("quantity")%>");
	
	
	<!-- To Add equipment details into DB-->
	
	$("#addEquipment").click(function(){
		var success = "successfully created";
		var data = JSON.stringify($("#addEquipment-form").serializeObject());
		console.log(data);
		$.ajax({
			url:"supplier/addequipment",
		     type:"post",
		     contentType:"application/json; charset=utf-8",
		     data: data,
		     success:function(data){
		    	console.log(data);
		      }
		});
	});
});
</script>

<script type="text/javascript">
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
</script>


</head>
<body>

    <div class="container-fluid">
			<h3 class="text-left">
				UPDATE EQUIPMENT FORM
			</h3>
					<form role="form" id="addEquipment-form">
					<div class="form-group">
                 <input type="hidden" id="equipmentId" name="equipmentId">	
				 </div>
				<div class="form-group">
				<label for="selectCategory">
						Equipment Category
					</label>
                 <input type="text" id="equipmentCategory" name="equipmentCategory">	
				 </div>
				 
				<div class="form-group">
				<label for="equipment">
						Equipment Name
					</label>
                 <input type="text" id="equipment" name="equipment">	
				 </div>
				 
				<div class="form-group">
				<label for="manufacturer">
						Equipment Manufacturer
					</label>
                 <input type="text" id="manufacturer" name="manufacturer">	
				 </div>
				 
				 <div class="form-group">
				<label for="yearOfManufacturing">
						Year Of Manufacturing
					</label>
                 <input type="text" id="yearOfManufacturing" name="yearOfManufacturing">	
				 </div>
				 
				 <div class="form-group">
				<label for="model">
						Model Details
					</label>
                 <input type="text" id="model" name="model">	
				 </div>
				 
				 <div class="form-group">
				<label for="capacity">
						Capacity Details
					</label>
                 <input type="text" id="capacity" name="capacity">	
				 </div>
				 
				 <div class="form-group">
				<label for="quantity">
						Equipment Quantity
					</label>
                 <input type="text" id="quantity" name="quantity">	
				 </div>
				 
				 <button type="button" class="btn btn-default" id="addEquipment">
					Update
				</button>
					</form>
		</div>
</body>
</html>