$(document).ready(function () {
  getAllCourse();

  $(".skilloptions").on("change", function () {
    getCourseByKeywordAndTag();
  });

  $(".leveloptions").on("change", function () {
    getCourseByKeywordAndTag();
  });

  $("#searchbar").on("input", function () {
    getCourseByKeywordAndTag();
  });
});

function getAllCourse() {
  $.ajax({
    url: "http://localhost:8080/ski/course_GA",
    type: "POST",
    dataType: "json",
    success: function (data) {
      renderCourse(data);
    },
    error: function () {
      console.log("error");
    },
  });
}

function getCourseByKeywordAndTag() {
  var keyWord = $("#searchbar").val();
  var courseLevel = $("input[name='leveloptions']:checked").val();
  var courseSkill = $("input[name='skilloptions']:checked").val();

  if (!keyWord) {
    keyWord = "";
  }
  if (!courseLevel) {
    courseLevel = "-1";
  }
  if (!courseSkill) {
    courseSkill = "-1";
  }

  $.ajax({
    url: "http://localhost:8080/ski/course_GBKAT",
    type: "POST",
    dataType: "json",
    data: {
      keyWord: keyWord,
      courseLevel: courseLevel,
      courseSkill: courseSkill,
    },
    success: function (data) {
      renderCourse(data);
    },
    error: function () {
      console.log("error");
    },
  });
}

function renderCourse(Course) {
  $("#courseContent").empty();
  Course.forEach((course) => {
    // 子頁導向連結生成
    subDirectLink = $("<a>")
      .attr(
        "href",
        "http://localhost:8080/ski/course/frontend_courseDetail.html?courseID=" +
          course.courseID
      )
      .text("詳細資訊")
      .css({
        "text-decoration": "none",
        color: "white",
      });
    // 子頁導向按鈕生成
    const subDirectButton = $("<button>")
      .addClass("btn btn-secondary m-2 subDirect")
      .append(subDirectLink);

    //購物車按鈕生成
    addCartButton = $("<button>")
      .addClass("btn btn-secondary m-2 addCart")
      .text("加入購物車");

    //加入購物車功能綁定
    $(".addCart").on("click", function () {
      $.ajax({
        url: "http://localhost:8080/ski/",
        type: "POST",
        dataType: "json",
        data: { keyWord: keyWord },
        success: function (data) {
          alert("加入購物車成功");
        },
        error: function () {
          console.log("error");
        },
      });
    });

    const base64Image = btoa(
      new Uint8Array(course.coursePhoto).reduce(
        (data, byte) => data + String.fromCharCode(byte),
        ""
      )
    );
    const imageSrc = `data:image/png;base64,${base64Image}`;

    cardStr = `
       <div class="courseCard border-top my-3 p-2 d-flex align-items-center position-relative">

           <div class="imgZone mx-2 ">
             <img class="crousePhoto" src='${imageSrc}' style="max-height: 180px; max-width: 250px; color: gray; height: 180px; width: 250px;">
           </div>

           <div class="textZone mb-3">
            <div id="titleGroup" class="d-flex ">
               <h1>${course.courseName}</h1>
             </div>
             <p class="p-2">
               ${course.courseIntroduce}
             </p>
           </div>

           <div class="btnGroup m-2 position-absolute bottom-0 end-0">
           </div>
     		</div>
     `;

    $("#courseContent").append(cardStr);
    $(".courseCard").each(function () {
      const btnGroup = $(this).find(".btnGroup");
      btnGroup.append(subDirectButton, addCartButton);
    });
  });
}
