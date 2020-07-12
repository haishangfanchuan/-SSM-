$(function () {
    // 获取文章类别的接口
    var articleListUrl = '/graphicSystem/article/queryArticleList';
    // 文章页码
    var pageIndex = 1;
    // 文章空标题（文章标题是用来查询文章）
    var articleName = '';
    var article={};
    article.headline=articleName;
    addItems(article);

    //获取前页码

    //获取文章总数
    var articleCount=0;


    //搜索文章
    $("#errorSearchFormSubmitButton").click(function () {
        articleName = $("#search").val();
        var article={};
        article.headline=articleName;
        // alert(articleName);
        addItems(article);
    });

    //查看文章详情


    // 文章列表的方法
    function addItems(article) {

        // var article={};
        // article.headline="";

        // 需要重述url
        var url = articleListUrl + '?rowIndex=' + pageIndex;

        $.ajax({
            url:url,
            type:'POST',
            data:JSON.stringify(article),
            dataTypes:'json',
            contentType:"application/json;charset=UTF-8",
            success:function (data) {
                if (data.success) {
                    var html = '';
                    articleCount = data.articleCount;
                    // 将数据库的文章列表遍历到html页面中
                    data.articleList.map(function (item, index) {
                        html += '<div class="portfolioOneItemWrapper">' +
                            '<a href="../article/queryArticleByIdB?articleId='+item.articleId+'" data-id="' + item.articleId + '" class="portfolioOneItemImageWrapper go">' +
                            '<img src="' + item.coverImgAddress + '"/>' +
                            '</a>' +
                            '<div class="portfolioOneItemInfoWrapper">' +
                            '<h4 class="portfolioOneItemTitle">' + item.headline + '</h4>' +
                            '</div>' +
                            '<div class="portfolioOneItemButtonsWrapper">' +
                            '<a href="#" data-id="' + item.articleId + '" class="portfolioOneExpandButton go">' + item.user.username + '</a>' +
                            '<a href="#" data-id="' + item.articleId + '" class="portfolioOneDetailsButton go">'+new Date(item.lastTime).Format('yyyy-MM-dd')+'</a>' +
                            '</div>' +
                            '</div>';
                    });
                    $('.article-list').html(html);
                }else{
                    alert(data.errMsg)
                }
            }
        });
        /*$.getJSON(url, JSON.stringify(article),
            function (data) {
            if (data.success) {
                var html = '';
                 articleCount = data.articleCount;
                // 将数据库的文章列表遍历到html页面中
                data.articleList.map(function (item, index) {
                    html += '<div class="portfolioOneItemWrapper">' +
                        '<a href="../article/queryArticleByIdB?articleId='+item.articleId+'" data-id="' + item.articleId + '" class="portfolioOneItemImageWrapper go">' +
                        '<img src="' + item.coverImgAddress + '"/>' +
                        '</a>' +
                        '<div class="portfolioOneItemInfoWrapper">' +
                        '<h4 class="portfolioOneItemTitle">' + item.headline + '</h4>' +
                        '</div>' +
                        '<div class="portfolioOneItemButtonsWrapper">' +
                        '<a href="#" data-id="' + item.articleId + '" class="portfolioOneExpandButton go">' + item.user.username + '</a>' +
                        '<a href="#" data-id="' + item.articleId + '" class="portfolioOneDetailsButton go">'+new Date(item.lastTime).Format('yyyy-MM-dd')+'</a>' +
                        '</div>' +
                        '</div>';
                });
                $('.article-list').html(html);
            }
        });*/
    }


});