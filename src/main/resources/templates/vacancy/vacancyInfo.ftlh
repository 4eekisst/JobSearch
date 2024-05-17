<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="col-md-3">
        <div class="card" style="max-width: 450px">
            <#list employers as employer>
                <div class="card-body border-bottom-2">
                    <h6>Работодатель: ${vacancy.authorEmail}</h6>
                    <a href="/vacancies/info/${employer.id}" class="text-decoration-none text-dark">${employer.name}</a>
                    <p class="card-text">${employer.salary}₽</p>
                </div>
            </#list>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col-md-9">
            <div class="card my-2" style="max-width: 900px">
                <div class="card-body">
                    <h3 class="mt-2 mb-5"><a href="#" class="text-decoration-none rounded-2 px-3 py-1">${vacancy.name}</a></h3>
                    <h5 class="card-title">${vacancy.description}</h5>
                    <p class="card-text">${vacancy.salary}</p>
                    <p class="text-end">${vacancy.updateTime.getDayOfMonth()} - ${vacancy.updateTime.getMonth()}</p>
                    <select id="resumesDropdown" class="form-select">
                        <option selected disabled>Выберите резюме</option>
                    </select>
                    <input type="hidden" id="vacancyId" value="${vacancy.id}">
                    <button id="applyButton" class="btn btn-primary mt-3">Отправить отклик</button>
            </div>
        </div>
    </div>

    <script>
        fetch('/vacancies/currentUser')
            .then(response => response.json())
            .then(data => {
                const dropdown = document.getElementById('resumesDropdown');
                data.forEach(resume => {
                    const option = document.createElement('option');
                    option.text = resume.name;
                    option.value = resume.id;
                    dropdown.add(option);
                });
            })
            .catch(error => console.error('Ошибка получения резюме:', error));

        document.getElementById('applyButton').addEventListener('click', function() {
            const selectedResumeId = document.getElementById('resumesDropdown').value;
            const selectedVacancyId = document.getElementById('vacancyId').value;
            fetch('/vacancies/apply?vacancyId=' + selectedVacancyId + '&resumeId=' + selectedResumeId, {
                method: 'POST'
            })
                .then(response => {
                    if (response.ok) {
                        alert('Отклик успешно отправлен на вакансию.');
                    } else {
                        alert('Ошибка при отправке отклика на вакансию.');
                    }
                })
                .catch(error => console.error('Ошибка при отправке отклика:', error));
        });

    </script>

</@main.layout>
