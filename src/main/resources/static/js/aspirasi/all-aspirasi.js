$(document).ready(function() {
    var table = $('#table-aspirasi').removeAttr('width').DataTable( {
        scrollY:        "300px",
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        columnDefs: [
            { width: 200, targets: 0 }
        ],
        fixedColumns: true,
        ajax: {
            url: '/api/aspirasi/all-aspirasi',
            dataSrc: ''
        },
        columns: [{
                data: 'id'
            },
            {
                data: 'date'
            },
            {
                data: 'approval.name'
            },
            {
                data: 'description'
            },
            {
                data: 'user.username'
            },
            {
                data: 'aspirasiCategory.categoryName'
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
    } );
} );