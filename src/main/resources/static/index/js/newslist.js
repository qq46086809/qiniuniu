
jQuery(function(){
  $(".fx-a2").mouseenter(function(index, element) {
   layer.tips('上', $(this), {
     tips: [1, '#0FA6D8'], //还可配置颜色
     time: 200000
   });
        });
            $(".fx-a2").mouseleave(function(index, element) {
                            layer.close(layer.index);
                });

    });



