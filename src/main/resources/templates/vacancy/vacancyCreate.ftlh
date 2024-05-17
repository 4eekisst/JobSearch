<#import "../layout.ftlh" as main>
<@main.layout>

    <main>
        <#if _csrf??>
        <form action="/vacancies/create" method="POST" enctype="application/x-www-form-urlencoded">
            <div class="card mb-4">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <label for="vacancyName" class="form-label">Название Вакансии</label>
                            <input type="text" class="form-control" id="vacancyName" name="name" required>
                        </div>
                        <div class="col-md-12">
                            <label for="category" class="form-label">Категория:</label>
                            <input type="text" list="datalistOptions" class="form-control" id="category" name="category">
                            <datalist id="datalistOptions">
                                <#list categories as category>
                                <option value=${category.name}>
                                    </#list>
                            </datalist>
                        </div>
                        <div class="col-md-12">
                            <label for="salary" class="form-label">Зарплата</label>
                            <input type="text" class="form-control" id="salary" name="salary" required>
                        </div>
                        <div class="col-md-12">
                            <label for="description" class="form-label">Описание</label>
                            <textarea  class="form-control" id="description" name="description" required></textarea>
                        </div>
                        <div class="col-md-6">
                            <label for="expFromId" class="form-label">Опыт работы от</label>
                            <input type="number" class="form-control" id="expFromId" name="expFrom">
                        </div>
                        <div class="col-md-6">
                            <label for="expToId" class="form-label">Опыт работы до</label>
                            <input type="number" class="form-control" id="expToId" name="expTo">
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary btn-lg">Создать</button>
            </div>
        </form>
        </#if>
    </main>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</@main.layout>
