
jQuery(function(){
	$(".box2List li").each(function(index, element) {
        $(this).attr("data-wow-delay",index*3/10+"s");
    });

    $(".high-sec").mouseenter(function(index, element) {
            $(this).stop(true, true).animate({top: "-=100px"}, 500);
             $(this).find(".chanpin").stop(true, true).animate({height: "+=100px"}, 500);
             $(this).find(".btn-none").attr("style","display:block")
        });
            $(".high-sec").mouseleave(function(index, element) {
                    $(this).stop(true, true).animate({top: "+=100px"}, 500);
                      $(this).find(".chanpin").stop(true, true).animate({height: "-=100px"}, 500);
                       $(this).find(".btn-none").attr("style","display:none")
                });
    });


	function cards1(){
	$(".cards-1").attr("style","display:block");
    $(".cards-2").attr("style","display:none");
    $(".cards-3").attr("style","display:none");
    $(".cards-4").attr("style","display:none");
    $(".cards-5").attr("style","display:none");
    $(".cards-6").attr("style","display:none");
	}
	function cards2(){
	$(".cards-1").attr("style","display:none");
    $(".cards-2").attr("style","display:block");
    $(".cards-3").attr("style","display:none");
    $(".cards-4").attr("style","display:none");
    $(".cards-5").attr("style","display:none");
    $(".cards-6").attr("style","display:none");
	}
    function cards3(){
	$(".cards-1").attr("style","display:none");
    $(".cards-2").attr("style","display:none");
    $(".cards-3").attr("style","display:block");
    $(".cards-4").attr("style","display:none");
    $(".cards-5").attr("style","display:none");
    $(".cards-6").attr("style","display:none");
	}
    function cards4(){
	$(".cards-1").attr("style","display:none");
    $(".cards-2").attr("style","display:none");
    $(".cards-3").attr("style","display:none");
    $(".cards-4").attr("style","display:block");
    $(".cards-5").attr("style","display:none");
    $(".cards-6").attr("style","display:none");
	}
    function cards5(){
	$(".cards-1").attr("style","display:none");
    $(".cards-2").attr("style","display:none");
    $(".cards-3").attr("style","display:none");
    $(".cards-4").attr("style","display:none");
    $(".cards-5").attr("style","display:block");
    $(".cards-6").attr("style","display:none");
	}
    function cards6(){
	$(".cards-1").attr("style","display:none");
    $(".cards-2").attr("style","display:none");
    $(".cards-3").attr("style","display:none");
    $(".cards-4").attr("style","display:none");
    $(".cards-5").attr("style","display:none");
    $(".cards-6").attr("style","display:block");
	}


function news1(){
$(".news1").attr("style","display:block");
    $(".news2").attr("style","display:none");
    $(".news3").attr("style","display:none");
}

function news2(){
$(".news1").attr("style","display:none");
    $(".news2").attr("style","display:block");
    $(".news3").attr("style","display:none");
}

function news3(){
$(".news1").attr("style","display:none");
    $(".news2").attr("style","display:none");
    $(".news3").attr("style","display:block");
}
