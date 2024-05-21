<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="row">
        <div class="col-md-8">
            <div class="row">
                <#list resumes as resume>
                    <div class="col-sm-6 mb-3 mb-sm-5">
                        <div class="card mb-3">
                            <div class="card-body">
                                <h5 class="mt-2 mb-5"><a href="/resume/${resume.id}"
                                                         class="text-decoration-none text-dark rounded-2 px-3 py-1" style="height: 50px; overflow: hidden;">${resume.name}</a>
                                </h5>
                                <h2 class="card-text">${resume.category}</h2>
                                <p class="card-text"><@spring.message'filter.resume.salary'/><br> ${resume.salary}</p>
                                <a href="/resume/${resume.id}" class="btn btn-primary"><@spring.message'filter.resume.more'/></a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
        <div class="col-md-4 h-100">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message'filter.resume.filtered'/></h5>
                    <#if _csrf??>
                    <form action="/resume/filter" method="get">
                        <div class="mb-3">
                            <label for="category" class="form-label"><@spring.message'filter.resume.category'/></label>
                            <input type="text" list="datalistOptions" class="form-control" id="category" name="category"
                                   placeholder="<@spring.message'filter.resume.name.of.category'/>">
                            <datalist id="datalistOptions">
                                <#list categories as category>
                                <option value=${category.name}>
                                    </#list>
                            </datalist>
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label"><@spring.message'filter.resume.salary'/></label>
                            <input type="number" class="form-control" id="salary" name="salary"
                                   placeholder="<@spring.message'filter.resume.salary.info'/>">
                        </div>
                        <div class="mb-3">
                            <label for="applicant" class="form-label"><@spring.message'filter.resume.employee'/></label>
                            <input type="email" class="form-control" id="applicant" name="email"
                                   placeholder="<@spring.message'filter.resume.employee.email'/>">
                        </div>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        <button type="submit" class="btn btn-primary"><@spring.message'filter.resume.submit'/></button>
                    </form>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
