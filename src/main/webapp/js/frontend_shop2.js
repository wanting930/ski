fetch('http://localhost:8080/ski/getAll')
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.json();
  })
  .then(data => {
    console.log(data);
    
    // Query the product container element
    const productContainer = document.querySelector('.product-container');

    // Iterate through the array and append to the product container
    data.forEach(product => {
      const productDiv = document.createElement('div');
      productDiv.classList.add('product');
	console.log(product.productImage)
      const productHtml = `
    <a href="product-page.html">
      <img src="http://localhost:8080/ski/loadImage?productID=${product.productID}" alt="商品圖片" style="width: 130px;" data-productID="${product.productID}">
      <div class="productName">${product.productName}</div>
      <div class="productPrice">${product.productPrice}</div>
    </a>
    <button class="add-to-cart-btn">加入購物車</button>
  `;	

      productDiv.innerHTML = productHtml;

      productContainer.appendChild(productDiv);
    });
  })
  .catch(error => console.error('There has been a problem with your fetch operation: ', error));

  
  
    document.addEventListener('DOMContentLoaded', () => {
      const productClassButtons = document.querySelectorAll('.ProductClass');

      productClassButtons.forEach(button => {
        button.addEventListener('click', () => {
          const productClass = button.innerText.trim();

          fetch(`http://localhost:8080/ski/productSelectByClass?productClass=${productClass}`)
            .then(response => response.json())
            .then(data => {
              const productContainer = document.querySelector('.product-container');
              productContainer.innerHTML = '';

              data.forEach(product => {
                const productDiv = document.createElement('div');
                productDiv.classList.add('product');

                const productHtml = `
                  <a href="product-page.html">
                    <img  id="productImage" src="http://localhost:8080/ski/loadImage?productID=${product.productID}" alt="product Image" style="width: 130px;">
                    <div class="productName">${product.productName}</div>
                    <div class="productPrice">${product.productPrice}</div>
                  </a>
                  <button class="add-to-cart-btn">加入購物車</button>
                `;

                productDiv.innerHTML = productHtml;

                productContainer.appendChild(productDiv);
              });
            })
            .catch(error => {
              console.error('There has been a problem with your fetch operation: ', error);
            });
        });
      });
    });
    
    const allProductsButton = document.querySelector('#allProductsButton');
  allProductsButton.addEventListener('click', () => {
    // 向後端API發送GET請求以獲取所有商品
    fetch('http://localhost:8080/ski/getAll')
      .then(response => response.json())
      .then(data => {
        const productContainer = document.querySelector('.product-container');
        productContainer.innerHTML = '';

        data.forEach(product => {
          const productDiv = document.createElement('div');
          productDiv.classList.add('product');

          const productHtml = `
            <a href="product-page.html">
              <img src="http://localhost:8080/ski/loadImage?productID=${product.productID}" alt="商品圖片" style="width: 130px;">
              <div class="productName">${product.productName}</div>
              <div class="productPrice">${product.productPrice}</div>
            </a>
            <button class="add-to-cart-btn">加入購物車</button>
          `;

          productDiv.innerHTML = productHtml;

          productContainer.appendChild(productDiv);
        });
      })
      .catch(error => {
        console.error('There has been a problem with your fetch operation: ', error);
      });
  });
  
  
  
$(document).ready(function() {
  // 搜尋按鈕點擊事件處理函式
  $(".search").click(function() {
    var searchTerm = $(".search-bar").val(); // 取得搜尋欄中的值
    searchProduct(searchTerm); // 呼叫搜尋商品的函式
  });

  // 執行搜尋商品的函式
  function searchProduct(searchTerm) {
    // 假設您有一個名為 searchProductAPI 的 API 可以搜尋商品
    var url = "http://localhost:8080/ski/productSelectByName?productName=" + searchTerm;

    // 使用 Fetch API 發送 API 請求
    fetch(url)
      .then(function(response) {
        if (!response.ok) {
          throw new Error("搜尋商品時發生錯誤");
        }
        return response.json();
      })
      .then(function(data) {
        var products = data; // 從 API 回傳的資料中取得商品

        // 清空商品容器
        $(".product-container").empty();

        // 生成商品元素並添加到商品容器中
        for (var i = 0; i < products.length; i++) {
          var product = products[i];
          var productItem = $("<div>").addClass("product").html(
            '<a href="product-page.html?productId=' + product.productId + '">' +
            '<img src="http://localhost:8080/ski/loadImage?productID=' + product.productId + '" alt="商品圖片" style="width: 130px;">' +
            '<div class="productName">' + product.productName + '</div>' +
            '<div class="productPrice">' + product.productPrice + '</div>' +
            '</a>' +
            '<button class="add-to-cart-btn">加入購物車</button>'
          );

          $(".product-container").append(productItem);
        }
      })
      .catch(function(error) {
        console.log(error);
      });
  }
});

