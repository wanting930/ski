$(document).ready(function () {
  // 綁定欄位失去焦點事件
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).blur(function () {
    validateField($(this));
  });

  // 綁定欄位輸入事件
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).on("input", function () {
    validateField($(this));
  });

  //   // 點擊經度欄位時檢查格式
  //   $("#courseName").click(function () {
  //     validateField($(this));
  //   });

  //   // 點擊緯度欄位時檢查格式
  //   $("#courseLocation").click(function () {
  //     validateField($(this));
  //   });

  // 顯示欄位不能為空的錯誤提示
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).addClass("is-invalid");
  //   $(".invalid-feedback");

  $("#insert_btn").on("click", function (event) {
    courseID = $("#courseID").val();
    merge(courseID);
  });

  $("#update_btn").on("click", function (event) {
    merge();
  });
});

function validateField(field) {
  var value = field.val().trim();

  if (value === "") {
    field.removeClass("is-valid");
    field.addClass("is-invalid");
    field.next(".invalid-feedback");
  } else {
    field.removeClass("is-invalid");
    field.addClass("is-valid");
    field.next(".invalid-feedback");
  }
}

function merge(courseID) {
  $("#submit_btn").on("click", function (event) {
    event.preventDefault();

    if ($(".is-invalid").length > 0) {
      alert("請填寫完整資料！");
    } else {
      exist = false;
      method = "IS";
      if (courseID) {
        exist = true;
      }

      var courseSkill = $("#courseSkill").val();
      var courseLevel = $("#courseLevel").val();
      var courseName = $("#courseName").val();
      var courseLocation = $("#courseLocation").val();
      var courseDate = $("#courseDate").val();
      var startDate = $("#startDate").val();
      var endDate = $("#endDate").val();
      var coursePrice = $("#coursePrice").val();
      var courseMax = $("#courseMax").val();
      var courseMin = $("#courseMin").val();
      var courseStatus = $("#courseStatus").val();
      var coursePhoto = $("#skiImage")[0].files[0];
      var courseIntroduce = $("#courseIntroduce").val();

      var formData = new FormData();
      formData.append("courseSkill", courseSkill);
      formData.append("courseLevel", courseLevel);
      formData.append("courseName", courseName);
      formData.append("courseLocation", courseLocation);
      formData.append("courseDate", courseDate);
      formData.append("startDate", startDate);
      formData.append("endDate", endDate);
      formData.append("coursePrice", coursePrice);
      formData.append("courseMax", courseMax);
      formData.append("courseMin", courseMin);
      formData.append("courseStatus", courseStatus);
      formData.append("coursePhoto", coursePhoto);
      formData.append("courseIntroduce", courseIntroduce);
      if (exist) {
        formData.append("courseID", courseID);
        method = "UD";
      }

      console.log("formData" + formData);
      console.log("method" + method);

      // $.ajax({
      //   url: "course_" + method,
      //   type: "POST",
      //   data: formData,
      //   contentType: false,
      //   processData: false,
      //   success: function (response) {
      //     // console.log("資料傳送成功");
      //     // console.log(response);
      //     alert("執行成功！請到雪點列表點擊上架顯示到滑雪地圖");
      //     location.reload();
      //   },
      //   error: function (xhr, status, error) {
      //     console.log("資料傳送失敗");
      //     console.log(error);
      //   },
      // });
    }
  });
}
