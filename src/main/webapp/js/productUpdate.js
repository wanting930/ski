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
	    // 在這裡，data 就是你的商品資訊
	    // 你可以將它填入商品修改頁面的表單中
	    fillForm(data[0]);  // 使用 data[0] 而不是 data
	    console.log(data[0]) // 顯示第一個商品的資訊
	})
	.catch((error) => console.error('錯誤:', error));
	
	// 最後，定義一個函數來填充表單
	function fillForm(product) {
	    document.getElementById('productName').value = product.productName;
	    document.getElementById('productClass').value = product.productClass;
	    document.getElementById('productStatus').value = product.productStatus;
	    document.getElementById('productDetail').value = product.productDetail;
	    document.getElementById('productPrice').value = product.productPrice;
	    document.getElementById('productQuantity').value = product.productQuantity;
	    document.getElementById('productDate').value = product.productDate;
	    console.log(product)
	    // 請注意，文件上傳輸入（如商品圖片）可能需要其他處理方式，並且不能僅僅通過 .value 來設置。
	}
	// 為"商品修改"按鈕添加點擊事件監聽器
	document.querySelector(".btn-info").addEventListener("click", updateProduct);
	
	function updateProduct() {
	    console.log('Update button clicked');
	    // 從表單獲取新的商品資訊
	    const updatedProduct = {
	        productID: productID,
	        productClass: document.getElementById('productClass').value,
	        productName: document.getElementById('productName').value,
	        productStatus: document.getElementById('productStatus').value,
	        productDetail: document.getElementById('productDetail').value,
	        productPrice: document.getElementById('productPrice').value,
	        productQuantity: document.getElementById('productQuantity').value,
	        productDate: document.getElementById('productDate').value
	        // 商品圖片需要特別處理，此處省略
	    };
	
	    // 向後端API發送更新請求
	    fetch(`http://localhost:8080/ski/productUpdate`, {
	        method: 'POST', // 或是其他適合的HTTP方法，如PUT
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify(updatedProduct) // 將資料轉為JSON格式
	    })
	    .then(response => {
	        if (!response.ok) {
	            throw new Error('網路連線錯誤，請稍後再試');
	        }
	        return response.json();
	    })
	    .then(data => {
	        // 更新成功，導向商品管理頁面
	        window.location.href = "/ski/product/backend_productPut.html"; 
	        
	    })
	    .catch((error) => console.error('錯誤:', error));
	}
	
	//幫我另外寫在下面
	$(document).ready(function() {
	    function validateInput(input, errorMsg) {
	      if (input.val().trim() === "") {
	        errorMsg.show();
	      } else {
	        errorMsg.hide();
	      }
	    }
	  
	    function validateSelect(select, errorMsg) {
	      if (select.val() === "請選擇商品類別") {
	        errorMsg.show();
	      } else {
	        errorMsg.hide();
	      }
	    }
	  
	    function validateRadio(radioName, errorMsg) {
	      if ($(`input[name="${radioName}"]:checked`).length === 0) {
	        errorMsg.show();
	      } else {
	        errorMsg.hide();
	      }
	    }
	  
	    function validateFile(input, errorMsg) {
	      if (input.get(0).files.length === 0) {
	        errorMsg.show();
	      } else {
	        errorMsg.hide();
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
	  });
	  
	  $("#inputGroupFile01").on("change", function() {
	    const file = this.files[0];
	    const fileTypes = ["image/jpeg", "image/png", "image/gif", "image/webp"];
	  
	    if (file && fileTypes.includes(file.type)) {
	      $(".text-danger:eq(7)").hide();
	    } else {
	      $(".text-danger:eq(7)").text("請上傳有效的圖片文件（jpg, png, gif, webp）");
	      $(".text-danger:eq(7)").show();
	      this.value = "";
	    }
	  });