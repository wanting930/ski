$(document).ready(function () {
  getRunnungCourse();

  var data = {
    userID: sessionStorage.getItem("userID"),
  };
  var jsonData = JSON.stringify(data);
  //var level = setLevel(jsonData);

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

function setLevel(jsonData) {
  $.ajax({
    url: "http://localhost:8080/ski/member/getOneMember",
    data: jsonData,
    type: "POST",
    dataType: "json",
    success: function (data) {
      level = data.level;
    },
    error: function () {
      console.log("error");
    },
  });
}

function getRunnungCourse() {
  $.ajax({
    url: "http://localhost:8080/ski/course_GRC",
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
      .addClass("btn btn-secondary m-2 subDirect ")
      .append(subDirectLink);

    //購物車按鈕生成
    register_allow = true;
    valid_result = "";

    if (register_allow) {
      valid_result = "加入購物車";
    } else {
      valid_result = "會員層級不符";
    }

    addCartButton = $("<button>")
      .addClass("btn btn-secondary m-2 addCart")
      .text(valid_result);

    if (register_allow) {
      addCartButton.addClass("disable");
    }

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

           <div class="btnGroup m-2 position-absolute bottom-0 end-0"></div>
           <div class="cardMask position-absolute" style="background-color: rgba(43, 43, 43, 0.8);" level="${course.level}> 
			</div>
     		</div>
     `;
    $("#courseContent").append(cardStr);
    resizeMask();

    $(".courseCard").each(function () {
      const btnGroup = $(this).find(".btnGroup");
      btnGroup.append(subDirectButton, addCartButton);
    });
  });
}

function resizeMask() {
  //resize mask
  var sourceDiv = $(".courseCard");
  var targetDiv = $(".cardMask");

  var sourceWidth = sourceDiv.width();
  var sourceHeight = sourceDiv.height();

  targetDiv.width(sourceWidth);
  targetDiv.height(sourceHeight);
  targetDiv.addClass("d-flex justify-content-center align-item-center");
  filterConfirm = $("<p>").text("該課程難度過高").css({
    color: "white",
  });

  targetDiv.html(filterConfirm);
}
