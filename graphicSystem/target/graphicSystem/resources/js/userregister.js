$(function () {
    var url = '/graphicSystem/user/registerUser';
    var user = {};
    $('#submit').click(function () {
        var username =$('#userName').val();
        var userLoginName = $('#userLoginName').val();
        var userPassword = $('#userPassword').val();
        var confirmPassword = $('#confirmPassword').val();
        if(userPassword!=confirmPassword){
            alert("两次输入密码不一致！");
            return;
        }
        $.ajax({
            url:url,
            type:'POST',
            data:JSON.stringify({"username":username,"userLoginName":userLoginName,"userPassword":userPassword}),
            dataTypes:'json',
            contentType:"application/json;charset=UTF-8",
            success:function (date) {
                if(date.success){
                    alert(date.errMsg);
                    window.location.href='../user/userLoginB'
                }else{
                    alert(date.errMsg);
                }
            }
        })
    })

    $("#yyzh").click(function () {
        window.location.href='../../WEB-INF/html/useradmin/userlogin.html'
    })
});