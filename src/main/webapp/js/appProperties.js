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
	
	var colors = ["#1abc9c", "#16a085", "#f1c40f", "#f39c12", "#2ecc71", "#27ae60", "#e67e22", "#d35400", "#3498db", "#2980b9", "#e74c3c", "#c0392b", "#9b59b6", "#8e44ad", "#bdc3c7", "#34495e", "#2c3e50", "#95a5a6", "#7f8c8d", "#ec87bf", "#d870ad", "#f69785", "#9ba37e", "#b49255", "#b49255", "#a94136"];
    var finalColor;
	
	$.getAvatar = function(user){
        
		var fName = user.firstname;
		var lName = user.lastname;
		var initial = fName.charAt(0)+lName.charAt(0);
		
		var colorIndex = Math.floor(Math.random() * (colors.length-1));
        finalColor = colors[colorIndex]
		
		$('#profile-avatar').html(initial);
		$('#profile-avatar').css({"background-color":finalColor,"display":"inline-block","width":"50px","height":"50px","border-radius":"50%","font-family":"Roboto,sans-serif","font-weight":"400","font-size":"20px","color":"#fff","text-align":"center","line-height":"50px"});
	}
	
});

