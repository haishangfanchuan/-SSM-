$(function () {


    var imageDetail = "/graphicSystem/image/getImageById";
   /* var commentAddUrl = "/graphicSystem/comment/addComment";
    var getCommentList = "/graphicSystem/comment/getCommentList";
*/
    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
    var imageId=getUrlParam('imageId');
    var image={};
    image.imageId = imageId;
    // alert(articleId);


    //图片标题
    var imageName = $("#imageName");
    //图片列表
    var imageList = $("#imgList");
    //图片作者
    var imageUser = $("#imageUser");
    //图片修改时间
    var imageTime = $("#imageTime");
    //图片评论总数
    // var commentCount = $("#commentCount");

    var loginUser={};

    $.ajax({
        url:imageDetail,
        type:'POST',
        data:JSON.stringify(image),
        dataTypes:'json',
        contentType:"application/json;charset=UTF-8",
        success:function (date) {
            if(date.success){
                image = date.image;
                loginUser=date.loginUser;

                alert(date.imageList)



                //图片列表
                var html='';
                date.imageList.map(function (item, index) {

                    html+='<div class="portfolioFilterableItemWrapper" data-type="websites,logos">\n' +
                        // '                <a href="../image/getImageByIdB?imageId='+item.imageId+'" class="portfolioFilterableItemImageWrapper">' +
                        '                <img src="'+item.imageAddress+'" alt="" class="largeImage"/></a>\n' +
                        '                <div class="portfolioFilterableItemInfoWrapper">\n' +
                        '                    <h4 class="portfolioFilterableItemTitle"></h4>\n' +
                        // '                    <p>'+item.imageName+'</p>\n' +
                        '                </div>\n' +
                        '                <div class="portfolioFilterableItemButtonsWrapper">\n' +
                        '                    <a href="'+item.imageAddress+'" class="portfolioFilterableExpandButton">查看图片</a>\n' +
                        // '                    <a href="../image/getImageByIdB?imageId='+item.imageId+'" class="portfolioFilterableDetailsButton">图片详情</a>\n' +
                        '                </div>\n' +
                        '            </div>';

                });
                date.imageListList.map(function (item, index) {

                    html+='<div class="portfolioFilterableItemWrapper" data-type="websites,logos">\n' +
                        // '                <a href="../image/getImageByIdB?imageId='+item.imageId+'" class="portfolioFilterableItemImageWrapper">' +
                        '                <img src="'+item.imageListAddress+'" alt="" class="largeImage"/></a>\n' +
                        '                <div class="portfolioFilterableItemInfoWrapper">\n' +
                        '                    <h4 class="portfolioFilterableItemTitle"></h4>\n' +
                        // '                    <p>'+item.imageName+'</p>\n' +
                        '                </div>\n' +
                        '                <div class="portfolioFilterableItemButtonsWrapper">\n' +
                        '                    <a href="'+item.imageListAddress+'" class="portfolioFilterableExpandButton">查看图片</a>\n' +
                        // '                    <a href="../image/getImageByIdB?imageId='+item.imageId+'" class="portfolioFilterableDetailsButton">图片详情</a>\n' +
                        '                </div>\n' +
                        '            </div>';

                });
                imageList.html(html);
                // imageList.attr("src",article.coverImgAddress);
                //图片标题
                imageName.text( date.imageList[0].imageName);
                //图片作者
                imageUser.text(date.imageList[0].user.username);
                //文章修改时间
                imageTime.text(new Date(date.imageList[0].lastTime).Format('yyyy-MM-dd'));
                //文章评论总数
                // commentCount.text(commentCount);

            }else{
                alert(date.errMsg);
            }
        }
    })


/*    //添加评论
    $("#addComment").click(function () {
        //获取评论内容
        var commentText = $("#commentText").val();
        var addComment={};
        addComment.image=image;
        addComment.imageId=imageId;
        addComment.user=loginUser;
        addComment.userId = loginUser.userId;
        addComment.userName=loginUser.username;
        addComment.userImgAddress = loginUser.userImgAddress;
        addComment.commentContent=commentText;
        // addComment.articleId=-1;
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
    })*/


});

