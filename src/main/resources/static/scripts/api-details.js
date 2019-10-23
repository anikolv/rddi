$( document ).ready(function() {
   $( ".nav-link" ).click(function() {
	   $('.nav-link').each(function(i, obj) {
		   $(this).removeClass('active');
		});
	   
	   $(this).addClass('active');
	   
	   if ($(this).hasClass('play-navbar')) {
		  $('.content-panel').hide();
		  $('.play-panel').show();
	   }
	   
	   if ($(this).hasClass('info-navbar')) {
			  $('.content-panel').hide();
			  $('.info-panel').show();
		}
	   
	   if ($(this).hasClass('integrate-navbar')) {
			  $('.content-panel').hide();
			  $('.integrate-panel').show();
		}
	   
	   if ($(this).hasClass('inspect-navbar')) {
			  $('.content-panel').hide();
			  $('.inspect-panel').show();
			  
			   $.get( $("#contract-url").val(), function( data ) {
				   var editor = ace.edit("editor");
				   editor.setTheme("ace/theme/monokai");
				   editor.session.setMode("ace/mode/json");
				   editor.setValue(JSON.stringify(data, null, '\t'));
				   editor.setReadOnly(true);
				 });
		}
	   
	   if ($(this).hasClass('discuss-navbar')) {
			  $('.content-panel').hide();
			  $('.discuss-panel').show();
		}
	 });
});