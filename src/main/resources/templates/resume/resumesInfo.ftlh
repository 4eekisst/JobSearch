<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container py-5">
        <div class="row">

            <div class="col-md-3">
                <div class="card" style="max-width: 450px">
                        <div class="card-body border-bottom-2">
                            <h6>${user.name} ${user.surname}</h6>
                            <p class="text-muted"><strong><@spring.message'resumes.info.age'/></strong> ${user.age}</p>
                            <p class="text-muted"><strong><@spring.message'resumes.info.phone'/></strong> ${user.phoneNumber}</p>
                        </div>
                </div>
            </div>

            <div class="col-md-8">
                <div class="card border-0 shadow-lg">
                    <div class="card-body">
                        <h3 class="card-title mb-4">${resume.name}</h3>
                        <p class="card-text text-muted"><strong><@spring.message'resumes.info.category'/></strong> ${resume.category}</p>
                        <p class="card-text text-muted"><strong><@spring.message'resumes.info.salary'/></strong> ${resume.salary}</p>
                        <p class="card-text text-muted"><strong><@spring.message'resumes.info.email'/></strong> ${resume.userEmail}</p>
                        <p class="card-text text-muted"><strong><@spring.message'resumes.info.active'/></strong> ${resume.isActive ? string('Да', 'Нет')}</p>

                        <hr class="my-4">

                        <h5 class="card-subtitle mb-3 text-muted"><@spring.message'resumes.info.work.exp'/></h5>
                        <div class="work-experience">
                            <#list works as work>
                                <div class="mb-3">
                                    <h6>${work.position} - ${work.companyName}</h6>
                                    <p>${work.responsibilities}</p>
                                    <p class="text-muted"><@spring.message'resumes.info.worked'/>${work.years}<@spring.message'resumes.info.year'/></p>
                                </div>
                            </#list>
                        </div>

                        <hr class="my-4">

                        <h5 class="card-subtitle mb-3 text-muted"><@spring.message'resumes.info.education'/></h5>
                        <div class="education">
                            <#list educations as edu>
                                <div class="mb-3">
                                    <h6>${edu.degree}, ${edu.institution}</h6>
                                    <p>${edu.program}</p>
                                    <p class="text-muted">${edu.startDate} - ${edu.endDate}</p>
                                </div>
                            </#list>
                        </div>

                        <hr class="my-4">

                        <h5 class="card-subtitle mb-3 text-muted"><@spring.message'resumes.info.contact'/></h5>
                        <div class="work-experience">
                            <#list contacts as contact>
                                <div class="mb-3">
                                    <h6>${contact}</h6>
                                </div>
                            </#list>
                        </div>

                        <hr class="my-4">
                        <#if resume.updateDate.getYear() == 2024>
                            <p class="card-text mt-4 text-muted text-end">
                                <small><@spring.message'resumes.info.last.update'/>${resume.updateDate.getDayOfMonth()} - ${resume.updateDate.getMonth().name()}</small>
                            </p>
                        <#else>
                            <p class="card-text mt-4 text-muted text-end">
                                <small><@spring.message'resumes.info.last.update'/>${resume.updateDate.getDayOfMonth()} - ${resume.updateDate.getMonth().name()} (${resume.updateDate.getYear()})</small>
                            </p>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
