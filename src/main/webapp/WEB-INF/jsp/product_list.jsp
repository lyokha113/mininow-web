<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/4.1.3/css/bootstrap.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/webjars/datatables/1.10.19/media/css/jquery.dataTables.min.css"/>'>
    <link rel='stylesheet' href='<spring:url value="/css/product.css"/>'>
    <script src='<c:url value="/webjars/jquery/3.3.1/dist/jquery.min.js"/>'></script>
    <script src='<c:url value="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"/>'></script>
    <script src='<c:url value="/webjars/datatables/1.10.19/media/js/jquery.dataTables.min.js"/>'></script>
    <script src='<spring:url value="/js/sweetalert.min.js"/>'></script>
    <script src="https://www.gstatic.com/firebasejs/5.5.8/firebase.js"></script>
    <script src="https://www.gstatic.com/firebasejs/5.5.8/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/5.5.8/firebase-storage.js"></script>
    <script src='<spring:url value="/js/products.js"/>'></script>
    <link href="/dashboard/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="/dashboard/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link href="/dashboard/css/sb-admin.css" rel="stylesheet">
    <link href="/dashboard/css/custom.css" rel="stylesheet">

</head>

<body id="page-top">

<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="index.html">Shop Management</a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
    </button>

    <!-- Navbar Search -->
    <div class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
        <!--   <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
            <div class="input-group-append">
              <button class="btn btn-primary" type="button">
                <i class="fas fa-search"></i>
              </button>
            </div>
          </div> -->
    </div>

    <!-- Navbar -->
    <ul class="navbar-nav ml-auto ml-md-0" style="right: 0px">
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="" id="userDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="true">
                <i class="fas fa-user-circle fa-fw"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
            </div>
        </li>
    </ul>

</nav>

<div id="wrapper">

    <!-- Sidebar -->
    <ul class="sidebar navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="/product/">
                <i class="fab fa-product-hunt"></i>
                <span>Quản lý sản phẩm</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/orders/">
                <i class="fas fa-file-invoice"></i>
                <span>Theo dõi đơn hàng</span>
            </a>
        </li>
    </ul>

    <!-- Content -->
    <div id="content-wrapper">
        <div class="container-fluid">

            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="#">Quản lý sản phẩm</a>
                </li>
            </ol>
        </div>

        <!-- Datatable -->

        <div class="container" style="margin-top: 20px;">
            <h2>Danh sách sản phẩm</h2>

            <button type="button" class="btn btn-primary insert" data-toggle="modal" data-target="#detail"
                    style="margin-top: 10px; margin-bottom: 10px">
                Thêm sản phẩm
            </button>

            <table class="table table-striped" id="productsTable">
                <thead>
                <th scope="row">ID</th>
                <th scope="row">Tên</th>
                <th scope="row">Giá</th>
                <th scope="row">Ảnh</th>
                <th scope="row">Tuỳ chọn</th>
                <th scope="row">Cập nhật</th>
                <th scope="row">Xoá</th>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

        <div class="modal fade" id="detail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Chi tiết</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="d-none" id="id" name="id"></div>
                        <div class="form-group">
                            <label>Tên</label>
                            <input class="form-control" id="name" name="name">
                        </div>
                        <div class="form-group">
                            <label>Giá</label>
                            <input class="form-control" type="number" id="price" name="price">
                        </div>
                        <div class="form-group" style="margin-top: 25px">
                            <div class="custom-file" id="customFile" lang="vn">
                                <input type="file" class="custom-file-input" id="imgUrl" aria-describedby="fileHelp">
                                <label class="custom-file-label" for="imgUrl">
                                    Chọn ảnh
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Chú thích</label>
                            <input class="form-control" type="text" id="description" name="description">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-primary save" data-dismiss="modal">Lưu</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="extra" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Thông tin thêm</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="d-none" id="pid" name="pid"></div>
                        <div>
                            <label>Tuỳ chọn thay đổi</label> <label id="errorRequire"
                                                                    style="color: red"
                                                                    for="errorRequire"></label>
                            <div class="form-group">
                                <input id="inputRequireName" class="form-control" placeholder="Tên">
                                <input type="number" class="form-control" id="inputRequirePrice" placeholder="Giá" style="margin-bottom: 10px; margin-top: 10px">
                                <button type="button" class="btn btn-primary newrequired">Thêm</button>
                            </div>
                            <ul id="listRequire"></ul>
                        </div>
                        <div>
                            <label>Tuỳ chọn thêm</label> <label id="errorExtra" style="color: red; margin-top: 20px"
                                                                for="errorExtra"></label>
                            <div class="form-group">
                                <input id="inputExtraName" class="form-control" placeholder="Tên">
                                <input type="number" class="form-control" id="inputExtraPrice" placeholder="Giá" style="margin-bottom: 10px; margin-top: 10px">
                                <button type="button" class="btn btn-primary newextra">Thêm</button>
                            </div>
                            <ul id="listExtra"></ul>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-primary saveExtra" data-dismiss="modal">Lưu</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->

    <!-- Sticky Footer -->
    <footer class="sticky-footer">
        <div class="container my-auto">
            <div class="copyright text-center my-auto">
                <span>Copyright © Your Website 2018</span>
            </div>
        </div>
    </footer>
</div>
<!-- /.content-wrapper -->

<!-- Bootstrap core JavaScript-->
<script src="/dashboard/vendor/jquery/jquery.min.js"></script>
<script src="/dashboard/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/dashboard/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Page level plugin JavaScript-->
<script src="/dashboard/vendor/chart.js/Chart.min.js"></script>
<script src="/dashboard/vendor/datatables/jquery.dataTables.js"></script>
<script src="/dashboard/vendor/datatables/dataTables.bootstrap4.js"></script>

<!-- Custom scripts for all pages-->
<script src="/dashboard/js/sb-admin.min.js"></script>

<!-- Demo scripts for this page-->
<script src="/dashboard/js/demo/datatables-demo.js"></script>
<script src="/dashboard/js/demo/chart-area-demo.js"></script>
<script type="text/javascript" src="dashboard/js/custom.js"></script>
</body>

</html>