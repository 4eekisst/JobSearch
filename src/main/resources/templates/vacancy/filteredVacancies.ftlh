<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <div class="col-md-8">
            <div class="row">
                <#list vacancies as vacancy>
                    <div class="col-sm-6 mb-3 mb-sm-5">
                        <div class="card mb-3">
                            <div class="card-body">
                                <a href="#" class="text-decoration-none text-dark">${vacancy.authorEmail}</a>
                                <h3 class="mt-2 mb-5"><a href="vacancies/info/${vacancy.id}"
                                                         class="text-decoration-none text-dark rounded-2 px-3 py-1" style="height: 50px; overflow: hidden;">${vacancy.name}</a>
                                </h3>
                                <h5 class="card-title">${vacancy.description}</h5>
                                <p class="card-text">Зарплата:<br>${vacancy.salary}</p>
                                <a href="/vacancies/info/${vacancy.id}" class="btn btn-primary">Подробнее</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Фильтрация вакансий</h5>
                    <form action="/vacancies/filter" method="get">
                        <div class="mb-3">
                            <label for="category" class="form-label">Категория:</label>
                            <input type="text" list="datalistOptions" class="form-control" id="category" name="category"
                                   placeholder="название категории">
                            <datalist id="datalistOptions">
                                <#list categories as category>
                                <option value=${category.name}>
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
