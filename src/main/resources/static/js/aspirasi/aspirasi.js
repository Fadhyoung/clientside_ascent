$('#table-aspirasi').DataTable({
    ajax: {
        url: '/api/aspirasi',
        dataSrc: ''
    },
    columns: [{
            data: 'id'
        },
        {
            data: 'subject'
        },
        {
            data: 'like'
        },
        {
            data: 'dislike'
        },
        {
            data: 'description'
        },
        {
            data: 'user.id',
            render: function (data) {
                return `
                    <a style="text-decoration: none; color: grey;">${data}</a>
                `;
            }
        },
        {
            data: null,
            render: function (data, type, row, meta) {
                return `
                    <button style="background-color: #b1ffff; color: black;" type="button" class="btn fw-bold"
                     data-bs-toggle="modal" data-bs-target="#detailAspirasi"  onclick="findById(${data.id})">
                        Detail
                    </button>
                    <button style="background-color: #26a6a6;" type="button" class="btn fw-bold" data-bs-toggle="modal"
                    data-bs-target="#updateCountry">
                        Edit
                    </button>
                    <button class="btn btn-danger fw-bold">
                        Delete
                    </button>
                `;
            }
        }
    ]
})

$(function() {
    var html = "";
    $.ajax({
      url: "/api/aspirasi",
      success: function(result) {
        $.each(result, function(index, item) {
            html += '<div class="col "></div>';
            html += '<div class="cardAspriasi m-3" style="width : 25%;">';
            html += '<div class="p-3"><img th:src="@{images/jakarta.jpg}" class="cardImg card-img-top" alt="..." style="background_color:red;"></div>';
            html += '<div class="card-body d-felx ">';
            html += `<p class="card-title">${item.subject}</p>`;
            html += '<hr>';
            html += `<div><p class="card-text flex-nowrap  overflow-hidden">${item.description}</p></div>`;
            html += '</div>';
            html += `<div class="card-footer text-muted">
            <a href="${item.subject}"><button class="btn btn-outline-info m-3" type="button">Detail</button></a>
        </div>`;
            html += "</div>";
            html += "</div>";
          //using .html() will display one card,use loop to display each card
        });
        $(".aspirasiData").html(html);
      }
    });
  });


function findById(id) {
    console.log("bisa")
    $.ajax({
        method: "GET",
        url: "/api/aspirasi/" + id,
        dataType: "json",
        success: (result) => {
            console.log(`${result.subject}`)
            $("#aspirasi-id").text(`${result.id}`)
            $("#aspirasi-subject").text(`${result.subject}`)
            $("#aspirasi-like").text(`${result.like}`)
            $("#aspirasi-dislike").text(`${result.dislike}`)
            $("#aspirasi-description").text(`${result.description}`)
            $("#aspirasi-creator").text(`${result.user.username}`)
        }
    })
}

function findCommentsByAspirasi(id) {
    console.log("masuk sini bro")
    $.ajax({
        method: "GET",
        url: "/api/aspirasi/comments/" + id,
        dataType: "json"
    })
}

$("#getData").click(function (event) {
    event.preventDefault();
    $.ajax({
        url: "api/aspirasi",
        type: "GET",
        dataType: "json",
        success: (result) => {
            console.log(result)
            $("#output").text(result)
        }
    });
});

function createAspirasi() {
    let subjectVal = $("#input-subject").val()
    let descVal = $("#input-description").val()
    let nimVal = $("#input-nim").val()
    console.log(subjectVal)
    console.log(descVal)
    console.log(nimVal)

    $.ajax({
        method: "POST",
        url: "api/aspirasi",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            subject: subjectVal,
            description: descVal,
            nim: nimVal
        }),
        beforeSend: addCsrfToken(),
        success: (result) => {
            $("#createAspirasi").modal('hide')
            $("#table-aspirasi").DataTable().ajax.reload()
            $("#input-subject").val("")
            $("#input-description").val("")
            $("#input-nim").val("")
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
            $("#createAspirasi").modal('hide')
            $("#table-aspirasi").DataTable().ajax.reload()
            $("#input-subject").val("")
            $("#input-description").val("")
            $("#input-nim").val("")
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Aspirasi has alredy exists',
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
        var reply = button.data('cmt');
        var replyId = button.data('cmtid');
        var modal = $(this);
        modal.find('.modal-title').text('Reply to  ' + replyId);
        modal.find('.modal-body .replyid').val(replyId);
        modal.find('.modal-body .reply').val(reply);
    });
});


function getUserId(id) {
    var id = id;
    console.log("bisa")

}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    console.log(decodedCookie)
    let ca = decodedCookie.split(';');
    console.log(ca)
    for(let i = 0; i < ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
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

function uploadFileAspirasi() {
    let id = $("#id-aspirasi").val()
    var form = $('#file-upload-form')[0];
    var formData = new FormData(form);
    $.ajax({
        method: "POST",
        url: "/file/uploadByAspriasi/" + id,
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

function deleteFileAspirasi(id) {
    alert("deleteFileAspirasi")
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