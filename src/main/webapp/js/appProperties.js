var baseUrl = window.location.host;
if(baseUrl.indexOf("localhost") !== -1){
	
	baseUrl = baseUrl + '/congun';
	console.log(baseUrl);
}
var AppData = {
			/*relativeUrl: 'http://'+baseUrl+'/congun/',
			imageUrl:'http://'+baseUrl+'/congun/congun/uploadedimages/'*/
			relativeUrl: 'http://'+baseUrl+'/',
			imageUrl:'http://'+baseUrl+'/congun/uploadedimages/'
		};


$(document).ready(function(){
	
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

