$(document).ready(function() {
    var table = $('#table-user').removeAttr('width').DataTable( {
        scrollY:        "300px",
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        columnDefs: [
            { width: 200, targets: 0 }
        ],
        fixedColumns: true,
        ajax: {
            url: '/api/user/all-user',
            dataSrc: ''
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'nim'
            },
            {
                data: 'username'
            },
            {
                data: 'password'
            },
            {
                data: 'email'
            },
            {
                data: 'notelp'
            },
            {
                data: 'verificationCode'
            },
            {
                data: 'isAccountLocked'
            },
            {
                data: 'isEnabled'
            },
            {
                data: null,
                render: function (data, type, row, meta) {
                    return `
                    <a href="/user/detailuser/${data.username}"> 
                        <button style="background-color: #b1ffff; color: black;" type="button" class="btn fw-bold"
                        data-bs-toggle="modal" data-bs-target="#detailAspirasi" >
                        Detail
                    </button>
                    </a>        
                    
                    `;
                }
            }
        ]
    } );
} );