$(document).ready(function () {
  getAllCourse();
  resetInputState();

  // 綁定欄位輸入事件
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).on("input", function () {
    validateInputField($(this));
  });

  $("#insert_btn").on("click", function (event) {
    resetForm();
    merge(courseID);
  });

  //更新事件綁定
  $(".card_zone").on("click", ".update_btn", function (event) {
    resetForm();
    var courseID = $(this)
      .closest(".btnGroup")
      .siblings(".textZone")
      .find(".courseID")
      .val();
    console.log("ID: " + courseID);
    getCourseInfo(courseID);
    merge(courseID);
  });
});

function getAllCourse() {
  $.ajax({
    url: "http://localhost:8080/ski/course_GA",
    type: "POST",
    dataType: "json",
    success: function (response) {
      renderCourse(response);
    },
    error: function () {
      console.log("error");
    },
  });
}

function renderCourse(Course) {
  Course.forEach((course) => {
    //刪除按鈕生成
    const deleteButton = $("<button>")
      .addClass("btn mx-1 btn-primary delete_btn")
      .attr("type", "button")
      .attr("data-bs-toggle", "modal")
      .attr("data-bs-target", "#delete")
      .text("刪除課程");

    //更新按鈕生成
    const updateButton = $("<button>")
      .addClass("btn mx-1 btn-primary update_btn")
      .attr("type", "button")
      .attr("data-bs-toggle", "modal")
      .attr("data-bs-target", "#merge")
      .text("更改課程資訊");

    const base64Image = btoa(
      new Uint8Array(course.coursePhoto).reduce(
        (data, byte) => data + String.fromCharCode(byte),
        ""
      )
    );
    const imageSrc = `data:image/png;base64,${base64Image}`;
    const courseDate = moment(course.courseDate).format("YYYY-MM-DD");

    var courseSkill = "";
    if (course.skill == 0) {
      courseSkill = "單板";
    } else if (course.skill == 1) {
      courseSkill = "雙版";
    }

    var courseLevel = "";
    if (course.level == 0) {
      courseLevel = "初階";
    } else if (course.level == 1) {
      courseLevel = "中階";
    } else if (course.level == 2) {
      courseLevel = "高階";
    }

    var courseStatus = "";
    var textDec="";
    if (course.courseStatus == 0) {
      courseStatus = "下架中";
      textDec = "text-danger";
    } else if (course.courseStatus == 1) {
      courseStatus = "上架中";
      textDec = "text-success";
    }

    cardStr = `
       <div class="courseCard border-top my-3 p-2 d-flex align-items-start justify-content-start position-relative">

           <div class="imgZone mx-2 ">
             <img class="crousePhoto" src='${imageSrc}' style="max-height: 160px; max-width: 180px; height: 160px; width: 180px;">
           </div>

           <div class="textZone fw-bold mb-3">
                <div id="titleGroup " class="d-flex align-items-center">
                  
                  <p class="mx-2 my-0 fs-3">${course.courseName}</p>
                  <p class="mb-0">${courseSkill}/${courseLevel}</p>
   
                  <input type="text" class="d-none courseID" value="${course.courseID}">
                </div>
                <div class="d-flex flex-column ">
                
                  <p class="mb-0 mx-2 text-start">${courseDate}</p>
                  <p class="mb-0 mx-2 text-start ${textDec}">${courseStatus}</p>
                  
                </div>
              </div>       

           <div class="btnGroup m-2 position-absolute bottom-0 end-0">
           </div>
     		</div>
     `;

    $(".card_zone").append(cardStr);
    $(".courseCard").each(function () {
      const btnGroup = $(this).find(".btnGroup");
      btnGroup.append(deleteButton, updateButton);
    });
  });
}

function merge(courseID) {
  $("#submit_btn").on("click", function (event) {
    event.preventDefault();
    console.log("treger");

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

      $.ajax({
        url: "course_" + method,
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: function (response) {
          // console.log("資料傳送成功");
          // console.log(response);
          alert("執行成功！請到雪點列表點擊上架顯示到滑雪地圖");
          location.reload();
        },
        error: function (xhr, status, error) {
          console.log("資料傳送失敗");
          console.log(error);
        },
      });
    }
  });
}

