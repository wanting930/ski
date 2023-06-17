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

	function validateFile(input, errorMsg) {
		if (input.get(0).files.length === 0) {
			errorMsg.show();
			return false;
		} else {
			errorMsg.hide();
			return true;
		}
	}

	$("#productStatusInput").on("change", function() {
		validateSelect($(this), $(this).parent().parent().next());
	});

	$("#productClass").on("change", function() {
		validateSelect($(this), $(this).parent().parent().next());
	});

	$("input[type='text']").on("input", function() {
		validateInput($(this), $(this).parent().parent().next());
	});

	$("#productImage").on("change", function() {
		validateFile($(this), $(this).parent().parent().next());
	});

	$("#productDetail").on("input", function() {
		validateInput($(this), $(this).parent().parent().next());
	});

	$("#productImage").on("change", function() {
		const file = this.files[0];
		const fileTypes = ["image/jpeg", "image/png", "image/gif", "image/webp"];

		if (file && fileTypes.includes(file.type)) {
			$(this).parent().parent().next().hide();
		} else {
			$(this).parent().parent().next().text("請上傳有效的圖片文件（jpg, png, gif, webp）");
			$(this).parent().parent().next().show();
			this.value = "";
		}
	});

	$("#addButton").on("click", function() {
		const productClass = $("#productClass");
		const productName = $("#productNameInput");
		const productPrice = $("#productPriceInput");
		const productQuantity = $("#productQuantityInput");
		const productImage = $("#productImage");
		const productDetail = $("#productDetail");

		const isValidProductClass = validateSelect(productClass, productClass.parent().parent().next());
		const isValidProductName = validateInput(productName, productName.parent().parent().next());
		const isValidProductPrice = validateInput(productPrice, productPrice.parent().parent().next());
		const isValidProductQuantity = validateInput(productQuantity, productQuantity.parent().parent().next());
		const isValidProductImage = validateFile(productImage, productImage.parent().parent().next());
		const isValidProductDetail = validateInput(productDetail, productDetail.parent().parent().next());

		if (isValidProductClass && isValidProductName && isValidProductPrice && isValidProductQuantity && isValidProductImage && isValidProductDetail) {
			const formData = new FormData();
			formData.append('productClass', productClass.val());
			formData.append('productName', productName.val());
			formData.append('productPrice', productPrice.val());
			formData.append('productQuantity', productQuantity.val());
			formData.append('productStatus', $("#productStatusInput").val());
			formData.append('productImage', productImage.get(0).files[0]);
			formData.append('productDetail', productDetail.val());


			fetch('/ski/productAdd', {
				method: 'POST',
				body: formData
			})
				.then(response => {
					console.log(response);
					return response.json();
				})
				.then(data => {
					console.log(data);
					if (data.status === "success") {
						console.log("Success:", data.message);
						
						window.location.href = "/ski/product/backend_productPut.html";
					} else if (data.status === "failure") {
						console.error("Failure:", data.message);
					}
				})
				.catch((error) => {
					console.error('Error:', error);
				});
		}
	});
});
