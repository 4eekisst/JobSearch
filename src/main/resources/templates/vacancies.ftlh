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
    <a class="navbar-brand" href="#">JobSearch</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/register/create">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Contact</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/vacancies">Просмотреть все вакансии!</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <h2>All Vacancies</h2>
    <#if vacancies?has_content>
        <#list vacancies as vacancy>
            <div class="card mb-3" style="max-width: 18rem;">
                <div class="card-header">${vacancy.name}</div>
                <div class="card-body">
                    <p class="card-text">${vacancy.description}</p>
                    <p class="card-text">Category: ${vacancy.class}</p>
                    <p class="card-text">Salary: ${vacancy.salary}</p>
                    <a href="/vacancies/${vacancy.id}" class="btn btn-primary">View Details</a>
                </div>
            </div>
        </#list>
    <#else>
        <p>No vacancies available</p>
    </#if>
</div>
</body>
</html>
