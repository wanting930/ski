var articleID1 = 1;
var userID1 = 1;
var articleTypeID1 = 1;
var articleTitle1 = "";
var articleContent1 = "";
var articleDateTime1 = "";
var articleModified1 = "";
var articleLike1 = 1;
var articleStatus1 = "";
var saveUserName = "";
var saveArticleTypeContent = "";
const savearticleID = sessionStorage.getItem("articleID");
var savearticleTitle = sessionStorage.getItem("articleTitle");
const saveUserID = sessionStorage.getItem("userID");
var savearticleLike = 0;
var savecomment = 0;
var commentShow = "hidden";
var open1 = 0;

//顯示全部
$(document).ready(async function init() {
    // console.log(savearticleTitle);
    
    console.log("js載入成功");
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "articleID": savearticleID,
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
                    saveUserName=data.userName

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
                            $.ajax({
                                url: "http://localhost:8080/ski/FrontendArticle",
                                type: "Post",
                                data: {
                                    "articleID": savearticleID,
                                    "userID": userID,
                                    "action": "selectByArticleIDuserID"
                                },
                                dataType: "json",
                                success: function (data) {
                                    console.log(data);
                                    let articleLikeID = data.articleLikeID;
                                    let articleID = data.articleID;
                                    let userID = data.userID;
                                    // console.log(data.articleLikeStatus);
                                    let articleLikeStatus = data.articleLikeStatus;
                                    if(saveUserID == null){
                                        articleLikeStatus = "1";
                                    }
                                    $.ajax({
                                        url: "http://localhost:8080/ski/FrontendArticle",
                                        type: "Post",
                                        data: {
                                            "articleID": savearticleID,
                                            "action": "showComment"
                                        },
                                        dataType: "json",
                                        success: function (data) {
                                            if (articleLikeStatus == "1") {
                                                if (articleModified == undefined) {
                                                    if (articleStatus == "0") {
                                                        list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;發文時間:${articleDateTime}&ensp;&ensp;by${saveUserName}</p>
                                                                <hr>
                                                                <p>${articleContent}</p>
                                                                <br>
                                                                <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up like-icon">${articleLike}</i><span><i class="fa fa-commenting" onclick="showComment()"></i></span><span class="comment">${data.length}</span><i class="fa fa-exclamation-triangle" onclick="openReportForm()"></i></p>`;
                                                        $("#type").append(list_html); // 加入網頁表格中
                                                    }
                                                } else {
                                                    if (articleStatus == "0") {
                                                        list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;修改時間:${articleModified}&ensp;&ensp;by${saveUserName}</p>
                                                                <hr>
                                                                <p>${articleContent}</p>
                                                                <br>
                                                                <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up like-icon">${articleLike}</i><span><i class="fa fa-commenting" onclick="showComment()"></i></span><span class="comment">${data.length}</span><i class="fa fa-exclamation-triangle" onclick="openReportForm()"></i></p>`;
                                                        $("#type").append(list_html); // 加入網頁表格中
                                                    }
                                                }
                                            } else if (articleLikeStatus == "0") {
                                                if (articleModified == undefined) {
                                                    if (articleStatus == "0") {
                                                        list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;發文時間:${articleDateTime}&ensp;&ensp;by${saveUserName}</p>
                                                                <hr>
                                                                <p>${articleContent}</p>
                                                                <br>
                                                                <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up like-icon active">${articleLike}</i><span><i class="fa fa-commenting" onclick="showComment()"></i></span><span class="comment">${data.length}</span><i class="fa fa-exclamation-triangle" onclick="openReportForm()"></i></p>`;
                                                        $("#type").append(list_html); // 加入網頁表格中
                                                    }
                                                } else {
                                                    if (articleStatus == "0") {
                                                        list_html += `<p class="articleType text-start">#${saveArticleTypeContent}&ensp;&ensp;${articleTitle}&ensp;&ensp;修改時間:${articleModified}&ensp;&ensp;by${saveUserName}</p>
                                                                <hr>
                                                                <p>${articleContent}</p>
                                                                <br>
                                                                <p class="right-align"><i onclick="myFunction(this)" class="fa fa-thumbs-up like-icon active">${articleLike}</i><span><i class="fa fa-commenting" onclick="showComment()"></i></span><span class="comment">${data.length}</span><i class="fa fa-exclamation-triangle" onclick="openReportForm()"></i></p>`;
                                                        $("#type").append(list_html); // 加入網頁表格中
                                                    }
                                                }
                                            }
                                        }
                                    });

                                }
                            });
                            articleID1 = articleID;
                            userID1 = userID;
                            articleTypeID1 = articleTypeID;
                            articleTitle1 = articleTitle;
                            articleContent1 = articleContent;
                            articleDateTime1 = articleDateTime;
                            articleModified1 = articleModified;
                            articleLike1 = 1;
                            articleStatus1 = articleStatus;
                        },
                    });
                }
            });
        }
    });
});

