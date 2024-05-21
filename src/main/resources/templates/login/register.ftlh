<#import "../layout.ftlh" as register>
<@register.layout ; spring>
    <main>
        <#if _csrf??>
        <form action="/register" method="post">
            <fieldset>
                <p><@spring.message'register.registration'/></p>
                <div class="form-element">
                    <div class="row gx-5 gy-3">
                        <div class="col-12">
                            <input name="name" type="text" class="form-control mb-3" placeholder="<@spring.message'register.name'/>"
                                   aria-label="Имя">
                        </div>
                        <div class="col-12">
                            <input name="surname" type="text" class="form-control mb-3" placeholder="<@spring.message'register.surname'/>"
                                   aria-label="Фамилия">
                        </div>
                        <div class="col-12">
                            <input name="email" type="email" class="form-control mb-3" placeholder="<@spring.message'register.email'/>"
                                   aria-label="Электронная почта">
                        </div>
                        <div class="col-12">
                            <input name="password" type="password" class="form-control mb-3" placeholder="<@spring.message'register.password'/>"
                                   aria-label="Пароль">
                        </div>
                        <div class="col-12">
                            <input name="age" type="number" class="form-control mb-3" placeholder="<@spring.message'register.age'/>"
                                   aria-label="Возраст">
                        </div>
                        <div class="col-12">
                            <input name="phoneNumber" type="tel" class="form-control mb-3" placeholder="<@spring.message'register.phone.number'/>"
                                   aria-label="Номер телефона">
                        </div>
                        <div class="col-12">
                            <label for="account-type" class="form-label"><@spring.message'register.account.type'/></label>
                            <select name="accType" id="account-type" class="form-select mb-3" required>
                                <option value="1"><@spring.message'register.employer'/></option>
                                <option value="2"><@spring.message'register.employee'/></option>
                            </select>
                        </div>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        <div class="col-12">
                            <button class="btn btn-primary" type="submit"><@spring.message'register.submit'/></button>
                        </div>
                        <div class="col-12">
                            <a class="btn btn-link text-decoration-none text-dark" onclick="window.location.href='/login'">
                               <@spring.message'register.have.account'/>
                            </a>
                        </div>
                    </div>
                </div>
            </fieldset>
        </form>
        </#if>
    </main>
</@register.layout>
