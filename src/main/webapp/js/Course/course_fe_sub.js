const urlParams = new URLSearchParams(window.location.search);
const courseID = urlParams.get("courseID");

$(document).ready(function () {
  console.log("ready");
  getCourseInfo();
});

function getCourseInfo() {
  // Perform AJAX request to fetch course data
  $.ajax({
    url: "http://localhost:8080/ski/course_GBI",
    data: { courseID: courseID },
    type: "POST",
    success: function (response) {
      console.log("pass");
      const courseDate = moment(response.courseDate).format("YYYY-MM-DD");
      const startDate = moment(response.startDate).format("YYYY-MM-DD");
      const endDate = moment(response.endDate).format("YYYY-MM-DD");

      $("#courseName").text(response.courseName);
      $("#coursePrice").text(response.coursePrice);
      $("#courseSkill").text(response.skill);
      $("#courseLevel").text(response.level);

      $("#courseCouch").text(response.couchID);
      $("#courseLocation").text(response.pointID);
      $("#courseDate").text(courseDate);
      $("#coursePeriod").text(startDate + " ~ " + endDate);

      $("#coursePerson").text(
        response.coursePerson + " / " + response.courseMax
      );

      //   const base64Image = btoa(
      //     new Uint8Array(response.coursePhoto).reduce(
      //       (data, byte) => data + String.fromCharCode(byte),
      //       ""
      //     )
      //   );
      //   const imageSrc = `data:image/png;base64,${base64Image}`;
      //   document.getElementById("originalImage").src = imageSrc;
      //   document.getElementById("originalImage").dataset.originalImage =
      //     base64Image;

      const blob = new Blob(response.coursePhoto);

      // Create a URL for the Blob object
      const blobURL = URL.createObjectURL(blob);

      // Set the Blob URL as the source of the img tag
      $("#coursePhoto").src = blobURL;

      $("#courseIntroduce").text(response.courseIntroduce);
    },
    error: function (status, error) {
      console.error("Request failed. Status:", status, "Error:", error);
    },
  });
}
