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

