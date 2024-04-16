<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
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
            <li class="nav-item">
                <a class="nav-link" href="/vacancies">List of vacancies</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/resumes">List of resumes</a>
            </li>
        </ul>
    </div>
</nav>

<#--проверка на соискателя и работодателя -->
<div class="container mt-5">
    <#if user??>
        <#if user.roleId == 1>
            <div class="card mt-3">
                <div class="card-body">
                    <h5 class="card-title">Личный кабинет Соискателя</h5>
                </div>
            </div>
        </#if>
        <#if user.roleId == 2>
            <div class="card mt-3">
                <div class="card-body">
                    <h5 class="card-title">Личный кабинет Работодателя</h5>
                </div>
            </div>
        </#if>
    </#if>
    <#--проверка на соискателя и работодателя конец -->

    <div class="card">
        <div class="card-body d-flex justify-content-between align-items-center">
            <div>
                <h5 class="card-title">
                    <span>${user.name}</span> <span>${user.surname}</span>
                </h5>
                <h5 class="card-title">Возраст: ${user.age}</h5>
                <button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#editProfileModal">
                    Редактировать
                </button>
            </div>
            <div class="text-right">
                <form action="<#if isEmployer>/vacancies/create_vacancy<#else>/resumes/create_resume</#if>" method="get"
                      class="mb-0">
                    <button type="submit" class="btn btn-primary btn-square btn-3d">
                        <#if isEmployer>Добавить вакансию<#else>Создать резюме</#if>
                    </button>
                </form>
            </div>
        </div>
    </div>

    <#--соискатель-->
    <div class="container mt-5">
        <#if user??>
            <#if user.roleId == 1>
                <#if resumes?? && resumes?size != 0>
                    <h3 class="my-3 text-center">Резюме:</h3>
                    <div class="row row-cols-2">
                        <#list resumes as resume>
                            <div class="col">
                                <div class="card mb-3">
                                    <div class="card-header">
                                        <h5 class="card-title">${resume.name}</h5>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text">
                                            <#if resume.updatedDate??>
                                        <h6>Updated Date: ${resume.updateTime}</h6>
                                        <#else>
                                            <h6>Created Date: ${resume.createdDate}</h6>
                                        </#if>
                                        </p>
                                    </div>
                                    <div class="card-footer">
                                        <a href="#" class="btn btn-secondary">Update</a>
                                        <a href="#" class="btn btn-secondary">Edit</a>
                                    </div>
                                </div>
                            </div>
                        </#list>
                    </div>
                <#else>
                    <div class="alert alert-info" role="alert">
                        You have no resumes yet
                    </div>
                </#if>
            </#if>
        </#if>
        <#--соискатель-->

        <#--работодатель-->
        <#if user.roleId?has_content && user.roleId == 2>
            <#if vacancies?? && vacancies?size != 0>
                <h3 class="my-3 text-center">Созданные вакансии:</h3>
                <div class="row row-cols-2">
                    <#list vacancies as vacancy>
                        <div class="col">
                            <div class="card mb-3">
                                <div class="card-header">
                                    <h5 class="card-title">${vacancy.name}</h5>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">
                                        <#if vacancy.updatedTime??>
                                    <h6>Updated Date: ${vacancy.updatedTime}</h6>
                                    <#else>
                                        <h6>Created Date: ${vacancy.createdDate}</h6>
                                    </#if>
                                    </p>
                                </div>
                                <div class="card-footer">
                                    <form action="/vacancies/edit/${vacancy.id}" method="get" style="display: inline;">
                                        <button type="submit" class="btn btn-primary">Редактировать вакансию</button>
                                    </form>
                                    <form action="/vacancies/edit/${vacancy.id}" method="post" style="display: inline;">
                                        <button type="submit" class="btn btn-primary">Обновить вакансию</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            <#else>
                <div class="alert alert-info" role="alert">
                    You have no vacancies yet
                </div>
            </#if>
        </#if>
    </div>
    <#--работодатель-->
    <#--список вакансий и резюме конец-->

    <#--модальное окно для редактирования профиля-->
    <div class="modal fade" id="editProfileModal" tabindex="-1" role="dialog"
         aria-labelledby="editProfileModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editProfileModalLabel">Редактирование профиля</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="editProfileForm" action="/profile/update" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <div class="form-group">
                            <label for="name">Имя</label>
                            <input type="text" class="form-control" id="name" name="name" value="${user.name}">
                        </div>
                        <div class="form-group">
                            <label for="surname">Фамилия</label>
                            <input type="text" class="form-control" id="surname" name="surname"
                                   value="${user.surname}">
                        </div>
                        <div class="form-group">
                            <label for="age">Возраст</label>
                            <input type="number" class="form-control" id="age" name="age" value="${user.age}">
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber">Номер телефона</label>
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
                                   value="${user.phoneNumber}">
                        </div>
                        <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<#--модальное окно для редактирования профиля конец-->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
