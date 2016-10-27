validateOnClick = function($this) {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
	var form_submit = false;
	
	var form_group = $this.parents('form');
	form_group.find('input[type="text"], input[type="password"], textarea').each(function() {
		if( $(this).val() == "") {
			form_submit = false;
			$(this).parents('.form-group').addClass('has-error has-feedback');
			if($(this).next('span').length == 0){
			$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($(this));
			}
			if($(this).next('span').next('span').length == 0){
			$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($(this).next("span"));
			}
		}else {
			form_submit = true;
			$(this).addClass('has-success').removeClass('has-error');
			$(this).next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
			$(this).next('.error-message').remove();
		}
});//end of each loop
	
/*form_group.find('input[type="radio"]').each(function() {	
if($('input[type="radio"]').is(':checked')){
	form_submit = true;
	console.log("in the if block form submit true");
	$('input[type="radio"]').parents('.form-group').addClass('has-success').removeClass('has-error');
	$('input[type="radio"]').parents('.form-group').next('span').remove();
	break;
}else{
	form_submit = false;
	console.log("in the else block");
	$('input[type="radio"]').parents('.form-group').addClass('has-error has-feedback');
	if($('input[type="radio"]').parents('.form-group').next('span').length == 0){
	$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($('input[type="radio"]').parents('.form-group'));
	}
}	
	});//end of each loop	
	
*/	
	
	form_group.find('input[type="radio"]').each(function() {	
		if($(this).is(':checked')){
			form_submit = true;
			console.log("in the if block form submit true");
			$(this).parents('.form-group').addClass('has-success').removeClass('has-error');
			$(this).parents('.form-group').next('span').remove();
			return false;
		}else{
			form_submit = false;
			console.log("in the else block");
			$(this).parents('.form-group').addClass('has-error has-feedback');
			if($(this).parents('.form-group').next('span').length == 0){
			$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($(this).parents('.form-group'));
			}
		}	
			});//end of each loop	
	
	
return form_submit;	
	
};//function ends

validateOnFocusout = function($this){
	var inputs = [];
	var form_submit = false;
	var form_group = $this;
	var count = 0;
	form_group.find('input[type="text"], input[type="password"], textarea').each(function() {
		inputs[count] = $(this);
		count++;
	});
	
	$('input[type=radio]').change(function() {
		form_submit = true;
		$(this).parents('.form-group').addClass('has-success').removeClass('has-error');
		$(this).parents('.form-group').next('span').remove();
    });
	
	$.each(inputs,function(index,value){
	value.focusout(function(){
		if(value.val() == ""){
			form_submit = false;
				value.parents('.form-group').addClass('has-error has-feedback');
    			if(value.next('span').length == 0){
	    			$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(value);
	    			}
	    			if(value.next('span').next('span').length == 0){
	    			$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter(value.next("span"));
	    			}
    		}
    		else {
    			form_submit = true;
    			value.parents('.form-group').addClass('has-success').removeClass('has-error');
    			value.addClass('has-success').removeClass('has-error');
    			value.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			value.next('span').next('span').remove();
    		}
	});
	});
	return form_submit;
};

//param : this method takes form object as the parameter.
validateReset = function($this){
	var inputs = [];
	var input_fields = $this;
	var count = 0;
	input_fields.find('input[type="text"], input[type="password"], textarea').each(function() {
		inputs[count] = $(this);
		count++;
	});
	
	$.each(inputs,function(index,value){
		value.parents('.form-group').removeClass('has-error has-feedback has-success');
	    value.next('span').next('span').remove();
	    value.next('span').remove();
	});
};



