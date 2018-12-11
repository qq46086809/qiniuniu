$(function() {
    var delParent;
    var defaults = {
        fileType: ["jpg", "png", "bmp", "jpeg"],
        // 上传图片支持的格式
        fileSize: 1024 * 1024 * 10 // 上传的图片大小不得超过 10M
    };
    /*点击图片*/
    $(".file").change(function() {
        var idFile = $(this).attr("id");
        var file = document.getElementById(idFile);
        var imgContainer = $(this).parents(".aui-photo");
        //存放图片的父元素
        var fileList = file.files;
        //获取的图片文件
        console.log(fileList + "======filelist=====");
        var input = $(this).parent();
        //文本框的父亲元素
        var imgArr = [];
        //遍历得到的图片
        var numUp = imgContainer.find(".aui-up-section").length;
        var totalNum = numUp + fileList.length;
        //图片总的数量可自定义
        if (fileList.length > 1 || totalNum > 1) {
            alert("你好！上传图片不得超过1张，请重新选择");
            //一次选择上传超过3个  自己定义
        } else if (numUp < 2) {
            fileList = validateUp(fileList);
            for (var i = 0; i < fileList.length; i++) {
                var imgUrl = window.URL.createObjectURL(fileList[i]);
                imgArr.push(imgUrl);
                var $section = $("<section class='aui-up-section fl loading' id='mm'>");
                imgContainer.prepend($section);
                var $span = $("<span class='aui-up-span'>");
                $span.appendTo($section);

                var $img0 = $("<img class='aui-close-up-img'>").on("click", function(event) {
                    event.preventDefault();
                    event.stopPropagation();
                    $(".aui-works-mask").show();
                    delParent = $(this).parent();
                });
                $img0.attr("src", "/static/admin/image/close.png").appendTo($section);
                var $img = $("<img class='aui-to-up-img aui-up-clarity'>");
                $img.attr("src", imgArr[i]);
                $img.appendTo($section);
                var $p = $("<p class='img-aui-img-name-p'>");
                $p.html(fileList[i].name).appendTo($section);
                var $input = $("<input id='actionId' name='actionId' value='' type='hidden'>");
                $input.appendTo($section);
                var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
                $input2.appendTo($section);

            }
        }
        setTimeout(function() {
            $(".aui-up-section").removeClass("loading");
            $(".aui-to-up-img").removeClass("aui-up-clarity");
        }, 4100);
        numUp = imgContainer.find(".aui-up-section").length;
        if (numUp >= 10) {
            $(this).parent().hide();
        }

        $(this).val("");
    });






        $(".file2").change(function() {
            var idFile = $(this).attr("id");
            var file = document.getElementById(idFile);
            var imgContainer = $(this).parents(".aui-photo");
            //存放图片的父元素
            var fileList = file.files;
            //获取的图片文件
            console.log(fileList + "======filelist=====");
            var input = $(this).parent();
            //文本框的父亲元素
            var imgArr = [];
            //遍历得到的图片
            var numUp = imgContainer.find(".aui-up-section").length;
            var totalNum = numUp + fileList.length;
            //图片总的数量可自定义
            if (fileList.length > 1 || totalNum > 10) {
                alert("你好！只能单张上传");
                //一次选择上传超过1个  自己定义
            } else if (numUp < 11) {
            var sj = Math.random().toString(36).substr(2);
                fileList = validateUp2(fileList,sj);
                for (var i = 0; i < fileList.length; i++) {
                    var imgUrl = window.URL.createObjectURL(fileList[i]);
                    imgArr.push(imgUrl);
                    var $section = $("<section class='aui-up-section fl loading' id='"+sj+"'> ");
                    imgContainer.prepend($section);
                    var $span = $("<span class='aui-up-span'>");
                    $span.appendTo($section);

                    var $img0 = $("<img class='aui-close-up-img'>").on("click", function(event) {
                        event.preventDefault();
                        event.stopPropagation();
                        $(".aui-works-mask").show();
                        delParent = $(this).parent();
                    });
                    $img0.attr("src", "/static/admin/image/close.png").appendTo($section);
                    var $img = $("<img class='aui-to-up-img aui-up-clarity'>");
                    $img.attr("src", imgArr[i]);

                    $img.appendTo($section);
                    var $p = $("<p class='img-aui-img-name-p'>");
                    $p.html(fileList[i].name).appendTo($section);
                    var $input = $("<input id='actionId' name='actionId' value='' type='hidden'>");
                    $input.appendTo($section);
                    var $input2 = $("<input id='tags' name='tags' value='' type='hidden'/>");
                    $input2.appendTo($section);

                }
            }
            setTimeout(function() {
                $(".aui-up-section").removeClass("loading");
                $(".aui-to-up-img").removeClass("aui-up-clarity");
            }, 4100);
            numUp = imgContainer.find(".aui-up-section").length;
            if (numUp >= 10) {
                $(this).parent().hide();
            }

            $(this).val("");
        });

    $(".aui-photo").delegate(".aui-close-up-img", "click", function() {
        $(".aui-works-mask").show();
        delParent = $(this).parent();
    });

    $(".aui-accept-ok").click(function() {
        $(".aui-works-mask").hide();
        var numUp = delParent.siblings().length;
       var ids = delParent.attr('id');
        if (numUp < 10) {
            delParent.parent().find(".aui-file-up").show();
        }
        if(ids!="mm"){
                var myobj = eval($("#content").val());
              for(var key in myobj[0]){

                  if(key==ids){
                  delete myobj[0][key]
                  }
                  }
                  if(JSON.stringify(myobj)=="[{}]"){
                  $("#content")[0].value="";
                  }else{
                   $("#content")[0].value=JSON.stringify(myobj);
                  }

        }else{
         $("#cover")[0].value="";
        }

        delParent.remove();

    });

    $(".aui-accept-no").click(function() {
        $(".aui-works-mask").hide();
    });

    function validateUp(files) {
        var arrFiles = [];
        //替换的文件数组
        for (var i = 0, file; file = files[i]; i++) {
            //获取图片上传的后缀名
            var newStr = file.name.split("").reverse().join("");
            if (newStr.split(".")[0] != null) {
                var type = newStr.split(".")[0].split("").reverse().join("");
                console.log(type + "===type===");
                if (jQuery.inArray(type, defaults.fileType) > -1) {
                    // 符合图片格式，可以上传
                     blobToDataURL(file, function (dataurl) {
$("#cover")[0].value="[{'mm':'"+dataurl+"'}]";
                                        });
                    if (file.size >= defaults.fileSize) {
                        alert('您这个"' + file.name + '"文件大小过大');
                    } else {
                        arrFiles.push(file);
                    }
                } else {
                    alert('您这个"' + file.name + '"上传类型不符合');
                }
            } else {
                alert('您这个"' + file.name + '"没有类型, 无法识别');
            }
        }
        return arrFiles;
    }


        function validateUp2(files,sj) {
            var arrFiles = [];
            //替换的文件数组
            for (var i = 0, file; file = files[i]; i++) {
                //获取图片上传的后缀名
                var newStr = file.name.split("").reverse().join("");
                if (newStr.split(".")[0] != null) {
                    var type = newStr.split(".")[0].split("").reverse().join("");
                    console.log(type + "===type===");
                    if (jQuery.inArray(type, defaults.fileType) > -1) {
                        // 符合图片格式，可以上传
                         blobToDataURL(file, function (dataurl) {
                                                var v = $("#content")[0].value;
if(v==""){
$("#content")[0].value="[{'"+sj+"':'"+dataurl+"'}]";
}else{
$("#content")[0].value="[{'"+sj+"':'"+dataurl+"',"+$("#content")[0].value.substring(2,$("#content")[0].value.length-2)+"}]";
}
                                            });
                        if (file.size >= defaults.fileSize) {
                            alert('您这个"' + file.name + '"文件大小过大');
                        } else {
                            arrFiles.push(file);
                        }
                    } else {
                        alert('您这个"' + file.name + '"上传类型不符合');
                    }
                } else {
                    alert('您这个"' + file.name + '"没有类型, 无法识别');
                }
            }
            return arrFiles;
        }

     function blobToDataURL(blob, callback) {
            var a = new FileReader();
            a.onload = function (e) { callback(e.target.result); }
            a.readAsDataURL(blob);
        }

})
