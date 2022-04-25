const urlUsers = 'http://localhost:8080/api/users';

async function getAllUsers() {
    let usersAll = document.querySelector('#bodyAllUsers');
    await fetch(urlUsers)
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


getAllUsers();

