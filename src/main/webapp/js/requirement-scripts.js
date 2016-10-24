
function scroll_to_class(element_class, removed_height) {
	var scroll_to = $(element_class).offset().top - removed_height;
	if($(window).scrollTop() != scroll_to) {
		$('html, body').stop().animate({scrollTop: scroll_to}, 0);
	}
}

function bar_progress(progress_line_object, direction) {
	var number_of_steps = progress_line_object.data('number-of-steps');
	var now_value = progress_line_object.data('now-value');
	var new_value = 0;
	if(direction == 'right') {
		new_value = now_value + ( 100 / number_of_steps );
	}
	else if(direction == 'left') {
		new_value = now_value - ( 100 / number_of_steps );
	}
	progress_line_object.attr('style', 'width: ' + new_value + '%;').data('now-value', new_value);
}

jQuery(document).ready(function() {
	
    
    /*
        Form
    */
    $('.f1 fieldset:first').fadeIn('slow');
    
    $('.f1 input[type="text"], .f1 input[type="password"], .f1 textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    // next step
    $('.f1 .btn-next').on('click', function() {
	    var i=0;
    	var parent_fieldset = $(this).parents('fieldset');
    	var next_step = true;
    	// navigation steps / progress steps
    	var current_active_step = $(this).parents('.f1').find('.f1-step.active');
    	var progress_line = $(this).parents('.f1').find('.f1-progress-line');
    	console.log("Entered NEXT BUtton");
    	// fields validation
    	parent_fieldset.find('input[type="text"], input[type="password"], textarea').each(function() {
    		if( $(this).val() == "" ) {
    			$(this).addClass('input-error');
    			next_step = false;
				console.log("Empty Elements: "+document.getElementsByTagName("input")[i].id);
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
			i++;
    	});
    	// fields validation
    	
    	if( next_step ) {
    		parent_fieldset.fadeOut(400, function() {
    			// change icons
    			current_active_step.removeClass('active').addClass('activated').next().addClass('active');
    			// progress bar
    			bar_progress(progress_line, 'right');
    			// show next step
	    		$(this).next().fadeIn();
	    		// scroll window to beginning of the form
    			scroll_to_class( $('.f1'), 20 );
	    	});
    	}
    	
    });
    
    // previous step
    $('.f1 .btn-previous').on('click', function() {
    	// navigation steps / progress steps
    	var current_active_step = $(this).parents('.f1').find('.f1-step.active');
    	var progress_line = $(this).parents('.f1').find('.f1-progress-line');
    	
    	$(this).parents('fieldset').fadeOut(400, function() {
    		// change icons
    		current_active_step.removeClass('active').prev().removeClass('activated').addClass('active');
    		// progress bar
    		bar_progress(progress_line, 'left');
    		// show previous step
    		$(this).prev().fadeIn();
    		// scroll window to beginning of the form
			scroll_to_class( $('.f1'), 20 );
    	});
    });
    
    // submit
    $('.f1 #btn-submit').on('click', function(e) {
	var i=0;
	console.log("Entered JS script of submit from requirement form");
	var parent_fieldset = $(this).parents('fieldset');
    	var valid_form = true;
    	// fields validation
    	parent_fieldset.find('input[type="text"], input[type="password"], textarea').each(function() {
    		if( $(this).val() == "" ) {
    			$(this).addClass('input-error');
				e.preventDefault();
				valid_form = false;
				console.log("Empty Elements: "+document.getElementsByTagName("input")[i].id);
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
			i++;
    	});
    	// fields validation
		if(valid_form){
		
		var success = "successfully created";
		var data = JSON.stringify($("#contractor-requirement-form").serializeObject());
		//console.log(data);
		data= $.parseJSON(data);
		var manufacturer = [];
		if($.isArray(data.manufacturer)){
		console.log("It is an Array");
		}else{
		console.log("doesnt contain braces");
		manufacturer.push(data.manufacturer);
		data.manufacturer = manufacturer;
		console.log(data);
		}
		data = JSON.stringify(data);
		console.log(data);
		
            
            $.ajax({
			url:AppData.relativeUrl+"contractor/contractorRequirement",
		     type:"post",
		     contentType:"application/json; charset=utf-8",
		     data: data,
		     success:function(data){
		    	 console.log(data);
		    	 if(data === "CS01"){
		    		 console.log("inside if");
		    		 $('#modal-success-dialog').modal('show').delay(50000);
		    		 $('#modal-success-dialog').on('shown.bs.modal', function() {
		    			 $(location).attr('href',AppData.relativeUrl+"user/contractor-dashboard.html");
		    			 //history.back();	    
		    			});
		    	 }
		      }

                });
				}else{
				alert("Please enter all highlighted fields!!!");
				}
    });
    
		$("#addEquipment-left").click(function(e){
	console.log("from left JS");
	var parent_fieldset = $(this).parents('fieldset');
    	var valid_form = true;
    	// fields validation
    	parent_fieldset.find('input[type="text"], input[type="password"], textarea').each(function() {
    		if( $(this).val() == "") {
    			$(this).addClass('input-error');
				console.log(this.id);
				//e.preventDefault();
				valid_form = false;
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
		
		if(valid_form){
		var success = "successfully created";
		var data = JSON.stringify($("#addEquipment-form").serializeObject());
		console.log(data);
		
		$.ajax({
			url: AppData.relativeUrl+"supplier/addequipment",
		     type:"post",
		     contentType:"application/json; charset=utf-8",
		     data: data,
		     success:function(data){
		    	console.log(data);
		    	if(data === "SS01"){
				 $('#modal-success-dialog').modal('show').delay(50000);
		    		 $('#modal-success-dialog').on('shown.bs.modal', function() {
		    		$(location).attr('href',"user/supplier-profile.html");
					});
		    	}
		      }
		});
		}else{
				alert("Please enter all highlighted fields!!!");
				}
	});
	
		$("#addEquipment-right").click(function(e){
	console.log("from right JS");
	var parent_fieldset = $(this).parents('fieldset');
    	var valid_form = true;
    	// fields validation
    	parent_fieldset.find('input[type="text"], input[type="password"], textarea').each(function() {
    		if( $(this).val() == "" && this.id != "model-right") {
    			$(this).addClass('input-error');
				//e.preventDefault();
				console.log(this.id);
				valid_form = false;
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
		
		if(valid_form){
		var success = "successfully created";
		var data = JSON.stringify($("#addEquipment-form-right").serializeObject());
		console.log(data);
		
		$.ajax({
			url: AppData.relativeUrl+"supplier/addequipment",
		     type:"post",
		     contentType:"application/json; charset=utf-8",
		     data: data,
		     success:function(data){
		    	console.log(data);
		    	if(data === "SS01"){
				 $('#modal-success-dialog').modal('show').delay(50000);
		    		 $('#modal-success-dialog').on('shown.bs.modal', function() {
		    		$(location).attr('href',"user/supplier-profile.html");
					});
		    	}
		      }
		});
		}else{
				alert("Please enter all highlighted fields!!!");
				}
	});
	
	 // submit
    $('#btn-quote').on('click', function() {
	console.log("Entered JS script of submit from quote form");
	var parent_fieldset = $(this).parents('fieldset');
	 //console.log(parent_fieldset);
    	var valid_form = true;
    	// fields validation
		//console.log(parent_fieldset.find('input[type=text]'));
    	parent_fieldset.find('input[type=text]').each(function() {
    		if( $(this).val() == "" ) {
    			$(this).addClass('input-error');
				//console.log("Empty elements : "+$(this));
				valid_form = false;
				
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
			});
    	// fields validation
		if(valid_form){
		
		var success = "successfully created";
		var data = JSON.stringify($("#supplier-quote-form").serializeObject());
		console.log(data);
            
		data= $.parseJSON(data);
		var manufacturer = [];
		if($.isArray(data.manufacturer)){
		console.log("It is an Array");
		}else{
		console.log("doesnt contain braces");
		manufacturer.push(data.manufacturer);
		data.manufacturer = manufacturer;
		console.log(data);
		}
		data = JSON.stringify(data);
		console.log(data);
		
            $.ajax({
			url:AppData.relativeUrl+"supplier/submitquote",
		     type:"post",
		     contentType:"application/json; charset=utf-8",
		     data: data,
		     success:function(data){
		    	 console.log(data);
		    	 if(data === "SS01"){
		    		 console.log("inside if");
		    		 $('#modal-success-dialog').modal('show').delay(5000);
		    		 $('#modal-success-dialog').on('shown.bs.modal', function() {
		    			 $(location).attr('href',AppData.relativeUrl+"user/supplier-dashboard.html");
		    			 //history.back();	    
		    			});
		    	 }
		      }

                });
				}else{
				alert("Please enter all highlighted fields!!!");
				}
    });
	
	//add Used Machine
	 $('#addUsedMachine').on('click', function(){
	 console.log("Entered Used Machines");
	 
	 	var parent_fieldset = $(this).parents('fieldset');
    	var valid_form = true;
    	// fields validation
    	parent_fieldset.find('input[type="text"], input[type="password"], textarea').each(function() {
    		if( $(this).val() == "") {
    			$(this).addClass('input-error');
				//e.preventDefault();
				console.log(this.id);
				valid_form = false;
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
			});
				if(valid_form){
		var success = "successfully created";
		var data = JSON.stringify($("#addUsedMachine-form").serializeObject());
		console.log(data);
		
		$.ajax({
			url: AppData.relativeUrl+"usedmachine/postusedmachines",
		     type:"post",
		     contentType:"application/json; charset=utf-8",
		     data: data,
		     success:function(data){
		    	console.log(data);
		    	if(data === "SS01"){
				 $('#modal-success-dialog').modal('show').delay(50000);
		    		 $('#modal-success-dialog').on('shown.bs.modal', function() {
		    		$(location).attr('href',"user/myused-equipment.html");
					});
		    	}
		      }
		});
		}else{
				alert("Please enter all highlighted fields!!!");
				}
		
		
	 
	 });
	 
	 $("#upload-image").on("change", function() {
	 console.log("No of images :"+$("#upload-image")[0].files.length)
         if($("#upload-image")[0].files.length > 5) {
                   alert("You can select only 5 images!!!");
				   $("#upload-image").val('');
         } 
		 });
	 
	 //upon click to upload Image
	  $('#upload-direct').on('click', function(){
	  $('#modal-image-upload').modal('show').delay(50000);
	  });
	  
	  //upload file
	  $('#imageuploadform').submit(function(event){
	   var user = localStorage.getItem("user");
	   user = $.parseJSON(user);
	  event.preventDefault();
	  console.log("Entered Image Uploaded method");
	  var formData = new FormData ( this );
	  console.log(formData);
	  $.ajax({
	   url: AppData.relativeUrl+"upload/uploadimage/"+user.userId+"/"+document.getElementById("uploadtype").value,
	   type: "post",
	   data: formData,
	   contentType: false,
	   processData: false,
	   async:true,
	   success: function(data){
	   console.log("success");
	   console.log(data);
	  if(data !== "UF01"){
	               //$('#modal-image-upload').modal('hide').delay(30000);
				   $('#workareaimages').val(data);
				   console.log("Images Uploaded : "+document.getElementById("workareaimages").value)
				   $('#modal-image-upload').modal('hide');
				   $('#modal-upload-success-dialog').modal('show');
				   setTimeout(function() { $('#modal-upload-success-dialog').modal('hide'); }, 5000);
		    	}
	  },
	    error: function(data){
		console.log("error");
		console.log(data);
		}
	  });
	  });
	  
	 // $( "#imageuploadform" ).submit(function( event ) {
  //alert( "Handler for .submit() called." );
   //event.preventDefault();
   //return false;
//});
    
});