//比讚的按鈕
function myFunction(x) {
    if(saveUserID!=null){
        x.classList.toggle('active');
        // x.classList.toggle("fa-thumbs-down");// 動態可以正讚跟倒讚互換
    // let articleLike = $(this).closest("i").find("i.fa fa-thumbs-up").text();
    // console.log(savearticleID);
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "articleID": savearticleID,
            "userID": saveUserID,
            "action": "searchArticleLikeID"
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            let articleLikeID = data.articleLikeID;
            let articleID = data.articleID;
            let userID = data.userID;
            let articleLikeStatus = data.articleLikeStatus;
            if (articleLikeStatus == "1") {
                $.ajax({
                    url: "http://localhost:8080/ski/FrontendArticle",
                    type: "Post",
                    data: {
                        "articleLikeID": articleLikeID,
                        "articleID": articleID,
                        "userID": userID,
                        "articleLikeStatus": "0",
                        "action": "updateStatus"
                    },
                    dataType: "json",
                    success: function (data) {
                        savearticleLike = Number($("i.fa.fa-thumbs-up").text()) + 1;
                        $.ajax({
                            url: "http://localhost:8080/ski/FrontendArticle",
                            type: "Post",
                            data: {
                                "articleID": articleID,
                                "userID": userID1,
                                "articleTypeID": articleTypeID1,
                                "articleTitle": articleTitle1,
                                "articleContent": articleContent1,
                                "articleDateTime": articleDateTime1,
                                "articleModified": articleModified1,
                                "articleLike": savearticleLike,
                                "articleStatus": "0",
                                "action": "updateLike"
                            },
                            dataType: "json",
                            success: function (data) {
                                console.log("按讚");
                                const likeCountElement = document.querySelector('i.fa.fa-thumbs-up');
                                likeCountElement.textContent = savearticleLike;
                            }
                        });
                    }
                });
            } else if (articleLikeStatus == "0") {
                $.ajax({
                    url: "http://localhost:8080/ski/FrontendArticle",
                    type: "Post",
                    data: {
                        "articleLikeID": articleLikeID,
                        "articleID": articleID,
                        "userID": userID,
                        "articleLikeStatus": "1",
                        "action": "updateStatus"
                    },
                    dataType: "json",
                    success: function (data) {
                        savearticleLike = Number($("i.fa.fa-thumbs-up").text()) - 1;
                        $.ajax({
                            url: "http://localhost:8080/ski/FrontendArticle",
                            type: "Post",
                            data: {
                                "articleID": articleID,
                                "userID": userID1,
                                "articleTypeID": articleTypeID1,
                                "articleTitle": articleTitle1,
                                "articleContent": articleContent1,
                                "articleDateTime": articleDateTime1,
                                "articleModified": articleModified1,
                                "articleLike": savearticleLike,
                                "articleStatus": "1",
                                "action": "updateLike"
                            },
                            dataType: "json",
                            success: function (data) {
                                console.log("收讚");
                                const likeCountElement = document.querySelector('i.fa.fa-thumbs-up'); // 即時更新畫面的按讚數
                                likeCountElement.textContent = savearticleLike;
                            }
                        });
                    }
                });
            }
        }
    });
    }else{
        alert("請登入會員才能點讚");
    }
    
    
}

