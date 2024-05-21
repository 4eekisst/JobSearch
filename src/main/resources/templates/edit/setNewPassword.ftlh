<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container my-5">
        <div class="password-field">
            <h1><@spring.message'change.password'/></h1>
            <main>
                <#if _csrf??>
                    <#if accType == 'employee'>
                        <form id="password-form" action="/employee/changePassword" method="post">
                    <#else>
                            <form id="password-form" action="/employer/changePassword" method="post">
                        </#if>
                        <div class="mb-3">
                            <input type="password" class="form-control" id="current-password"
                                   placeholder="<@spring.message'change.password.current.password'/>"
                                   name="oldPassword" required>
                        </div>
                        <div class="mb-3">
                            <input type="email" class="form-control" id="current-email"
                                   placeholder="<@spring.message'change.password.current.email'/>" name="email"
                                   required>
                        </div>
                        <div class="mb-3">
                            <input type="password" class="form-control" id="newPassword" placeholder="<@spring.message'change.password.new.password'/>"
                                   name="newPassword" required>
                        </div>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                        <button type="submit" class="btn btn-primary"><@spring.message'change.password.submit'/></button>
                        <p class="error-message mt-3" id="error-message"></p>
                        </form>
                    </form>
                </#if>
            </main>
        </div>
    </div>
</@main.layout>
