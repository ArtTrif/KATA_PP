'use strict';
const newUserUrl = 'http://localhost:8080/api/users';
let formCreate = document.querySelector('#formCreate');
let roleList = [
    {id: 1, role: "ROLE_USER"},
    {id: 2, role: "ROLE_ADMIN"}
]

formCreate.addEventListener('submit', (e) => {
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

    async function createUser(newUser) {
        await fetch(newUserUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newUser)
        }).then(res => {
            if (res.ok) {
                return res.json()
                getAllUsers();
            } else {
                alert('Что то пошло не так');
            }

        })

    }

    createUser(newUser);


});

