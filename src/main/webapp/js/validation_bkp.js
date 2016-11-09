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

var nameRegex = /^[a-zA-Z]+$/;
var emailRegex = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
var mobileRegex = /^[0-9]\d{10}$/;

$('#firstName').focusout(function(e){
	console.log("inside focus out method")
if($('#firstName').val() !== 'undefined' ){
	console.log("inside firstname if",$('#firstName').val());
	if(!($('#firstName').val().match(nameRegex))){
		//registerform_submit = false;
		$('#firstName').parents('.form-group').addClass('has-error has-feedback');
		if($('#firstName').next('span').length == 0){
			$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($('#firstName'));
			}
			if($('#firstName').next('span').next('span').length == 0){
			$("<span class='error-message' style='color:red'>only Alphabets are allowed</span>").insertAfter($('#firstName').next("span"));
		}
	}
}

});


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
