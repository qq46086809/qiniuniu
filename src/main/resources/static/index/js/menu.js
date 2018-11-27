$(function(){
  var menuwidth  = 200; // 边栏宽度
  var menuspeed  = 400; // 边栏滑出耗费时间
    var menuspeed2  = 10; // 边栏滑出耗费时间
  var $bdy       = $('body');
  var $container = $('#pgcontainer');
  var $burger    = $('#hamburgermenu');
  var $burger2    = $('#hamburgermenu2');
  var negwidth   = "-"+menuwidth+"px";
  var poswidth   = menuwidth+"px";
  
  $('.menubtn').mouseenter(function(e){
  jsAnimateMenu('open');
  });
   $('.menu2').mouseenter(function(e){
   var nav1 ='<ul class="sf-menu" id="fh5co-primary-menu" style="width:200px">' +
       '<li  style="float:none;display: inline;margin-left: 0; padding-left: 0;"><a href="index.html" class="fh5co-sub-ddown">最新1活动</a></li>' +
       '<li style="float:none"> <a href="portfolio.html" class="fh5co-sub-ddown">支持与服务</a></li> ' +
       '<li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">解决方案</a>   </li>' +
       '<li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">体验中心</a>    </li>' +
       '<li style="float:none">   <a href="services.html" class="fh5co-sub-ddown">下载中心</a>      </li>  ' +
       '  <li style="float:none">                 <a href="services.html" class="fh5co-sub-ddown">合作伙伴</a>  ' +
       ' </li> </ul>';
   $burger2.html(nav1);
    jsAnimateMenu2('open');
    });
 $('.menu3').mouseenter(function(e){
   var nav1 ='<ul class="sf-menu" id="fh5co-primary-menu" style="width:200px"><li  style="float:none;display: inline;margin-left: 0; padding-left: 0;"><a href="index.html" class="fh5co-sub-ddown">最新2活动</a></li>' +
       '<li style="float:none"> <a href="portfolio.html" class="fh5co-sub-ddown">支持与服务</a></li> <li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">解决方案</a>   </li><li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">体验中心</a>    </li><li style="float:none">   <a href="services.html" class="fh5co-sub-ddown">下载中心</a>      </li>    <li style="float:none">                 <a href="services.html" class="fh5co-sub-ddown">合作伙伴</a>   </li> </ul>';
     $burger2.html(nav1);
    jsAnimateMenu2('open');
    });
     $('.menu4').mouseenter(function(e){
   var nav1 ='<ul class="sf-menu" id="fh5co-primary-menu" style="width:200px"><li  style="float:none;display: inline;margin-left: 0; padding-left: 0;"><a href="index.html" class="fh5co-sub-ddown">最新3活动</a></li><li style="float:none"> <a href="portfolio.html" class="fh5co-sub-ddown">支持与服务</a></li> <li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">解决方案</a>   </li><li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">体验中心</a>    </li><li style="float:none">   <a href="services.html" class="fh5co-sub-ddown">下载中心</a>      </li>    <li style="float:none">                 <a href="services.html" class="fh5co-sub-ddown">合作伙伴</a>   </li> </ul>';
         $burger2.html(nav1);
        jsAnimateMenu2('open');
        });
         $('.menu5').mouseenter(function(e){
   var nav1 ='<ul class="sf-menu" id="fh5co-primary-menu" style="width:200px"><li  style="float:none;display: inline;margin-left: 0; padding-left: 0;"><a href="index.html" class="fh5co-sub-ddown">最新4活动</a></li><li style="float:none"> <a href="portfolio.html" class="fh5co-sub-ddown">支持与服务</a></li> <li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">解决方案</a>   </li><li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">体验中心</a>    </li><li style="float:none">   <a href="services.html" class="fh5co-sub-ddown">下载中心</a>      </li>    <li style="float:none">                 <a href="services.html" class="fh5co-sub-ddown">合作伙伴</a>   </li> </ul>';
             $burger2.html(nav1);
            jsAnimateMenu2('open');
            });
             $('.menu6').mouseenter(function(e){
    var nav1 ='<ul class="sf-menu" id="fh5co-primary-menu" style="width:200px"><li  style="float:none;display: inline;margin-left: 0; padding-left: 0;"><a href="index.html" class="fh5co-sub-ddown">最新5活动</a></li><li style="float:none"> <a href="portfolio.html" class="fh5co-sub-ddown">支持与服务</a></li> <li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">解决方案</a>   </li><li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">体验中心</a>    </li><li style="float:none">   <a href="services.html" class="fh5co-sub-ddown">下载中心</a>      </li>    <li style="float:none">                 <a href="services.html" class="fh5co-sub-ddown">合作伙伴</a>   </li> </ul>';
                  $burger2.html(nav1);
                jsAnimateMenu2('open');
                });
                 $('.menu7').mouseenter(function(e){
    var nav1 ='<ul class="sf-menu" id="fh5co-primary-menu" style="width:200px"><li  style="float:none;display: inline;margin-left: 0; padding-left: 0;"><a href="index.html" class="fh5co-sub-ddown">最新6活动</a></li><li style="float:none"> <a href="portfolio.html" class="fh5co-sub-ddown">支持与服务</a></li> <li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">解决方案</a>   </li><li style="float:none">  <a href="services.html" class="fh5co-sub-ddown">体验中心</a>    </li><li style="float:none">   <a href="services.html" class="fh5co-sub-ddown">下载中心</a>      </li>    <li style="float:none">                 <a href="services.html" class="fh5co-sub-ddown">合作伙伴</a>   </li> </ul>';
                       $burger2.html(nav1);
                    jsAnimateMenu2('open');
                    });

  $('body').on('click', function(e){
      if($bdy.hasClass('openmenu2')) {
          jsAnimateMenu2('close');
      }else if($bdy.hasClass('openmenu')){
       jsAnimateMenu('close');
      }
    });

  
  function jsAnimateMenu(tog) {
    if(tog == 'open') {
      $bdy.addClass('openmenu');
      $burger.animate({width: poswidth}, menuspeed);
    }
    
    if(tog == 'close') {
     $bdy.removeClass('openmenu');
      $burger.animate({width: "0"}, menuspeed);
    }
  }


    function jsAnimateMenu2(tog) {
      if(tog == 'open') {
        $bdy.addClass('openmenu2');
        $burger2.animate({width: poswidth}, menuspeed);
      }
      if(tog == 'close') {
       $bdy.removeClass('openmenu');
         $bdy.removeClass('openmenu2');
          $burger2.stop(true, true).animate({width: "0"}, menuspeed2);
         $burger.animate({width: "0"}, menuspeed);
      }
    }
});