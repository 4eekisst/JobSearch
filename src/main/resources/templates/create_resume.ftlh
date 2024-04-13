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
            <label for="experienceYear">Год работы:</label>
            <input type="number" class="form-control" id="experienceYear" name="experienceYear" required>
        </div>
        <div class="form-group">
            <label for="companyName">Название компании:</label>
            <input type="text" class="form-control" id="companyName" name="companyName" required>
        </div>
        <div class="form-group">
            <label for="position">Должность:</label>
            <input type="text" class="form-control" id="position" name="position" required>
        </div>
        <div class="form-group">
            <label for="responsibilities">Обязанности:</label>
            <textarea class="form-control" id="responsibilities" name="responsibilities" required></textarea>
        </div>
        <div class="form-group">
            <label for="educationPlace">Место обучения:</label>
            <input type="text" class="form-control" id="educationPlace" name="educationPlace" required>
        </div>
        <div class="form-group">
            <label for="educationDuration">Срок обучения:</label>
            <input type="text" class="form-control" id="educationDuration" name="educationDuration" required>
        </div>
        <div class="form-group">
            <label for="socialLinks">Ссылки на соц. сети:</label><br>
            <span class="mr-3"><i class="fab fa-telegram"></i> <input type="text" class="form-control" id="telegram" name="telegram" placeholder="Telegram"></span>
            <span class="mr-3"><i class="far fa-envelope"></i> <input type="email" class="form-control" id="email" name="email" placeholder="Email"></span>
            <span class="mr-3"><i class="fas fa-phone"></i> <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Phone Number"></span>
            <span class="mr-3"><i class="fab fa-facebook"></i> <input type="text" class="form-control" id="facebook" name="facebook" placeholder="Facebook"></span>
            <span class="mr-3"><i class="fab fa-linkedin"></i> <input type="text" class="form-control" id="linkedin" name="linkedin" placeholder="Linkedin"></span>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Опубликовать</button>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
