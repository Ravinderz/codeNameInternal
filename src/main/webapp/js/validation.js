validateOnClick = function($this) {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
	var form_submit = false;
	
	var form_group = $this.parents('form');
	form_group.find('input[type="text"], input[type="password"], textarea').each(function() {
		if( $(this).val() == "" ) {
		form_submit = false;
			$(this).parents('.form-group').addClass('has-error has-feedback');
			if($(this).next('span').length == 0){
			$("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter($(this));
			}
			if($(this).next('span').next('span').length == 0){
			$("<span class='error-message' style='color:red'>Field is required</span>").insertAfter($(this).next("span"));
			}
		}
		else {
			form_submit = true;
			$(this).addClass('has-success').removeClass('has-error');
			$(this).next('span').addClass('glyphicon-ok').removeClass('glyphicon-remove');
			$(this).next('.error-message').remove();
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
