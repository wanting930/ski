fetch('http://localhost:8080/ski/getAll')
  .then(response => response.json())
  .then(data => {
    // Process the data retrieved from the server
    renderProducts(data);
  })
  .catch(error => {
    // Handle any errors that occurred during the request
    console.error(error);
  });

function renderProducts(products) {
  const tableBody = document.querySelector('tbody');
  tableBody.innerHTML = '';

  products.forEach(product => {
    const row = document.createElement('tr');
    row.classList.add('align-middle');

    const editButton = document.createElement('button');
    editButton.classList.add('btn', 'btn-secondary');
    editButton.textContent = '修改';
    editButton.addEventListener('click', () => {
      jumpupdatepage(product.productID);
    });

    const deleteButton = document.createElement('button');
    deleteButton.classList.add('btn', 'btn-secondary');
    deleteButton.textContent = '刪除';

    // 將圖片資料轉換成 Base64 編碼的字串
    const base64Image = btoa(new Uint8Array(product.productImage).reduce((data, byte) => data + String.fromCharCode(byte), ''));
    const imageSrc = `data:image/png;base64,${base64Image}`;

    row.innerHTML = `
      <th scope="row">${product.productID}</th>
      <td class="col-2">
          <img src="${imageSrc}" class="img-thumbnail">
      </td>
      <td>${product.productClass}</td>
      <td>${product.productName}</td>
      <td>${product.productStatus}</td>
      <td>${product.productDetail}</td>
      <td>${product.productDate}</td>
      <td></td>
      <td></td>
    `;

    row.querySelector('td:nth-child(8)').appendChild(editButton);
    row.querySelector('td:nth-child(9)').appendChild(deleteButton);

    tableBody.appendChild(row);
  });
}

function jumpupdatepage(productID) {
  const url = `/ski/product/backend_productUpdate.html?id=${productID}`;
  window.location.href = url;
}

function deleteProduct(productID) {
  // Call the backend DELETE API here
  fetch(`http://localhost:8080/ski/deleteProduct/${productID}`, {
    method: 'DELETE',
  })
    .then(response => response.json())
    .then(data => {
      // Process the response from the server
      console.log('Product deleted successfully:', data);
      // You can update the UI or perform any other actions as needed
      // For example, you can reload the product list after deletion
      fetch('http://localhost:8080/ski/getAll')
        .then(response => response.json())
        .then(data => {
          renderProducts(data);
        })
        .catch(error => {
          console.error(error);
        });
    })
    .catch(error => {
      // Handle any errors that occurred during the request
      console.error(error);
    });
}


