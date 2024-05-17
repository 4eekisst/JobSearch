<#import "../layout.ftlh" as register>
<@register.layout>
    <main>
        <#if _csrf??>
        <#if accType == 'employee'>
        <form action="/employee/edit" method="post" enctype="multipart/form-data">
            <#else>
            <form action="/employer/edit" method="post" enctype="multipart/form-data">
                </#if>
                <fieldset>
                    <div class="form-element">
                        <div class="row gx-5 gy-3">
                            <div class="col-12">
                                <input name="name" type="text" class="form-control" placeholder="Имя"
                                       aria-label="Имя">
                            </div>
                            <div class="col-12">
                                <input name="surname" type="text" class="form-control" placeholder="Фамилия"
                                       aria-label="Фамилия">
                            </div>
                            <div class="col-12">
                                <input name="age" type="number" class="form-control" placeholder="Возраст"
                                       aria-label="Возраст">
                            </div>
                            <div class="col-12">
                                <input name="phoneNumber" type="tel" class="form-control" placeholder="Номер телефона"
                                       aria-label="Номер телефона">
                            </div>

                        </div>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        <button class="btn btn-primary mt-5" style="width: 200px" type="submit">Редактировать</button>
                    </div>
                </fieldset>
            </form>
        </form>
        </#if>
    </main>
</@register.layout>