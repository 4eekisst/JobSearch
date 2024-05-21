<#import "../layout.ftlh" as register>
<@register.layout ; spring>
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
                                <input name="name" type="text" class="form-control" placeholder="<@spring.message'edit,user.name'/>"
                                       aria-label="Имя">
                            </div>
                            <div class="col-12">
                                <input name="surname" type="text" class="form-control" placeholder="<@spring.message'edit.user,surname'/>"
                                       aria-label="Фамилия">
                            </div>
                            <div class="col-12">
                                <input name="age" type="number" class="form-control" placeholder="<@spring.message'edit.user.age'/>"
                                       aria-label="Возраст">
                            </div>
                            <div class="col-12">
                                <input name="phoneNumber" type="tel" class="form-control" placeholder="<@spring.message'edit.user.phone.number'/>"
                                       aria-label="Номер телефона">
                            </div>

                        </div>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        <button class="btn btn-primary mt-5" style="width: 200px" type="submit"><@spring.message'edit.user.confirm'/></button>
                    </div>
                </fieldset>
            </form>
        </form>
        </#if>
    </main>
</@register.layout>