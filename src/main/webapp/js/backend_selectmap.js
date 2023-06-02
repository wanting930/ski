



console.log("111");


// 卡片
// 上架按钮点击事件处理函数
function confirmShelve(button, itemData) {
  var itemData = JSON.parse(decodeURIComponent(encodedItemData));
  var listItem = $(button).closest('.col-md-4');
  var title = listItem.find('.title-inner');
  var isShelved = itemData.pointStatus === '上架中'; // 检查是否已经是上架状态

  if (result) {
    console.log("執行上架操作");
    // 发送请求到后端更新数据库中对应数据的状态为上架中
    $.ajax({
      url: 'backend_statusmap', // 后端更新状态的接口地址
      method: 'POST',
      data: { id: itemData.pointID, status: '上架中' }, // 传递商品ID和新的状态值
      success: function(response) {
        // 更新成功后执行的操作
        console.log("狀態更新成功");
        // 修改标题文字颜色为警告色
        changeTitleColor('warning', button);
        // 移除下架状态的字体样式
        title.removeClass('text-secondary');
        // 更新状态为上架中
        itemData.pointStatus = '上架中';
      },
      error: function(error) {
        // 更新失败后执行的操作
        console.log("狀態更新失败");
      }
    });
  } else {
    console.log("取消上架");
  }
}

// 下架按钮点击事件处理函数
function confirmUnshelve(button, itemData) {
  var itemData = JSON.parse(decodeURIComponent(encodedItemData));
  var listItem = $(button).closest('.col-md-4');
  var title = listItem.find('.title-inner');
  var isUnshelved = itemData.pointStatus === '下架中'; // 检查是否已经是下架状态

  if (result) {
    console.log("執行下架操作");
    // 发送请求到后端更新数据库中对应数据的状态为下架中
    $.ajax({
      url: 'backend_statusmap', // 后端更新状态的接口地址
      method: 'POST',
      data: { id: itemData.pointID, status: '下架中' }, // 传递商品ID和新的状态值
      success: function(response) {
        // 更新成功后执行的操作
        console.log("狀態更新成功");
        // 修改标题文字颜色为下架状态的字体样式
        changeTitleColor('secondary', button);
        // 添加下架状态的字体样式
        title.addClass('text-secondary');
        // 更新状态为下架中
        itemData.pointStatus = '下架中';
      },
      error: function(error) {
        // 更新失败后执行的操作
        console.log("狀態更新失败");
      }
    });
  } else {
    console.log("取消下架");
  }
}





// 删除
function confirmDelete(button) {
  var result = confirm("是否確定將資料整筆刪除？");

  if (result) {
    // 确认删除，执行删除相关操作
    // 可以在这里添加您需要的代码
    console.log("執行刪除操作");

    // 获取当前列表项
    var listItem = $(button).closest('.col-md-4');
    // 在列表中移除当前列表项
    listItem.remove();
  } else {
    // 取消删除，不执行删除操作
    // 可以在这里添加您需要的代码
    console.log("取消刪除");
  }
}




// 阻止默认的滚动行为
function preventScroll(event) {
  event.preventDefault();
}

// 获取所有需要阻止滚动的元素
var elements = document.querySelectorAll('.item-pic, .button-group');

// 给每个元素添加点击事件监听器
elements.forEach(function(element) {
  element.addEventListener('click', preventScroll);
});






window.onload = function() {
  // 在這裡放置您的程式碼
 // 取得商品圖片欄位和預覽照片元素
 const productImageInput = document.getElementById('skiImage');
 const previewImage = document.getElementById('skiImagePreview');
 
 // 監聽商品圖片欄位的變動事件
 productImageInput.addEventListener('change', function(event) {
   // 確認使用者選擇了圖片
   if (event.target.files && event.target.files[0]) {
     // 讀取選擇的圖片檔案
     const reader = new FileReader();
     reader.onload = function(e) {
       // 將讀取到的圖片顯示在預覽照片上
       previewImage.src = e.target.result;
     };
     reader.readAsDataURL(event.target.files[0]);


   }
 });
};





$(document).ready(function() {


  $('#exampleModal').on('hidden.bs.modal', function (e) {
    // 当模态框关闭后，重新启用背后的内容
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
  });
  
  // 從後端獲取資料
  console.log("aaa");
  $.ajax({
    url: 'backend_selectmap',
    method: 'GET',
    dataType: 'json',
    success: function(response) {
      // 循环处理每个数据项
      response.forEach(function(itemData) {
        const avatar = itemData.pointPicture;
        const uint8Array = new Uint8Array(avatar);
        const blob = new Blob([uint8Array]);
  
        // 动态生成列表项
        var listItem = `
          <div class="col-md-4 mb-5">
            <a href="#" class="item-pic card pic-primary">
              <img class="photo" src="" alt="" />
              <div class="card-body">
                <h3 class="h4 card-title">
                  <span class="title-inner bold text-secondary" id="title">${itemData.pointID}</span>
                </h3>
                <div class="button-group">
                <button class="btn btn-warning bold text-dark" onclick="confirmShelve(this, encodeURIComponent(JSON.stringify(itemData)))">上架</button>
                <button class="btn btn-secondary bold text-dark" onclick="confirmUnshelve(this, encodeURIComponent(JSON.stringify(itemData)))">下架</button>




                  <button class="btn btn-info bold text-dark" data-toggle="modal" data-target="#exampleModal" data-item="${encodeURIComponent(JSON.stringify(itemData))}">編輯</button>
                  <button class="btn btn-danger bold text-dark" onclick="confirmDelete()">刪除</button>
                </div>
              </div>
            </a>
          </div>
        `;
  
        // 将生成的列表项插入到列表中
        $('#mycon').append(listItem);
           // 根据每个数据项选择对应的图像元素并设置 src 属性
  var photoElement = $('#mycon .photo:last');
  photoElement.attr('src', URL.createObjectURL(blob));

   // 更新字体颜色和按钮状态
   var title = $('#mycon .title-inner:last');
   var shelveButton = $('#mycon .btn-warning:last');
   var unshelveButton = $('#mycon .btn-secondary:last');

   if (itemData.pointStatus === '上架中') {
     title.removeClass('text-secondary');
     title.addClass('text-warning');
     shelveButton.prop('disabled', true);
     unshelveButton.prop('disabled', false);
   } else if (itemData.pointStatus === '下架中') {
     title.addClass('text-secondary');
     shelveButton.prop('disabled', false);
     unshelveButton.prop('disabled', true);
   }
      });
    },
    error: function(error) {
      console.log('請求失敗:', error);
    }
  });
  
});





// 监听编辑按钮的点击事件
$(document).on('click', '.btn.btn-info', function() {
  // 获取保存在自定义属性中的数据
  // var itemData = $(this).data('item');
  var itemData = JSON.parse(decodeURIComponent($(this).data('item')));


  // 将数据填充到编辑弹窗的输入字段中
  $('#skiNameInput').val(itemData.pointName);
  $('#areaInput').val(itemData.pointArea);
  $('#latInput').val(itemData.latitude);
  $('#lngInput').val(itemData.longitude);
  $('#infoInput').val(itemData.pointInfo);
  $('#ratingInput').val(itemData.pointRating);
  $('#starMonthInput').val(itemData.starMonth);
  $('#endMonthInput').val(itemData.endMonth);
  $('#skiView').val(itemData.pointView);
  // $('#skiImage').attr('src', itemData.pointPicture);
  // $('#skiImagePreview').attr('src', itemData.pointPicture);
});






