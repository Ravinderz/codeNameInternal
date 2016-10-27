var AppData = {
		/*relativeUrl: 'http://www.mydailydriver.in/'*/
			/*relativeUrl: 'http://139.59.6.85:8080/congun/'*/
			relativeUrl: "http://localhost:8070/congun/",
			imageUrl:"http://localhost:8070/congun/uploadedimages/"
			/*relativeUrl: "http://localhost:8080/congun/",
			imageUrl:"http://localhost:8080/congun/uploadedimages/"*/
		};

$(document).ready(function(){
	
	$('[data-toggle="tooltip"]').tooltip()
	
	$('#enquire-form').hide();
	var display = false;
	$('.fa-minus').click(function(e){
		e.preventDefault();
		if(!display){
		$('#enquire-form').show("slow");
		display=true;
		}else{
			$('#enquire-form').hide("slow");
			display=false;
		}
	});
	
	
	$.getUser = function(){
		var user = localStorage.getItem("user");
		user = $.parseJSON(user);
		return user;
	};
	
	$.setUser = function(user){
		var user = localStorage.setItem("user",user);
	};
	
	if($.getUser() !== null){
	
	$.ajaxSetup({
		    beforeSend: function(xhr) {
		        xhr.setRequestHeader('token', $.getUser().token);
		    }
		});
	
	}else{
		$.ajaxSetup({
		    beforeSend: function(xhr) {
		        xhr.setRequestHeader('token', "");
		    }
		});
	}
	
	
	
});

