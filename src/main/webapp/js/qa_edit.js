$(document).ready(function() {
    $('#time').inputmask();
  });

//submit
    $(document).on("click", "#submit", function newQA() { 
        
        let title = $("#title").val();
        let type = $("#type").val();
        let time = $("#time").val();
        let ans = $("#ans").val();
        
        $.ajax({
            url: "insertQa",
            type: "POST",
            data: {
                questionTitle: title,
                questionType: type,
                questionDate: time,
                answerContent: ans            
            },
            dataType: "json",
            success: function (data) {
                console.log("a");
                
                // Swal.fire({
                //     title: '新增成功!',
                //     icon: 'success',
                //     showConfirmButton: false,   
                // });
                location.reload();
            },
            error: function (error) {
                console.log(error);
            }
        })
    })