<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container py-5">
        <div class="row">
            <div class="col-md-4">
                <#if user.avatar??>
                    <img src="/api/users/image/${user.avatar}" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                <#else >
                    <img src="api/users/image/anon.jpeg" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
                height: 250px;
                object-fit: cover;
                cursor: pointer;
                margin: 0 auto;
                transition: transform 0.2s ease-in-out; ">
                </#if>
                <div class="user-info mt-5">
                    <h4><@spring.message'edit.resume.contact.info'/></h4>
                    <p class="text-muted"><strong><@spring.message'edit.resume.user'/></strong>${user.name} ${user.surname}</p>
                    <p class="text-muted"><strong><@spring.message'edit.resume.age'/></strong> ${user.age}</p>
                    <p class="text-muted"><strong><@spring.message'edit.resume.phone'/></strong> ${user.phoneNumber}</p>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card border-0 shadow-lg">
                    <main>
                        <#if _csrf??>
                        <form action="/resume/update?id=${resume.id}" method="post">
                            <div class="card-body">
                                <h3 class="card-title mb-4">
                                    <label>
                                        <input type="text" class="form-control" name="title" value="${resume.title}"
                                               placeholder="Resume Name">
                                    </label>
                                </h3>
                                <p class="card-text text-muted">
                                    <strong><@spring.message'edit.resume.category'/></strong>
                                    <label>
                                        <input type="text"
                                               name="categoryName"
                                               list="datalistOptions"
                                               value="${resume.categoryName}"
                                               placeholder="<@spring.message'edit.resume.category.place.holder'/>">
                                    </label>
                                    <datalist id="datalistOptions">
                                        <#list categories as category>
                                        <option value="${category.name}">
                                            </#list>
                                    </datalist>
                                </p>
                                <p class="card-text text-muted"><strong><@spring.message'edit.resume.salary'/></strong> <label>
                                        <input type="text"
                                               name="salary"
                                               value="${resume.salary}"
                                               placeholder="<@spring.message'edit.resume.salary.place.holder'/>">
                                    </label>
                                </p>

                                <hr class="my-4">

                                <h5 class="card-subtitle mb-3 text-muted"><@spring.message'edit.resume.work.exp'/></h5>
                                <div class="work-experience">
                                    <#list resume.workExpInfoEdit as work>
                                        <div class="mb-5">
                                            <h6>
                                                <label>
                                                    <input name="workExpInfoEdit[${work_index}].id" value="${work.id}"
                                                           placeholder="id" disabled>
                                                </label>
                                                <label>
                                                    <input type="text" name="workExpInfoEdit[${work_index}].position" value="${work.position}"
                                                           placeholder="<@spring.message'edit.resume.position'/>">
                                                </label> - <label>
                                                    <input type="text" name="workExpInfoEdit[${work_index}].companyName"
                                                           value="${work.companyName}"
                                                           placeholder="<@spring.message'edit.resume.company.name'/>">
                                                </label></h6>
                                            <p><label>
                                                    <textarea name="workExpInfoEdit[${work_index}].responsibilities"
                                                              placeholder="<@spring.message'edit.resume.responsibilities'/>">${work.responsibilities}</textarea>
                                                </label>
                                            </p>
                                            <p class="text-muted">Worked: <label>
                                                    <input type="text" name="workExpInfoEdit[${work_index}].years"
                                                           placeholder="<@spring.message'edit.resume.seniority'/>">
                                                </label> years</p>
                                        </div>
                                    </#list>
                                    <#assign workExpLastIndex = resume.workExpInfoEdit?size - 1>
                                </div>
                                <div id="work">
                                    <div class="d-flex justify-content-center my-4">
                                        <input id="add-work-btn" class="btn btn-secondary mt-3 mb-1" type="button"
                                               value="<@spring.message'edit.resume.add.work.exp'/>">
                                    </div>
                                </div>
                                <hr class="my-4">

                                <h5 class="card-subtitle mb-3 text-muted"><@spring.message'edit.resume.education'/></h5>
                                <div class="education">
                                    <#list resume.educationInfo as edu>
                                        <div class="mb-3">
                                            <label>
                                                <input name="educationInfo[${edu_index}].id" value="${edu.id}"
                                                       placeholder="id" disabled>
                                            </label>
                                            <h6><label>
                                                    <input type="text" name="educationInfo[${edu_index}].degree" value="${edu.degree}"
                                                           placeholder="<@spring.message'edit.resume.higher.education'/>">
                                                </label>, <label>
                                                    <input type="text" name="educationInfo[${edu_index}].institution"
                                                           placeholder="<@spring.message'edit.resume.educational.establishment'/>">
                                                </label></h6>
                                                <p>
                                                <label>
                                                    <textarea name="educationInfo[${edu_index}].program"
                                                placeholder="<@spring.message'edit.resume.program'/>">${edu.program}</textarea>
                                                </label></p>
                                            <p class="text-muted"><label>
                                                    <input type="date" name="educationInfo[${edu_index}].startDate"
                                                           value="${edu.startDate}"
                                                           placeholder="<@spring.message'edit.resume.start.date'/>">
                                                </label> - <label>
                                                    <input type="date"
                                                           name="educationInfo[${edu_index}].endDate"
                                                           value="${edu.endDate}"
                                                           placeholder="<@spring.message'edit.resume.end.date'/>">
                                                </label>
                                        </div>
                                    </#list>
                                    <#assign eduLastIndex = resume.educationInfo?size-1>
                                </div>
                                <div id="education">
                                    <div id="educationInfo" class="d-flex justify-content-center my-4">
                                        <input id="add-education-btn" class="btn btn-secondary my-3" type="button" value="<@spring.message'edit.resume.add.info.for.education'/>">
                                    </div>
                                </div>

                                <hr class="my-4">
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-secondary btn-md"><@spring.message'edit.resume.submit'/></button>
                                </div>
                            </div>
                        </form>
                        </#if>
                    </main>
                </div>
            </div>
        </div>
    </div>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        var workExpLastIndex = ${workExpLastIndex};
        var eduLastIndex = ${eduLastIndex};
    </script>
    <script src="/js/editResume.js"></script>

    <link rel="stylesheet" href="/resumeInfo.css">
</@main.layout>
