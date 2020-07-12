$(function () {


    var articleDetail = "/graphicSystem/article/queryArticleById";
    var commentAddUrl = "/graphicSystem/comment/addComment";
    var getCommentList = "/graphicSystem/comment/getCommentList";

    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
    var articleId=getUrlParam('articleId');
    var article={};
    article.articleId = articleId;
    // alert(articleId);

    //文章封面图
    var articleImg = $("#articleImg");
    //文章标题
    var articleName = $("#articleName");
    //文章内容
    var articleContent = $("#articleContent");
    //文章作者
    var articleUser = $("#articleUser");
    //文章修改时间
    var articleTime = $("#articleTime");
    //文章评论总数
    var commentCount = $("#commentCount");

    var loginUser={};

    $.ajax({
        url:articleDetail,
        type:'POST',
        data:JSON.stringify(article),
        dataTypes:'json',
        contentType:"application/json;charset=UTF-8",
        success:function (date) {
            if(date.success){
                article = date.article;
                loginUser=date.loginUser;

                // var commentList=[];

                var rowIndex=1;
                var comment={};
                comment.articleId=articleId;
                comment.userId=loginUser.userId;
                comment.article=article;
                //获取评论列表
                $.ajax({
                    url:getCommentList+'?rowIndex='+rowIndex,
                    type:'POST',
                    data:JSON.stringify(comment),
                    dataTypes:'json',
                    contentType:"application/json;charset=UTF-8",
                    success:function (date) {
                        if(date.success){
                             // commentList = date.commentList1;
                            //评论列表
                            var html='';
                            date.commentList.map(function (item, index) {
                                html+='<div class="commentWrapper">\n' +
                                    '                    <a href="" class="commentAvatar">\n' +
                                    '                        <img src="'+item.user.userImgAddress+'" alt="" />\n' +
                                    '                    </a>\n' +
                                    '                    <p class="commentInfoWrapper">by\n' +
                                    '                        <a href="" class="">'+item.user.username+'</a> on '+new Date(item.createTime).Format('yyyy-MM-dd')+'\n' +
                                    '                    </p>\n' +
                                    '                    <div class="clear"></div>\n' +
                                    '                    <div class="comment">\n' +
                                    '                        <p>'+item.commentContent+'</p>\n' +
                                    '                    </div>'
                            });
                            $(".commentsWrapper").html(html)

                        }else{
                            alert(date.errMsg);
                        }
                    }
                });
                /*$.each(commentList, function (i, item) {
                    alert(item.commentId.toString() + "，"  + item.commentContent.toString());
                });*/

                //文章封面图
                articleImg.attr("src",article.coverImgAddress);
                //文章标题
                articleName.text(article.headline);
                //文章内容
                articleContent.text(article.content);
                //文章作者
                articleUser.text(article.username);
                //文章修改时间
                articleTime.val(article.lasttime);
                //文章评论
                var commentCount = $("#commentCount");

            }else{
                alert(date.errMsg);
            }
        }
    })


    //添加评论
    $("#addComment").click(function () {
        //获取评论内容
        var commentText = $("#commentText").val();
        var addComment={};
        addComment.article=article;
        addComment.articleId=articleId;
        addComment.user=loginUser;
        addComment.userId = loginUser.userId;
        addComment.userName=loginUser.username;
        addComment.userImgAddress = loginUser.userImgAddress;
        addComment.commentContent=commentText;
        // addComment.imageId=-1;
        $.ajax({
            url:commentAddUrl,
            type:'POST',
            data:JSON.stringify(addComment),
            dataTypes:'json',
            contentType:"application/json;charset=UTF-8",
            success:function (date) {
                if(date.success){
                    alert(date.errMsg);
                }else{
                    alert(date.errMsg);
                }
            }
        });
    })


});

