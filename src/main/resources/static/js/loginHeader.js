let loginHeader = document.querySelector('#loginHeader');
const urlUserAuth = 'http://localhost:8080/api/user';

function getUserAuth() {
    fetch(urlUserAuth)
        .then(response => response.json())
        .then(userAuth => {

            let userAuthAutority = "";
            for (let userAuthAuthorities of userAuth.authorities) {
                userAuthAutority += userAuthAuthorities.nameRole.replace("ROLE_", "") + " ";
            }
            loginHeader.innerHTML += `
                <strong><span>${userAuth.email}</span></strong> with roles: <span>${userAuthAutority}</span>
            `;
        });
}

getUserAuth();