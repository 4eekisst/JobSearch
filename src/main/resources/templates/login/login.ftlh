<#import "../layout.ftlh" as login>
<@login.layout ; spring>
    <main>
        <#if _csrf??>
        <form action="/login" method="post">
            <fieldset>
                <p><@spring.message'login.authorization'/></p>
                <div class="form-element mb-5">
                    <div class="row gx-5 gy-3">
                        <div class="col-12">
                            <input name="username" type="email" class="form-control mb-3" placeholder="<@spring.message'login.email'/>"
                                   aria-label="Электронная почта">
                        </div>
                        <div class="col-12">
                            <input name="password" type="password" class="form-control mb-3" placeholder="<@spring.message'login.password'/>"
                                   aria-label="Пароль">
                        </div>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        <div class="col-12">
                            <button class="btn btn-primary" type="submit"><@spring.message'login.submit'/></button>
                        </div>
                        <div class="col-12">
                            <a class="btn btn-link text-decoration-none text-dark" onclick="window.location.href='/register'">
                               <@spring.message'login.dont.have.an.account'/>
                            </a>

                            <a class="btn btn-link text-decoration-none text-dark" onclick="window.location.href='/forgot_password'">
                               <@spring.message'login.forgot.password'/>
                            </a>
                        </div>

                    </div>
                </div>
            </fieldset>
        </form>
        </#if>
    </main>
</@login.layout>
