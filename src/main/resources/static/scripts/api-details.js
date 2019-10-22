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
	 });
});