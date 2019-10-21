$( document ).ready(function() {
	initFirstModalForm();
   
   $( ".btn-next" ).click(function() {
	   initSecondModalForm();
	 });
   
   $( ".btn-cancel, .close, .btn-back" ).click(function() {
	   initFirstModalForm();
	 });
   
   $( ".btn-add-api" ).click(function() {
	   $( ".add-api-form" ).submit();
	 });
});

function initFirstModalForm() {
	   $(".web-service-form").hide();
	   $(".btn-add-api").hide();
	   $(".btn-back").hide();
	   $(".modal-title").text("Service provider information");
	   
	   $(".service-provider-form").show();
	   $(".btn-next").show();
	 }

function initSecondModalForm() {
	   $(".service-provider-form").hide();
	   $(".btn-next").hide();
	   
	   $(".web-service-form").show();
	   $(".btn-add-api").show();
	   $(".btn-back").show();
	   $(".modal-title").text("Web API information");
	 }