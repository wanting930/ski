var saveArticleID = sessionStorage.getItem("saveArticleID");
// var saveArticleID = 2;
var articleID1 =1;
var userID1 = 1;
var articleTypeID1 = 1;
var articleTitle1 = "";
var articleContent1 = "";
var articleDateTime1 = "";
var articleModified1 = "";
var articleLike1 = 1;
var articleStatus1 = "";


//顯示全部
$(document).ready(function init() {

    console.log("js載入成功");
    console.log(saveArticleID);
    $.ajax({
        url: "http://localhost:8080/ski/BackendArticle",
        type: "Post",
        data: {
            "articleID": saveArticleID,
            "userID": userID1,
            "articleTypeID": articleTypeID1,
            "articleTitle": articleTitle1,
            "articleContent": articleContent1,
            "articleDateTime": articleDateTime1,
            "articleModified": articleModified1,
            "articleLike": articleLike1,
            "articleStatus": articleStatus1,
            "action": "showAll"
        },
        dataType: "json",
        success: function (data) {
            // console.log(data.length);
            console.log(data);
            console.log("後端文章載入成功");
            let list_html = "";
            var articleID = data[saveArticleID-1].articleID;
            var userID = data[articleID-1].userID;
            var articleTypeID = data[articleID-1].articleTypeID;
            var articleTitle = data[articleID-1].articleTitle;
            var articleContent = data[articleID-1].articleContent;
            var articleDateTime = data[articleID-1].articleDateTime;
            var articleModified = data[articleID-1].articleModified;
            var articleLike = data[articleID-1].articleTitle;
            var articleStatus = data[articleID-1].articleStatus;

            if(articleStatus == "1"){
                list_html += `<p class="datetime">發表時間:${articleDateTime} by ${userID}, 修改時間時間:${articleModified}</p>
                                <h1 class="title">${articleTitle}</h1>
                                <h5 class="type">${articleTypeID}</h5>
                                <div class="content_section">
                                <p class="content">${articleContent}</p>
                                <button class="changeStatus1">下架文章</button>
                                <button class="changeStatus0 -none">重新上架文章</button>
                                </div>
                                `;
            }else if(articleStatus == "0"){
                list_html += `<p class="datetime">發表時間:${articleDateTime} by ${userID}, 修改時間時間:${articleModified}</p>
                                <h1 class="title">${articleTitle}</h1>
                                <h5 class="type">${articleTypeID}</h5>
                                <div class="content_section">
                                <p class="content">${articleContent}</p>
                                <button class="changeStatus1 -none">下架文章</button>
                                <button class="changeStatus0">重新上架文章</button>
                                </div>
                                `;
            }

            $("#type").append(list_html); // 加入網頁表格中
            number = articleID; // 紀錄目前分類編號最後一碼

        },
        error: function (err) {
            console.log("後端文章載入失敗");
            console.log(err);
        }

    })
});
//修改狀態1(下架文章)
$("#type").on("click", "button.changeStatus1", function () {
    console.log("下架文章囉!");
    $("#type").closest("div").find("button.changeStatus1").addClass("-none"); // 隱藏下架按鈕
    $("#type").closest("div").find("button.changeStatus0").removeClass("-none"); // 顯示上架文章按鈕
    $.ajax({
        url: "http://localhost:8080/ski/BackendArticle",
        type: "Post",
        data: {
            "articleID": saveArticleID,
            "userID": userID1,
            "articleTypeID": articleTypeID1,
            "articleTitle": articleTitle1,
            "articleContent": articleContent1,
            "articleDateTime": articleDateTime1,
            "articleModified": articleModified1,
            "articleLike": articleLike1,
            "articleStatus": "1",
            "action": "updateStatus1"
        },
        dataType: "json",
        success: function (data) {
            alert("下架文章成功");
        },
        error: function (err) {
            console.log("後端文章載入失敗");
            console.log(err);
        }
    })

});

//修改狀態0(重新上架文章)
$("#type").on("click", "button.changeStatus0", function () {
    console.log("重新上架文章囉!");
    $("#type").closest("div").find("button.changeStatus1").removeClass("-none"); // 隱藏下架按鈕
    $("#type").closest("div").find("button.changeStatus0").addClass("-none"); // 顯示上架文章按鈕
    $.ajax({
        url: "http://localhost:8080/ski/BackendArticle",
        type: "Post",
        data: {
            "articleID": saveArticleID,
            "userID": userID1,
            "articleTypeID": articleTypeID1,
            "articleTitle": articleTitle1,
            "articleContent": articleContent1,
            "articleDateTime": articleDateTime1,
            "articleModified": articleModified1,
            "articleLike": articleLike1,
            "articleStatus": "0",
            "action": "updateStatus0"
        },
        dataType: "json",
        success: function (data) {
            alert("重新上架成功");
        },
        error: function (err) {
            console.log("後端文章載入失敗");
            console.log(err);
        }
    })
});