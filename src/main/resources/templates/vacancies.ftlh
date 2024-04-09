<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Vacancies</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/register">Job Search</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/register">Registration Form
                    <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/vacancies">List of vacancies</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/resumes">List of resumes</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <h2>List of Vacancies:</h2>
    <#if vacancies?has_content>
        <#list vacancies as vacancy>
            <div class="card mb-3" style="max-width: 18rem;">
                <div class="card-header">${vacancy.name}</div>
                <div class="card-body">
                    <a href="/vacancies/${vacancy.id}" class="btn btn-primary">View Details</a>
                </div>
            </div>
        </#list>
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item<#if currentPage == 0><#else> active</#if>">
                    <a class="page-link" href="/vacancies?page=${currentPage - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
                <#list 0..(totalPages - 1) as page>
                    <li class="page-item<#if currentPage == page><#else> active</#if>">
                        <a class="page-link" href="/vacancies?page=${page}">${page + 1}</a>
                    </li>
                </#list>
                <li class="page-item<#if currentPage == (totalPages - 1)><#else> active</#if>">
                    <a class="page-link" href="/vacancies?page=${currentPage + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </ul>
        </nav>
    <#else>
        <p>No vacancies available</p>
    </#if>
</div>
</body>
</html>
