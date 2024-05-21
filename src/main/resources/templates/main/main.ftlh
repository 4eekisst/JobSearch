<#import "../layout.ftlh" as main>
<@main.layout ; spring>
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
                                <p class="card-text"><@spring.message'main.salary'/> ${vacancy.salary}</p>
                                <a href="vacancies/info/${vacancy.id}" class="btn btn-primary"><@spring.message'main.more'/></a>
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
                            <label class="input-group-text" for="sortBy"><@spring.message'main.sorting'/></label>
                            <select class="form-select" id="sortBy" name="sortBy">
                                <option value=""><@spring.message'main.no.sorting'/></option>
                                <option value="Date"><@spring.message'main.date.sorting'/></option>
                                <option value="Applies"><@spring.message'main.applies.sorting'/></option>
                            </select>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                            <button class="btn btn-primary" type="submit"><@spring.message'main.submit.sorting'/></button>
                        </div>
                    </form>
                    </#if>
                </div>
            </div>

            <div class="mt-4">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="/vacancies?page=${page - 1}"><@spring.message'main.pagination.previous'/></a></li>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=1">1</a></li>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=2">2</a></li>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=3">3</a></li>
                        <li class="page-item"><a class="page-link" href="/vacancies?page=${page + 1}"><@spring.message'main.pagination.next'/></a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message'main.filtered.vacancy'/></h5>
                    <#if _csrf??>
                        <form action="/vacancies/filter" method="get">
                            <div class="mb-3">
                                <label for="category" class="form-label"><@spring.message'main.filtered.category'/></label>
                                <select class="form-control" id="category" name="category">
                                    <option value="Software Development"><@spring.message'main.category.software.development'/></option>
                                    <option value="Marketing"><@spring.message'main.category.marketing'/></option>
                                    <option value="Finance"><@spring.message'main.category.finance'/></option>
                                    <option value="Bio Engineer"><@spring.message'main.category.bio.engineer'/></option>
                                    <option value="Engineering"><@spring.message'main.category.engineering'/></option>
                                    <option value="Sales"><@spring.message'main.category.sales'/></option>
                                    <option value="Human Resources"><@spring.message'main.category.human.resources'/></option>
                                    <option value="Customer Support"><@spring.message'main.category.customer.support'/></option>
                                    <option value="Operations"><@spring.message'main.category.operations'/></option>
                                    <option value="Design"><@spring.message'main.category.design'/></option>
                                    <option value="Healthcare"><@spring.message'main.category.healthcare'/></option>
                                    <option value="Education"><@spring.message'main.category.education'/></option>
                                    <option value="Legal"><@spring.message'main.category.legal'/></option>
                                    <option value="Hospitality"><@spring.message'main.category.hospitality'/></option>
                                    <option value="Construction"><@spring.message'main.category.construction'/></option>
                                    <option value="Research"><@spring.message'main.category.research'/></option>
                                </select>
                            </div>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                            <button type="submit" class="btn btn-primary"><@spring.message'main.submit.filter'/></button>
                        </form>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