//留言按鈕(顯示此文章的留言)
async function showComment() {
    let list_input = "";
    if (saveUserID != null) {
        list_input = `<tr>
        <td>
            <textarea id="content" name="content" rows="5" cols=20" class="content"></textarea><button class="btn_addcomment">送出</button>
        </td>
    </tr>`;
    }else{
        list_input = `<a href="/ski/member/login.html">請登入會員才能留言</ㄇ>`;
    }

    $("thead.thead").append(list_input);
    // 清空留言
    // $("tbody.tbody").empty();

    if (commentShow == "hidden") {
        commentShow = "show";

        // 發送獲取留言的異步請求
        const commentData = await $.ajax({
            url: "http://localhost:8080/ski/FrontendArticle",
            type: "Post",
            data: {
                "articleID": savearticleID,
                "action": "showComment"
            },
            dataType: "json"
        });

        // 按順序添加留言
        for (let i = 0; i < commentData.length; i++) {
            const comment = commentData[i];
            const commentID = comment.commentID;
            const userID = comment.userID;
            const commentContent = comment.commentContent;
            const commentDateTime = comment.commentDateTime;

            // 發送獲取使用者資訊的異步請求
            const userData = await $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "userID": userID,
                    "action": "getMemberUserName"
                },
                dataType: "json"
            });

            const userName = userData.userName;

            const list_comment = `
                <tr>
                    <td>
                        <div class="dontBR">
                            <img id="preview${i}" src="#" alt="預覽圖片" class="fixed-image" />
                            <p class="text-left">${userName}</p>
                        </div>
                        <br>
                        <p class="text-left">${commentContent}</p>
                        <p class="time right-align">${commentDateTime}</p>
                    </td>
                </tr>
            `;

            $("tbody.tbody").append(list_comment);

            const preview = $(`#preview${i}`);

            // 發送獲取大頭貼圖片的異步請求
            const avatarData = await $.ajax({
                url: "/ski/member/memberInfo",
                type: "POST",
                dataType: "json",
                data: JSON.stringify({ userID: userID })
            });

            const avatar = avatarData.photo;
            const uint8Array = new Uint8Array(avatar);
            const blob = new Blob([uint8Array]);
            preview.attr('src', URL.createObjectURL(blob));
        }
    }else if(commentShow == "show" && open1 == 1){
        // 發送獲取留言的異步請求
         // 清空留言
         $("tbody.tbody").empty();
         // 清空新增留言
         $("thead.thead").empty();
         list_input = `<tr>
        <td>
            <textarea id="content" name="content" rows="5" cols=20" class="content"></textarea><button class="btn_addcomment">送出</button>
        </td>
    </tr>`;
    $("thead.thead").append(list_input);
        const commentData = await $.ajax({
            url: "http://localhost:8080/ski/FrontendArticle",
            type: "Post",
            data: {
                "articleID": savearticleID,
                "action": "showComment"
            },
            dataType: "json"
        });

        // 按順序添加留言
        for (let i = 0; i < commentData.length; i++) {
            const comment = commentData[i];
            const commentID = comment.commentID;
            const userID = comment.userID;
            const commentContent = comment.commentContent;
            const commentDateTime = comment.commentDateTime;

            // 發送獲取使用者資訊的異步請求
            const userData = await $.ajax({
                url: "http://localhost:8080/ski/FrontendArticle",
                type: "Post",
                data: {
                    "userID": userID,
                    "action": "getMemberUserName"
                },
                dataType: "json"
            });

            const userName = userData.userName;

            const list_comment = `
                <tr>
                    <td>
                        <div class="dontBR">
                            <img id="preview${i}" src="#" alt="預覽圖片" class="fixed-image" />
                            <p class="text-left">${userName}</p>
                        </div>
                        <br>
                        <p class="text-left">${commentContent}</p>
                        <p class="time right-align">${commentDateTime}</p>
                    </td>
                </tr>
            `;

            $("tbody.tbody").append(list_comment);

            const preview = $(`#preview${i}`);

            // 發送獲取大頭貼圖片的異步請求
            const avatarData = await $.ajax({
                url: "/ski/member/memberInfo",
                type: "POST",
                dataType: "json",
                data: JSON.stringify({ userID: userID })
            });

            const avatar = avatarData.photo;
            const uint8Array = new Uint8Array(avatar);
            const blob = new Blob([uint8Array]);
            preview.attr('src', URL.createObjectURL(blob));
            open1 = 0;
        }
    } else if (commentShow == "show") {
        commentShow = "hidden";
        // 清空留言
        $("tbody.tbody").empty();
        // 清空新增留言
        $("thead.thead").empty();
    }
}

