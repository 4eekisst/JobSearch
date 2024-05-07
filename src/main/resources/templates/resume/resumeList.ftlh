<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <div class="col-md-8">
            <div class="row">
                <#list resumes.content as resume>
                    <div class="col-sm-6 mb-3 mb-sm-5">
                        <div class="card">
                            <div class="card-body">
                                <a href="#" class="text-decoration-none text-dark">${resume.userEmail}</a>
                                <h5 class="card-title mt-2 mb-3">
                                    <a href="/resume/${resume.id}" class="text-decoration-none text-dark">${resume.name}</a>
                                </h5>
                                <p class="card-text">${resume.category}</p>
                                <p class="card-text">Зарплата: ${resume.salary}</p>
                                <a href="/resume/${resume.id}" class="btn btn-primary">Подробнее</a>

                            </div>
                        </div>
                    </div>
                </#list>
            </div>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="/resume?page=${page - 1}">Предыдущая</a></li>
                    <li class="page-item"><a class="page-link" href="/resume?page=1">1</a></li>
                    <li class="page-item"><a class="page-link" href="/resume?page=2">2</a></li>
                    <li class="page-item"><a class="page-link" href="/resume?page=3">3</a></li>
                    <li class="page-item"><a class="page-link" href="/resume?page=${page + 1}">Следующая</a></li>
                </ul>
            </nav>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Фильтрация резюме</h5>
                    <form action="/resume/filter" method="get">
                        <div class="mb-3">
                            <input type="text" list="datalistOptions" class="form-control" id="category" name="category"
                                   placeholder="Название категории">
                            <datalist id="datalistOptions">
                                <#list categories as category>
                                <option value="${category.name}">
                                    </#list>
                            </datalist>
                        </div>
                        <button type="submit" class="btn btn-primary">Найти</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
