$(function () {

    const STORE_ID = 1;

    var config = {
        apiKey: "AIzaSyBOUyhBhWMBhV24YxdoS1aaYujMncWvsu0",
        authDomain: "mini-now.firebaseapp.com",
        databaseURL: "https://mini-now.firebaseio.com",
        projectId: "mini-now",
        storageBucket: "mini-now.appspot.com",
        messagingSenderId: "363572246420"
    };
    firebase.initializeApp(config);

    const fbStorage = firebase.storage();

    const productsTable = $("#productsTable").DataTable({
        paging: false,
        ordering: false,
        searching: false,
        ajax: {
            url: '/api/store/' + STORE_ID + '/product/',
            dataSrc: ''
        },
        columns: [
            {"data": "id", "width": "5%"},
            {"data": "name", "width": "35%"},
            {"data": "price", "width": "10%"},
            {
                "render": function (data, type, full, meta) {
                    return '<img src="' + full.imgUrl + '" width="150px" height="100px"/>';
                },
                "width": "20%"
            },
            {
                "render": function (data, type, full, meta) {
                    return '<button type="button" class="btn btn-primary extra" ' +
                        'data-toggle="modal" data-target="#extra" data-id="' + full.id + '">' +
                        'Thêm</button>';
                },
                "width": "10%"
            },
            {
                "render": function (data, type, full, meta) {
                    return '<button type="button" class="btn btn-primary update" ' +
                        'data-toggle="modal" data-target="#detail" data-id="' + full.id + '">' +
                        'Cập nhật </button>';
                },
                "width": "10%"
            },
            {
                "render": function (data, type, full, meta) {
                    return '<button type="button" class="btn btn-danger delete" ' +
                        'data-id="' + full.id + '">' +
                        'Xoá </button>';
                },
                "width": "10%"
            }
        ]
    });

    function ajax(options) {
        return new Promise((resolve, reject) => {
            $.ajax(options).done(resolve).fail(reject);
        });
    }

    function clearForm() {
        $('#id').text("");
        $('#name').val("");
        $('#price').val("");
        $('#imgUrl').val("");
        $('#description').val("");
        $(".custom-file-label").text("");
        selectedImg = undefined;
    }

    let insertFunc = function () {
        let product = {
            name: $('#name').val(),
            price: $('#price').val(),
            description: $('#description').val(),
            store: STORE_ID,
            imgUrl: "default"
        };
        ajax({
            url: '/api/product/',
            method: "POST",
            data: product
        }).then(result => {
            let imgUrl = "PO-" + result.id + ".jpg";
            fbStorage.ref().child(imgUrl).put(selectedImg).then(function (snapshot) {
                fbStorage.ref().child(imgUrl).getDownloadURL().then(function (url) {
                    let productTmp = {
                        id: result.id,
                        name: result.name,
                        price: result.price,
                        description: result.description,
                        imgUrl: url
                    };
                    ajax({
                        url: '/api/product/',
                        method: "PUT",
                        data: productTmp
                    }).then(result => {
                        productsTable.ajax.reload();
                        clearForm();
                    }).catch(error => {
                        console.log(error);
                    });
                }).catch(function (error) {
                });
            });
        }).catch(error => {
            console.log(error);
        });
    };

    let updateFunc = function () {
        let imgUrl = "PO-" + $('#id').text() + ".jpg";
        fbStorage.ref().child(imgUrl).put(selectedImg).then(function (snapshot) {
            fbStorage.ref().child(imgUrl).getDownloadURL().then(function (url) {
                let product = {
                    id: $('#id').text(),
                    name: $('#name').val(),
                    price: $('#price').val(),
                    description: $('#description').val(),
                    imgUrl: url
                };
                ajax({
                    url: '/api/product/',
                    method: "PUT",
                    data: product
                }).then(result => {
                    productsTable.ajax.reload();
                    clearForm();
                }).catch(error => {
                    console.log(error);
                });
            }).catch(function (error) {
            });
        });
    };

    $('.insert').on('click', function () {
        clearForm();
        $('.save').on('click', insertFunc);
    });

    let selectedImg;
    $('#imgUrl').on('change', function () {
        let file = this.files[0];
        let fileType = file["type"];
        let ValidImageTypes = ["image/gif", "image/jpeg", "image/png"];
        if ($.inArray(fileType, ValidImageTypes) >= 0) {
            $(".custom-file-label").text(file.name);
            selectedImg = file;
        } else {
            $(".custom-file-label").text("");
        }
    });

    productsTable.on('click', '.update', function () {
        ajax({
            url: '/api/product/' + $(this).data('id'),
            method: "GET"
        }).then(result => {
            $('#id').text(result.id);
            $('#name').val(result.name);
            $('#price').val(result.price);
            $('.save').on('click', updateFunc);
        }).catch(error => {
            console.log(error);
        });
    });

    productsTable.on('click', '.delete', function () {
        swal({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.value) {
                ajax({
                    url: '/api/product/' + $(this).data('id'),
                    method: "DELETE"
                }).then(result => {
                    productsTable.ajax.reload();
                }).catch(error => {
                    console.log(error);
                });
            }
        })
    });

    productsTable.on('click', '.extra', function () {
        $("#pid").text($(this).data('id'));
        $("#listRequire").empty();
        $("#listExtra").empty();
        ajax({
            url: '/api/product/' + $(this).data('id') + '/extra',
            method: "GET"
        }).then(result => {
            let optionals = JSON.parse(result.optional);
            for (let i = 0; i < optionals.length; i++) {
                newItemExtra(optionals[i].split(" - ")[0], optionals[i].split(" - ")[1]);
            }
            let requireds = JSON.parse(result.required);
            for (let i = 0; i < requireds.length; i++) {
                newItemRequire(requireds[i].split(" - ")[0], requireds[i].split(" - ")[1]);
            }
        }).catch(error => {
            console.log(error);
        });
    });

    $(".saveExtra").click(function () {
        let requires = new Set();
        let optionals = new Set();
        $("#listRequire li").each(function (i) {
            requires.add($(this).text());
        });
        $("#listExtra li").each(function () {
            optionals.add($(this).text());
        });
        let product = {
            id: parseInt($("#pid").text()),
            required: JSON.stringify(Array.from(requires)),
            optional: JSON.stringify(Array.from(optionals))
        };
        ajax({
            url: '/api/product/extra',
            method: "POST",
            data: product
        }).then(result => {
            $("#listRequire").empty();
            $("#listExtra").empty();
        }).catch(error => {
            console.log(error);
        });

    })


    function newItemExtra(name, price) {
        document.getElementById("errorExtra").innerHTML = '';
        var ul = document.getElementById("listExtra");
        var li = document.createElement("li");
        li.appendChild(document.createTextNode("" + name + " - " + price));
        ul.appendChild(li);
        li.onclick = removeItem;
    }

    function newItemRequire(name, price) {
        var ul = document.getElementById("listRequire");
        var li = document.createElement("li");
        li.appendChild(document.createTextNode("" + name + " - " + price));
        ul.appendChild(li);
        li.onclick = removeItem;
    }

    function removeItem(e) {
        e.target.parentElement.removeChild(e.target);
    }

});