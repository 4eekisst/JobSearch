<#macro layout>
    <!doctype html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <meta name="_csrf_token" content="${_csrf.token}"/>
        <title>Main page</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;500;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="/style.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </head>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark bg-primary pt-5 pb-5" data-bs-theme="dark">
            <div class="container">
                <a class="navbar-brand" href="/">Job Search</a>
                <button class="navbar-toggler text-light bg-orange btn-user ms-auto" type="button"
                        data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i class="bi bi-list-nested"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav fw-medium mx-auto">
                        <li class="mx-3 nav-item">
                            <a class="nav-link" aria-current="page" href="/vacancies">Список вакансий
                            </a>
                        </li>
                        <li class="mx-3 nav-item">
                            <a class="nav-link" aria-current="page" href="/resume">Список резюме
                            </a>
                        </li>
                        <li class="mx-3 nav-item">
                            <a class="nav-link" href="/register">Зарегистрироваться</a>
                        </li>
                        <li class="mx-3 nav-item">
                            <a class="nav-link" href="/profile">Перейти в личный кабинет</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <body>
    <div class=" py-5">
        <div class="container">
            <#nested springMacroRequestContext>
        </div>
    </div>

    </body>

    </html>

</#macro>
