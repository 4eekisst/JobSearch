<#import "../layout.ftlh" as main>
<@main.layout ; spring>
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
                                <p class="card-text"><@spring.message'filtered.vacancy.salary'/><br>${vacancy.salary}</p>
                                <a href="/vacancies/info/${vacancy.id}" class="btn btn-primary"><@spring.message'filtered.vacancy.more'/></a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message'filtered.vacancy.filtered'/></h5>
                    <#if _csrf??>
                        <form action="/vacancies/filter" method="get">
                            <div class="mb-3">
                                <label for="category" class="form-label"><@spring.message'filtered.vacancy.category'/></label>
                                <select class="form-control" id="category" name="category">
                                    <option value="Software Development"><@spring.message'filtered.vacancy.software.development'/></option>
                                    <option value="Marketing"><@spring.message'filtered.vacancy.marketing'/></option>
                                    <option value="Finance"><@spring.message'filtered.vacancy.finance'/></option>
                                    <option value="Bio Engineer"><@spring.message'filtered.vacancy.bio.engineer'/></option>
                                    <option value="Engineering"><@spring.message'filtered.vacancy.engineering'/></option>
                                    <option value="Sales"><@spring.message'filtered.vacancy.sales'/></option>
                                    <option value="Human Resources"><@spring.message'filtered.vacancy.human.resources'/></option>
                                    <option value="Customer Support"><@spring.message'filtered.vacancy.customer.support'/></option>
                                    <option value="Operations"><@spring.message'filtered.vacancy.operations'/></option>
                                    <option value="Design"><@spring.message'filtered.vacancy.design'/></option>
                                    <option value="Healthcare"><@spring.message'filtered.vacancy.healthcare'/></option>
                                    <option value="Education"><@spring.message'filtered.vacancy.education'/></option>
                                    <option value="Legal"><@spring.message'filtered.vacancy.legal'/></option>
                                    <option value="Hospitality"><@spring.message'filtered.vacancy.hospitality'/></option>
                                    <option value="Construction"><@spring.message'filtered.vacancy.construction'/></option>
                                    <option value="Research"><@spring.message'filtered.vacancy.research'/></option>
                                </select>
                            </div>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                            <button type="submit" class="btn btn-primary"><@spring.message'filtered.vacancy.filter'/></button>
                        </form>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
