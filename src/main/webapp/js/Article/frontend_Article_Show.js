var articleID = 1;
var userID = 1;
var articleTypeID = 1;
var articleTitle = "";
var articleContent = "";
var articleDateTime = "";
var articleModified = "";
var articleLike = 1;
var articleStatus = "";
var saveUserName = "";
var saveArticleTypeContent = "";
var savearticleID = sessionStorage.getItem("articleID");
var savearticleTitle = sessionStorage.getItem("articleTitle");

//比讚的按鈕
function myFunction(x) { 
    // x.classList.toggle("fa-thumbs-down");// 動態可以正讚跟倒讚互換
    // let articleLike = $(this).closest("i").find("i.fa fa-thumbs-up").text();
    console.log(savearticleID);
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "articleID":articleID,
            "action": "searchArticleLikeID"
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            let articleLikeID = data.articleLikeID;
            let articleID = data.articleID;
            let userID = data.userID;
            let articleLikeStatus = data.articleLikeStatus;
            if(articleLikeStatus == "1"){
                $.ajax({
                    url: "http://localhost:8080/ski/FrontendArticle",
                    type: "Post",
                    data: {
                        "articleLikeID":articleLikeID,
                        "articleID":articleID,
                        "userID":userID,
                        "articleLikeStatus":"0",
                        "action": "updateStatus"
                    },
                    dataType: "json",
                    success: function (data) {
                        console.log($("i.fa").text());
                        $.ajax({
                            url: "http://localhost:8080/ski/FrontendArticle",
                            type: "Post",
                            data: {
                                "articleID":articleID,
                                "action": "searchArticle"
                            },
                            dataType: "json",
                            success: function (data) {

                            }
                        });

                    }
                });
            }else if(articleLikeStatus == "0"){
                $.ajax({
                    url: "http://localhost:8080/ski/FrontendArticle",
                    type: "Post",
                    data: {
                        "articleLikeID":articleLikeID,
                        "articleID":articleID,
                        "userID":userID,
                        "articleLikeStatus":"1",
                        "action": "updateStatus"
                    },
                    dataType: "json",
                    success: function (data) {

                    }
                });
            }
        }
    });
}

//顯示全部
$(document).ready(function init() {
    // console.log(savearticleTitle);
    console.log("js載入成功");
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "articleID":savearticleID,
            "action": "searchArticle"
        },
        dataType: "json",
        success: function (data) {
            // console.log(data);
            let list_html = "";
            let articleID = data.articleID;
            let userID = data.userID;
            let articleTypeID = data.articleTypeID;
            let articleTitle = data.articleTitle;
            let articleContent = data.articleContent;
            let articleDateTime = data.articleDateTime;
            let articleModified = data.articleModified;
            let articleLike = data.articleLike;
            let articleStatus = data.articleStatus;
            // console.log(articleModified);

            $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "userID": userID,
                    "action": "getMemberUserName"
                },
                dataType: "json",
                success: function (data) {
                    saveUserName = data.userName;
                    $.ajax({
                        url: "http://localhost:8080/ski/FrontendArticle",
                        type: "Post",
                        data: {
                            "articleTypeID": articleTypeID,
                            "action": "searchID",
                        },
                        dataType: "json",
                        success: function (data) {
                            saveArticleTypeContent = data.articleTypeContent;
                            if(articleModified==undefined){
                                if (articleStatus == "0") {
                                    list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;發文時間:${articleDateTime}&ensp;&ensp;by${saveUserName}</p>
                                            <hr>
                                            <p>${articleContent}</p>
                                            <br>
                                            <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up">${articleLike}</i><span><i class="fa fa-commenting"></i></span><span>留言數</span><i class="fa fa-exclamation-triangle"></i></p>`;
                                    $("#type").append(list_html); // 加入網頁表格中
                                }
                            }else{
                                if (articleStatus == "0") {
                                    list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;修改時間:${articleModified}&ensp;&ensp;by${saveUserName}</p>
                                            <hr>
                                            <p>${articleContent}</p>
                                            <br>
                                            <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up">${articleLike}</i><span><i class="fa fa-commenting"></i></span><span>留言數</span><i class="fa fa-exclamation-triangle"></i></p>`;
                                    $("#type").append(list_html); // 加入網頁表格中
                                }
                            }
                        },
                    });
                }
            });
        }
    });
});

