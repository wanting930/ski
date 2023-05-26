// Usage example: Call the function with the course ID to retrieve the information
const urlParams = new URLSearchParams(window.location.search);
const courseId = urlParams.get("id"); // Replace 'your_course_id' with the actual ID of the course

$(document).ready(function () {
  getCourseInfo();

  // Get form element by ID
  const form = $("#courseForm");

  // Add event listener for form submission
  $("#addButton").on("click", function (event) {
    event.preventDefault(); // Prevent form from being submitted
	console.log("tregger");
    // Reset error messages
    $(".text-danger").addClass("d-none");

    // Validate form inputs
    let isValid = true;

    form
      .find(
        'select, input[type="text"], input[type="file"], input[type="date"], textarea'
      )
      .each(function () {
        const input = $(this);
        const value = input.val();
        if (value === null || value === "") {
          const errorMsg = input.parent().parent().siblings(".text-danger");
          errorMsg.removeClass("d-none");
          isValid = false;
        }
      });

    if (isValid) {
      // Get form values
      const courseSkill = document.getElementById("courseSkill").value;
      const courseLevel = document.getElementById("courseLevel").value;
      const courseName = document.getElementById("courseName").value;
      const courseLocation = document.getElementById("coursePrice").value;
      const courseDate = document.getElementById("courseDate").value;
      const startDate = document.getElementById("startDate").value;
      const endDate = document.getElementById("endDate").value;
      const coursePrice = document.getElementById("coursePrice").value;
      const courseMax = document.getElementById("courseMax").value;
      const courseMin = document.getElementById("courseMin").value;
      const courseStatus = document.getElementById("courseStatus").value;
      const coursePhoto = document.getElementById("coursePhoto").files[0];
      const courseIntroduce = document.getElementById("courseIntroduce").value;

	console.log(courseSkill);
	console.log(courseLevel);
	console.log(courseName);
	console.log(courseLocation);
	console.log(courseDate);
	console.log(startDate);
	console.log(endDate);
	console.log(courseMax);
	console.log(courseMin);
	console.log(courseStatus);
	console.log(coursePhoto);
	console.log(courseIntroduce);

      // Create FormData object to store form data
      const formData = new FormData();
      formData.append("courseID",courseId);
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
      console.log(formData.get("courseID"));
      
      
     $.ajax({
  url: "http://localhost:8080/ski/course_UD",
  type: "POST",
  data: formData,
  processData: false,
  contentType: false,
  success: function (response) {
    alert("更新成功");
  },
  error: function () {
    alert("更新失敗");
  }
});







    }
  });
});

// Function to retrieve course information by ID
function getCourseInfo(courseId) {
  // Perform AJAX request to fetch course data
  $.ajax({
    url: "http://localhost:8080/ski/course_GBI",
    type: "GET",
    success:  function (a) {
    	const response = a;
      // Populate the form fields with the retrieved data
		console.log(response);  
      $("#courseSkill").val(response.skill);
      $("#courseLevel").val(response.level);
      $("#courseName").val(response.courseName);
      $("#courseLocation").val(response.pointID);
      
      $("#courseDate").val(response.courseDate);
      $("#startDate").val(response.startDate);
      $("#endDate").val(response.endDate);
      $("#coursePrice").val(response.coursePrice);
      $("#courseMax").val(response.courseMax);
      $("#courseMin").val(response.courseMin);
      $("#courseStatus").val(response.courseStatus);
      $("#coursePhoto").val(response.coursePhoto);
      $("#courseIntroduce").val(response.courseIntroduce);

      // Additional handling or UI updates can be done here if needed
    },
    error: function (status, error) {
      console.error("Request failed. Status:", status, "Error:", error);
    },
  });
}