fetch('http://localhost:8080/ski/getAll')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data)
        
        // query the product container element
        const productContainer = document.querySelector('div.product-container')

        // iterate through the array and append to the product container
        data.forEach(product => {
            const productDiv = document.createElement('div');
            productDiv.classList.add('product');
            productDiv.style.width = '150px';

            const productHtml = `
                <a href="product-page.html">
                   <img src="http://localhost:8080/productest/loadImage?productID=${product.productID}" alt="商品圖片" style="width: 130px;">
                  <div class="productName">商品名稱: ${product.productName}</div>
                  <div class="productPrice">$${product.productPrice}</div>
                </a>
                <button class="add-to-cart-btn" onClick="addCart(${product.productId})">加入購物車</button>
            `;

            productDiv.innerHTML = productHtml;

            productContainer.appendChild(productDiv);
        });
    })
    .catch(error => console.error('There has been a problem with your fetch operation: ', error));

$(document).ready(function() {
    $(window).resize(function() {
        // Update navbar text size based on window width
        if ($(window).width() < 768) {
            $('.navbar .nav-link').css('font-size', '14px');
        } else {
            $('.navbar .nav-link').css('font-size', '18px');
        }

        // Update footer text size based on window width
        if ($(window).width() < 768) {
            $('.footer').css('font-size', '12px');
        } else {
            $('.footer').css('font-size', '16px');
        }

        // Update product display based on window width
        if ($(window).width() < 768) {
            $('.product').css('width', '100px');
            $('.product img').css('width', '80px');
        } else {
            $('.product').css('width', '150px');
            $('.product img').css('width', '130px');
        }
    });
});
