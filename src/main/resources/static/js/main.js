/*import loginHeader from './loginHeader.js';
import usersAll from './usersAll';
import user from './user';
import newUser from './newUser';*/
/*const urlUsers = 'http://localhost:8080/api/users';
const userFetchService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    createUser: async (user) => await fetch(urlUsers, {
        method: 'POST',
        headers: userFetchService.head,
        body: JSON.stringify(user)
    })
}*/

window.addEventListener('DOMContentLoaded', () => {
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
                for (let jsonElement in res) {
                    console.log(res[jsonElement]);
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
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${res[jsonElement].id}">
                                    Edit
                                </button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModalDel${res[jsonElement].id}">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    `;
                }
            });
    }

    getAllUsers(urlUsers, usersAll);
    ///////////////////////////////////////////////////////////////////////////////////
    //все пользователи вариант 2
    /*let users;

    async function getAllUsers(urlUsers, usersAll) {
        const response = await fetch(urlUsers);
        users = await response.json()


        usersAll.innerHTML = ''

        for (const user of users) {
            let nameAutority = "";
            for (let authority of user.authorities) {
                nameAutority += authority.nameRole.replace("ROLE_", "") + " ";

            }

            const trElement = document.createElement("tr")
            trElement.id = user.id
            trElement.appendChild(createTd(user.id));
            trElement.appendChild(createTd(user.firstName));
            trElement.appendChild(createTd(user.lastName));
            trElement.appendChild(createTd(user.age));
            trElement.appendChild(createTd(user.email));

            trElement.appendChild(roleTd(nameAutority));
            const editTd = document.createElement("td");
            editTd.innerHTML = `
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${user.id}">
                    Edit
                </button>
            `;
            trElement.appendChild(editTd);

            const deleteTd = document.createElement("td");
            deleteTd.innerHTML = `
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModalDel${user.id}">
                    Delete
                </button>
            `;
            trElement.appendChild(deleteTd);
            usersAll.appendChild(trElement)
        }
    }

    function createTd(user) {
        const tdElement = document.createElement("td");
        tdElement.textContent = user;
        return tdElement;
    }

    function roleTd(nameAutority) {
        const tdRole = document.createElement("td");
        tdRole.innerHTML = nameAutority;
        return tdRole;
    }

    getAllUsers(urlUsers, usersAll);*/
    /////////////////////////////////////////////////////////////////////////////////
    //// все пользователи вариант 65


     /*let users = [];
     const showAllUsers = (users) => {
         let output = ''
         users.forEach(user => {
             //output - элемент вывода
             let nameAutority = "";
             for (let authority of user.authorities) {
                 nameAutority += authority.nameRole.replace("ROLE_", "") + " ";
             }
             output += `
             <tr id=${'tr' + user.id}>
                 <td>${user.id}</td>
                 <td>${user.firstName}</td>
                 <td>${user.lastName}</td>
                 <td>${user.age}</td>
                 <td>${user.email}</td>
                 <td>
                     <span>${user.authorities.nameRole}</span>
                 </td>
                 <td class="text-white">
                 <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${user.id}">
                     Edit
                 </button></td>
                 <td class="text-white">
                 <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModalDel${user.id}">
                     Delete
                 </button></td>
             </tr>
        `;
         });

         bodyAllUsers.innerHTML = output;
     }
         fetch(urlUsers)
             .then(res => res.json())
             // получаем ответ
             .then(data => {
                 showAllUsers(data);
                 users.push(...data);
             })
             .catch(error => console.log(error))*/
///////////////////////////////////////////////////////////////////////////////////
//создание юзера
    let formCreate = document.querySelector('#formCreate');

    function createUser() {
        formCreate.addEventListener('submit', async (e) => {
            e.preventDefault()


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
                usersAll.innerHTML="";
                getAllUsers(urlUsers, usersAll);
                document.querySelector('#home').classList.add('show', 'active');
                document.querySelector('#profile').classList.remove('show', 'active');
                document.querySelector('#home-tab').classList.add('active');
                document.querySelector('#profile-tab').classList.remove('active');


            })



        });
    };
    createUser();




////////////////////////////////////////////////////////////////////////////////////////////
//создание юзера вариант 2
    /*    let formCreate = document.querySelector('#formCreate');
        async function createUser() {
            formCreate.addEventListener('submit', async (e) => {


                let form = document.getElementById('formCreate')
                let formData = new FormData(form)
                let values = Object.fromEntries(formData.entries())

                let option = form.querySelectorAll('option')
                let roleArr = []

                for (let i = 0; i < option.length; i++) {
                    if (option[i].selected === true) {
                        let eachRole = {}
                        eachRole.id = option[i].value
                        roleArr.push(eachRole)
                    }
                }
                values.roles = roleArr;

                await fetch(urlUsers, {
                    method: 'POST',
                    headers: {
                        // 'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        // 'Referer': null
                    },
                    body: JSON.stringify(values)
                }).then(form.reset())
                getAllUsers(urlUsers, usersAll);
                document.getElementById('nav-users-table-tab').click();
            });
        }

        createUser();
    });*/
///////////////////////////////////////////////////////////////////////////////////
//создание юзера jquery
    /*$("#formCreate").find('#addNewUserBtn').on('click', async (event) => {

        let formCreate = $('#formCreate');

        let firstName = formCreate.find('#firstNameCreate').val().trim();
        let lastName = formCreate.find('#lastNameCreate').val().trim();
        let age = formCreate.find('#ageCreate').val().trim();

        let email = formCreate.find('#emailCreate').val().trim();
        let password = formCreate.find('#passwordCreate').val().trim();
        let authorities = formCreate.find('#roleCreate').val();
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
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Referer': null
                    },
                    body: JSON.stringify(newUser)
                }).then(r => {
                    event.target.reset();
                    getAllUsers(urlUsers, usersAll);
                })
    })
 });*/
/////////////////////////////////////////////////////////////////////////////////////////////
//версия 4
//добавляем пользователя
/*    const addUser = document.querySelector('#formCreate')
    addUser.addEventListener('submit', (e) => {
        e.preventDefault()
        let formCreate = $('#formCreate')
        let firstName = formCreate.find('#firstNameCreate').val().trim();
        let lastName = formCreate.find('#lastNameCreate').val().trim();
        let age = formCreate.find('#ageCreate').val().trim();
        let email = formCreate.find('#emailCreate').val().trim();
        let password = formCreate.find('#passwordCreate').val().trim();
        // let role = formCreate.find('#roleCreate').val();
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
        let newUserData = {
            firstName: firstName,
            lastName: lastName,
            age: age,
            email: email,
            password: password,
            roles: roles()
        }

        fetch(urlUsers, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newUserData)
        })
            // .then(res => res.json())
            .then(user => {
                $('#nav-home-tab').addClass('active');
                $('#nav-home').addClass('active');
                $('#nav-profile-tab').removeClass('active');
                $('#nav-profile').removeClass('active');
                users.push(user)

                showAllUsers(users)
            })
    });
});*/


});