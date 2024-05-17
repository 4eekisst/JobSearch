<#import "../layout.ftlh" as login>
<@login.layout>
    <main>
        <#if _csrf??>
        <form action="/login" method="post">
            <fieldset>
                <p>Авторизация</p>
                <div class="form-element mb-5">
                    <div class="row gx-5 gy-3">
                        <div class="col-12">
                            <input name="username" type="email" class="form-control mb-3" placeholder="Ваш адрес электронной почты"
                                   aria-label="Электронная почта">
                        </div>
                        <div class="col-12">
                            <input name="password" type="password" class="form-control mb-3" placeholder="Пароль"
                                   aria-label="Пароль">
                        </div>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">Войти!</button>
                        </div>
                        <div class="col-12">
                            <a class="btn btn-link text-decoration-none text-dark" onclick="window.location.href='/register'">
                                Нет аккаунта?
                            </a>

                            <a class="btn btn-link text-decoration-none text-dark" onclick="window.location.href='/forgot_password'">
                                Забыли пароль?
                            </a>
                        </div>

                    </div>
                </div>
            </fieldset>
        </form>
        </#if>
    </main>
</@login.layout>
