$(document).ready(function () {
  // getAllCourse();
  renderCourse();

  $(".skilloptions").on("change", function () {
    getCourseByKeywordAndTag();
  });

  $(".leveloptions").on("change", function () {
    getCourseByKeywordAndTag();
  });

  $("#searchBar").on("change", function () {});
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

function getCourseByKeywordAndTag() {}

function renderCourse() {
  for (i = 0; i < 5; i++) {
    // 子頁導向按鈕生成
    const subDirectButton = $("<button>")
      .addClass("btn btn-secondary m-2 subDirect")
      .text("詳細資訊");
    // subDirectLink = $("<a>")
    //   .attr(
    //     "href",
    //     "http://localhost:8080/ski/course/backend_courseUpdate.html?courseID=" +
    //       course.courseID
    //   )
    //   .append(subDirectButton);
    //購物車按鈕生成
    addCartButton = $("<button>")
      .addClass("btn btn-secondary m-2 addCart")
      .text("加入購物車");

    //加入購物車功能綁定
    $(".addCart").on("click", function () {
      // $.ajax({
      //   url: "http://localhost:8080/ski/",
      //   type: "POST",
      //   dataType: "json",
      //   data: { keyWord: keyWord },
      //   success: function (data) {
      //     alert("加入購物車成功");
      //   },
      //   error: function () {
      //     console.log("error");
      //   },
      // });
    });

    // const base64Image = btoa(
    //   new Uint8Array(course.coursePhoto).reduce(
    //     (data, byte) => data + String.fromCharCode(byte),
    //     ""
    //   )
    // );
    // const imageSrc = `data:image/png;base64,${base64Image}`;

    // cardStr = `
    //   <div class="courseCard my-3 p-2 d-flex align-items-center position-relative">

    //       <div class="imgZone mx-2 ">
    //         <img class="crousePhoto" src='${imageSrc}' style="height: 180px; width: 250px; color: gray;">
    //       </div>

    //       <div class="textZone mb-3">
    //         <div id="titleGroup" class="d-flex ">
    //           <h1>${course.courseName}</h1>
    //         </div>
    //         <p class="p-2">
    //           ${course.courseIntroduce}
    //         </p>
    //       </div>

    //       <div class="btnGroup m-2 position-absolute bottom-0 end-0">
    //       </div>
    // 		</div>
    // `;

    cardStr = `
      <div class="courseCard  p-2 d-flex align-items-center position-relative">

          <div class="imgZone mx-2 ">
            <img class="crousePhoto" style="height: 180px; width: 250px; color: gray;">
          </div>

          <div class="textZone mb-3">
            <div id="titleGroup" class="d-flex ">
              <h1>課程名稱</h1>
            </div>
            <p class="p-2">
              課程介紹課程介紹課程介紹課程介紹課程介紹課程介紹課程介紹
              課程介紹課程介紹課程介紹課程介紹課程介紹課程介紹課程介紹
              課程介紹課程介紹課程介紹課程介紹課程介紹課程介紹課程介紹
              課程介紹課程介紹課程介紹課程介紹課程介紹課程介紹課程介紹
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
  }
}