$(document).ready(function(){

	var nameRegex = /^[a-zA-Z]+$/;
	var emailRegex = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var mobileRegex = /^[0-9]{10}$/;
	var passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,}$/ //must have 8 characters,with an alphabet and a letter
	
	
	$('#firstName').focusout(function(){
		var $firstName = $('#firstName');
		if($('#firstName').val() == ""){
			registerform_submit = false;
			$firstName.parents('.form-group').addClass('has-error has-feedback');
    		if($firstName.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($firstName);
	    	}
	    	if($firstName.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($firstName.next("span"));
	    	}
		}else if($('#firstName').val() !== "undefined"){
			if(!($('#firstName').val().match(nameRegex))){
				registerform_submit = false;
				$firstName.parents('.form-group').addClass('has-error has-feedback');
				if($firstName.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($firstName);
				}
				if($firstName.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Only Alphabets are allowed</span>").insertAfter($firstName.next("span"));
				}
			}else{
				registerform_submit = true;
    			$firstName.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$firstName.addClass('has-success').removeClass('has-error');
    			$firstName.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$firstName.next('span').next('span').remove();
			}
		}
	});
	
	$('#lastName').focusout(function(){
		var $lastName = $('#lastName');
		if($('#lastName').val() == ""){
			registerform_submit = false;
			$lastName.parents('.form-group').addClass('has-error has-feedback');
    		if($lastName.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($lastName);
	    	}
	    	if($lastName.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($lastName.next("span"));
	    	}
		}else if($('#lastName').val() !== "undefined"){
			if(!($('#lastName').val().match(nameRegex))){
				registerform_submit = false;
				$lastName.parents('.form-group').addClass('has-error has-feedback');
				if($lastName.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($lastName);
				}
				if($lastName.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Only Alphabets are allowed</span>").insertAfter($lastName.next("span"));
				}
			}else{
				registerform_submit = true;
    			$lastName.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$lastName.addClass('has-success').removeClass('has-error');
    			$lastName.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$lastName.next('span').next('span').remove();
			}
		}
	});
	
	$('#register-username').focusout(function(){
		var $username = $('#register-username');
		if($('#register-username').val() == ""){
			registerform_submit = false;
			$username.parents('.form-group').addClass('has-error has-feedback');
    		if($username.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($username);
	    	}
	    	if($username.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($username.next("span"));
	    	}
		}else if($('#register-username').val() !== "undefined"){
			if(!($('#register-username').val().match(emailRegex))){
				registerform_submit = false;
				$username.parents('.form-group').addClass('has-error has-feedback');
				if($username.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($username);
				}
				if($username.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Please enter a valid username</span>").insertAfter($username.next("span"));
				}
			}else{
				registerform_submit = true;
    			$username.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$username.addClass('has-success').removeClass('has-error');
    			$username.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$username.next('span').next('span').remove();
			}
		}
	});
	
	
	
	$('#register-password').focusout(function(){
		var $password = $('#register-password');
		if($('#register-password').val() == ""){
			registerform_submit = false;
			$password.parents('.form-group').addClass('has-error has-feedback');
    		if($password.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($password);
	    	}
	    	if($password.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($password.next("span"));
	    	}
		}else if($('#register-password').val() !== "undefined"){
			if(!($('#register-password').val().match(passwordRegex))){
				registerform_submit = false;
				$password.parents('.form-group').addClass('has-error has-feedback');
				if($password.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($password);
				}
				if($password.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Password should be atleast 8 characters long with 1 letter and 1 alphabet</span>").insertAfter($password.next("span"));
				}
			}else{
				registerform_submit = true;
    			$password.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$password.addClass('has-success').removeClass('has-error');
    			$password.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$password.next('span').next('span').remove();
			}
		}
	});
	
	$('#mobile').focusout(function(){
		var $mobile = $('#mobile');
		if($('#mobile').val() == ""){
			registerform_submit = false;
			$mobile.parents('.form-group').addClass('has-error has-feedback');
    		if($mobile.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($mobile);
	    	}
	    	if($mobile.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($mobile.next("span"));
	    	}
		}else if($('#mobile').val() !== "undefined"){
			if(!($('#mobile').val().match(mobileRegex))){
				registerform_submit = false;
				$mobile.parents('.form-group').addClass('has-error has-feedback');
				if($mobile.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($mobile);
				}
				if($mobile.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Please enter a valid mobile number</span>").insertAfter($mobile.next("span"));
				}
			}else{
				registerform_submit = true;
    			$mobile.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$mobile.addClass('has-success').removeClass('has-error');
    			$mobile.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$mobile.next('span').next('span').remove();
			}
		}
	});
	
	
	
	$.validateRegisterForm = function(registerForm) {
		var submit_form = false;
		var count = 0;
		
		var $firstName = $('#firstName');
		if($('#firstName').val() == ""){
			submit_form = false;
			count++;
			$firstName.parents('.form-group').addClass('has-error has-feedback');
    		if($firstName.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($firstName);
	    	}
	    	if($firstName.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($firstName.next("span"));
	    	}
		}else if($('#firstName').val() !== "undefined"){
			if(!($('#firstName').val().match(nameRegex))){
				submit_form = false;
				count++;
				$firstName.parents('.form-group').addClass('has-error has-feedback');
				if($firstName.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($firstName);
				}
				if($firstName.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Only Alphabets are allowed</span>").insertAfter($firstName.next("span"));
				}
			}else{
				submit_form = true;
    			$firstName.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$firstName.addClass('has-success').removeClass('has-error');
    			$firstName.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$firstName.next('span').next('span').remove();
			}
		}
		
		var $lastName = $('#lastName');
		if($('#lastName').val() == ""){
			submit_form = false;
			count++;
			$lastName.parents('.form-group').addClass('has-error has-feedback');
    		if($lastName.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($lastName);
	    	}
	    	if($lastName.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($lastName.next("span"));
	    	}
		}else if($('#lastName').val() !== "undefined"){
			if(!($('#lastName').val().match(nameRegex))){
				submit_form = false;
				count++;
				$lastName.parents('.form-group').addClass('has-error has-feedback');
				if($lastName.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($lastName);
				}
				if($lastName.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Only Alphabets are allowed</span>").insertAfter($lastName.next("span"));
				}
			}else{
				submit_form = true;
    			$lastName.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$lastName.addClass('has-success').removeClass('has-error');
    			$lastName.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$lastName.next('span').next('span').remove();
			}
		}
		
		var $username = $('#register-username');
		if($('#register-username').val() == ""){
			submit_form = false;
			count++;
			$username.parents('.form-group').addClass('has-error has-feedback');
    		if($username.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($username);
	    	}
	    	if($username.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($username.next("span"));
	    	}
		}else if($('#register-username').val() !== "undefined"){
			if(!($('#register-username').val().match(emailRegex))){
				submit_form = false;
				count++;
				$username.parents('.form-group').addClass('has-error has-feedback');
				if($username.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($username);
				}
				if($username.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Please enter a valid username</span>").insertAfter($username.next("span"));
				}
			}else{
				submit_form = true;
    			$username.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$username.addClass('has-success').removeClass('has-error');
    			$username.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$username.next('span').next('span').remove();
			}
		}
		
		var $password = $('#register-password');
		if($('#register-password').val() == ""){
			submit_form = false;
			count++;
			$password.parents('.form-group').addClass('has-error has-feedback');
    		if($password.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($password);
	    	}
	    	if($password.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($password.next("span"));
	    	}
		}else if($('#register-password').val() !== "undefined"){
			if(!($('#register-password').val().match(passwordRegex))){
				submit_form = false;
				count++;
				$password.parents('.form-group').addClass('has-error has-feedback');
				if($password.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($password);
				}
				if($password.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Please enter a valid username</span>").insertAfter($password.next("span"));
				}
			}else{
				submit_form = true;
    			$password.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$password.addClass('has-success').removeClass('has-error');
    			$password.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$password.next('span').next('span').remove();
			}
		}
		
		var $mobile = $('#mobile');
		if($('#mobile').val() == ""){
			submit_form = false;
			count++;
			$mobile.parents('.form-group').addClass('has-error has-feedback');
    		if($mobile.next('span').length == 0){
	    		$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($mobile);
	    	}
	    	if($mobile.next('span').next('span').length == 0){
	    		$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($mobile.next("span"));
	    	}
		}else if($('#mobile').val() !== "undefined"){
			if(!($('#mobile').val().match(mobileRegex))){
				submit_form = false;
				count++;
				$mobile.parents('.form-group').addClass('has-error has-feedback');
				if($mobile.next('span').length == 0){
					$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($mobile);
				}
				if($mobile.next('span').next('span').length == 0){
					$("<span class='error-message' style='color:red'>Please enter a valid mobile number</span>").insertAfter($mobile.next("span"));
				}
			}else{
				submit_form = true;
    			$mobile.parents('.form-group').addClass('has-success').removeClass('has-error');
    			$mobile.addClass('has-success').removeClass('has-error');
    			$mobile.next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
    			$mobile.next('span').next('span').remove();
			}
		}
	 
	 var radioCount = 0;
	
	$('#register-form').find('input[type="radio"]').each(function() {	
		if($(this).is(':checked')){
			form_submit = true;
			console.log("in the if block form submit true");
			$(this).parents('.form-group').addClass('has-success').removeClass('has-error');
			$(this).parents('.form-group').next('span').remove();
		}else{
			form_submit = false;
			radioCount++;
			console.log("in the else block");
			$(this).parents('.form-group').addClass('has-error has-feedback');
			if($(this).parents('.form-group').next('span').length == 0){
			$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($(this).parents('.form-group'));
			}
		}	
			});
		
			
			console.log(count);
		if((count > 0  && radioCount == 2) || (count == 0 && radioCount == 2)){
			submit_form = false;
		}else{
			submit_form = true;
		}
		return submit_form;
	};
	
});
