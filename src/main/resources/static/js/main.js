const urlUsers = 'http://localhost:8080/api/users';
let usersAll = document.querySelector('#bodyAllUsers');
let roleList = [
    {id: 1, nameRole: "ROLE_USER"},
    {id: 2, nameRole: "ROLE_ADMIN"}
]

//получение всех юзеров
function getAllUsers(urlUsers, usersAll) {
    fetch(urlUsers)
        .then(response => response.json())
        .then(res => {
            usersAll.innerHTML = "";
            for (let jsonElement in res) {
                let nameAutority = "";
                for (let authority of res[jsonElement].authorities) {
                    nameAutority += authority.nameRole.replace("ROLE_", "") + " ";
                }

                usersAll.innerHTML += `
                        <tr>
                            <td>${res[jsonElement].id}
                            </td>
                            <td>${res[jsonElement].firstName}
                            </td>
                            <td>${res[jsonElement].lastName}</td>
                            <td>${res[jsonElement].age}
                            </td>
                            <td>${res[jsonElement].email}
                            </td>
                            <td><span>${nameAutority}</span>
                            </td>                            
                            <td>
                            <button type="button" class="btn btn-primary btn-edit" onclick="editModalId(${res[jsonElement].id})" data-bs-toggle="modal" data-bs-target="#exampleModalEdit" value="${res[jsonElement].id}">
                                    Edit
                            </button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" onclick="delModalId(${res[jsonElement].id})" data-bs-toggle="modal" data-bs-target="#exampleModalDel">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    `;
            }
        });
}
function showUsers() {
    usersAll.innerHTML="";
    getAllUsers(urlUsers, usersAll);
}
showUsers();

///////////////////////////////////////////////////////////////////////////////////
//создание юзера
let formCreate = document.querySelector('#formCreate');

function createUser() {
    formCreate.addEventListener('submit', async (e) => {
        e.preventDefault();
        let firstName = formCreate.querySelector('#firstNameCreate').value;
        let lastName = formCreate.querySelector('#lastNameCreate').value;
        let age = formCreate.querySelector('#ageCreate').value;
        let email = formCreate.querySelector('#emailCreate').value;
        let password = formCreate.querySelector('#passwordCreate').value;
        let roles = () => {
            let arrayRoles = []
            let options = document.querySelector('#roleCreate').options
            for (let i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    arrayRoles.push(roleList[i])
                }
            }
            return arrayRoles;
        }
        let newUser = {
            firstName: firstName,
            lastName: lastName,
            age: age,
            email: email,
            password: password,
            authorities: roles()
        }
        fetch(urlUsers, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newUser)
        }).then(r => {
            e.target.reset();
            showUsers();
            document.querySelector('#home').classList.add('show', 'active');
            document.querySelector('#profile').classList.remove('show', 'active');
            document.querySelector('#home-tab').classList.add('active');
            document.querySelector('#profile-tab').classList.remove('active');
        });
    });
};
createUser();

// получение юзера по user.id через onclick на кнопке и заполнение формы редактирования
function editModalId(id) {
    let editForm = document.querySelector('#formEdit')
    const urlEdit = "http://localhost:8080/api/users/" + id;

    fetch(urlEdit)
        .then(response => response.json())
        .then(userEdit => {

            let inputEdit = editForm.querySelectorAll('.inputEdit');
            for (let inputEditElement of inputEdit) {

                switch (inputEditElement.name) {
                    case 'id':
                        inputEditElement.value = userEdit.id
                        break;
                    case 'firstName':
                        inputEditElement.value = userEdit.firstName
                        break;
                    case 'lastName':
                        inputEditElement.value = userEdit.lastName
                        break;
                    case 'age':
                        inputEditElement.value = userEdit.age
                        break;
                    case 'email':
                        inputEditElement.value = userEdit.email
                        break;
                    case 'password':
                        inputEditElement.value = userEdit.password
                        break;
                }

            }
        });
//отправка формы для изменения юзера
    editForm.addEventListener('submit', (event) => {
        event.preventDefault();
        let id = editForm.querySelector('#idEdit').value
        let firstName = editForm.querySelector('#firstNameEdit').value;
        let lastName = editForm.querySelector('#lastNameEdit').value;
        let age = editForm.querySelector('#ageEdit').value;
        let email = editForm.querySelector('#emailEdit').value;
        let password = editForm.querySelector('#passwordEdit').value;
        let roles = () => {
            let arrayRoles = []
            let options = document.querySelector('#rolesEdit').options
            for (let i = 0; i < options.length; i++) {
                if (options[i].selected) {
                    arrayRoles.push(roleList[i])
                }
            }
            return arrayRoles;
        }

        let editUser = {
            id: id,
            firstName: firstName,
            lastName: lastName,
            age: age,
            email: email,
            password: password,
            authorities: roles()
        }
        fetch(urlUsers, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(editUser)
        }).then(r => {
            console.log(editUser)
            event.target.reset();
            showUsers();
            $('#exampleModalEdit').modal('hide');
        });
    });
}



// удаление юзера


function delModalId(id) {
    let delForm = document.querySelector('#formDel');
    const urlDel = "http://localhost:8080/api/users/" + id;

    fetch(urlDel)
        .then(response => response.json())
        .then(userDel => {

            let inputDel = delForm.querySelectorAll('.inputDel');
            for (let inputDelElement of inputDel) {

                switch (inputDelElement.name) {
                    case 'id':
                        inputDelElement.value = userDel.id
                        break;
                    case 'firstName':
                        inputDelElement.value = userDel.firstName
                        break;
                    case 'lastName':
                        inputDelElement.value = userDel.lastName
                        break;
                    case 'age':
                        inputDelElement.value = userDel.age
                        break;
                    case 'email':
                        inputDelElement.value = userDel.email
                        break;
                    case 'password':
                        inputDelElement.value = userDel.password
                        break;
                }

            }
        });
//отправка формы для изменения юзера
    delForm.addEventListener('submit', (event) => {
        event.preventDefault();

        fetch(urlDel, {
            method: 'DELETE',
        }).then(r => {

            event.target.reset();
            showUsers();
            $('#exampleModalDel').modal('hide');
        });
    });
}