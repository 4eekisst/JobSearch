<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Vacancy</title>
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
    <h2>Create Vacancy</h2>
    <form action="/profile/vacancy" method="post">
        <div class="form-group">
            <label for="name">Vacancy Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description" required></textarea>
        </div>
        <div class="form-group">
            <label for="categoryId">Category:</label>
            <select class="form-control" id="categoryId" name="categoryId" required>
                <option value="">Select category</option>
                <option value="1">IT</option>
                <option value="2">Marketing</option>
                <option value="3">Finance</option>
            </select>
        </div>
        <div class="form-group">
            <label for="salary">Salary:</label>
            <input type="number" class="form-control" id="salary" name="salary">
        </div>
        <div class="form-group">
            <label for="expFrom">Experience From:</label>
            <input type="number" class="form-control" id="expFrom" name="expFrom">
        </div>
        <div class="form-group">
            <label for="expTo">Experience To:</label>
            <input type="number" class="form-control" id="expTo" name="expTo">
        </div>
        <button type="submit" class="btn btn-primary">Publish</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
