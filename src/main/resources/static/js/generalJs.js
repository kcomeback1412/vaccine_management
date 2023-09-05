$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('.sidebar').toggleClass('collapse');
        $('.content_wrap').toggleClass('col-md-12');
    });
});

$(document).ready(function(){
	// Add minus icon for collapse element which is open by default
	$(".collapse.show").each(function(){
		$(this).prev(".card-header").addClass("highlight");
	});
	
	// Highlight open collapsed element 
	$(".card-header .btn").click(function(){
		$(".card-header").not($(this).parents()).removeClass("highlight");
		$(this).parents(".card-header").toggleClass("highlight");
		
		$(".card-header").not($(this).parents()).find(".fa-plus").removeClass("collapse");
		$(".card-header").not($(this).parents()).find(".fa-minus").removeClass("show");
		$(this).find(".fa-plus").toggleClass("collapse");
		$(this).find(".fa-minus").toggleClass("show");
	});
});




