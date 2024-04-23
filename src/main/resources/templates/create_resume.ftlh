<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание резюме</title>
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

<div class="container mt-5">
    <h2>Создание резюме:</h2>
    <form action="/resumes/create_resume" method="post">
        <div class="form-group">
            <label for="name">Название резюме:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="categoryId">Категория:</label>
            <select class="form-control" id="categoryId" name="categoryId" required>
                <option value="">Выберите категорию</option>
                <option value="1">IT</option>
                <option value="2">Marketing</option>
                <option value="3">Finance</option>
            </select>
        </div>
        <div class="form-group">
            <label for="salary">Ожидаемый оклад:</label>
            <input type="number" class="form-control" id="salary" name="salary" required>
        </div>
        <div class="form-group">
            <label for="aboutMe">О себе:</label>
            <textarea class="form-control" id="aboutMe" name="aboutMe" required></textarea>
        </div>

        <div class="form-group">
            <button type="button" class="btn btn-primary" id="addEducation">Добавить образование</button>
            <button type="button" class="btn btn-primary" id="addExperience">Добавить опыт работы</button>
            <button type="button" class="btn btn-primary" id="addSocialLinks">Добавить ссылки на соц. сети</button>
        </div>

        <div id="educationForm"></div>
        <div id="experienceForm"></div>
        <div id="socialLinksForm"></div>

        <div>
            <button type="submit" class="btn btn-primary">Опубликовать</button>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $("#addEducation").click(function() {
        if ($("#educationForm .form-group").length === 0) {
            $("#educationForm").append('<div class="form-group">' +
                '<label>Образование:</label>' +
                '<input type="text" class="form-control" name="education[]" required>' +
                '</div>');
        }
    });

    $("#addExperience").click(function() {
        if ($("#experienceForm .form-group").length === 0) {
            $("#experienceForm").append('<div class="form-group">' +
                '<label>Опыт работы:</label>' +
                '<input type="text" class="form-control" name="experience[]" required>' +
                '</div>');
        }
    });

    $("#addSocialLinks").click(function() {
        if ($("#socialLinksForm .form-group").length === 0) {
            $("#socialLinksForm").append('<div class="form-group">' +
                '<label>Ссылка на соц.сеть:</label>' +
                '<input type="url" class="form-control" name="socialLinks[]" required>' +
                '</div>');
        }
    });
</script>

</body>
</html>
