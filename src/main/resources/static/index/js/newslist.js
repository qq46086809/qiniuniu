
jQuery(function(){
  $(".fx-a2").mouseenter(function(index, element) {
   layer.tips('<img src="/static/index/images/newList/news-ma.png" alt="二维码">', $(this), {
     tips: [1, '#fff'], //背景颜色
     time: 200000
   });
        });
    $(".fx-a2").mouseleave(function(index, element) {
           layer.close(layer.index);
     });

    });



