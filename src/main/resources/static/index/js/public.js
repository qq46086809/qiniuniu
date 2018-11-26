
jQuery(function(){
	$(".box2List li").each(function(index, element) {
        $(this).attr("data-wow-delay",index*3/10+"s");
    });

    $(".high-sec").mouseenter(function(index, element) {
            $(this).stop(true, true).animate({top: "-=50px"}, 500);
             $(this).find(".chanpin").stop(true, true).animate({height: "+=50px"}, 500);
             $(this).find(".btn-none").attr("style","display:block")
        });
            $(".high-sec").mouseleave(function(index, element) {
                    $(this).stop(true, true).animate({top: "+=50px"}, 500);
                      $(this).find(".chanpin").stop(true, true).animate({height: "-=50px"}, 500);
                       $(this).find(".btn-none").attr("style","display:none")
                });
                 $(".cover-view-pink").mouseenter(function(index, element) {
                                             $(this).removeClass("content-v");
                                                $(this).addClass("content-v2");
                                        });
                                         $(".cover-view-pink").mouseleave(function(index, element) {
                                                                      $(this).removeClass("content-v2");
                                                                       $(this).addClass("content-v");
                                                                });

                                            $(".div-shadow").mouseenter(function(index, element) {
                                            $(this).attr("style","transition:0.5s;-moz-box-shadow:0px 0px 18px #757575; -webkit-box-shadow:0px 0px 18px #757575; box-shadow:0px 0px 18px #757575;");
                                                                   });

                                                                    $(".div-shadow").mouseleave(function(index, element) {
                                            $(this).attr("style","transition:0.5s;-moz-box-shadow:0px 0px 0px #333333; -webkit-box-shadow:0px 0px 0px #333333; box-shadow:0px 0px 0px #333333;");
                                                                                                                      });
                                         $(".div-red").mouseenter(function(index, element) {
                                                                    $(this).attr("style","border:1px solid #ff2c63");
                                                                                           });

                                           $(".div-red").mouseleave(function(index, element) {
                                                                                         $(this).attr("style","transition:0.5s;border:");
                                                     });
    });


(function(){
    $(".cards-2").removeClass("animated");
    $(".cards-3").removeClass(" animated");
    $(".cards-4").removeClass(" animated");
    $(".cards-5").removeClass(" animated");
    $(".cards-6").removeClass("animate-box fadeInUp animated");
})();


	function cards1(){
	$(".cards-1").attr("style","display:block");
    $(".cards-2").attr("style","display:none");
    $(".cards-3").attr("style","display:none");
    $(".cards-4").attr("style","display:none");
    $(".cards-5").attr("style","display:none");
    $(".cards-6").attr("style","display:none");

	}
	function cards2(){
        $(".cards-2").addClass(" fadeInUp animated");
	$(".cards-1").attr("style","display:none");
    $(".cards-2").attr("style","display:block");
    $(".cards-3").attr("style","display:none");
    $(".cards-4").attr("style","display:none");
    $(".cards-5").attr("style","display:none");
    $(".cards-6").attr("style","display:none");

	}
    function cards3(){
        $(".cards-3").addClass(" fadeInUp animated");
	$(".cards-1").attr("style","display:none");
    $(".cards-2").attr("style","display:none");
    $(".cards-3").attr("style","display:block");
    $(".cards-4").attr("style","display:none");
    $(".cards-5").attr("style","display:none");
    $(".cards-6").attr("style","display:none");
	}
    function cards4(){
        $(".cards-4").addClass(" fadeInUp animated");
	$(".cards-1").attr("style","display:none");
    $(".cards-2").attr("style","display:none");
    $(".cards-3").attr("style","display:none");
    $(".cards-4").attr("style","display:block");
    $(".cards-5").attr("style","display:none");
    $(".cards-6").attr("style","display:none");
	}
    function cards5(){
        $(".cards-5").addClass(" fadeInUp animated");
	$(".cards-1").attr("style","display:none");
    $(".cards-2").attr("style","display:none");
    $(".cards-3").attr("style","display:none");
    $(".cards-4").attr("style","display:none");
    $(".cards-5").attr("style","display:block");
    $(".cards-6").attr("style","display:none");
	}
    function cards6(){
        $(".cards-6").addClass(" fadeInUp animated");
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
