$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('.sidebar').toggleClass('collapse');
        $('.content_wrap').toggleClass('col-md-12');
    });
    
    var parts = window.location.pathname.split("/");
  
    var selector1 = '#' + parts[1];
    var selector2 = '#' + parts[2];
    
    $(selector1).addClass("show");
    $(selector1).prev().addClass("highlight");
    $(selector1).prev().find(".fa-plus").remove();
	$(selector1).prev().find(".fa-minus").removeClass("collapse");
	$(selector1).removeAttr("data-parent");
	$(selector1).prev().on('click', function(e) {
		e.stopPropagation();
		e.preventDefault();
	});
	
	$(selector2).attr('style', 'background-color: #535b63; color: white !important;');

	$(".card-header .btn").filter(function() {
  		return !$(this).is($(selector1).prev().find('.btn'));
	}).on('click', function(){
		$(".card-header").not($(this).parents(".card-header")).removeClass("highlight");
		$(this).parents(".card-header").toggleClass("highlight");
		
		$(".card-header").not($(this).parents()).find(".fa-plus").removeClass("collapse");
		$(".card-header").not($(this).parents()).find(".fa-minus").removeClass("show");
		$(this).find(".fa-plus").toggleClass("collapse");
		$(this).find(".fa-minus").toggleClass("show");

		$(selector1).prev().addClass("highlight");
	});
});




