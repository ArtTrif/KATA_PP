const urlUser = 'http://localhost:8080/api/user';
let userAuth = document.querySelector('#userAuth');

function getUser() {
    fetch(urlUser)
        .then(response => response.json())
        .then(userView => {
            let userViewAutority = "";
            console.log(userView);
            for (let userAuthorities of userView.authorities) {
                userViewAutority += userAuthorities.nameRole.replace("ROLE_", "") + " ";
                console.log(typeof userViewAutority)
            }
            userAuth.innerHTML += `
                <tr>
                    <td>${userView.id}</td>
                    <td>${userView.firstName}</td>
                    <td>${userView.lastName}</td>
                    <td>${userView.age}</td>
                    <td>${userView.email}</td>
                    <td><span>${userViewAutority}</span></td>
                </tr>
            `;
        })

}

getUser();