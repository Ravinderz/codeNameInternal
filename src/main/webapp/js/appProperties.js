var AppData = {
			/*relativeUrl: 'http://139.59.6.85:8080/congun/'*/
			relativeUrl: "http://localhost:8070/congun/"
		};

$(document).ready(function(){
	
	
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

