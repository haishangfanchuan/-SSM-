$(function () {

    var addArticleUrl = "/graphicSystem/article/addArticle";//添加文章接口


    //添加文章按钮点击触发事件
    $("#submit").click(function () {

        var article = {};
        article.headline = $("#articleName").val();
        article.coverImgAddress = $("#small-img").val();
        article.content = $("#articleContent").val();

        $.ajax({
            url:addArticleUrl,
            type:'POST',
            data:JSON.stringify(article),
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