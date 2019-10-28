$( document ).ready(function() {	 
   $('#rateMe1').mdbRate();
	  
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
	   
	   if ($(this).hasClass('evaluate-navbar')) {
			  $('.content-panel').hide();
			  $('.еvaluate-panel').show();
		}
	   
	   if ($(this).hasClass('integrate-navbar')) {
			  $('.content-panel').hide();
			  $('.integrate-panel').show();
		}
	   
	   if ($(this).hasClass('fork-navbar')) {
			  $('.content-panel').hide();
			  $('.fork-panel').show();
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
	   
	   if ($(this).hasClass('rate-navbar')) {
			  $('.content-panel').hide();
			  $('.rate-panel').show();
		}
	 });
   
   var apiRated = $.cookie('rated-api-' +  Number($("#web-service-id").val()));
   if (apiRated) {
	   $(".api-rating").hide();
	   $(".api-rated").show();
   }
   
   $('#rateMe1').on('click', '.rate-popover',  function() {
	    var webServiceIdValue =  Number($("#web-service-id").val());
	    var ratingValue = Number($(this).attr("data-index")) + 1;
	    var dataJson = JSON.stringify({rating: ratingValue, webServiceId: webServiceIdValue});
	    
	    $.ajax({
	        type: 'POST',
	        url: '/ws/rate',
	        data: dataJson,
	        contentType: "application/json",
	        dataType: 'json'
	    });
	    
	    $.cookie('rated-api-' + webServiceIdValue, true);
	    $(".api-rating").hide();
		$(".api-rated").show();
   });
});