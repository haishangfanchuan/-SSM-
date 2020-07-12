$(function () {

    var url = '/graphicSystem/user/userLogin';
    $("#userLogin").click(function () {
       var userLoginName=$("#userLoginName").val();
       var userPassword=$("#userPassword").val();
        if(userPassword!=null||userLoginName!=null){
            /*var formData = new FormData();
            formData.append("userStr",JSON.stringify(user));*/
            $.ajax({
                url:url,
                type:'POST',
                data:JSON.stringify({userLoginName:userLoginName,userPassword:userPassword}),
                dataTypes:'json',
                contentType:'application/json;charset=utf-8',
                processData:false,
                cache:false,
                success:function (date) {
                    if(date.success){
                        window.location.href="../user/getUserInfoB";
                    }else{
                        alert(date.errMsg)
                    }
                }
            })
        }else{
            alert("用户名或者密码为空！")
        }

    });

});