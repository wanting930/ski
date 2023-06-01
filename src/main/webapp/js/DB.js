function init() {
        $.ajax({
            url: "courseAdList",           // 資料請求的網址
            type: "POST",                 // GET | POST | PUT | DELETE | PATCH
            dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
            success: function(){      // request 成功取得回應後執行
            console.log(data);
            },
            error: function () {
                console.log("bad");
           }
        });
    }

(() => {

    fetch('courseAdList')
    .then(response => response.json())
    .then(itemList => {
        // 將資料放入表格
        let tableBody = $('#adList');
        itemList.forEach(item => {
            let html = `
            <tr>
            <td>${item.courseAD}</td>
            <td>${item.courseID}</td>
            <td>${item.courseName}</td>
            <td>${item.startTime}</td>
            <td>${item.endTime}</td>
            <td><button class="btn btn-outline-danger btn-delete" data-id="${item.courseAD}">刪除</button></td></tr>`;
            tableBody.append(html);
        });
        // 監聽刪除按鈕的點擊事件
        $('.btn-delete').click(function() {
            var itemId = $(this).data('courseAD');
            // 使用 Fetch API 發送 POST 請求將要刪除的資料傳送至後端
            fetch('後端刪除資料URL', {
                method: 'POST',
                body: JSON.stringify({ id: itemId }),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => {
                if (response.ok) {
                    console.log('資料已刪除');
                    // 可以重新載入或更新資料列表
                } else {
                    console.log('刪除資料失敗');
                }
            })
            .catch(error => {
                console.log('刪除資料失敗:', error);
            });
        });
    })
    .catch(error => {
        console.log('獲取資料失敗:', error);
    });

    // $("#insrtbutton").on("click", () => {

    // })


})();

