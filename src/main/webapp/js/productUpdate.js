
// 首先，從 URL 查詢參數中獲取商品ID
const urlParams = new URLSearchParams(window.location.search);
const productID = urlParams.get('id');

// 接著，使用這個商品ID來獲取商品詳情
fetch(`http://localhost:8080/ski/productSelectByID?productID=${productID}`, {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json'
  }
})
  .then(response => response.json())
  .then(data => {
    // 在這裡，data 就是您的商品資訊
    // 您可以將它填入商品修改頁面的表單中
    fillForm(data[0]);  // 使用 data[0] 而不是 data
    console.log(data[0]); // 顯示第一個商品的資訊
  })
  .catch((error) => console.error('錯誤:', error));

// 最後，定義一個函數來填充表單
function fillForm(product) {
  document.getElementById('productName').value = product.productName || '';
  document.getElementById('productClass').value = product.productClass || '';
  document.getElementById('productStatus').value = product.productStatus || '';
  document.getElementById('productDetail').value = product.productDetail || '';
  document.getElementById('productPrice').value = product.productPrice || '';
  document.getElementById('productQuantity').value = product.productQuantity || '';
  document.getElementById('productDate').value = product.productDate || '';

  // 請注意，文件上傳輸入（如商品圖片）可能需要其他處理方式，並且不能僅僅通過 .value 來設置。
}

// 為"商品修改"按鈕添加點擊事件監聽器
document.addEventListener('DOMContentLoaded', function() {
  const updateProductBtn = document.querySelector(".btn-info");
  updateProductBtn.addEventListener("click", updateProduct);
});

function updateProduct() {
  const productClass = document.getElementById('productClass').value;
  const productName = document.getElementById('productName').value;
  const productPrice = document.getElementById('productPrice').value;
  const productQuantity = document.getElementById('productQuantity').value;
  const productDate = document.getElementById('productDate').value;
  const productStatus = document.getElementById('productStatus').value;
  const productImage = document.getElementById('productImage').files[0];
  const productDetail = document.getElementById('productDetail').value;

  // 建立 FormData 物件以以 multipart/form-data 的形式發送數據
  const formData = new FormData();
  formData.append('productClass', productClass);
  formData.append('productName', productName);
  formData.append('productPrice', productPrice);
  formData.append('productQuantity', productQuantity);
  formData.append('productDate', productDate);
  formData.append('productStatus', productStatus);
  formData.append('productImage', productImage);
  formData.append('productDetail', productDetail);

  // 檢查欄位是否有變更，若無變更則不執行更新
  if (
    productClass.trim() === '' &&
    productName.trim() === '' &&
    productPrice.trim() === '' &&
    productQuantity.trim() === '' &&
    productDate.trim() === '' &&
    productStatus.trim() === '' &&
    productImage === undefined &&
    productDetail.trim() === ''
  ) {
    console.log('沒有變更');
    return;
  }

  // 更新資料的程式碼，您可以使用 Fetch API 進行資料更新
  fetch('http://localhost:8080/ski/productUpdate', {
    method: 'POST',
    body: formData
  })
    .then(response => response.json())
    .then(data => {
      // 處理伺服器的回應
      console.log(data); // 根據需求自定義處理方式
      // 導航回商品管理頁面
      window.location.href = '/ski/product/backend_productPut.html';
    })
    .catch(error => {
      console.error('錯誤:', error);
    });
}
  
  
  

//幫我另外寫在下面
$(document).ready(function() {
    function validateInput(input, errorMsg) {
      if (input.val().trim() === "") {
        errorMsg.show();
        return false;
      } else {
        errorMsg.hide();
        return true;
      }
    }
  
    function validateSelect(select, errorMsg) {
      if (select.val() === "請選擇商品類別") {
        errorMsg.show();
        return false;
      } else {
        errorMsg.hide();
        return true;
      }
    }
  
    function validateRadio(radioName, errorMsg) {
      if ($(`input[name="${radioName}"]:checked`).length === 0) {
        errorMsg.show();
        return false;
      } else {
        errorMsg.hide();
        return true;
      }
    }
  
    function validateFile(input, errorMsg) {
      if (input.get(0).files.length === 0) {
        errorMsg.show();
        return false;
      } else {
        errorMsg.hide();
        return true;
      }
    }
  
    $("#inputGroupSelect01").on("change", function() {
      validateSelect($(this), $(".text-danger:eq(0)"));
    });
  
    $("input[type='text']").on("input", function() {
      validateInput($(this), $(this).parent().parent().next());
    });
  
    $("input[type='radio']").on("change", function() {
      validateRadio($(this).attr("name"), $(this).parent().parent().parent().next());
    });
  
    $("#inputGroupFile01").on("change", function() {
      validateFile($(this), $(this).parent().parent().next());
    });
  
    $("#floatingTextarea2").on("input", function() {
      validateInput($(this), $(this).parent().parent().next());
    });
  
    // 隱藏已經有值的紅字錯誤訊息
    $(".text-danger").each(function() {
      if ($(this).text() !== "") {
        $(this).hide();
      }
    });
  
   
    $("#updateProductBtn").on("click", function(e) {
      e.preventDefault(); 
  
      // 执行表单验证
      const isProductClassValid = validateSelect($("#inputGroupSelect01"), $(".text-danger:eq(0)"));
      const isProductNameValid = validateInput($("#productName"), $("#productName").parent().parent().next());
      const isProductPriceValid = validateInput($("#productPrice"), $("#productPrice").parent().parent().next());
      const isProductQuantityValid = validateInput($("#productQuantity"), $("#productQuantity").parent().parent().next());
      const isProductDateValid = validateInput($("#productDate"), $("#productDate").parent().parent().next());
      const isProductStatusValid = validateSelect($("#productStatus"), $(".text-danger:eq(3)"));
      const isProductImageValid = validateFile($("#inputGroupFile01"), $("#inputGroupFile01").parent().parent().next());
      const isProductDetailValid = validateInput($("#floatingTextarea2"), $("#floatingTextarea2").parent().parent().next());
  
      
      if (
        isProductClassValid &&
        isProductNameValid &&
        isProductPriceValid &&
        isProductQuantityValid &&
        isProductDateValid &&
        isProductStatusValid &&
        isProductImageValid &&
        isProductDetailValid
      ) {
        
        updateProduct();
      }
    });
  });