//留言按鈕(顯示此文章的留言)(舊寫法)
// async function showComment() {
//     console.log("觸發到留言按鈕");
//     // var data = { //大頭貼
//     //     userID: sessionStorage.getItem("userID")
//     // };
//     // var jsonData = JSON.stringify(data);
//     // $.ajax({
//     //     url: "/ski/member/memberInfo",
//     //     type: "POST",
//     //     dataType: "json", //指定回傳的資料格式
//     //     data: jsonData,
//     //     success: function (resp) {
//     //         console.log(resp)
//     //         const avatar = resp.photo;
//     //         const uint8Array = new Uint8Array(avatar);
//     //         const blob = new Blob([uint8Array]);
//     //         preview.attr('src', URL.createObjectURL(blob));
//     //     }
//     // });
//     let list_input = "";
//     if (saveUserID != null) {
//         list_input = `<tr>
//         <td>
//             <textarea id="content" name="content" rows="5" cols=20" class="content"></textarea><button class="btn_addcomment">送出</button>
//         </td>
//     </tr>`;
//     }else{
//         list_input = `<a href="/ski/member/login.html">請登入會員才能留言</ㄇ>`;
//     }

//     $("thead.thead").append(list_input);
//     if (commentShow == "hidden") {
//         commentShow = "show";
//         $.ajax({
//             url: "http://localhost:8080/ski/FrontendArticle",
//             type: "Post",
//             data: {
//                 "articleID": savearticleID,
//                 "action": "showComment"
//             },
//             dataType: "json",
//             success: function (data) {
//                 // console.log(data);
//                 if (data.length <= 10) {
//                     for (let i = 0; i < data.length; i++) {
//                         let commentID = data[i].commentID;
//                         let articleID = data[i].articleID;
//                         let userID = data[i].userID;
//                         let commentContent = data[i].commentContent;
//                         let commentDateTime = data[i].commentDateTime;
//                         let commentModified = data[i].commentModified;
//                         let commentLike = data[i].commentLike;
//                         // console.log(data[0].userID);
//                         $.ajax({
//                             url: "http://localhost:8080/ski/FrontendArticle",
//                             type: "Post",
//                             data: {
//                                 "userID": data[i].userID,
//                                 "action": "getMemberUserName"
//                             },
//                             dataType: "json",
//                             success: function (data) {
//                                 // console.log(data);
//                                 let saveUserName1 = data.userName;
//                                 let list_comment = "";
//                                 list_comment = `<tr>
//                         <td>
//                         <div class="dontBR"><img id="preview${i}" src="#" alt="預覽圖片" class="fixed-image"/><p class="text-left">${saveUserName1}</p></div>
//                             <br>
//                             <p class="text-left">${commentContent}</p><p class="time right-align">${commentDateTime}</p>
//                         </td>
//                         </tr>`;
//                                 $("tbody.tbody").append(list_comment);
//                                 const preview = $(`#preview${i}`);
//                                 var data = {
//                                     userID: data.userID
//                                 };
//                                 let jsonData = JSON.stringify(data);
//                                 $.ajax({
//                                     url: "/ski/member/memberInfo",
//                                     type: "POST",
//                                     dataType: "json", //指定回傳的資料格式
//                                     data: jsonData,
//                                     success: function (resp) {
//                                         // console.log(resp)
//                                         let avatar = resp.photo;
//                                         let uint8Array = new Uint8Array(avatar);
//                                         let blob = new Blob([uint8Array]);
//                                         preview.attr('src', URL.createObjectURL(blob));
//                                     }
//                                 });
//                             }
//                         });
//                     }
//                 } else if (data.length > 10) {
//                     for (let i = 0; i < 10; i++) {
//                         let commentID = data[i].commentID;
//                         let articleID = data[i].articleID;
//                         let userID = data[i].userID;
//                         let commentContent = data[i].commentContent;
//                         let commentDateTime = data[i].commentDateTime;
//                         let commentModified = data[i].commentModified;
//                         let commentLike = data[i].commentLike;
//                         $.ajax({
//                             url: "http://localhost:8080/ski/FrontendArticle",
//                             type: "Post",
//                             data: {
//                                 "userID": data.userID,
//                                 "action": "getMemberUserName"
//                             },
//                             dataType: "json",
//                             success: function (data) {
//                                 let saveUserName1 = data.userName;
//                                 let list_comment = "";
//                                 list_comment = `<tr>
//                         <td>
//                         <div class="dontBR"><img id="preview${i}" src="#" alt="預覽圖片" class="fixed-image"/><p class="text-left">${saveUserName1}</p></div>
//                             <br>
//                             <p class="text-left">${commentContent}</p><p class="time right-align">${commentDateTime}</p>
//                         </td>
//                         </tr>`;
//                                 $("tbody.tbody").append(list_comment);
//                                 const preview = $(`#preview${i}`);
//                                 var data = {
//                                     userID: data.userID
//                                 };
//                                 let jsonData = JSON.stringify(data);
//                                 $.ajax({
//                                     url: "/ski/member/memberInfo",
//                                     type: "POST",
//                                     dataType: "json", //指定回傳的資料格式
//                                     data: jsonData,
//                                     success: function (resp) {
//                                         // console.log(resp)
//                                         let avatar = resp.photo;
//                                         let uint8Array = new Uint8Array(avatar);
//                                         let blob = new Blob([uint8Array]);
//                                         preview.attr('src', URL.createObjectURL(blob));
//                                     }
//                                 });
//                             }
//                         });
//                     }
//                 }
//             }
//         });
//     } else if (commentShow == "show") {
//         commentShow = "hidden";
//         $("tbody.tbody").empty(); //清空留言
//         $("thead.thead").empty(); //清空新增留言
//     }
// }


