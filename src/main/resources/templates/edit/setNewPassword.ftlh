<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container my-5">
        <div class="password-field">
            <h1>Смена пароля учетной записи</h1>
            <main>
                <#if accType == 'employee'>
                <form id="password-form" action="/employee/changePassword" method="post">
                    <#else>
                    <form id="password-form" action="/employer/changePassword" method="post">
                        </#if>
                        <div class="mb-3">
                            <input type="password" class="form-control" id="current-password"
                                   placeholder="Текущий пароль"
                                   name="oldPassword" required>
                        </div>
                        <div class="mb-3">
                            <input type="email" class="form-control" id="current-email"
                                   placeholder="Текущий адрес электронной почты" name="email"
                                   required>
                        </div>
                        <div class="mb-3">
                            <input type="password" class="form-control" id="newPassword" placeholder="Новый пароль"
                                   name="newPassword" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Готово</button>
                        <p class="error-message mt-3" id="error-message"></p>
                    </form>
                </form>
            </main>
        </div>
    </div>
</@main.layout>
