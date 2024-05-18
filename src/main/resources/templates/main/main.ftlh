<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <div class="col-md-8">
            <div class="row mt-4">
                <#list vacancies.content as vacancy>
                    <div class="col-sm-6 mb-3 mb-sm-5">
                        <div class="card">
                            <div class="card-body">
                                <a href="#" class="text-decoration-none text-dark">${vacancy.authorEmail}</a>
                                <h5 class="card-title mt-2 mb-3">
                                    <a href="vacancies/info/${vacancy.id}" class="text-decoration-none text-dark">${vacancy.name}</a>
                                </h5>
                                <p class="card-text">${vacancy.description}</p>
                                <p class="card-text">Зарплата: ${vacancy.salary}</p>
                                <a href="vacancies/info/${vacancy.id}" class="btn btn-primary">Подробнее</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>

            <div class="row mt-4">
                <div class="col-md-12">
                    <#if _csrf??>
                    <form action="/vacancies" method="get">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="sortBy">Сортировка:</label>
                            <select class="form-select" id="sortBy" name="sortBy">
                                <option value="">Без сортировки</option>
                                <option value="Date">По дате публикации</option>
                                <option value="Applies">По количеству откликов</option>
                            </select>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                            <button class="btn btn-primary" type="submit">Применить</button>
                        </div>
                    </form>
                    </#if>
                </div>
            </div>

            <div class="mt-4">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="/vacancies?page=${page - 1}">Предыдущая</a></li>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=1">1</a></li>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=2">2</a></li>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=3">3</a></li>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=${page + 1}">Следующая</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Фильтрация вакансий</h5>
                    <#if _csrf??>
                        <form action="/vacancies/filter" method="get">
                            <div class="mb-3">
                                <label for="category" class="form-label">Category:</label>
                                <select class="form-control" id="category" name="category">
                                    <option value="Software Development">Software Development</option>
                                    <option value="Marketing">Marketing</option>
                                    <option value="Finance">Finance</option>
                                    <option value="Bio Engineer">Bio Engineer</option>
                                    <option value="Engineering">Engineering</option>
                                    <option value="Sales">Sales</option>
                                    <option value="Human Resources">Human Resources</option>
                                    <option value="Customer Support">Customer Support</option>
                                    <option value="Operations">Operations</option>
                                    <option value="Design">Design</option>
                                    <option value="Healthcare">Healthcare</option>
                                    <option value="Education">Education</option>
                                    <option value="Legal">Legal</option>
                                    <option value="Hospitality">Hospitality</option>
                                    <option value="Construction">Construction</option>
                                    <option value="Research">Research</option>
                                </select>
                            </div>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                            <button type="submit" class="btn btn-primary">Filter</button>
                        </form>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
