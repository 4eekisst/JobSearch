<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <h1 class="mb-4"><@spring.message'resume.create'/></h1>

    <main>
        <#if _csrf??>
        <form action="/resume/create" method="POST">
            <div class="card mb-4">
                <div class="card-body">
                    <div class="col mb-3">
                        <label for="resumeName" class="form-label"><@spring.message'resume.create.name.resume'/></label>
                        <input type="text" class="form-control" id="resumeName" name="title" required>
                    </div>
                    <div class="col mb-3">
                        <label for="category" class="form-label"><@spring.message'resume.create.category'/></label>
                        <input type="text" list="datalistOptions" class="form-control" id="category"
                               name="categoryName"
                               placeholder="<@spring.message'resume.create.name.of.category'/>">
                        <datalist id="datalistOptions">
                            <#list categories as category>
                            <option value=${category.name}>
                                </#list>
                        </datalist>
                    </div>
                    <div class="col mb-3">
                        <label for="salary" class="form-label"><@spring.message'resume.create.salary'/></label>
                        <input type="text" class="form-control" id="salary" name="salary" required>
                    </div>
                </div>
            </div>

            <div class="card mb-4" id="workExp">
                <h5 class="card-title text-center"><@spring.message'resume.create.work.exp'/></h5>
                <div id="work" class="d-flex justify-content-center">
                    <input id="add-work-btn" class="btn btn-secondary my-3" type="button" value="<@spring.message'resume.create.work.exp.info'/>">
                </div>
            </div>

            <div class="card mb-4" id="education">
                <h5 class="card-title text-center"><@spring.message'resume.create.education'/></h5>
                <div id="educationInfo" class="d-flex justify-content-center">
                    <input id="add-education-btn" class="btn btn-secondary my-3" type="button" value="<@spring.message'resume.create.education.info'/>">
                </div>
            </div>

            <div class="card mb-4" id="addContacts">
                <div class="card-body">
                    <h5 class="card-title"><@spring.message'resume.create.contact.info'/></h5>
                    <div class="mb-3">
                        <label for="instagram" class="form-label"><@spring.message'resume.create.link'/></label>
                        <input type="text" value="Instagram" class="form-control" id="instagram" name="contacts[0].type">
                    </div>
                    <div class="mb-3">
                        <label for="instagramValue" class="form-label"><@spring.message'resume.create.phone'/></label>
                        <input type="text" value="Instagram" class="form-control" id="instagramValue" name="contacts[0].infoValue">
                    </div>
                </div>
                <div id="contacts" class="d-flex justify-content-center">
                    <input id="add-contacts-btn" class="btn btn-success my-3" type="button" value="<@spring.message'resume.create.add.more'/>">
                </div>
            </div>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary btn-lg"><@spring.message'resume.create.submit'/></button>
            </div>
        </form>
        </#if>
    </main>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/js/createResume.js"></script>
</@main.layout>
