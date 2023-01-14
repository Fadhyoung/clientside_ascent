function createUser(e) {
    let usernmaeVal = $("#input-username").val()
    let passwordVal =$("#input-password").val()
    let nimVal = $("#input-nim").val()
    let emailVal = $("#input-email").val()
    let notelpVal = $("#input-notelp").val()
    let rolaVal = "user"
    let isAccountLockedVal = false
    let isEnabledVal = true
    console.log(usernmaeVal)
    console.log(passwordVal)
    console.log(nimVal)
    console.log(emailVal)
    console.log(notelpVal)
    console.log(rolaVal)
    console.log(isAccountLockedVal)
    console.log(isEnabledVal)
    e.preventDefault()
    $.ajax({
        method: "POST",
        url: "/api/user",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            nim: nimVal,
            username: usernmaeVal,
            password : passwordVal,
            email: emailVal,
            notelp : notelpVal,
            isAccountLocked : isAccountLockedVal,
            isEnabled : isEnabledVal,
            role : rolaVal
            
        }),
        beforeSend: addCsrfToken(),
        success: (result) => {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'User has been created',
                showConfirmButton: false,
                timer: 1500
            })
            reload()
            var link = document.querySelector('#backToLogin');
            if(link) {
                link.click();
                }   
        },
        error: (jqXHR, exception) => {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'User has alredy exists',
                showConfirmButton: false,
                timer: 80000
            })
            reload()
            var link = document.querySelector('#backToLogin');
            if(link) {
                link.click();
                }   
        }
    })
}

function registerUser(e) {
    let usernmaeVal = $("#input-username").val()
    let passwordVal =$("#input-password").val()
    let nimVal = $("#input-nim").val()
    let emailVal = $("#input-email").val()
    let notelpVal = $("#input-notelp").val()
    let rolaVal = "user"
    let isAccountLockedVal = false
    let isEnabledVal = false
    console.log(usernmaeVal)
    console.log(passwordVal)
    console.log(nimVal)
    console.log(emailVal)
    console.log(notelpVal)
    console.log(rolaVal)
    console.log(isAccountLockedVal)
    console.log(isEnabledVal)
    console.log("register")
    e.preventDefault()
    Swal.fire({
        icon: 'info',
        position: 'center',
        title: 'Mohon Tunggu sebentar',
        text : 'Sedang memeriksa data anda',
        showConfirmButton: false
    })
    $.ajax({
        method: "POST",
        url: "/api/user/register",
        contentType: "application/json",
        data: JSON.stringify({
            nim: nimVal,
            username: usernmaeVal,
            password : passwordVal,
            email: emailVal,
            notelp : notelpVal,
            isAccountLocked : isAccountLockedVal,
            isEnabled : isEnabledVal,
            role : rolaVal
            
        }),
        beforeSend: function(){
            addCsrfToken(),
            $('#loader').css("visibility", "visible");
          },
        success: (result) => {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Registrasi Akun Anda Berhasil',
                text: 'Verifikasi akunmu melalui email yang kami kirimkan',
            }).then(function() {
                window.location = '/login';
            });
        },
        error: (jqXHR, exception) => {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Usaha registrasi anda gagal',
                text: 'Periksa lagi data diri anda',
            }).then(function() {
                window.location = '/login';
            });  
        }
    })
}


$(document).ready(function() {
    $("#show_hide_password a").on('click', function(event) {
        event.preventDefault();
        if($('#show_hide_password input').attr("type") == "text"){
            $('#show_hide_password input').attr('type', 'password');
            $('#show_hide_password i').addClass( "fa-eye-slash" );
            $('#show_hide_password i').removeClass( "fa-eye" );
        }else if($('#show_hide_password input').attr("type") == "password"){
            $('#show_hide_password input').attr('type', 'text');
            $('#show_hide_password i').removeClass( "fa-eye-slash" );
            $('#show_hide_password i').addClass( "fa-eye" );
        }
    });
});


function reload(){
    window.location.reload();
}

function updateUser(id) {
    let usernmaeVal = $("#input-username").val()
    let passwordVal =$("#input-password").val()
    let nimVal = $("#input-nim").val()
    let emailVal = $("#input-email").val()
    let notelpVal = $("#input-notelp").val()
    let rolaVal = "user"
    let isAccountLockedVal = false
    let isEnabledVal = true
    console.log(usernmaeVal)
    console.log(passwordVal)
    console.log(nimVal)
    console.log(emailVal)
    console.log(notelpVal)
    console.log(rolaVal)
    console.log(isAccountLockedVal)
    console.log(isEnabledVal)
    console.log(id)
    $('#loader').show();
    $.ajax({
        method: "PUT",
        url: "/api/user/updateUser/" + id,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            nim: nimVal,
            username: usernmaeVal,
            password : passwordVal,
            email: emailVal,
            notelp : notelpVal,
            isAccountLocked : isAccountLockedVal,
            isEnabled : isEnabledVal,
            role : rolaVal
        }),
        beforeSend: addCsrfToken(),
        success: (result) => {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'User has been created',
                showConfirmButton: false,
                timer: 1500
            })
            reload()
            var link = document.querySelector('#backToLogin');
            if(link) {
                link.click();
                }   
        },
        error: (jqXHR, exception) => {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'User has alredy exists',
                showConfirmButton: false,
                timer: 80000
            })
            reload()
            var link = document.querySelector('#backToLogin');
            if(link) {
                link.click();
                }   
        }
    })
}

function deleteUser(nim) {
    console.log(nim)
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be delete this User!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "/api/user/delete/" + nim,
                contentType: "application/json",
                dataType: "json",
                beforeSend: addCsrfToken(),
                success: (result) => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'User has been Deleted',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    reload()
                    var link = document.querySelector('#backToLogin');
                    if(link) {
                        link.click();
                        }   
                },error: (jqXHR, exception) => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'User has alredy exists',
                        showConfirmButton: false,
                        timer: 80000 
                    })
                    reload()            
                    var link = document.querySelector('#backToLogin');
                    if(link) {
                        link.click();
                        }   
                }
            })
        }
    })
}