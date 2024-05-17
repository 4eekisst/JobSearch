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
                            <input type="text" list="datalistOptions" class="form-control" id="category" name="category"
                                   placeholder="Название категории">
                            <datalist id="datalistOptions">
                                <option value="Marketing Manager">
                                <option value="Data base architecture">
                                <option value="Software Engineer">
                                <option value="Marketing Manager">
                                <option value="Sales Executive">
                                <option value="Financial Analyst">
                                <option value="HR Manager">
                                <option value="Customer Support Specialist">
                                <option value="Operations Manager">
                                <option value="UX/UI Designer">
                                <option value="Nurse">
                                <option value="Math Teacher">
                                <option value="Legal Counsel">
                                <option value="Hotel Manager">
                                <option value="Construction Engineer">
                                <option value="Research Scientist">
                                <option value="Event Coordinator">
                                <option value="Civil Rights Attorney">
                            </datalist>
                        </div>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        <button type="submit" class="btn btn-primary">Найти</button>
                    </form>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
