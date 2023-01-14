function createAspirasiCategory() {
    let cNameVal = $("#input-category-name").val()
    let cDescVal = $("#input-category-description").val()
    console.log(cNameVal)
    console.log(cDescVal)
    $.ajax({
        method: "POST",
        url: "/api/aspirasiCategory",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            categoryName: cNameVal,
            categoryDescription: cDescVal,
        }),
        beforeSend: addCsrfToken(),
        success: (result) => {
            $("#cardCreateAspriasiCategory").modal('hide')
            reload()
            $("#input-category-name").val("")
            $("#input-category-description").val("")
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'category has been created',
                showConfirmButton: false,
                timer: 1500
            })
            reload()
        },
        error: (jqXHR, exception) => {
            $("#cardCreateAspriasiCategory").modal('hide')
            reload()
            $("#input-category-name").val("")
            $("#input-category-description").val("")
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'category has alredy exists',
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

$(function(){
    $('#updateAspirasiCategory').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var categoryName = button.data('cn');
        var categoryId = button.data('ci');
        var categoryDesc = button.data('cd');
        var categoryOnGoing = button.data('co');
        var modal = $(this);
        modal.find('.modal-title').text('Aspirasimu pada subject '+ categoryName);
        modal.find('.modal-body .updateAspirasiSubject').val(categoryName);
        modal.find('.modal-body .updateAspirasiId').val(categoryId);
        modal.find('.modal-body .updateAspirasiOnGoing').val(categoryOnGoing);
        modal.find('.modal-body .updateAspirasiDesc').val(categoryDesc);
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

function deleteAspirasiCategory(id) {
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
                url: "/api/aspirasiCategory/delete/" + id,
                contentType: "application/json",
                dataType: "json",
                beforeSend: addCsrfToken(),
                success: (result) => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Aspirasi Category has been Deleted',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    reload()
                },error: (jqXHR, exception) => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Aspirasi Category has alredy exists',
                        showConfirmButton: false,
                        timer: 80000 
                    })
                    reload()
                }
            })
        }
    })
}