<#import "../layout.ftlh" as main>
<@main.layout>
<div class="container my-5 mx-auto" style="max-width: 5000px; background: white; border-radius: 15px">
    <div class="row g-2 my-3 py-3">
        <div class="col-md-3">
            <div class="card border-0">
                <#if user.avatar??>
                    <img src="api/users/image/${user.avatar}" class="avatar-img" alt="User image" style="border-radius: 30%; width: 300px;
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
            </div>
        </div>

        <div class="col-md-6">
            <div class="card border-0 ps-3">
                <h5 class="card-title">Данные: ${user.name} ${user.surname}</h5>
                <p class="card-text">Адрес электронной почты: ${user.email}</p>
                <p class="card-text">Возраст: ${user.age}</p>

                <div class="d-grid gap-2">
                    <a href="/${user.accType}/edit" class="btn btn-secondary btn-sm mb-2" style="width: 150px;">Редактировать профиль</a>
                    <a href="/${user.accType}/changePassword" class="btn btn-secondary btn-sm mb-2" style="width: 150px;">Забыли пароль?</a>
                    <a href="/${user.accType}/uploadImage" class="btn btn-secondary btn-sm" style="width: 150px;">Изменить аватарку профиля</a>
                </div>
            </div>
        </div>

        <#if user.accType == 'employer'>
            <div class="col-md">
                <div class="card border-0 align-items-center text-align-center">
                    <a href="/vacancies/create" class="btn btn-secondary res-btn text-decoration-none">+ Создать вакансию</a>
                </div>
            </div>

            <div class="col-md">
                <div class="card border-0 align-items-center">
                    <a href="#" class="btn btn-secondary res-btn text-decoration-none text-center">Отклики</a>
                </div>
            </div>
        </#if>


        <#if user.accType == 'employee'>
            <div class="col-md">
                <div class="card border-0 align-items-center text-align-center">
                    <a href="/resume/create" class="btn btn-secondary res-btn text-decoration-none">+ Создать резюме</a>
                </div>
            </div>
        </#if>


        <#if user.accType=='employer'>
            <#if vacancies?has_content>
                <h6 class="mt-5">Созданные вакансии:</h6>
                <div class="row row-cols-1 row-cols-md-3 g-4">
                    <#list vacancies as vacancy>
                        <div class="col mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title"><a href="/vacancies/info/${vacancy.id}"
                                                              class="text-decoration-none text-dark">${vacancy.name}</a>
                                    </h5>
                                    <p class="card-text">Дата обнволения: ${vacancy.getUpdateTime().getDayOfMonth()}
                                        -${vacancy.updateTime.getMonth()} (${vacancy.getUpdateTime().getYear()})</p>
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between gx-2 my-2">
                                <button type="button" class="btn btn-secondary col-6"
                                        onclick="window.location.href='/vacancies/update?id=${vacancy.id}'">Обновить
                                </button>
                                <button type="button" class="btn btn-secondary col-6"
                                        onclick="window.location.href='/vacancies/update?id=${vacancy.id}'">Редактировать
                                </button>

                            </div>
                        </div>
                    </#list>
                </div>
            </#if>
        <#else>

        </#if> <#if resumes?has_content>
            <h6 class="mt-5">Резюме:</h6>
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <#list resumes as resume>
                    <div class="col mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title"><a href="/resume/${resume.id}"
                                                          class="text-decoration-none text-dark">${resume.name}</a></h5>
                                <hr>
                                <p class="card-text">Дата обновления: ${resume.updateDate.getDayOfMonth()}
                                    -${resume.updateDate.getMonth()} (${resume.updateDate.getYear()})</p>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between gx-2 my-2">
                            <button type="button" class="btn btn-secondary col-6"
                                    onclick="window.location.href='/resume/update?id=${resume.id}'">Обновить
                            </button>
                            <button type="button" class="btn btn-secondary col-6"
                                    onclick="window.location.href='/resume/update?id=${resume.id}'">Редактировать
                            </button>

                        </div>
                    </div>

                </#list>
            </div>
        </#if>
        </@main.layout>
