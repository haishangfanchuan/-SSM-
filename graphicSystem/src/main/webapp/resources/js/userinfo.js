$(function () {


    var loginUserUrl = "/graphicSystem/user/getUserInfo";//登陆用户信息接口
    var updateUPUrl = "/graphicSystem/user/updateUserPassword";//更新用户密码接口
    var getArticleListUrl="/graphicSystem/article/queryArticleList";
    var updateUserInfo = "/graphicSystem/user/updateUserInfo";//更新用户信息接口
    var updateArticleUrl="/graphicSystem/article/updateArticle";//修改发表文章接口
    var delArticleUrl="/graphicSystem/article/delArticle";//删除发表文章接口
    var queryImageListUrl="/graphicSystem/image/getImageList";//查询图片列表接口
    var delImageListUrl="/graphicSystem/image/delImage";//删除图片列表接口
    //userInfo表单信息回写

    var rowArticleId=0;//选择update或del文章的id
    var rowImageId=0;
    var user={};
    $.ajax({
        type: "post",
        url: loginUserUrl,
        dataTypes: 'json',
        contentType: 'application/json;charset=utf-8',
        success: function (date) {
            if (date.success) {
                user = date.loginUser;
                // alert(typeof user.username)

                //1、个人信息
                if(user.username!=null){
                    var username = user.username;
                    $("#userName").val(username)
                }
                if(user.userPhoneNumber!=null){
                    $("#userPhone").val(user.userPhoneNumber);
                }
                if(user.userEmail!=null){
                    $("#userEmail").val(user.userEmail);
                }
                if(user.userAddress!=null){
                    $("#userAddress").text(user.userAddress);
                }
                //4、修改登陆密码
                $("#userLoginName").val(user.userLoginName)


                //获取发表图片列表
                var image={};
                var imageListHtml = '';
                image.userId=user.userId;
                image.user=user;
                var imageRowIndex=1;
                $.ajax({
                    type:"post",
                    url:queryImageListUrl+'?rowIndex='+imageRowIndex,
                    dataTypes:"json",
                    contentType:"application/json;charset=UTF-8",
                    data:JSON.stringify(image),
                    success:function (data) {
                        if(data.success){
                            //修改成功
                            // alert(date.errMsg)
                            data.imageList.map(function (item, index){
                                rowImageId=item.imageId;
                                imageListHtml+='<tr>\n' +
                                    '                                    <td>'+item.imageName+'</td>\n' +
                                    '                                    <td>'+new Date(item.lastTime).Format('yyyy-MM-dd')+'</td>\n' +
                                    '                                    <td><a href="../image/updateImageB?imageId='+rowImageId+'">修改</a>' +
                                    '                                    |<a href="#" id="delImage" class="deleteImage">删除</a></td>\n' +
                                    '                                </tr>'
                            });
                            $(".image-wrap").html(imageListHtml);
                        }else{
                            alert(data.errMsg)
                        }
                    }
                });






                //获取文章列表
                var article={};
                var html='';
                article.user=user;
                article.userId=user.userId;
                // alert(article.userId+'---'+user.userId);
                var rowIndex=1;
                $.ajax({
                    type:"post",
                    url:getArticleListUrl+'?rowIndex='+rowIndex,
                    dataTypes:"json",
                    contentType:"application/json;charset=UTF-8",
                    data:JSON.stringify(article),
                    success:function (data) {
                        if(data.success){
                            //修改成功
                            // alert(date.errMsg)
                            data.articleList.map(function (item, index){
                                rowArticleId=item.articleId;
                                html+='<tr>\n' +
                                    '                                    <td>'+item.headline+'</td>\n' +
                                    '                                    <td>'+new Date(item.lastTime).Format('yyyy-MM-dd')+'</td>\n' +
                                    '                                    <td><a href="../article/updateArticleB?articleId='+rowArticleId+'">修改</a>' +
                                    '                                    |<a href="#" id="delArticle" class="deleteArticle">删除</a></td>\n' +
                                    '                                </tr>'
                            });
                            $(".article-wrap").html(html);
                        }else{
                            alert(data.errMsg)
                        }
                    }
                });

            } else {
                alert(date.errMsg)
            }
        }
    });


    //2、分享图片
    //3、发表文章



    //更新用户信息
    $("#changeUser").click(function () {
        var userUpdate={};
        userUpdate.username = $("#userName").val();
        userUpdate.userPhoneNumber = $("#userPhone").val();
        userUpdate.userImgAddress = $("#userImg").val();
        userUpdate.userEmail = $("#userEmail").val();
        userUpdate.userAddress = $("#userAddress").val();
        $.ajax({
            type:"post",
            url:updateUserInfo,
            dataTypes:"json",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(userUpdate),
            success:function (date) {
                if(date.success){
                    //修改成功
                    alert(date.errMsg)
                }else{
                    alert(date.errMsg)
                }
            }
        });
    });


    //更新图片



    //删除图片
    $(".image-wrap").on("click",".deleteImage",function () {

        // alert(rowImageId);
        var image = {};
        image.imageId = rowImageId;
        $.ajax({
            type: "post",
            url: delImageListUrl,
            data:JSON.stringify(image),
            dataTypes: 'json',
            contentType: 'application/json;charset=utf-8',
            success: function (date) {
                if (date.success) {
                    alert(date.errMsg)

                } else {
                    alert(date.errMsg)
                }
            }
        });
    });







    //删除文章
    $(".article-wrap").on("click",".deleteArticle",function () {

        // alert(rowArticleId)
        //获取删除文章id
        var article={};
        article.articleId=rowArticleId;

        $.ajax({
            type: "post",
            url: delArticleUrl,
            data:JSON.stringify(article),
            dataTypes: 'json',
            contentType: 'application/json;charset=utf-8',
            success: function (date) {
                if (date.success) {
                    alert(date.errMsg)
                } else {
                    alert(date.errMsg)
                }
            }
        });

    });



    //修改密码确认按钮单击事件
    $("#changePwd").click(function () {
        //获取输入的用户名
        var userLoginName = $("#userLoginName").val();
        //获取输入的原来密码
        var oldUserPassword = $("#userPassword").val();
        //获取输入的新密码
        var newUserPassword = $("#newPassword").val();
        //获取输入的新密码
        var confirmNewUserPassword = $("#confirmPassword").val();

        var array = new Array();
        array.push(userLoginName);
        array.push(oldUserPassword);
        array.push(newUserPassword);
        array.push(confirmNewUserPassword);
        //比较两次输入密码是否一致
        if(newUserPassword==confirmNewUserPassword){
            //AJAX事件
            $.ajax({
                type:"post",
                url:updateUPUrl,
                dataTypes:"json",
                contentType:"application/json;charset=UTF-8",
                data:JSON.stringify(array),
                success:function (date) {
                    if(date.success){
                        //修改成功
                        alert(date.errMsg)
                    }else{
                        alert(date.errMsg)
                    }
                }
            })
        }else{
            alert("两次输入密码不一致，请重新输入")
        }
    })
});