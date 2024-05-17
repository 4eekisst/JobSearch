<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <#if _csrf??>
                <form id="avatar-form" action="<#if accType = 'employee'>/employee/uploadImage<#else>/employer/uploadImage</#if>" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="avatar-input" class="form-label">Выберите изображение</label>
                        <input name="file" class="form-control" type="file" id="avatar-input">
                    </div>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                    <button type="submit" class="btn btn-primary">Изменить аватар</button>
                </form>
                </#if>
                <div class="mt-3">
                    <p class="text-muted">Обратите внимание, что желательно использовать свою фотографию в качестве аватарки.</p>
                </div>
            </div>
        </div>
    </div>
</@main.layout>
