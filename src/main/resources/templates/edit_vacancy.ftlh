<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Vacancy</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Редактирование вакансии</h2>
    <form action="/vacancies/edit/${vacancy.id}" method="post">
        <div class="form-group">
            <label for="name">Название вакансии:</label>
            <input type="text" class="form-control" id="name" name="name" value="${vacancy.name}" required>
        </div>
        <div class="form-group">
            <label for="description">Описание:</label>
            <textarea class="form-control" id="description" name="description" rows="3" required>${vacancy.description}</textarea>
        </div>
        <div class="form-group">
            <label for="category">Категория:</label>
            <input type="text" class="form-control" id="category" name="category" value="${vacancy.categoryId}" required>
        </div>
        <div class="form-group">
            <label for="salary">Зарплата:</label>
            <input type="number" class="form-control" id="salary" name="salary" value="${vacancy.salary}" required>
        </div>
        <button type="submit" class="btn btn-primary">Обновить вакансию</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
