<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Vacancy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h1 class="text-center mt-5">Редактирование вакансии</h1>
<main class="container mt-5">
    <#if _csrf??>
    <form action="/vacancies/update?id=${vacancy.id}" method="post" class="row g-3">
        <div class="col-md-6">
            <label for="name" class="form-label">Название:</label>
            <input type="text" id="name" name="name" value="${vacancy.name}" class="form-control" required maxlength="30">
        </div>
        <div class="col-md-6">
            <label for="description" class="form-label">Описание:</label>
            <textarea id="description" name="description" class="form-control" required maxlength="245">${vacancy.description}</textarea>
        </div>
        <div class="col-md-6">
            <label for="category" class="form-label">Категория:</label>
            <input type="text" list="datalistOptions" id="category" name="category" class="form-control" value="${vacancy.category}" required maxlength="30">
            <datalist id="datalistOptions">
                <#list categories as category>
                <option value="${category.name}">
                    </#list>
            </datalist>
        </div>
        <div class="col-md-3">
            <label for="salary" class="form-label">Зарплата:</label>
            <input type="text" id="salary" name="salary" pattern="^\d+$" title="Please enter only digits" class="form-control" value="${vacancy.salary}" required>
        </div>
        <div class="col-md-3">
            <label for="expFrom" class="form-label">Опыт работы от:</label>
            <input type="number" id="expFrom" name="expFrom" min="0" max="40" class="form-control" value="${vacancy.expFrom}" required>
        </div>
        <div class="col-md-3">
            <label for="expTo" class="form-label">Опыт работы до:</label>
            <input type="number" id="expTo" name="expTo" min="0" max="50" class="form-control" value="${vacancy.expTo}" required>
        </div>
        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Обновить вакансию</button>
        </div>
    </form>
    </#if>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
