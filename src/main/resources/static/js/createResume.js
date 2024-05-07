document.addEventListener('DOMContentLoaded', function () {
    const workExpContainer = document.getElementById('workExp');
    const educationContainer = document.getElementById('education');
    const contactsContainer = document.getElementById('addContacts');

    let workExpCounter = 0;
    let educationCounter = 0;
    let contactsCounter = 0;

    function createCard(container, counter, addBtn) {
        const cardBody = document.createElement('div');
        cardBody.className = 'card-body';

        let closeButton = document.createElement('button');
        closeButton.className = 'btn btn-danger';
        closeButton.classList.add('card-close');
        closeButton.textContent = 'удалить';
        closeButton.addEventListener('click', () => {
            cardBody.remove();
            addBtn.disabled = false;
        });

        if (container === workExpContainer) {
            const workPositionInput = createInputField('text', `workExpInfo[${counter}].position`,'Должность');
            cardBody.appendChild(workPositionInput);

            const companyNameInput = createInputField('text', `workExpInfo[${counter}].companyName`,'Название компании');
            cardBody.appendChild(companyNameInput);

            const responsibilitiesTextarea = createTextarea(`workExpInfo[${counter}].responsibilities`,'Обязанности');
            cardBody.appendChild(responsibilitiesTextarea);

            const yearsOfWorkInput = createInputField('number', `workExpInfo[${counter}].years`,'Трудовой стаж в годах');
            cardBody.appendChild(yearsOfWorkInput);

            workExpCounter++;
        } else if (container === educationContainer) {
            const degreeInput = createInputField('text', `educationInfo[${counter}].degree`,'Высшее образование - ср.');
            cardBody.appendChild(degreeInput);

            const institutionInput = createInputField('text', `educationInfo[${counter}].institution`,'Учебное заведение');
            cardBody.appendChild(institutionInput);

            const programTextarea = createTextarea(`educationInfo[${counter}].program`,'Информация об образовании');
            cardBody.appendChild(programTextarea);

            const startDateInput = createInputField('date', `educationInfo[${counter}].startDate`,'Дата начала');
            cardBody.appendChild(startDateInput);

            const endDateInput = createInputField('date', `educationInfo[${counter}].endDate`,'Дата окончания');
            cardBody.appendChild(endDateInput);

            educationCounter++;
        } else if (container === contactsContainer) {
            const type = createInputField('text', `contacts[${counter}].type`,'Ссылка на соц.сети');
            cardBody.appendChild(type);

            const value = createInputField('text', `contacts[${counter}].infoValue`,'Ссылка на соц.сети');
            cardBody.appendChild(value);
            contactsCounter++;
        }

        cardBody.appendChild(closeButton);

        container.appendChild(cardBody);
        addBtn.disabled = true;
    }

    function createInputField(type, name, label) {
        const container = document.createElement('div');
        container.className = 'mb-3';

        const labelElement = document.createElement('label');
        labelElement.textContent = label;
        labelElement.className = 'form-label';
        container.appendChild(labelElement);

        const inputElement = document.createElement('input');
        inputElement.type = type;
        inputElement.name = name;
        inputElement.className = 'form-control';
        container.appendChild(inputElement);

        return container;
    }

    function createTextarea(name,label) {
        const container = document.createElement('div');
        container.className = 'mb-3';

        const labelElement = document.createElement('label');
        labelElement.textContent = label;
        labelElement.className = 'form-label';
        container.appendChild(labelElement);

        const textareaElement = document.createElement('textarea');
        textareaElement.className = 'form-control';
        textareaElement.name = name;
        container.appendChild(textareaElement);

        return container;
    }

    const addWorkExpBtn = document.getElementById('add-work-btn');
    addWorkExpBtn.addEventListener('click', function () {
        createCard(workExpContainer, workExpCounter, addWorkExpBtn);
    });

    const addEducationBtn = document.getElementById('add-education-btn');
    addEducationBtn.addEventListener('click', function () {
        createCard(educationContainer, educationCounter, addEducationBtn);
    });

    const addContactsBtn = document.getElementById('add-contacts-btn');
    addContactsBtn.addEventListener('click', function () {
        createCard(contactsContainer, contactsCounter, addContactsBtn);
    });

});
