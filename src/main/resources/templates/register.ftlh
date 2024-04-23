<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Форма регистрации</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Форма регистрации</h1>
    <form action="/register" method="post">
        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="surname">Фамилия:</label>
            <input type="text" class="form-control" id="surname" name="surname" required>
        </div>
        <div class="form-group">
            <label for="age">Возраст:</label>
            <input type="number" class="form-control" id="age" name="age" min="18" max="50" required>
        </div>
        <div class="form-group">
            <label for="email">Ваш адрес электронной почты:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Номер телефона:</label>
            <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" pattern="[0-9]+" required>
        </div>
        <div class="form-group">
            <label for="accountType">Кто вы?:</label>
            <select class="form-control" id="accountType" name="accountType" required>
                <option value="applicant">Соискатель</option>
                <option value="employer">Работодатель</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Зарегестрироваться</button>
    </form>
</div>
</body>
</html>
