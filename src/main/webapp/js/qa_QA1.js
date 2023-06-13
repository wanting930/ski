window.addEventListener('DOMContentLoaded', function() {
    var inputElement = document.getElementById('title');
    inputElement.value = '關於我們';
    var inputElement = document.getElementById('ans');
    inputElement.value = '我們是一群熱愛滑雪的人，但由於台灣對於滑雪相關資訊相對匱乏，我們希望能做出一個整合各方面資訊的網站，包含，裝備、雪點、教練、雪友等相關資訊這裡都找的到喔。';
});

//submit
$(document).on("click", "#submit", function newQA() { 
        
    let title = $("#title").val();
    let type = $("#qaType").val();
    // let time = $("#time").val();
    let ans = $("#ans").val();
    console.log("title:", title);
    console.log("type:", type);
    console.log("ans:", ans);
    
    $.ajax({
        url: "/ski/editQa",
        type: "POST",
        data:JSON.stringify({
            questionTitle: title,
            questionType: type,
            // questionDate: time,
            answerContent: ans,
            qaID:qaId
        }),
        dataType: "json",
        success: function (data) {
            console.log("a");
            
            // Swal.fire({
            //     title: '新增成功!',
            //     icon: 'success',
            //     showConfirmButton: false,   
            // });
            alert("123")
            
            window.location.href = '/ski/qa/back_qaList.html';
        },
        error: function (error) {
            console.log(error);
            window.location.href = '/ski/qa/back_qaList.html';
        }
    })

    
})