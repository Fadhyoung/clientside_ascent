function createCmt() {
    let commentVal = $("#input-comment").val()
    let usernameVal = $("#input-username").val()
    let aspirasiIdVal = $("#input-aspirasiId").val()
    console.log(commentVal)
    console.log(usernameVal)
    console.log(aspirasiIdVal)
    $.ajax({
        method: "POST",
        url: "/api/comment",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            comment: commentVal,
            username: usernameVal,
            parentComment: null,
            aspirasiId : aspirasiIdVal
        }),
        beforeSend: addCsrfToken(),
        success: (result) => {
            $("#commentContainer").modal('hide')
            reload()
            $("#input-comment").val("")
            $("#input-description").val("")
            $("#input-username").val("")
            $("#input-aspirasiId").val("")
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Comment has been created',
                showConfirmButton: false,
                timer: 1500
            })
            reload()
        },
        error: (jqXHR, exception) => {
            $("#commentContainer").modal('hide')
            reload()
            $("#input-comment").val("")
            $("#input-description").val("")
            $("#input-username").val("")
            $("#input-aspirasiId").val("")
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Comment has alredy exists',
                showConfirmButton: false,
                timer: 80000
            })
        }
    })

}