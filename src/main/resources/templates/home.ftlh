<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resumes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/register">Job Search</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/register">Registration Form
                    <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/login">Login
                    <span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>
</nav>
<div class="container mt-5">
    <h2>Список резюме</h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
        </tr>
        </thead>
        <tbody>
        <#list resumes as resume>
            <tr>
                <th scope="row">${resume.name}</th>
                <td>${resume.categoryId}</td>
                <td>${resume.salary}</td>
                <td>${resume.createdDate}</td>
                <td>${resume.updateTime}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item<#if currentPage == 0><#else> active</#if>">
                <a class="page-link" href="/?page=${currentPage - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <#list 0..(totalPages - 1) as page>
                <li class="page-item<#if currentPage == page><#else> active</#if>">
                    <a class="page-link" href="/?page=${page}">${page + 1}</a>
                </li>
            </#list>
            <li class="page-item<#if currentPage == (totalPages - 1)><#else> active</#if>">
                <a class="page-link" href="/?page=${currentPage + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </nav>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
