
$(document).ready(function() {
	$("time.timeago").timeago();
				
	$("#logoutLink").click(function(event){
	$("#logoutForm").submit();
						});
				
	var pathname = window.location.pathname;
	$("nav a[href='"+pathname+"']").parent().addClass("active");
				
});
			
			