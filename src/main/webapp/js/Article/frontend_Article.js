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
var savearticleTitle = sessionStorage.getItem("savearticleTitle");

//顯示全部
$(document).ready(function init() {

    console.log("js載入成功");
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "action": "showAll"
        },
        dataType: "json",
        success: function (data) {
            if (data.length <= 10) {
                for (let i = 0; i < data.length; i++) {
                    let list_html = "";
                    let articleID = data[i].articleID;
                    let userID = data[i].userID;
                    let articleTypeID = data[i].articleTypeID;
                    let articleTitle = data[i].articleTitle;
                    let articleDateTime = data[i].articleDateTime;
                    let articleLike = data[i].articleLike;
                    let articleStatus = data[i].articleStatus;
        
                    $.ajax({
                        url: "http://localhost:8080/ski/FrontendArticle",
                        type: "Post",
                        data: {
                            "userID": userID,
                            "action": "getMemberUserName"
                        },
                        dataType: "json",
                        success: function (data) {
                            // console.log(data);
                            saveUserName = data.userName;
                            $.ajax({
                                url: "http://localhost:8080/ski/BackendArticleType",
                                type: "Post",
                                data: {
                                    "articleTypeID": articleTypeID,
                                    "action": "searchIDAndContent",
                                    "type": "Number"
                                },
                                dataType: "json",
                                success: function (data) {
                                    saveArticleTypeContent = data.articleTypeContent;
                                    if (articleStatus == "0") {
                                        list_html += `<tr class="tr">
                                        <td>
                                            <a href="#"><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName}</p></a>
                                        </td>
                                        </tr>`;
                                        $("#type > table > tbody").append(list_html); // 加入網頁表格中
                                    }
                                },
                            })
                        }
                    });
                }
        
            } else if (data.length > 10) {
                for (let i = 0; i < 10; i++) {
                    let list_html = "";
                    let articleID = data[i].articleID;
                    let userID = data[i].userID;
                    let articleTypeID = data[i].articleTypeID;
                    let articleTitle = data[i].articleTitle;
                    let articleDateTime = data[i].articleDateTime;
                    let articleLike = data[i].articleLike;
                    let articleStatus = data[i].articleStatus;
        
                    $.ajax({
                        url: "http://localhost:8080/ski/FrontendArticle",
                        type: "Post",
                        data: {
                            "userID": userID,
                            "action": "getMemberUserName"
                        },
                        dataType: "json",
                        success: function (data) {
                            // console.log(data);
                            saveUserName = data.userName;
                            $.ajax({
                                url: "http://localhost:8080/ski/BackendArticleType",
                                type: "Post",
                                data: {
                                    "articleTypeID": articleTypeID,
                                    "action": "searchIDAndContent",
                                    "type": "Number"
                                },
                                dataType: "json",
                                success: function (data) {
                                    saveArticleTypeContent = data.articleTypeContent;
                                    if (articleStatus == "0") {
                                        list_html += `<tr class="tr">
                                        <td>
                                            <a href="#"><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName}</p></a>
                                        </td>
                                        </tr>`;
                                        $("#type > table > tbody").append(list_html); // 加入網頁表格中
                                    }
                                },
                            })
                        }
                    });
                }
            }


        }
    });
});


//發表文章
$("#type").on("click", "button.btn_addArticle", function (e) {
    window.location.href = "http://localhost:8080/ski/article/frontend_Article_AddAndUpdate.html"; // 將存下來的ID丟到此網址
})



//點文章標題
$("#type").on("click", "a", function (e) {
    articleTitle = $(this).closest("tr").find("p.article").text();
    articleID = $(this).closest("tr").find("p.ID").text();
    sessionStorage.setItem("articleID", articleID); // 存下文章ID
    sessionStorage.setItem("articleTitle", articleTitle); // 存下文章標題
    window.location.href = "http://localhost:8080/ski/article/frontend_Article_Show.html"; // 將存下來的ID丟到此網址
});


//搜尋文章標題
$("#type").on("click", "button.search", function (e) {

    var task_name_search = $("input.text_search").val().trim(); // 將輸入的文字存下來
    if ($("input.text_search").val().trim() != "") { // 如果輸入的文字扣掉空格不為空值的話就執行
        if (!$(this).hasClass("-disabled")) { // 如果新增按鈕的class沒有"-disabled"就執行
            $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "articleTitle": task_name_search,
                    "action": "searchTitle"
                },
                dataType: "json",
                success: function (data) {
                    $("#type").find("button.btn_limit").remove(); // 刪除分頁按鈕
                    $("#type").find("button.btn_limit2").remove(); // 刪除分頁按鈕
                    $("#type > table > tbody").find("tr").remove(); // 刪除原網頁表格
                    if (data.length <= 10) {
                        for (let i = 0; i < data.length; i++) {
                            let list_html = "";
                            let articleID = data[i].articleID;
                            let userID = data[i].userID;
                            let articleTypeID = data[i].articleTypeID;
                            let articleTitle = data[i].articleTitle;
                            let articleDateTime = data[i].articleDateTime;
                            let articleLike = data[i].articleLike;
                            let articleStatus = data[i].articleStatus;
                
                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "userID": userID,
                                    "action": "getMemberUserName"
                                },
                                dataType: "json",
                                success: function (data) {
                                    // console.log(data);
                                    saveUserName = data.userName;
                                    $.ajax({
                                        url: "http://localhost:8080/ski/BackendArticleType",
                                        type: "Post",
                                        data: {
                                            "articleTypeID": articleTypeID,
                                            "action": "searchIDAndContent",
                                            "type": "Number"
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            saveArticleTypeContent = data.articleTypeContent;
                                            if (articleStatus == "0") {
                                                list_html += `<tr class="tr">
                                                <td>
                                                    <a href="#"><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName}</p></a>
                                                </td>
                                                </tr>`;
                                                $("#type > table > tbody").append(list_html); // 加入網頁表格中
                                            }
                                        },
                                    })
                                }
                            });
                        }
                
                    } else if (data.length > 10) {
                        for (let i = 0; i < 10; i++) {
                            let list_html = "";
                            let articleID = data[i].articleID;
                            let userID = data[i].userID;
                            let articleTypeID = data[i].articleTypeID;
                            let articleTitle = data[i].articleTitle;
                            let articleDateTime = data[i].articleDateTime;
                            let articleLike = data[i].articleLike;
                            let articleStatus = data[i].articleStatus;
                
                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "userID": userID,
                                    "action": "getMemberUserName"
                                },
                                dataType: "json",
                                success: function (data) {
                                    // console.log(data);
                                    saveUserName = data.userName;
                                    $.ajax({
                                        url: "http://localhost:8080/ski/BackendArticleType",
                                        type: "Post",
                                        data: {
                                            "articleTypeID": articleTypeID,
                                            "action": "searchIDAndContent",
                                            "type": "Number"
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            saveArticleTypeContent = data.articleTypeContent;
                                            if (articleStatus == "0") {
                                                list_html += `<tr class="tr">
                                                <td>
                                                    <a href="#"><p class="ID -none">${articleID}</p><p class="article text-start">讚數:${articleLike} &ensp;&ensp;留言:0 &ensp;&ensp;#${saveArticleTypeContent}</p><p class="article text-center">${articleTitle}</p><p class="article text-end">${articleDateTime} by${saveUserName}</p></a>
                                                </td>
                                                </tr>`;
                                                $("#type > table > tbody").append(list_html); // 加入網頁表格中
                                            }
                                        },
                                    })
                                }
                            });
                        }
                    }
                }
            });
            
        } else {
            alert("搜尋不可為空");
        }
    }
});