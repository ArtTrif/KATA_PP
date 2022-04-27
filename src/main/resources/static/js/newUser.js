/*
'use strict';
const newUserUrl = 'http://localhost:8080/api/users';
let formCreate = document.querySelector('#formCreate');
let roleList = [
    {id: 1, nameRole: "ROLE_USER"},
    {id: 2, nameRole: "ROLE_ADMIN"}
]
let newBtn = document.querySelector('#addNewUserBtn');
newBtn.addEventListener('click', async (e) => {
    /!*e.preventDefault()*!/

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



    await userFetchService.createUser(newUser);
    /!*setTimeout(refreshTable, 1000)*!/
    /!*fetch(newUserUrl)
        .then(response => response.json()).then((res) => {
            fillTable(res)
    })*!/
/!*    getAllUsers();*!/
    refreshTable();
    e.target.reset();

});

*/
