$(function () {

    var addImageUrl = "/graphicSystem/image/addImage";//添加图片接口

    //图片列表
    // alert($('.detail-img').length);
    $('.detail-img-div').on('change','.detail-img:last-child',function () {
        if($('.detail-img').length<6){
            $('#detail-img').append('<input type="file" class="detail-img" value="选择图片"/>');
        }
    });



    //添加图片按钮点击触发事件
    $("#submit").click(function () {

        // var formData = new FormData();
        var detailImage=new Array();
        //获取html传递的详情图
        $(".detail-img").map(function (item,index) {
            if($(".detail-img")[item].files.length>0){
                //见每一个图片放在format里面
                // alert($(".detail-img")[item].files[0]);
                detailImage.push(item.val());
            }
        });

        var image = {};
        image.imageName = $("#imageName").val();//封面图名称
        image.imageAddress = $("#small-img").val();//封面图


        var array = new Array();
        array.push(image);
        array.push(detailImage);

        $.ajax({
            url:addImageUrl,
            type:'POST',
            data:JSON.stringify(array),
            dataTypes:'json',
            contentType:"application/json;charset=UTF-8",
            success:function (date) {
                if(date.success){
                    alert(date.errMsg);
                }else{
                    alert(date.errMsg);
                }
            }
        })
    })

});