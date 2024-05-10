<#import "../layout.ftlh" as register>
<@register.layout>
    <main>
        <#if _csrf??>
        <form action="/register" method="post">
            <fieldset>
                <p>Регистрация</p>
                <div class="form-element">
                    <div class="row gx-5 gy-3">
                        <div class="col-12">
                            <input name="name" type="text" class="form-control mb-3" placeholder="Имя"
                                   aria-label="Имя">
                        </div>
                        <div class="col-12">
                            <input name="surname" type="text" class="form-control mb-3" placeholder="Фамилия"
                                   aria-label="Фамилия">
                        </div>
                        <div class="col-12">
                            <input name="email" type="email" class="form-control mb-3" placeholder="Ваш адрес электронной почты"
                                   aria-label="Электронная почта">
                        </div>
                        <div class="col-12">
                            <input name="password" type="password" class="form-control mb-3" placeholder="Пароль"
                                   aria-label="Пароль">
                        </div>
                        <div class="col-12">
                            <input name="age" type="number" class="form-control mb-3" placeholder="Ваш возраст"
                                   aria-label="Возраст">
                        </div>
                        <div class="col-12">
                            <input name="phoneNumber" type="tel" class="form-control mb-3" placeholder="Ваш номер телефона"
                                   aria-label="Номер телефона">
                        </div>
                        <div class="col-12">
                            <label for="account-type" class="form-label">Тип аккаунта:</label>
                            <select name="accType" id="account-type" class="form-select mb-3" required>
                                <option value="1">Работодатель</option>
                                <option value="2">Соискатель</option>
                            </select>
                        </div>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">Зарегистрироваться</button>
                        </div>
                        <div class="col-12">
                            <a class="btn btn-link text-decoration-none text-dark" onclick="window.location.href='/login'">
                                Уже есть аккаунт?
                            </a>
                        </div>
                    </div>
                </div>
            </fieldset>
        </form>
        </#if>
    </main>
</@register.layout>