//送出新增留言
$("thead.thead").on("click", "button.btn_addcomment",async function () {
    console.log("送出留言成功");
    let addCommentContent = $("textarea.content").val();
    // 建立一個新的 Date 物件
    var now = new Date();
    // 獲取年、月、日、時、分、秒
    var year = now.getFullYear();
    var month = ('0' + (now.getMonth() + 1)).slice(-2);  // 注意月份從0開始，需要+1
    var day = ('0' + now.getDate()).slice(-2);
    var hours = ('0' + now.getHours()).slice(-2);
    var minutes = ('0' + now.getMinutes()).slice(-2);
    var seconds = ('0' + now.getSeconds()).slice(-2);
    // 組合成所需的時間格式
    var formattedTime = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
    articleID1
    $.ajax({
        url: "http://localhost:8080/ski/FrontendArticle",
        type: "Post",
        data: {
            "articleID": savearticleID,
            "userID": saveUserID,
            "commentContent": addCommentContent,
            "commentDateTime": formattedTime,
            "commentModified": undefined,
            "commentLike": 0,
            "action": "addComment"
        },
        dataType: "json",
        success: function (data) {
            console.log("新增留言成功");
            // console.log(Number($("span.comment").text()));
            savecomment = Number($("span.comment").text()) + 1;
            const commentCountElement = document.querySelector('span.comment'); // 即時更新畫面的留言數
            commentCountElement.textContent = savecomment;
            $("tbody.tbody").empty(); //清空留言
            $("textarea.content").val(""); // 輸入框清空
            open1 = 1;
            console.log(commentShow);
            showComment();
            // $.ajax({
            //     url: "http://localhost:8080/ski/FrontendArticle",
            //     type: "Post",
            //     data: {
            //         "articleID": savearticleID,
            //         "action": "showComment"
            //     },
            //     dataType: "json",
            //     success: function (data) {
            //         // console.log(data);

            //         if (data.length <= 10) {
            //             for (let i = 0; i < data.length; i++) {
            //                 let commentID = data[i].commentID;
            //                 let articleID = data[i].articleID;
            //                 let userID = data[i].userID;
            //                 let commentContent = data[i].commentContent;
            //                 let commentDateTime = data[i].commentDateTime;
            //                 let commentModified = data[i].commentModified;
            //                 let commentLike = data[i].commentLike;
            //                 $.ajax({
            //                     url: "http://localhost:8080/ski/FrontendArticle",
            //                     type: "Post",
            //                     data: {
            //                         "userID": data[i].userID,
            //                         "action": "getMemberUserName"
            //                     },
            //                     dataType: "json",
            //                     success: function (data) {
            //                         let saveUserName1 = data.userName;
            //                         let list_comment = "";
            //                         list_comment = `<tr>
            //                 <td>
            //                 <div class="dontBR"><img id="preview${i}" src="#" alt="預覽圖片" class="fixed-image"/><p class="text-left">${saveUserName1}</p></div>
            //                     <br>
            //                     <p class="text-left">${commentContent}</p><p class="time right-align">${commentDateTime}</p>
            //                 </td>
            //                 </tr>`;
            //                         $("tbody.tbody").append(list_comment);
            //                         const preview = $(`#preview${i}`);
            //                         var data = {
            //                             userID: data.userID
            //                         };
            //                         let jsonData = JSON.stringify(data);
            //                         $.ajax({
            //                             url: "/ski/member/memberInfo",
            //                             type: "POST",
            //                             dataType: "json", //指定回傳的資料格式
            //                             data: jsonData,
            //                             success: function (resp) {
            //                                 // console.log(resp)
            //                                 let avatar = resp.photo;
            //                                 let uint8Array = new Uint8Array(avatar);
            //                                 let blob = new Blob([uint8Array]);
            //                                 preview.attr('src', URL.createObjectURL(blob));
            //                             }
            //                         });
            //                     }
            //                 });
            //             }
            //         } else if (data.length > 10) {
            //             for (let i = 0; i < 10; i++) {
            //                 let commentID = data[i].commentID;
            //                 let articleID = data[i].articleID;
            //                 let userID = data[i].userID;
            //                 let commentContent = data[i].commentContent;
            //                 let commentDateTime = data[i].commentDateTime;
            //                 let commentModified = data[i].commentModified;
            //                 let commentLike = data[i].commentLike;
            //                 $.ajax({
            //                     url: "http://localhost:8080/ski/FrontendArticle",
            //                     type: "Post",
            //                     data: {
            //                         "userID": data.userID,
            //                         "action": "getMemberUserName"
            //                     },
            //                     dataType: "json",
            //                     success: function (data) {
            //                         let saveUserName1 = data.userName;
            //                         let list_comment = "";
            //                         list_comment = `<tr>
            //                 <td>
            //                 <div class="dontBR"><img id="preview${i}" src="#" alt="預覽圖片" class="fixed-image"/><p class="text-left">${saveUserName1}</p></div>
            //                     <br>
            //                     <p class="text-left">${commentContent}</p><p class="time right-align">${commentDateTime}</p>
            //                 </td>
            //                 </tr>`;
            //                         $("tbody.tbody").append(list_comment);
            //                         const preview = $(`#preview${i}`);
            //                         var data = {
            //                             userID: data.userID
            //                         };
            //                         let jsonData = JSON.stringify(data);
            //                         $.ajax({
            //                             url: "/ski/member/memberInfo",
            //                             type: "POST",
            //                             dataType: "json", //指定回傳的資料格式
            //                             data: jsonData,
            //                             success: function (resp) {
            //                                 // console.log(resp)
            //                                 let avatar = resp.photo;
            //                                 let uint8Array = new Uint8Array(avatar);
            //                                 let blob = new Blob([uint8Array]);
            //                                 preview.attr('src', URL.createObjectURL(blob));
            //                             }
            //                         });
            //                     }
            //                 });
            //             }
            //         }
            //     }
            // });

        }
    });
});




//檢舉按鈕(按下按鈕呼叫此fumction)
function openReportForm() {
    if (saveUserID != null) {
// 這裡可以寫彈出填寫表單的程式碼，例如使用 JavaScript 的 prompt() 函式或彈出自訂的模態視窗等
    // 在這個範例中，我們使用 prompt() 函式彈出一個簡單的填寫表單
    const reportReason = prompt("請填寫檢舉原因：");
    if (reportReason) {
        // 在這裡可以處理使用者填寫的檢舉原因，例如發送到伺服器或進行其他操作
        $.ajax({
            url: "http://localhost:8080/ski/FrontendArticle",
            type: "Post",
            data: {
                "articleID": articleID1,
                "userID": saveUserID,
                "reportContent": reportReason,
                "action": "addReportContent"
            },
            dataType: "json",
            success: function (data) {
                alert("感謝你的檢舉！檢舉原因：" + reportReason);
            }
        });
    }
    }else{
        alert("請登入會員才能檢舉");
    }
    
}
