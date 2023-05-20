// Helper function to create product element
function createProductElement(product) {
  return fetch(`http://localhost:8080/ski/loadImage?productID=${product.productID}`)
    .then(response => response.json())
    .then(imageData => {
      const productDiv = document.createElement('div');
      productDiv.classList.add('product');
      productDiv.classList.add('row');

      const productHtml = `
      <div class="col-3">
        <div class="card text-center">
        <a href="http://localhost:8080/ski/product/frontend_productDetail.html?productID=${product.productID}">
        <img src="data:image/png;base64,${imageData.imageData}" alt="商品圖片" class="img-fluid">
          <div class="card-body">
              <div class="productName">${product.productName}</div>
              <div class="productPrice">${product.productPrice}元</div>
            </a>
          </div>
          <button class="add-to-cart-btn">加入購物車</button>
        </div>
      </div>
      `;

      productDiv.innerHTML = productHtml;

      return productDiv;
    });
}

function displayAllProducts() {
  fetch('http://localhost:8080/ski/getAll')
    .then(response => response.json())
    .then(data => {
      const productContainer = document.getElementById('productContent');
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
}

function searchProduct(searchTerm) {
  var url = "http://localhost:8080/ski/productSelectByName?productName=" + searchTerm;

  fetch(url)
    .then(response => response.json())
    .then(data => {
      const productContainer = document.getElementById('productContent');
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

document.addEventListener('DOMContentLoaded', () => {
  displayAllProducts();

  $(".search-bar").keypress(function(event) {
    if (event.which === 13) {
      event.preventDefault();
      var searchTerm = $(".search-bar").val();
      searchProduct(searchTerm);
    }
  });

  $(".search").click(function() {
    var searchTerm = $(".search-bar").val();
    searchProduct(searchTerm);
  });

  const productClassButtons = document.querySelectorAll('.ProductClass');

  productClassButtons.forEach(button => {
    button.addEventListener('click', () => {
      const productClass = button.innerText.trim();

      fetch(`http://localhost:8080/ski/productSelectByClass?productClass=${productClass}`)
        .then(response => response.json())
        .then(data => {
          const productContainer = document.getElementById('productContent');
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

  const allProductsButton = document.querySelector('#allProductsButton');
  allProductsButton.addEventListener('click', () => {
    displayAllProducts();
  });
});

document.addEventListener('keyup', function(event) {
  if (event.key === 'Enter') {
    var searchTerm = $(".search-bar").val();
    searchProduct(searchTerm);
  }
});
