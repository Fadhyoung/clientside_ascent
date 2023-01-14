function createAspirasi() {
    let cNameVal = $("#input-subject").val()
    let cDescVal = $("#input-description").val()
    let usernameVal = $("#input-username").val()
    let cIdVal = $("#input-category-id").val()
    console.log(cNameVal)
    console.log(cDescVal)
    console.log(usernameVal)
    console.log(cIdVal)
    $.ajax({
        method: "POST",
        url: "/api/aspirasi",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            approvalId: 1,
            description: cDescVal,
            username: usernameVal,
            aspirasiCategoryId: cIdVal
        }),
        beforeSend: addCsrfToken(),
        success: (result) => {
            $("#ListofAspirasiCategory")
            $("#input-subject").val("")
            $("#input-description").val("")
            $("#input-username").val("")
            $("#input-category-id").val("")
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Aspirasi has been created',
                showConfirmButton: false,
                timer: 1500
            })
            reload()
        },
        error: (jqXHR, exception) => {
            $("#ListofAspirasiCategory")
            $("#input-subject").val("")
            $("#input-description").val("")
            $("#input-username").val("")
            $("#input-category-id").val("")
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Aspirasi has alredy exists',
                showConfirmButton: false,
                timer: 80000
            })
            reload()
        }
    })
}

function uploadFileAspirasi() {
    var form = $('#file-upload-form')[0];
    var formData = new FormData(form);
    alert(formData)
    $.ajax({
        method: "POST",
        url: "/file/upload/3",
        enctype: 'multipart/form-data',
        processData: false, 
        contentType:false,
        data : formData,
        beforeSend: addCsrfToken(),
        success: (result) => {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Aspirasi file has been created',
                showConfirmButton: false,
                timer: 1500
            })
        },
        error: (jqXHR, exception) => {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Aspirasi file has alredy exists',
                showConfirmButton: false,
                timer: 80000
            })
        }
    })
}

function reload(){
    window.location.reload();
}

$(function(){
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var reply = button.data('sbc');
        var reply2 = button.data('sbcid');
        var categoryName = button.data('cn');
        console.log(categoryName);
        console.log(reply2);
        var modal = $(this);
        modal.find('.modal-title').text('Aspirasimu pada subject '+ reply);
        modal.find('.modal-body .createAspirasi').val(reply);
        modal.find('.modal-body .createAspirasi2').val(reply2);
        modal.find('.modal-body .createAspirasiSubject').val(categoryName);
    });
});

function updateAspirasiCategory() {
    let ciVal = $("#update-category-id").val()
    let cnVal = $("#update-subject").val()
    let cdVal = $("#update-description").val()
    let coVal = $("#update-onGoing").val()
    console.log(ciVal)
    console.log(cnVal)
    console.log(cdVal)
    console.log(coVal)

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't to change this Aspirasi Category?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "api/aspirasiCategory/" + ciVal,
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    categoryName: cnVal,
                    categoryDescription: cdVal,
                    onGoing: coVal
                }),
                beforeSend: addCsrfToken(),
                success: (result) => {
                    $("#update-category-id").val("")
                    $("#update-subject").val("")
                    $("#update-description").val("")
                    $("#update-onGoing").val("")
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Aspirasi Category has been updated',
                        showConfirmButton: false,
                        timer: 1500
                    })
                },error: (jqXHR, exception) => {
                    $("#update-category-id").val("")
                    $("#update-subject").val("")
                    $("#update-description").val("")
                    $("#update-onGoing").val("")
                    reload()
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Aspirasi Category has alredy exists',
                        showConfirmButton: false,
                        timer: 80000
                    })
                }
                
            })
        }
    })
}

$(function(){
    $('#uploadModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var categoryId = button.data('ci');
        var categoryName = button.data('cn');
        var modal = $(this);
        modal.find('.modal-title').text('Upload Category File pada subject '+ categoryName);
        modal.find('#file-upload-form .file-category-id').val(categoryId);
    });
});

function uploadFileCategory() {
    let id = $("#id-category").val()
    var form = $('#file-category-upload-form')[0];
    var formData = new FormData(form);
    $.ajax({
        method: "POST",
        url: "/file/uploadByCategory/" + id,
        enctype: 'multipart/form-data',
        processData: false, 
        contentType:false,
        data : formData,
        beforeSend: addCsrfToken(),
        success: (result) => {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Aspirasi file has been created',
            }).then(function() {
                $("#uploadModal").load(" #uploadModal > *");
                $("#downloadModal").load(" #downloadModal > *");
            });
        },
        error: (jqXHR, exception) => {
            alert(jqXHR)
            alert(exception)
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Aspirasi file has alredy exists',
                showConfirmButton: false,
                timer: 80000
            })
        }
    })
}

function deleteFile(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this Aspirasi File!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "/file/" + id,
                contentType: "application/json",
                beforeSend: addCsrfToken(),
                success: (result) => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'File has been Deleted',
                        showConfirmButton: false,
                        timer: 2000
                    }).then(function() {
                        $("#uploadModal").load(" #uploadModal > *");
                        $("#downloadModal").load(" #downloadModal > *");
                    });
                },error: (jqXHR, exception) => {
                    Swal.fire({
                        position: 'center',
                        icon: 'Failed',
                        title: 'Cannot delete this file',
                        showConfirmButton: false,
                        timer: 2000 
                    })
                    reload()
                }
            })
        }
    })
}

function deleteAspirasi(id) {
    console.log(id)
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this Aspirasi Category!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "/api/aspirasi/delete/" + id,
                contentType: "application/json",
                dataType: "json",
                beforeSend: addCsrfToken(),
                success: (result) => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Aspirasi has been Deleted',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    reload()
                },error: (jqXHR, exception) => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Aspirasi has alredy exists',
                        showConfirmButton: false,
                        timer: 80000 
                    })
                    reload()
                }
            })
        }
    })
}

$(function(){
    $('#approveAspirasi').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var aspirasiId = button.data('ai');
        var aspirasiApprove = button.data('aa');
        var modal = $(this);
        modal.find('.modal-body .approveAspirasiId').val(aspirasiId);
        modal.find('.modal-body .approveAspirasiAi').val(aspirasiApprove);
    });
});

function approveAspirasi(id) {
    let aiVal = $("#aspirasi-id").val()
    let aaVal = $("#aspirasi-approve-id").val()
    console.log(aiVal)
    console.log(aaVal)
    console.log(id)

    Swal.fire({
        title: 'Are you sure?',
        text: "You won't to change approval for this Aspirasi?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "/api/aspirasi/" + aiVal,
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    approvalId: id,
                }),
                beforeSend: addCsrfToken(),
                success: (result) => {
                    $("#aspirasi-id").val("")
                    $("#aspirasi-approve-id").val("")
                    reload()
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Aspirasi apprioval has been updated',
                        showConfirmButton: false,
                        timer: 1500
                    })
                },error: (jqXHR, exception) => {
                    $("#aspirasi-id").val("")
                    $("#aspirasi-approve-id").val("")
                    reload()
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Aspirasi apprioval has alredy exists',
                        showConfirmButton: false,
                        timer: 80000
                    })
                }
                
            })
        }
    })
}

