$(function () {

    var userInfoUrl = "/graphicSystem/user/getUserInfo";


    /*var user = {};
    // 将数据传输给HTML
    $.getJSON(userInfoUrl, function (data) {
        if (data.success) {
            user = data.user;
            if (user.userImgAddress != null || user.userImgAddress != undefined) {
                $('#userImg').attr('src', user.userImgAddress);
            }
            $('#userName').text(user.username);
        }
    });*/


    var user = {};
    //获取用户信息单击事件
    $.ajax({
        type: "post",
        url: userInfoUrl,
        dataTypes: 'json',
        contentType: 'application/json;charset=utf-8',
        success: function (date) {
            if (date.success) {
                user = date.loginUser;
                //回写用户名和头像
                $("#userName").text(user.username);
                $("#userImg").attr('src', user.userImgAddress);

            } else {
                alert(date.errMsg)
            }
        }
    })

});


