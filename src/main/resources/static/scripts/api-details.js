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
	   
	   if ($(this).hasClass('meetit-navbar')) {
			  $('.content-panel').hide();
			  $('.meetit-panel').show();
		}
	   
	   if ($(this).hasClass('provider-navbar')) {
			  $('.content-panel').hide();
			  $('.provider-panel').show();
		}
	   
	   if ($(this).hasClass('doc-navbar')) {
			  $('.content-panel').hide();
			  $('.doc-panel').show();
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
			  
			  var contactUrl = $("#contract-url").val();
			   $.get( contactUrl, function( data ) {
				   var editor = ace.edit("editor");
				   editor.setTheme("ace/theme/monokai");
				   
				   if (contactUrl.endsWith("json")) {
					   editor.session.setMode("ace/mode/json");
					   var jsonString = JSON.stringify(data, null, '\t');
					   editor.setValue(jsonString);
				   } else {
					   editor.session.setMode("ace/mode/yaml");
					   var spaces = parseInt(4 || 2, 10);
					   var object  = YAML.parse(data);
				       var prettified = YAML.stringify(object, 100, spaces);
				       editor.setValue(prettified);
				   }
				   editor.setReadOnly(true);
				 });
		}
	   
	   if ($(this).hasClass('discuss-navbar')) {
		   	  loadComments();
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
   
   $('.btn-comment').on('click', function() {
	    var webServiceIdValue =  Number($("#web-service-id").val());
	    var authorValue =  $("#author").val();
	    var commentValue = $("#comment").val();
	    var action = $(".comment-form").attr("action");
	    
	    if (!commentValue || !authorValue) {
	    	$(".comment-validation").show();
	    	return;
	    }
	    
	    var dataJson = JSON.stringify({webServiceId: webServiceIdValue, author: authorValue, comment: commentValue});
	    
	    $.ajax({
	        type: 'POST',
	        url: action,
	        data: dataJson,
	        contentType: "application/json",
	        dataType: 'json',
	        complete: function() {
	    	    var authorValue =  $("#author").val("");
	    	    var commentValue = $("#comment").val("");
	    	    loadComments();
	          } 
	    });
  });
});

function loadComments() {
	  $(".comment-validation").hide();
 	  var webServiceIdValue =  Number($("#web-service-id").val());
   	  $(".comment").remove();
      $.get( "/ws/comments?webServiceId=" + webServiceIdValue, function( data ) {
    	  $.each(data, function(i, item) {
    		   $(".comments-section").append(
    				   	'<div class="card bottom-margin comment">' +
						  '<h5 class="card-header"><i>' + item.author + '</i></h5>' +
						  '<div class="card-body">' +
						    '<p class="card-text">' + item.comment + '</p>' +
						  '</div>' +
						  '<div class="card-footer text-muted">' 
						  		+ formatDate(new Date(item.createdAt)) +
						   '</div>'	+	
						'</div>');
    		});	
	  });
}

function formatDate(date) {
	  var hours = date.getHours();
	  var minutes = date.getMinutes();
	  var ampm = hours >= 12 ? 'PM' : 'AM';
	  hours = hours % 12;
	  hours = hours ? hours : 12; // the hour '0' should be '12'
	  minutes = minutes < 10 ? '0'+minutes : minutes;
	  var strTime = hours + ':' + minutes + ' ' + ampm;
	  return date.getMonth()+1 + "/" + date.getDate() + "/" + date.getFullYear() + "  " + strTime;
	}