function resetInputState() {
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).removeClass("is-valid");
  $(
    "#courseSkill, #courseLevel, #courseName, #courseLocation, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#courseStatus,#skiImage,#courseIntroduce"
  ).addClass("is-invalid");
}

function resetForm() {
  resetInputState();
  function resetVal(field) {
    field.val("");
  }
  function resetOption(field) {
    field.prop("selectedIndex", -1);
  }

  $(
    "#courseName, #courseDate, #startDate, #endDate,#coursePrice,#courseMax,#courseMin,#skiImage,#courseIntroduce"
  ).blur(function () {
    resetVal($(this));
  });

  $("#courseSkill, #courseLevel,#courseLocation,#courseStatus").blur(
    function () {
      resetOption($(this));
    }
  );
}

function validateInputField(field) {
  var value;

  if (value === "") {
    field.removeClass("is-valid");
    field.addClass("is-invalid");
    field.next(".invalid-feedback");
  } else {
    value = field.val().trim();
    field.removeClass("is-invalid");
    field.addClass("is-valid");
    field.next(".invalid-feedback");
  }
}

function getCourseInfo(courseID) {
  $.ajax({
    url: "http://localhost:8080/ski/course_GBI",
    data: { courseID: courseID },
    type: "POST",
    dataType: "json",
    success: function (response) {
      // 地點選單生成
      option = $("<option>", {
        value: response.skiLocation.pointID,
        text: response.skiLocation.pointName,
      });

      $("#courseLocation").append(option);

      const courseDate = moment(response.courseDate).format("YYYY-MM-DD");
      const startDate = moment(response.startDate).format("YYYY-MM-DD");
      const endDate = moment(response.endDate).format("YYYY-MM-DD");

      $("#courseSkill").val(courseSkill);
      $("#courseLevel").val(courseLevel);
      $("#courseName").val(response.courseName);
      $("#courseLocation").val(response.skiLocation.pointID);
      $("#courseDate").val(courseDate);
      $("#startDate").val(startDate);
      $("#endDate").val(endDate);
      $("#coursePrice").val(response.coursePrice);
      $("#courseMax").val(response.courseMax);
      $("#courseMin").val(response.courseMin);
      $("#courseStatus").val(response.courseStatus);

      // $("#skiImage")[0].files[0];

      // const base64Image = btoa(
      //   new Uint8Array(response.coursePhoto).reduce(
      //     (data, byte) => data + String.fromCharCode(byte),
      //     ""
      //   )
      // );

      // const imageSrc = `data:image/png;base64,${base64Image}`;
      // document.getElementById("originalImage").src = imageSrc;
      // document.getElementById("originalImage").dataset.originalImage =
      //   base64Image;

      $("#courseIntroduce").val(response.courseIntroduce);
    },
    error: function () {
      console.log("error");
    },
  });
}

// function dateValidate(field) {
//   var value = field.val().trim();

//   if (value === "") {
//     field.removeClass("is-valid");
//     field.addClass("is-invalid");
//     field.next(".invalid-feedback");
//   } else {
//     field.removeClass("is-invalid");
//     field.addClass("is-valid");
//     field.next(".invalid-feedback");
//   }

//   // 檢查經度和緯度格式
//   if (field.attr("id") === "latInput") {
//     if (!isValidLatitude(value)) {
//       field.removeClass("is-valid");
//       field.addClass("is-invalid");
//       field.next(".invalid-feedback").text("請符合緯度格式(必填)");
//     }
//   } else if (field.attr("id") === "lngInput") {
//     if (!isValidLongitude(value)) {
//       field.removeClass("is-valid");
//       field.addClass("is-invalid");
//       field.next(".invalid-feedback").text("請符合經度格式(必填)");
//     }
//   }
// }
