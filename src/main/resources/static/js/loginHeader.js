let loginHeader = document.querySelector('#loginHeader');
const urlUserAuth = 'http://localhost:8080/api/user';
async function getUserAuth(){
    let user = await fetch(urlUserAuth);
    let userAuth = await user.json();
    let userAuthAutority = "";
    for (let userAuthAuthorities of userAuth.authorities) {
        userAuthAutority += userAuthAuthorities.nameRole.replace("ROLE_", "") + " ";
    }
    loginHeader.innerHTML += `
        <strong><span>${userAuth.email}</span></strong> with roles: <span>${userAuthAutority}</span>
    `;
}
getUserAuth();