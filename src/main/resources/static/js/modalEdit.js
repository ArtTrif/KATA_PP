// fetch('https://jsonplaceholder.typicode.com/posts', {
//     method: "POST",
//     body: JSON.stringify({name: 'Alex'}),
//     headers: {
//         'Content-type': 'application/json'
//     }
// })
//     .then(response => response.json())
//     .then(json => console.log(json));
const urlEdit = 'http://localhost:8080/api/users';
const modalUsersAll = document.querySelector('.modalBlock');
const modalEd = document.querySelector('.modalEd');
fetch(urlUsers)
    .then(response => response.json())
    .then(res => {
        for (let jsonElement in res) {
            console.log(res[jsonElement]);
            // modalEd.id=`exampleModal${res[jsonElement].id}`;
            let nameAutority = "";
            for (let authority of res[jsonElement].authorities) {
                nameAutority += authority.nameRole.replace("ROLE_", "") + " ";

                const div = document.createElement('div');
                div.classList.add('el');
                modalUsersAll.append(div);
                // div.innerHTML = `${jsonElement}`;
                div.innerHTML = `
            <div className="modal fade" id="exampleModal${res[jsonElement].id}" tabIndex="-1"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel">Edit user</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <form className="form_class mx-auto text-center fw-bold" style="width: 320px;" method="POST"
                                  action="@{/admin/${res[jsonElement].id}">
            
                                <label className="name_field" htmlFor="id">id</label>
                                <input className="input_field form-control" name="id" type="text" readOnly="readonly"
                                       value="${res[jsonElement].id}" id="id"/>
                                <br>
                                <label className="name_field" htmlFor="firstName">First name</label>
                                <input className="input_field form-control" name="firstName" th:accept-charset="UTF-8"
                                       type="text"
                                       value="${res[jsonElement].firstName}" id="firstName"/>
                                <br>
                                <label className="name_field" htmlFor="lastName">Last name</label>
                                <input className="input_field form-control" name="lastName" type="text"
                                       value="${res[jsonElement].lastName}" id="lastName"/>
                                <br>
                                <label className="name_field" htmlFor="age">Age</label>
                                <input className="input_field form-control" name="age" type="number"
                                       value="${res[jsonElement].age}"
                                       id="age"/>
                                <br>
                                <label className="name_field" htmlFor="email">Email</label>
                                <input className="input_field form-control" name="email" type="email"
                                       value="${res[jsonElement].email}"
                                       id="email"/>
                                <br>
                                <label className="name_field" htmlFor="password">Password</label>
                                <input className="input_field form-control" name="password" type="password"
                                       value="${res[jsonElement].password}" id="password"/>
                                <br>
                                <label className="name_field" htmlFor="role"> Role </label>
                                <select className="form-select" multiple name="rolesEdit">
                                    <option selected="${res[jsonElement].authorities} == 'ROLE_USER'">${nameAutority}</option>

                                </select>

                                <br>
            
            
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-secondary"
                                            data-bs-dismiss="modal">Close
                                    </button>
                                    <button type="submit" className="btn btn-primary">Edit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>           
            `;
            }

        }
    });



