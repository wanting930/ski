// Helper function to create product element
function createProductElement(product) {
  return fetch(`http://localhost:8080/ski/loadImage?productID=${product.productID}`)
    .then(response => response.json())
    .then(imageData => {
      const productDiv = document.createElement('div');
      productDiv.classList.add('product');

      const productHtml = `
        <a href="product-page.html">
          <img src="data:image/png;base64,${imageData.imageData}" alt="商品圖片" style="width: 130px;">
          <div class="productName">${product.productName}</div>
          <div class="productPrice">${product.productPrice}</div>
        </a>
        <button class="add-to-cart-btn">加入購物車</button>
      `;

      productDiv.innerHTML = productHtml;

      return productDiv;
    });
}

// Product class buttons event handler
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

          let promises = data.map(createProductElement);

          Promise.all(promises).then(productElements => {
            productElements.forEach(element => {
              productContainer.appendChild(element);
            });
          });
        })
        .catch(error => {
          console.error('There has been a problem with your fetch operation: ', error);
        });
    });
  });
});

// Click event for allProductsButton
const allProductsButton = document.querySelector('#allProductsButton');
allProductsButton.addEventListener('click', () => {
  fetch('http://localhost:8080/ski/getAll')
    .then(response => response.json())
    .then(data => {
      const productContainer = document.querySelector('.product-container');
      productContainer.innerHTML = '';

      let promises = data.map(createProductElement);

      Promise.all(promises).then(productElements => {
        productElements.forEach(element => {
          productContainer.appendChild(element);
        });
      });
    })
    .catch(error => {
      console.error('There has been a problem with your fetch operation: ', error);
    });
});

// Execute searchProduct function
function searchProduct(searchTerm) {
  var url = "http://localhost:8080/ski/productSelectByName?productName=" + searchTerm;

  fetch(url)
    .then(response => response.json())
    .then(data => {
      const productContainer = document.querySelector('.product-container');
      productContainer.innerHTML = '';

      let promises = data.map(createProductElement);

      Promise.all(promises).then(productElements => {
        productElements.forEach(element => {
          productContainer.appendChild(element);
        });
      });
    })
    .catch(error => {
      console.log(error);
    });
}

$(document).ready(function() {
  $(".search").click(function() {
    var searchTerm = $(".search-bar").val();
    searchProduct(searchTerm);
  });
});
