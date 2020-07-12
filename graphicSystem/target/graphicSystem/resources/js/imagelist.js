$(function () {
    // 获取文章类别的接口
    var imageListUrl = '/graphicSystem/image/getImageList';
    // 图片页码
    var pageIndex = 1;
    // 图片空标题（图片空标题是用来查询所有文章）
    var imageName = '';
    var image={};
    image.imageName=imageName;


    addItems(image);

    //获取前页码

    //获取文章总数
    var imageCount=0;


    //搜索图片
    $("#SearchImageListSubmitButton").click(function () {
        imageName = $("#search").val();
        var image={};
        image.imageName=imageName;
        addItems(image);
    });




    // 图片列表的方法
    function addItems(image) {

        // var article={};
        // article.headline="";

        // 需要重述url
        var url = imageListUrl + '?rowIndex=' + pageIndex;

        $.ajax({
            url:url,
            type:'POST',
            data:JSON.stringify(image),
            dataTypes:'json',
            contentType:"application/json;charset=UTF-8",
            success:function (data) {
                if (data.success) {
                    var html = '';
                    imageCount = data.imageCount;
                    // 将数据库的文章列表遍历到html页面中
                    data.imageList.map(function (item, index) {

                        html+='<div class="portfolioFilterableItemWrapper" data-type="websites,logos">\n' +
                            '                <a href="../image/getImageByIdB?imageId='+item.imageId+'" class="portfolioFilterableItemImageWrapper">' +
                            '                <img src="'+item.imageAddress+'" alt="" class="largeImage"/></a>\n' +
                            '                <div class="portfolioFilterableItemInfoWrapper">\n' +
                            '                    <h4 class="portfolioFilterableItemTitle"></h4>\n' +
                            '                    <p>'+item.imageName+'</p>\n' +
                            '                </div>\n' +
                            '                <div class="portfolioFilterableItemButtonsWrapper">\n' +
                            '                    <a href="'+item.imageAddress+'" class="portfolioFilterableExpandButton">查看图片</a>\n' +
                            '                    <a href="../image/getImageByIdB?imageId='+item.imageId+'" class="portfolioFilterableDetailsButton">图片详情</a>\n' +
                            '                </div>\n' +
                            '            </div>';

                    });
                    $('.image-list').html(html);
                }else{
                    alert(data.errMsg)
                }
            }
        });
    }


});