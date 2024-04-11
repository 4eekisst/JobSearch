<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration</title>
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
<div class="container">
    <h2>Registration Form</h2>
    <div class="form-group">
        <label for="userType">Кто вы?</label>
        <select class="form-control" id="userType" name="userType" required onchange="toggleFields()">
            <option value="applicant">Соискатель</option>
            <option value="employer">Работодатель</option>
        </select>
    </div>
    <form action="/register/submit" method="post">
        <div class="form-group" id="applicantFields">
            <label for="name">Имя:</label>
            <input type="text" class="form-control" id="name" name="name" required>
            <label for="surname">Фамилия:</label>
            <input type="text" class="form-control" id="surname" name="surname" required>
        </div>
        <div class="form-group" id="employerFields" style="display:none;">
            <label for="companyName">Название компании:</label>
            <input type="text" class="form-control" id="companyName" name="companyName" required>
        </div>
        <div class="form-group">
            <label for="age">Возраст:</label>
            <input type="number" class="form-control" id="age" name="age" required>
        </div>
        <div class="form-group">
            <label for="email">Адрес электронной почты:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Номер телефона:</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>

<script>
    function toggleFields() {
        var userType = document.getElementById("userType").value;
        var applicantFields = document.getElementById("applicantFields");
        var employerFields = document.getElementById("employerFields");

        if (userType === "applicant") {
            applicantFields.style.display = "block";
            employerFields.style.display = "none";
        } else {
            applicantFields.style.display = "none";
            employerFields.style.display = "block";
        }
    }
</script>

</body>
</html>
