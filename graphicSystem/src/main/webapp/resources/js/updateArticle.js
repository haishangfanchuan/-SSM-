$(function () {


    var updateArticleUrl="/graphicSystem/article/updateArticle";//更新文章接口
    var queryArticleByIdUrl="/graphicSystem/article/queryArticleById";//根据文章id查询文章接口



    //更新文章
    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
    var articleId=getUrlParam('articleId');
    //查询文章信息
    var article={};
    article.articleId=articleId;

    if(window.location.href.indexOf("updateArticleB")>=1) {
        $.ajax({
            url: queryArticleByIdUrl,
            type: 'POST',
            data: JSON.stringify(article),
            dataTypes: 'json',
            contentType: "application/json;charset=UTF-8",
            success: function (date) {
                if (date.success) {
                    // alert(date.errMsg);
                    //获取文章
                    article = date.article
                    //回写文章信息
                    $("#articleName").val(article.headline);
                    // $("#small-img").val(article.coverImgAddress);
                    $("#articleContent").val(article.content);

                    //更新文章
                    $("#submit").click(function () {

                        article.headline = $("#articleName").val();
                        article.coverImgAddress = $("#small-img").val();
                        article.content = $("#articleContent").val();
                        $.ajax({
                            url: updateArticleUrl,
                            type: 'POST',
                            data: JSON.stringify(article),
                            dataTypes: 'json',
                            contentType: "application/json;charset=UTF-8",
                            success: function (date) {
                                if (date.success) {
                                    alert(date.errMsg);
                                } else {
                                    alert(date.errMsg);
                                }
                            }
                        })
                    });
                }
            }
        });
    }

});